package com.softserve.edu.opencart.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceUtils {

    public static final int SCALE = 2;
    private static final String PRICE_PARSER_PATTERN = "\\d+\\.\\d{1,2}";

    public static BigDecimal getPrice(String price) {
        Matcher matcher = Pattern.compile(PRICE_PARSER_PATTERN).matcher(price.replaceAll(",",""));
        matcher.find();
        return new BigDecimal(matcher.group(0)).setScale(SCALE, RoundingMode.HALF_DOWN);
    }

}
