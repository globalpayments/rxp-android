package com.realex.realexremote;

    /*
     * Validate Amex CVN. Applies to Amex card types. Only allows 4 numeric
     * characters.
var validateAmexCvn = function(cvn) {
        // test numeric length 4
        if (!/^\d{4}$/.test(cvn)) {
        return false;
        }

        return true;
        };
     */

public class ValidateAmexCvn {

    public static boolean perform(String cvn) {
        // test numeric length 4
        if (cvn == null || !cvn.matches("^\\d{4}$")) {
            return false;
        }

        return true;
    }
}
