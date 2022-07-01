package com.epam.internetMarket.util.validators;

import static com.epam.internetMarket.util.constants.ParameterConstants.MAXIMUM_NUMBER_ASCII_VALUE;
import static com.epam.internetMarket.util.constants.ParameterConstants.MINIMUM_NUMBER_ASCII_VALUE;

public class NumberParameterValidator {
    public static boolean isNumberParameterValid(String request) {
        boolean isNumber = true;
        for (int i = 0; i < request.length(); i++) {
            if (!(request.charAt(i) >= MINIMUM_NUMBER_ASCII_VALUE && request.charAt(i) <= MAXIMUM_NUMBER_ASCII_VALUE)) {
                isNumber = false;
                break;
            }
        }
        return isNumber;
    }
}
