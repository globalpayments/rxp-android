package com.realexpayments.hpp;

/**
 *
     Implement HPPManagerListener In Your Activity
     There are three possible outcomes from the HPP interaction
     1) It concluded successfully
     2) It failed with an error
     3) It was cancelled by the user

     Implement the following methods specified in HPPManagerListener to receive back the result from the HPP Manager.

 * @param <T> class that represent response you are waiting from consumer url. Server answer will automaticaaly be converted into that class
 *
 *
 public class ConsumerResponse implements Serializable {

        @SerializedName("MERCHANT_ID")
        protected String merchantId;
        @SerializedName("ACCOUNT")
        protected String account;
        @SerializedName("ORDER_ID")
        protected String orderId;
        @SerializedName("AMOUNT")
        protected String amount;
        @SerializedName("AUTHCODE")
        protected String authcode;
        @SerializedName("TIMESTAMP")
        protected String timestamp;
        @SerializedName("SHA1HASH")
        protected String sha1hash;
        @SerializedName("RESULT")
        protected String result;
        @SerializedName("MESSAGE")
        protected String message;
        @SerializedName("CVNRESULT")
        protected String cvnResult;
        @SerializedName("PASREF")
        protected String pasRef;
        @SerializedName("BATCHID")
        protected String batchId;
        @SerializedName("ECI")
        protected String eci;
        @SerializedName("CAVV")
        protected String cavv;
        @SerializedName("XID")
        protected String xid;
        @SerializedName("COMMENT1")
        protected String comment1;
        @SerializedName("COMMENT2")
        protected String comment2;
        @SerializedName("TSS")
        protected String tss;
        @SerializedName("AVSADDRESSRESULT")
        protected String avsAddressResult;
        @SerializedName("AVSPOSTCODERESULT")
        protected String avsPostcodeResult;
        @SerializedName("pas_uuid")
        protected String pas_uuid;
        @SerializedName("CARD_STORAGE_ENABLE")
        protected String card_storage_enable;
        @SerializedName("VALIDATE_CARD_ONLY")
        protected String validate_card_only;
        @SerializedName("CARD_PAYMENT_BUTTON")
        protected String card_payment_button;
        @SerializedName("DCC_ENABLE")
        protected String dcc_enable;
        @SerializedName("HPP_LANG")
        protected String hpp_lang;
}
 *
 */

public interface HPPManagerListener<T> {

    public static final String HPP_MANAGER_COMPLETED_WITH_RESULT = "hppManagerCompletedWithResult";

    void hppManagerCompletedWithResult(T t);
    void hppManagerFailedWithError(HPPError error);
    void hppManagerCancelled();

}
