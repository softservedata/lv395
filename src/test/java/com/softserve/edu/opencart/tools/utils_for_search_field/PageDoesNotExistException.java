package com.softserve.edu.opencart.tools.utils_for_search_field;

/**
 * Exception for TheBiggestK class.
 * If input number is smaller then 1, then we will throw this Exception
 *
 * @author Iryna Ratushniak
 */
public class PageDoesNotExistException extends Exception {


    /**
     * Constructor.
     *
     * @param message - message that customer will see,
     *                if imputed value is incorrect.
     */
    public PageDoesNotExistException(final String message) {
        super(message);
    }


}
