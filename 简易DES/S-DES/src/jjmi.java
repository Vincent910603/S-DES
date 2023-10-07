public class jjmi {
    public static String jiami(String ming,String mi){
        String temp = "";
        String[] k = yue.syue(mi);
        String hexs = tans.str2HexStr(ming);
        byte[] mbyt = tans.parseHexStr2Byte(hexs);
        String[] mbyte = tans.Bytetobin(mbyt);
        for(int i=0; i<mbyte.length;i++){
            mbyte[i]=Pbox.ip(mbyte[i]);
            String l=mbyte[i].substring(0,4);
            String r=mbyte[i].substring(4,8);
            l = yh.huo(l,F.f(k[0],r));
            r = r;
            temp = l;
            l=r;
            r=temp;
            l = yh.huo(l,F.f(k[1],r));
            r = r;
            mbyte[i]=l+r;
            mbyte[i]=Pbox.ip1(mbyte[i]);
        }
        String result = tans.parseByte2HexStr(tans.bintoByte(mbyte));
        return result;
    }

    public static String jiemi(String mw,String mi){
        String temp = "";
        String[] k = yue.syue(mi);
        byte[] mbyt = tans.parseHexStr2Byte(mw);
        String[] mbyte = tans.Bytetobin(mbyt);
        for(int i=0; i<mbyte.length;i++){
            mbyte[i]=Pbox.ip(mbyte[i]);
            String l=mbyte[i].substring(0,4);
            String r=mbyte[i].substring(4,8);
            l = yh.huo(l,F.f(k[1],r));
            r = r;
            temp = l;
            l=r;
            r=temp;
            l = yh.huo(l,F.f(k[0],r));
            r = r;
            mbyte[i]=l+r;
            mbyte[i]=Pbox.ip1(mbyte[i]);
        }
        String result = tans.hexStr2Str(tans.parseByte2HexStr(tans.bintoByte(mbyte)));
        return result;
    }
}
