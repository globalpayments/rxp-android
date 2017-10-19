package com.realexpayments.hpp;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashMap;

/**
 * The main object the host app creates.

 The HPP Manager requires three server URLs.
 1) The request producer which takes the request from the component and encodes it using the shared secret stored on the server side
 2) The HPP server where the component sends the encoded request
 3) The response consumer which takes the encoded response received back from HPP

 You set these three URLs as follows;

 hppManager.setHppRequestProducerURL("https://myserver.com/HPP_Request_Producer.php");
 hppManager.setHppResponseConsumerURL("https://myserver.com/HPP_Response_Consumer.php"");
 hppManager.setHppURL("https://pay.sandbox.realexpayments.com/pay";

 Set HPP Properties

 hppManager.setMerchantId("realexsandbox");
 hppManager.setAccount("internet");
 hppManager.setAmount("100");
 hppManager.setCurrency("EUR");

 it is also possible to set options by createFromBundle function


 */
public class HPPManager extends HPPResponse {

    public static final String RESULT_MESSAGE = "RESULT_MESSAGE";

    public static final String HPPREQUEST_PRODUCER_URL = "HPPREQUEST_PRODUCER_URL";
    public static final String HPPRESPONSE_CONSUMER_URL = "HPPRESPONSE_CONSUMER_URL";
    public static final String HPPURL = "HPPURL";

    private String hppRequestProducerURL = "";
    private String hppResponseConsumerURL = "";
    private String hppURL = "https://pay.realexpayments.com/pay";

    //Supplementary data to be sent to Realex Payments. This will be returned in the HPP response.
    private HashMap<String, String> supplementaryData = new HashMap<String, String>();

    private static boolean isEncoded = false;

    public static boolean isEncoded() {
        return isEncoded;
    }

    public static void setIsEncoded(boolean isEncoded) {
        HPPManager.isEncoded = isEncoded;
    }

    /**
     *
     * @return hpp request producer url
     */
    public String getHppRequestProducerURL() {
        return hppRequestProducerURL;
    }

    /**
     * The request producer which takes the request from the component and encodes it using the shared secret stored on the server side.
     *
     * @param hppRequestProducerURL to set
     */
    public void setHppRequestProducerURL(String hppRequestProducerURL) {
        this.hppRequestProducerURL = hppRequestProducerURL;
    }

    /**
     *
     * @return hpp response consumer url
     */
    public String getHppResponseConsumerURL() {

        return hppResponseConsumerURL;
    }

    /**
     * The response consumer which takes the encoded response received back from HPP.
     *
     * @param hppResponseConsumerURL to set
     */
    public void setHppResponseConsumerURL(String hppResponseConsumerURL) {
        this.hppResponseConsumerURL = hppResponseConsumerURL;
    }

    /**
     *
     * @return hpp url
     */
    public String getHppURL() {
        return hppURL;
    }

    /**
     * The HPP server where the component sends the encoded request.
     *
     * @param hppURL to set
     */
    public void setHppURL(String hppURL) {
        this.hppURL = hppURL;
    }

