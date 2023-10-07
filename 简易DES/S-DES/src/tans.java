import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class tans {
//    public static byte[] strtoByte(String str){
//        byte[] bs = str.getBytes(StandardCharsets.UTF_8);
//        return bs;
//    }
//
//    public static String Bytetostr(byte[] bs) {
//        String str = new String(bs,StandardCharsets.UTF_8);
//        return str;
//    }
//
    public static String[] Bytetobin(byte[] bs){
        String[] bin = new String[bs.length];
        for(int i=0;i<bs.length;i++){
            if(bs[i]<1){
                bin[i]=Integer.toBinaryString(bs[i]).substring(24,32);
            }
            else {
                bin[i]=Integer.toBinaryString(bs[i]);
                bin[i] = String.format("%8s",Integer.toBinaryString(bs[i]));
                bin[i] = bin[i].replaceAll("\\s","0");
            }
        }
        return bin;
    }

    public static byte[] bintoByte(String[] bin){
        byte[] bs = new byte[bin.length];
        for(int i=0;i<bin.length;i++){
            bs[i] = (byte) Integer.parseInt(bin[i],2);
        }
        return bs;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        int length = (hexStr.length() / 2);
        byte[] bytes = new byte[length];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            int position = i * 2;
            n = str.indexOf(hexs[position]) * 16;
            n += str.indexOf(hexs[position + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
//            sb.append(' ');
        }
        return sb.toString().trim();
    }
}
