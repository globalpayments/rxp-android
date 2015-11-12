package com.realex.realexremote;

    /*
     * Validate Card Number. Returns true if card number valid. Only allows
     * non-empty numeric values between 12 and 19 characters. A Luhn check is
     * also run against the card number.
var validateCardNumber = function(cardNumber) {
        // test numeric and length between 12 and 19
        if (!/^\d{12,19}$/.test(cardNumber)) {
        return false;
        }

        // luhn check
        var sum = 0;
        var digit = 0;
        var addend = 0;
        var timesTwo = false;

        for (var i = cardNumber.length - 1; i >= 0; i--) {
        digit = parseInt(cardNumber.substring(i, i + 1), 10);
        if (timesTwo) {
        addend = digit * 2;
        if (addend > 9) {
        addend -= 9;
        }
        } else {
        addend = digit;
        }
        sum += addend;
        timesTwo = !timesTwo;
        }

        var modulus = sum % 10;
        if (modulus !== 0) {
        return false;
        }

        return true;
        };
     */

public class ValidateCardNumber {

    public static boolean perform(String cardNumber) {

        // test numeric and length between 12 and 19

        if (cardNumber == null || !cardNumber.matches("^\\d{12,19}$")) {
            return false;
        }

        // luhn check
        int sum = 0;
        int digit = 0;
        int addend = 0;
        boolean timesTwo = false;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            digit = Integer.parseInt(cardNumber.substring(i, i + 1), 10);
            if (timesTwo) {
                addend = digit * 2;
                if (addend > 9) {
                    addend -= 9;
                }
            } else {
                addend = digit;
            }
            sum += addend;
            timesTwo = !timesTwo;
        }

        int modulus = sum % 10;
        if (modulus != 0) {
            return false;
        }

        return true;
    }
}
