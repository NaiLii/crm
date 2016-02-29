package net.gddata.other.util;

import java.math.BigInteger;

/**
 * Created by zhangzf on 15/12/30.
 * lls, 修改64进制,26个字母大写小写总计52个,数字10个,符号(_ -)两个
 */
public class UUID {
    public static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x",
            "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "_", "-", "A", "B",
            "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //有 1/16M 的碰撞机会
    public static String getGDID() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 64]);
        }
        return shortBuffer.toString();
    }

    //基本不会碰撞
    public static String getGDUUID() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789_-ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] ret = "0000000000000000000000".toCharArray();
        BigInteger big64= new BigInteger("64");
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        BigInteger big = new BigInteger(uuid, 16);
        int i = 21;
        while (!big.equals(BigInteger.ZERO)) {
            BigInteger[] a = big.divideAndRemainder(big64);
            ret[i] = chars[Integer.parseInt(a[1].toString())];
            big = a[0];
            i--;
        }
        return new String(ret);
    }
}
