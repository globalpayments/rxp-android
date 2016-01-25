package com.realexpayments.remote;

/**
 * Validate Expiry Date Format. Only allows 4 numeric characters. Month must be between 1 and 12.
 */

public class ValidateExpiryDataFormat {

    public static boolean perform(String expiryDate) {
        // test numeric of length 4
        if (expiryDate == null || !expiryDate.matches("^\\d{4}$")) {
            return false;
        }

        int month = Integer.parseInt(expiryDate.substring(0, 2), 10);
        int year = Integer.parseInt(expiryDate.substring(2, 4), 10);

        // test month range is 1-12
        if (month < 1 || month > 12) {
            return false;
        }

        return true;
    }
}