    /**
     * initialize HPPManager from argument
     *
         Bundle args = new Bundle();

         args.putString(HPPManager.HPPREQUEST_PRODUCER_URL, "https://myserver.com/HPP_Request_Producer.php");
         args.putString(HPPManager.HPPRESPONSE_CONSUMER_URL, "https://myserver.com/HPP_Response_Consumer.php");
         args.putString(HPPManager.HPPURL, "https://pay.sandbox.realexpayments.com/pay");
         args.putString(HPPManager.MERCHANT_ID, "realexsandbox");
         args.putString(HPPManager.AMOUNT, "100");
         args.putString(HPPManager.CURRENCY, "EUR");
         args.putString(HPPManager.ACCOUNT, "internet");

         hppManager = hppManager.createFromBundle(args);

     *
     *
     * @param arg Bundle with arguments
     * @return
     */
    public static HPPManager createFromBundle(Bundle arg) {

        HPPManager hppManager = new HPPManager();

        hppManager.hppRequestProducerURL = arg.getString(HPPREQUEST_PRODUCER_URL);
        hppManager.hppResponseConsumerURL = arg.getString(HPPRESPONSE_CONSUMER_URL);
        hppManager.hppURL = arg.getString(HPPURL);

        hppManager.merchantId = arg.getString(MERCHANT_ID);
        hppManager.account = arg.getString(ACCOUNT);
        hppManager.orderId = arg.getString(ORDER_ID);
        hppManager.amount = arg.getString(AMOUNT);
        hppManager.currency = arg.getString(CURRENCY);
        hppManager.timestamp = arg.getString(TIMESTAMP);
        hppManager.autoSettleFlag = arg.getString(AUTO_SETTLE_FLAG);
        hppManager.commentOne = arg.getString(COMMENT1);
        hppManager.commentTwo = arg.getString(COMMENT2);
        hppManager.returnTss = arg.getString(RETURN_TSS);
        hppManager.shippingCode = arg.getString(SHIPPING_CODE);
        hppManager.shippingCountry = arg.getString(SHIPPING_CO);
        hppManager.billingCode = arg.getString(BILLING_CODE);
        hppManager.billingCountry = arg.getString(BILLING_CO);
        hppManager.customerNumber = arg.getString(CUST_NUM);
        hppManager.variableReference = arg.getString(VAR_REF);
        hppManager.productId = arg.getString(PROD_ID);
        hppManager.language = arg.getString(HPP_LANG);
        hppManager.hppVersion = arg.getString(HPP_VERSION);
        hppManager.hppPostResponse = arg.getString(HPP_POST_RESPONSE);
        hppManager.cardPaymentButtonText = arg.getString(CARD_PAYMENT_BUTTON);
        hppManager.cardStorageEnable = arg.getString(CARD_STORAGE_ENABLE);
        hppManager.offerSaveCard = arg.getString(OFFER_SAVE_CARD);
        hppManager.payerReference = arg.getString(PAYER_REF);
        hppManager.paymentReference = arg.getString(PMT_REF);
        hppManager.payerExists = arg.getString(PAYER_EXIST);
        hppManager.validateCardOnly = arg.getString(VALIDATE_CARD_ONLY);
        hppManager.dccEnable = arg.getString(DCC_ENABLE);
        hppManager.supplementaryData = (HashMap<String, String>) arg.getSerializable(SUPPLEMENTARYDATA);

        return hppManager;
    }

    /**
     *
     * @return Fragment that present payment form
     */
    public Fragment newInstance() {
        HPPManagerFragment fragment = new HPPManagerFragment();
        Bundle args = new Bundle();

        args.putString(HPPREQUEST_PRODUCER_URL, hppRequestProducerURL);
        args.putString(HPPRESPONSE_CONSUMER_URL, hppResponseConsumerURL);
        args.putString(HPPURL, hppURL);

        args.putString(MERCHANT_ID, merchantId);
        args.putString(ORDER_ID, orderId);
        args.putString(AMOUNT, amount);
        args.putString(CURRENCY, currency);
        args.putString(ACCOUNT, account);

        args.putString(MERCHANT_ID, merchantId);
        args.putString(ACCOUNT, account);
        args.putString(ORDER_ID, orderId);
        args.putString(AMOUNT, amount);
        args.putString(CURRENCY, currency);
        args.putString(TIMESTAMP, timestamp);
        args.putString(AUTO_SETTLE_FLAG, autoSettleFlag);
        args.putString(COMMENT1, commentOne);
        args.putString(COMMENT2, commentTwo);
        args.putString(RETURN_TSS, returnTss);
        args.putString(SHIPPING_CODE, shippingCode);
        args.putString(SHIPPING_CO, shippingCountry);
        args.putString(BILLING_CODE, billingCode);
        args.putString(BILLING_CO, billingCountry);
        args.putString(CUST_NUM, customerNumber);
        args.putString(VAR_REF, variableReference);
        args.putString(PROD_ID, productId);
        args.putString(HPP_LANG, language);
        args.putString(HPP_VERSION, hppVersion);
        args.putString(HPP_POST_RESPONSE, hppPostResponse);
        args.putString(CARD_PAYMENT_BUTTON, cardPaymentButtonText);
        args.putString(CARD_STORAGE_ENABLE, cardStorageEnable);
        args.putString(OFFER_SAVE_CARD, offerSaveCard);
        args.putString(PAYER_REF, payerReference);
        args.putString(PMT_REF, paymentReference);
        args.putString(PAYER_EXIST, payerExists);
        args.putString(VALIDATE_CARD_ONLY, validateCardOnly);
        args.putString(DCC_ENABLE, dccEnable);
        args.putSerializable(SUPPLEMENTARYDATA, supplementaryData);

        fragment.setArguments(args);
        return fragment;
    }

    /**
     *
     * @return dictionary of parameters
     */
    @Override
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = super.getMap();

        if (supplementaryData != null) {
            map.putAll(supplementaryData);
        }

        return map;
    }

    /**
     * in addition to the predefined properties, you can add any amount of additional arbitrary properties
     * @param key
     * @param value
     */
    public void setSupplementaryData(String key, String value) {
        supplementaryData.put(key,value);
    }
}
