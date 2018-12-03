package com.diudiu.applet.utils;

import java.math.BigDecimal;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 6/27/18
 * @since 0.1
 */
public class CalcUtils {


    public static final double add (Double one, Double two) {
        return add(one, two, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static final double add(Double one, Double two, int scale, int roundMode) {
        return BigDecimal.valueOf(one).add(BigDecimal.valueOf(two)).setScale(scale, roundMode).doubleValue();
    }


    public static final double sub(Double one, Double two) {
        return sub(one, two, 2, BigDecimal.ROUND_HALF_UP);
    }

    public static final double sub(Double one, Double two, int scale, int roundMode) {
        return BigDecimal.valueOf(one).subtract(BigDecimal.valueOf(two)).setScale(scale, roundMode).doubleValue();
    }


}
