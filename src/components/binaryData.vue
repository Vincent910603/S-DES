<template>
  <div class="container">
    <h2>数据加密和解密</h2>
    <div>
      <label for="plaintext">明\密文：</label>
      <input type="text" id="plaintext" v-model="plaintext" />
    </div>
    <div>
      <label for="key">密钥：</label>
      <input type="text" id="key" v-model="key" />
    </div>
    <button @click="encryptData">加密</button>
    <button @click="decryptData">解密</button>
    <div id="result">
      <h3>结果</h3>
      <p>密文：{{ ciphertext }}</p>
      <p>解密后的明文：{{ decryptedText }}</p>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      key: "",
      plaintext: "",
      P10: [3, 5, 2, 7, 4, 10, 1, 9, 8, 6],
      P8: [6, 3, 7, 4, 8, 5, 10, 9],
      IP: [2, 6, 3, 1, 4, 8, 5, 7],
      IPni: [4, 1, 3, 5, 7, 2, 8, 6],
      EPBox: [4, 1, 2, 3, 2, 3, 4, 1],
      SBox1: [
        [1, 0, 3, 2],
        [3, 2, 1, 0],
        [0, 2, 1, 3],
        [3, 1, 0, 2]
      ],
      SBox2: [
        [0, 1, 2, 3],
        [2, 3, 1, 0],
        [3, 0, 1, 2],
        [2, 1, 0, 3]
      ],
      SPBox: [2, 4, 3, 1],
      k1: "",
      k2: "",
      ciphertext: "",
      decryptedText: ""
    };
  },
  methods: {
    permute(inputStr, table) {
      let outputStr = "";
      for (let bitPosition of table) {
        outputStr += inputStr[bitPosition - 1];
      }
      return outputStr;
    },
    leftShift(key, n) {
      const leftHalf = key.substring(0, 5);
      const rightHalf = key.substring(5);
      const shiftedLeft = leftHalf.substring(n) + leftHalf.substring(0, n);
      const shiftedRight = rightHalf.substring(n) + rightHalf.substring(0, n);
      return shiftedLeft + shiftedRight;
    },
    generateKey(k, P10, P8) {
      const p10Key = this.permute(k, P10);
      const k1 = this.permute(this.leftShift(p10Key, 1), P8);
      const k2 = this.permute(this.leftShift(this.leftShift(p10Key, 1), 1), P8);
      this.k1 = k1;
      this.k2 = k2;
    },
    F(rightHalf, k) {
      const expanded = this.permute(rightHalf, this.EPBox);
      const xored = (parseInt(expanded, 2) ^ parseInt(k, 2)).toString(2).padStart(8, '0');
      const s0Input = xored.substring(0, 4);
      const s1Input = xored.substring(4);
      const s0Row = parseInt(s0Input[0] + s0Input[3], 2);
      const s0Col = parseInt(s0Input.substring(1, 3), 2);
      const s1Row = parseInt(s1Input[0] + s1Input[3], 2);
      const s1Col = parseInt(s1Input.substring(1, 3), 2);
      const s0Output = this.SBox1[s0Row][s0Col].toString(2).padStart(2, '0');
      const s1Output = this.SBox2[s1Row][s1Col].toString(2).padStart(2, '0');
      const sOutput = s0Output + s1Output;
      return this.permute(sOutput, this.SPBox);
    },
    encryptData() {
      const p = this.plaintext;
      const key = this.key;
      this.generateKey(key, this.P10, this.P8);
      const ciphertext = this.encrypt(p, this.k1, this.k2);
      this.ciphertext = ciphertext;
    },
    decryptData() {
      const c = this.ciphertext;
      const key = this.key;
      this.generateKey(key, this.P10, this.P8);
      const decryptedText = this.decrypt(c, this.k1, this.k2);
      this.decryptedText = decryptedText;
    },
    encrypt(p, k1, k2) {
      p = this.permute(p, this.IP);
      let l0 = p.substring(0, 4);
      let r0 = p.substring(4);
      const l1 = r0;
      let fResult = this.F(r0, k1);
      let r1 = (parseInt(l0, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
      fResult = this.F(r1, k2);
      let r2 = (parseInt(l1, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
      return this.permute(r2 + r1, this.IPni);
    },
    decrypt(c, k1, k2) {
      c = this.permute(c, this.IP);
      let r2 = c.substring(0, 4);
      let l2 = c.substring(4);
      let fResult = this.F(l2, k2);
      let l1 = (parseInt(r2, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
      fResult = this.F(l1, k1);
      let r1 = (parseInt(l2, 2) ^ parseInt(fResult, 2)).toString(2).padStart(4, '0');
      return this.permute(r1 + l1, this.IPni);
    }
  }
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
}
.container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
label {
  font-weight: bold;
}
input[type="text"] {
  width: 100%;
  padding: 8px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}
button {
  background-color: #4caf50;
  color: #fff;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 3px;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #45a049;
}
</style>
