package com.realexpayments.remote;

public class RealexRemote {

    /**
     * Validate Amex CVN. Applies to Amex card types. Only allows 4 numeric characters.
     *
     * @param cvn the Amex CVN to validate
     * @return true if the Amex CVN is valid
     */
    public static boolean validateAmexCvn(String cvn) {
        return ValidateAmexCvn.perform(cvn);
    }

    /**
     * Validate Card Holder Name. Returns true if card holder valid. Only allows non-empty ISO/IEC 8859-1 values 100 characters or less.
     *
     * @param name the card holder's name to validate
     * @return true if the card holder's name is valid
     */
    public static boolean validateCardHolderName(String name) {
        return ValidateCardHolderName.perform(name);
    }

    /**
     * Validate Card Number. Returns true if card number valid. Only allows non-empty numeric values between 12 and 19 characters. A Luhn check is also run against the card number.
     *
     * @param number the credit card number to be checked
     * @return true if the card number is valid
     */
    public static boolean validateCardNumber(String number) {
        return ValidateCardNumber.perform(number);
    }

    /**
     * Validate CVN. Applies to non-Amex card types. Only allows 3 numeric characters.
     *
     * @param cvn the CVN to validate
     * @return true if the CVN is valid
     */
    public static boolean validateCvn(String cvn) {
        return ValidateCvn.perform(cvn);
    }

    /**
     *  Validate Expiry Date Format. Only allows 4 numeric characters. Month must be between 1 and 12.
     *
     * @param date the card expiry date to validate
     * @return true if the expiry date is valid
     */
    public static boolean validateExpiryDateFormat(String date) {
        return ValidateExpiryDataFormat.perform(date);
    }

    /**
     * Validate Expiry Date Not In Past. Also runs checks from validateExpiryDateFormat.
     *
     * @param date the card expiry date to validate
     * @return true if the expiry date is not in the past
     */
    public static boolean validateExpiryDateNotInPast(String date) {
        return ValidateExpiryDateNotInPast.perform(date);
    }
}
