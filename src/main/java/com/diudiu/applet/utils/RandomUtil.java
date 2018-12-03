package com.diudiu.applet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtil.class);

    private static Random r;


    static {
        RandomUtil.r = new Random();
    }

    public static String generateRandom(Integer length, Integer origin, Integer firstBound, Integer bound, boolean canZero) {
        StringBuffer s = new StringBuffer(length);
        bound = bound == null ? 10 : bound;
        origin = origin == null ? 0 : origin;
        firstBound = firstBound == null ? 10 : firstBound;
        for (int i = 0; i < length; i++) {

            int v = 0;
            if (!canZero && i == 0) {
                if (firstBound == origin) {
                    s.append(firstBound);
                    continue;
                }

                int sum = 0;
                do {
                    sum++;
                    v = r.nextInt(firstBound);
                    // 循环10圈 如果还未跳出 表示进入死循环 强制中止
                    if (sum == 25) {
                        LOGGER.error("随机数生成进入死循环 强制退出==============");
                        break;
                    }
                } while (v == 0 || v < origin);
            } else {
                v = r.nextInt(bound);
            }
            s.append(v);
        }
        return s.toString();
    }

    public static Integer generateRandom(Integer length) {
        return Integer.valueOf(generateRandom(length, null, null, null, false));
    }

    public static String generateRandom(Integer length, boolean firstCanZero) {
        return generateRandom(length, null, null, null, firstCanZero);
    }

    public static Integer generateInMiddle(Integer first, Integer two) {
        if (first.intValue() == two.intValue())
            return first;

        int firstLen = String.valueOf(first).length();
        int twoLen = String.valueOf(two).length();

        if (firstLen == twoLen) {
            return Integer.valueOf(generateRandom(firstLen, Integer.valueOf(String.valueOf(first).substring(0, 1)),
                    Integer.valueOf(String.valueOf(two).substring(0, 1)), null,
                    false));
        } else {

            int len;
            if ((len = generateInMiddle(firstLen, twoLen + 1)) == firstLen) {
                return generateInMiddle(first, getLengthMaxInteger(firstLen));
            } else if (len == twoLen) {
                return generateInMiddle(getLengthMinInteger(twoLen), two);
            } else {
                return generateInMiddle(getLengthMinInteger(len), getLengthMaxInteger(len));
            }
        }
    }

    private static Integer getLengthMaxInteger(Integer length) {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < length; i++) {
            s.append(9);
        }
        return Integer.valueOf(s.toString());
    }

    private static Integer getLengthMinInteger(Integer length) {
        StringBuilder s = new StringBuilder();
        s.append(1);

        for (int i = 0; i < length - 1; i++) {
            s.append(0);
        }
        return Integer.valueOf(s.toString());
    }

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        String strs[] = uuid.split("-");
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
