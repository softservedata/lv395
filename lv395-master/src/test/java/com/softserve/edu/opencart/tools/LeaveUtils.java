package com.softserve.edu.opencart.tools;

public final class LeaveUtils {

    private LeaveUtils() {
    }

    public static void castExceptionByCondition(boolean condition, String message) {
        if (condition) {
            // TODO Add to Logger
            // TODO Develop Custom Exception
            throw new RuntimeException(message);
        }
    }
    
}
