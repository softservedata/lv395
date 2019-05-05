package com.softserve.edu.opencart.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceUtils {

    private static final int SCALE = 2;

    public static BigDecimal getPrice(String price) {
        Matcher matcher = Pattern.compile("\\d+\\.\\d{1,2}").matcher(price.replaceAll(",",""));
        matcher.find();
        return new BigDecimal(matcher.group(0)).setScale(SCALE, RoundingMode.HALF_DOWN);
    }

}
