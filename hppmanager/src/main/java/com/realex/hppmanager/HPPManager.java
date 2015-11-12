package com.realex.hppmanager;

import android.app.Fragment;
import android.os.Bundle;

import java.util.HashMap;

public class HPPManager extends HppResponse {

    public static final String RESULT_MESSAGE = "RESULT_MESSAGE";

    public static final String HPPREQUEST_PRODUCER_URL = "HPPREQUEST_PRODUCER_URL";
    public static final String HPPRESPONSE_CONSUMER_URL = "HPPRESPONSE_CONSUMER_URL";
    public static final String HPPURL = "HPPURL";

    private String hppRequestProducerURL = "";
    private String hppResponseConsumerURL = "";
    private String hppURL = "https://hpp.realexpayments.com/pay";

    private HashMap<String, String> supplementaryData = new HashMap<String, String>();


    public static boolean isLightBox() {
        return lightBox;
    }

    private static boolean lightBox = true;

    public String getHppRequestProducerURL() {
        return hppRequestProducerURL;
    }

    public void setHppRequestProducerURL(String hppRequestProducerURL) {
        this.hppRequestProducerURL = hppRequestProducerURL;
    }

    public String getHppResponseConsumerURL() {
        return hppResponseConsumerURL;
    }

    public void setHppResponseConsumerURL(String hppResponseConsumerURL) {
        this.hppResponseConsumerURL = hppResponseConsumerURL;
    }

    public String getHppURL() {
        return hppURL;
    }

    public void setHppURL(String hppURL) {
        this.hppURL = hppURL;
    }

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

    @Override
    public HashMap<String, String> getMap() {
        HashMap<String, String> map = super.getMap();

        if (supplementaryData != null) {
            map.putAll(supplementaryData);
        }

        return map;
    }
}
