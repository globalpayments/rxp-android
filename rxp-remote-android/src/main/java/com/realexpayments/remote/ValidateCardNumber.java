package com.realexpayments.remote;

/*
    Validate Card Number. Returns true if card number valid. Only allows non-empty numeric values between 12 and 19 characters. A Luhn check is also run against the card number.
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
