package com.realexpayments.remote;


/*
    Validate Card Holder Name. Returns true if card holder valid. Only allows non-empty ISO/IEC 8859-1 values 100 characters or less.
 */

public class ValidateCardHolderName {

    public static boolean perform(String cardHolderName) {
        // test for undefined
        if (cardHolderName == null) {
            return false;
        }

        // test white space only
        if (cardHolderName.trim().length() == 0) {
            return false;
        }

        // test ISO/IEC 8859-1 characters between 1 and 100
        if (!cardHolderName.matches("^[\\u0020-\\u007E\\u00A0-\\u00FF]{1,100}$")) {
            return false;
        }

        return true;
    }
}
