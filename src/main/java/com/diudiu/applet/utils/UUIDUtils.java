package com.diudiu.applet.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by liuzhibin .
 */
public class UUIDUtils {
    public static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    public static String generateShortUuid(int size) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < size; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * UUID 随机取其中一个字符串为大写
     *
     * @author Zhibin Liu
     * @time 12/23/17 11:55
     */
    public static String generateUuid() {
        char[] chars = UUID.randomUUID().toString().replace("-", "").toCharArray();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int next = r.nextInt(30);
            chars[next] = Character.toUpperCase(chars[next]);
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        System.out.println(generateUuid());
    }
}
