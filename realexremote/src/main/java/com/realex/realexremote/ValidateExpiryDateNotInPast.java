package com.realex.realexremote;

    /*
     * Validate Expiry Date Not In Past. Also runs checks from
     * validateExpiryDateFormat.
var validateExpiryDateNotInPast = function(expiryDate) {
        // test valid format
        if (!validateExpiryDateFormat(expiryDate)) {
        return false;
        }

        var month = parseInt(expiryDate.substring(0, 2), 10);
        var year = parseInt(expiryDate.substring(2, 4), 10);

        // test date is not in the past
        var now = new Date();
        var currentMonth = now.getMonth() + 1;
        var currentYear = now.getFullYear();
        if (year < (currentYear % 100)) {
        return false;
        } else if (year === (currentYear % 100) && month < currentMonth) {
        return false;
        }

        return true;
        };
     */

import java.util.Calendar;

public class ValidateExpiryDateNotInPast {

    public static boolean perform(String expiryDate) {
        // test valid format
        if (!ValidateExpiryDataFormat.perform(expiryDate)) {
            return false;
        }

        int month = Integer.parseInt(expiryDate.substring(0, 2), 10);
        int year = Integer.parseInt(expiryDate.substring(2, 4), 10);

        // test date is not in the past
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        if (year < (currentYear % 100)) {
            return false;
        } else if (year == (currentYear % 100) && month < currentMonth) {
            return false;
        }

        return true;
    }
}
