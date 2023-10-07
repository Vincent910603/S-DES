public class F {
    public static String f(String k,String r){
        String temp = Pbox.ep(r);
        temp = yh.huo(temp,k);
        String left =temp.substring(0,4),right =temp.substring(4,8);
        left = Pbox.sbox1(left); right = Pbox.sbox2(right);
        temp = left+right;
        temp = Pbox.spbox(temp);
        return temp;
    }
}
