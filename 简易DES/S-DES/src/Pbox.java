public class Pbox {
    public static String p10(String str){
        String temp = "";
        temp+=str.substring(2,3);
        temp+=str.substring(4,5);
        temp+=str.substring(1,2);
        temp+=str.substring(6,7);
        temp+=str.substring(3,4);
        temp+=str.substring(9,10);
        temp+=str.substring(0,1);
        temp+=str.substring(8,9);
        temp+=str.substring(7,8);
        temp+=str.substring(5,6);
        return temp;
    }

    public static String p8(String str){
        String temp = "";
        temp+=str.substring(5,6);
        temp+=str.substring(2,3);
        temp+=str.substring(6,7);
        temp+=str.substring(3,4);
        temp+=str.substring(7,8);
        temp+=str.substring(4,5);
        temp+=str.substring(9,10);
        temp+=str.substring(8,9);
        return temp;
    }

    public static String ip(String str){
        String temp = "";
        temp+=str.substring(1,2);
        temp+=str.substring(5,6);
        temp+=str.substring(2,3);
        temp+=str.substring(0,1);
        temp+=str.substring(3,4);
        temp+=str.substring(7,8);
        temp+=str.substring(4,5);
        temp+=str.substring(6,7);
        return temp;
    }

    public static String ip1(String str){
        String temp = "";
        temp+=str.substring(3,4);
        temp+=str.substring(0,1);
        temp+=str.substring(2,3);
        temp+=str.substring(4,5);
        temp+=str.substring(6,7);
        temp+=str.substring(1,2);
        temp+=str.substring(7,8);
        temp+=str.substring(5,6);
        return temp;
    }

    public static String ep(String str){
        String temp = "";
        temp+=str.substring(3,4);
        temp+=str.substring(0,1);
        temp+=str.substring(1,2);
        temp+=str.substring(2,3);
        temp+=str.substring(1,2);
        temp+=str.substring(2,3);
        temp+=str.substring(3,4);
        temp+=str.substring(0,1);
        return temp;
    }

    public static String sbox1(String str){
        int x=0,y=0;
        String[][] sb = {{"01","00","11","10"},
                          {"11","10","01","00"},
                          {"00","10","01","11"},
                          {"11","01","00","10"}
        };
        switch (str.substring(0,1)+str.substring(3,4)){
            case "00":
                x=0;
                break;
            case "01":
                x=1;
                break;
            case "10":
                x=2;
                break;
            case "11":
                x=3;
                break;
        }
        switch (str.substring(1,3)){
            case "00":
                x=0;
                break;
            case "01":
                x=1;
                break;
            case "10":
                x=2;
                break;
            case "11":
                x=3;
                break;
        }
        return sb[x][y];
    }

    public static String sbox2(String str){
        int x=0,y=0;
        String[][] sb = {{"00","01","10","11"},
                {"10","11","01","00"},
                {"11","00","01","10"},
                {"10","01","00","11"}
        };
        switch (str.substring(0,1)+str.substring(3,4)){
            case "00":
                x=0;
                break;
            case "01":
                x=1;
                break;
            case "10":
                x=2;
                break;
            case "11":
                x=3;
                break;
        }
        switch (str.substring(1,3)){
            case "00":
                x=0;
                break;
            case "01":
                x=1;
                break;
            case "10":
                x=2;
                break;
            case "11":
                x=3;
                break;
        }
        return sb[x][y];
    }

    public static String spbox(String str){
        String temp = "";
        temp+=str.substring(1,2);
        temp+=str.substring(3,4);
        temp+=str.substring(2,3);
        temp+=str.substring(0,1);
        return temp;
    }
}
