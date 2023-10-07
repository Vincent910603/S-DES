// 通用置换函数
function permute(inputStr, table) {
  let outputStr = "";
  for (let bitPosition of table) {
    outputStr += inputStr[bitPosition - 1];
  }
  return outputStr;
}

// 循环左移函数
function leftShift(key, n) {
  const leftHalf = key.substring(0, 5);
  const rightHalf = key.substring(5);
  const shiftedLeft = leftHalf.substring(n) + leftHalf.substring(0, n);
  const shiftedRight = rightHalf.substring(n) + rightHalf.substring(0, n);
  return shiftedLeft + shiftedRight;
}

// 子密钥生成
function generateKey(k, P10, P8) {
  // 执行 P10 置换
  const p10Key = permute(k, P10);
  // 对结果进行左移操作和 P8 置换，得到 K1
  const k1 = permute(leftShift(p10Key, 1), P8);
  // 再次对上一步结果进行左移操作和 P8 置换，得到 K2
  const k2 = permute(leftShift(leftShift(p10Key, 1), 1), P8);
  return { k1, k2 };
}

// S-DES 的 F 函数
function F(rightHalf, k) {
  // 对右半部分进行 E/P 扩展置换
  const expanded = permute(rightHalf, EPBox);
  // 对结果与 K1 进行异或操作
  const xored = (parseInt(expanded, 2) ^ parseInt(k, 2)).toString(2).padStart(8, '0');
  // 将结果分为两组，并根据 S-box 进行替换
  const s0Input = xored.substring(0, 4);
  const s1Input = xored.substring(4);
  // 根据 S 盒规则行列查找
  const s0Row = parseInt(s0Input[0] + s0Input[3], 2);
  const s0Col = parseInt(s0Input[1] + s0Input[2], 2);
  const s1Row = parseInt(s1Input[0] + s1Input[3], 2);
  const s1Col = parseInt(s1Input[1] + s1Input[2], 2);
  const s0Output = SBox1[s0Row][s0Col].toString(2).padStart(2, '0');
  const s1Output = SBox2[s1Row][s1Col].toString(2).padStart(2, '0');
  // 对两个输出串进行 P4 置换得到最终结果
  const sOutput = s0Output + s1Output;
  return permute(sOutput, SPBox);
}

// 加密过程
function encrypt(p, k1, k2) {
  // 执行初始置换
  p = permute(p, IP);
  // 进行两轮 Feistel 加密
  let l0 = p.substring(0, 4);
  let r0 = p.substring(4);
  const l1 = r0;


  // 第一轮的 P4（加密阶段使用的 P4）
  let fResult = F(r0, k1);
  // p4 和 l0 异或
  let r1 = (parseInt(l0, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
  // 第二轮的 P4（加密阶段使用的 P4） 以上都没问题
  fResult = F(r1, k2);
  // p4 和 l1 异或
  let l2 = (parseInt(l1, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
  // 逆置换并返回结果（左边 R2 右边 R1）
  return permute(l2 + r1, IPni);
}

// 解密过程
function decrypt(c, k1, k2) {
  // 执行初始置换
  c = permute(c, IP);
  // 进行两轮 Feistel 解密（注意子密钥的使用顺序）
  let l2 = c.substring(0, 4);
  let r2 = c.substring(4);
  const l1 = r2;
  // 第一轮的 P4（解密阶段使用的 P4）
  let fResult = F(r2, k2);
  // p4 和 r2 异或
  let r1 = (parseInt(l2, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
  // 第二轮的 P4（解密阶段使用的 P4）
  fResult = F(r1, k1);
  // p4 和 l1 异或
  let l0 = (parseInt(l1, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
  // 逆置换并返回明文
  return permute(l0 + r1, IPni);
}


// 测试

// 密钥 key、明文 p、各个置换、S 盒
const key = "1010000010";
const p = "10011010";
const P10 = [3, 5, 2, 7, 4, 10, 1, 9, 8, 6];
const P8 = [6, 3, 7, 4, 8, 5, 10, 9];
const IP = [2, 6, 3, 1, 4, 8, 5, 7];
const IPni = [4, 1, 3, 5, 7, 2, 8, 6];
const EPBox = [4, 1, 2, 3, 2, 3, 4, 1];
const SBox1 = [
  [1, 0, 3, 2],
  [3, 2, 1, 0],
  [0, 2, 1, 3],
  [3, 1, 0, 2]
];

const SBox2 = [
  [0, 1, 2, 3],
  [2, 3, 1, 0],
  [3, 0, 1, 2],
  [2, 1, 0, 3]
];
const SPBox = [2, 4, 3, 1];

// 生成子密钥 K1 和 K2
const { k1, k2 } = generateKey(key, P10, P8);

// 对明文进行加密
const ciphertext = encrypt(p, k1, k2);
// 对密文进行解密
const plaintext = decrypt(ciphertext, k1, k2);

// 输出解密结果
console.log("明文 P：", p);
console.log("密钥 K：", key);
console.log("子密钥 k1：", k1);
console.log("子密钥 k2：", k2);
console.log("密文：", ciphertext);
console.log("解密后的明文：", plaintext);

//暴力破解
const knownPlaintext = '00100000';
const knowCiphertext = '00000011';

let foundKey = null;

// 循环尝试所有可能的10位密钥
for (let key = 0; key < 1024; key++) {
  const keyBinary = key.toString(2).padStart(10, '0'); // 将密钥转换为10位二进制表示形式
  const { k1, k2 } = generateKey(keyBinary, P10, P8); // 生成子密钥

  // 使用当前密钥尝试解密密文
  const decryptedText = decrypt(knowCiphertext, k1, k2);

  // 如果解密结果与已知的明文匹配，则找到了正确的密钥
  if (decryptedText === knownPlaintext) {
    foundKey = keyBinary;
    break;
  }
}

if (foundKey) {
  console.log('找到了正确的密钥：', foundKey);
} else {
  console.log('未找到匹配的密钥。');
}
//// 封闭测试
//// 明文
const plaintext2 = "00001111";

// 生成随机的两个密钥
const key1 = generateRandomKey();
const key2 = generateRandomKey();

// 使用两个不同的密钥加密同一明文
const ciphertext1 = encrypt(plaintext2, key1);
const ciphertext2 = encrypt(plaintext2, key2);

console.log("明文：", plaintext2);
console.log("密钥1：", key1);
console.log("密钥2：", key2);
console.log("密文1：", ciphertext1);
console.log("密文2：", ciphertext2);

// 检查是否出现密钥碰撞
if (ciphertext1 === ciphertext2) {
  console.log("出现密钥碰撞！");
} else {
  console.log("未出现密钥碰撞。");
}

// 生成随机的S-DES密钥
function generateRandomKey() {
  let key = "";
  for (let i = 0; i < 10; i++) {
    key += Math.random() < 0.5 ? "0" : "1";
  }
  return key;
}
