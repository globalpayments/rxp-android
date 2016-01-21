package com.realexpayments.remote;

/*
    Validate CVN. Applies to non-Amex card types. Only allows 3 numeric characters.
 */

public class ValidateCvn {

    public static boolean perform(String cvn) {
        // test numeric length 3
        if (cvn == null || !cvn.matches("^\\d{3}$")) {
            return false;
        }

        return true;
    }
}
