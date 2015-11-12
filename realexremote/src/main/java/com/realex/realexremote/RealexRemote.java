package com.realex.realexremote;

public class RealexRemote {

    public static boolean validateAmexCvn(String cvn) {
        return ValidateAmexCvn.perform(cvn);
    }

    public static boolean validateCardHolderName(String name) {
        return ValidateCardHolderName.perform(name);
    }

    public static boolean validateCardNumber(String number) {
        return ValidateCardNumber.perform(number);
    }

    public static boolean validateCvn(String cvn) {
        return ValidateCvn.perform(cvn);
    }

    public static boolean validateExpiryDateFormat(String date) {
        return ValidateExpiryDataFormat.perform(date);
    }

    public static boolean validateExpiryDateNotInPast(String date) {
        return ValidateExpiryDateNotInPast.perform(date);
    }
}
