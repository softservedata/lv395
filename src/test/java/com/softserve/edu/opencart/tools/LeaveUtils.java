package com.softserve.edu.opencart.tools;

import org.apache.log4j.Logger;

public final class LeaveUtils {

    private static final Logger log = Logger.getLogger(LeaveUtils.class);

    private LeaveUtils() {
    }

    public static void castExceptionByCondition(boolean condition, String message) {
        if (condition) {
            log.error(message);
            // TODO Develop Custom Exception
            throw new RuntimeException(message);
        }
    }
    
}
