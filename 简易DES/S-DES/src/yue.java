import java.util.Random;

public class yue {
    public static String[] syue(String mi){
        String k0 = mi;
        String k[] = new String[]{"",""};
        Random r = new Random();
        if(k0.equals("")){
        for(int i=0;i<10;i++){
            k0+=Integer.toString(r.nextInt(2));
        }
        }
//        k0 = "1111111111";
        k0 = Pbox.p10(k0);
        String ls = k0.substring(0,5);
        String rs = k0.substring(5,10);
        ls = lshift(ls);
        rs = lshift(rs);
        k[0] = ls+rs;
        ls = lshift(ls);
        rs = lshift(rs);
        k[1] = ls+rs;
        k[0] = Pbox.p8(k[0]);
        k[1] = Pbox.p8(k[1]);
        return k;
    }

    public static String lshift(String str){
        String temp = "";
        temp+=str.substring(1,2);
        temp+=str.substring(2,3);
        temp+=str.substring(3,4);
        temp+=str.substring(4,5);
        temp+=str.substring(0,1);
        return temp;
    }
//    private String rshift(String str){
//        String temp = "";
//        temp+=str.substring(4,5);
//        temp+=str.substring(0,1);
//        temp+=str.substring(1,2);
//        temp+=str.substring(2,3);
//        temp+=str.substring(3,4);
//        return temp;
//    }
}
