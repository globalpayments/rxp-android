package com.realex.hppmanager;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class HppResponse {

    public static final String MERCHANT_ID = "MERCHANT_ID";
    public static final String ACCOUNT = "ACCOUNT";
    public static final String ORDER_ID = "ORDER_ID";
    public static final String AMOUNT = "AMOUNT";
    public static final String CURRENCY = "CURRENCY";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String AUTO_SETTLE_FLAG = "AUTO_SETTLE_FLAG";
    public static final String COMMENT1 = "COMMENT1";
    public static final String COMMENT2 = "COMMENT2";
    public static final String RETURN_TSS = "RETURN_TSS";
    public static final String SHIPPING_CODE = "SHIPPING_CODE";
    public static final String SHIPPING_CO = "SHIPPING_CO";
    public static final String BILLING_CODE = "BILLING_CODE";
    public static final String BILLING_CO = "BILLING_CO";
    public static final String CUST_NUM = "CUST_NUM";
    public static final String VAR_REF = "VAR_REF";
    public static final String PROD_ID = "PROD_ID";
    public static final String HPP_LANG = "HPP_LANG";
    public static final String CARD_PAYMENT_BUTTON = "CARD_PAYMENT_BUTTON";
    public static final String CARD_STORAGE_ENABLE = "CARD_STORAGE_ENABLE";
    public static final String OFFER_SAVE_CARD = "OFFER_SAVE_CARD";
    public static final String PAYER_REF = "PAYER_REF";
    public static final String PMT_REF = "PMT_REF";
    public static final String PAYER_EXIST = "PAYER_EXIST";
    public static final String VALIDATE_CARD_ONLY = "VALIDATE_CARD_ONLY";
    public static final String DCC_ENABLE = "DCC_ENABLE";
    public static final String SHA1_HASH = "SHA1HASH";
    public static final String HPP_TEMPLATE_TYPE = "HPP_TEMPLATE_TYPE";
    public static final String HPP_ORIGIN = "HPP_ORIGIN";
    public static final String SUPPLEMENTARYDATA = "SUPPLEMENTARYDATA";


    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAutoSettleFlag() {
        return autoSettleFlag;
    }

    public void setAutoSettleFlag(String autoSettleFlag) {
        this.autoSettleFlag = autoSettleFlag;
    }

    public String getCommentOne() {
        return commentOne;
    }

    public void setCommentOne(String commentOne) {
        this.commentOne = commentOne;
    }

    public String getCommentTwo() {
        return commentTwo;
    }

    public void setCommentTwo(String commentTwo) {
        this.commentTwo = commentTwo;
    }

    public String getReturnTss() {
        return returnTss;
    }

    public void setReturnTss(String returnTss) {
        this.returnTss = returnTss;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getBillingCode() {
        return billingCode;
    }

    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getVariableReference() {
        return variableReference;
    }

    public void setVariableReference(String variableReference) {
        this.variableReference = variableReference;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCardPaymentButtonText() {
        return cardPaymentButtonText;
    }

    public void setCardPaymentButtonText(String cardPaymentButtonText) {
        this.cardPaymentButtonText = cardPaymentButtonText;
    }

    public String getCardStorageEnable() {
        return cardStorageEnable;
    }

    public void setCardStorageEnable(String cardStorageEnable) {
        this.cardStorageEnable = cardStorageEnable;
    }

    public String getOfferSaveCard() {
        return offerSaveCard;
    }

    public void setOfferSaveCard(String offerSaveCard) {
        this.offerSaveCard = offerSaveCard;
    }

    public String getPayerReference() {
        return payerReference;
    }

    public void setPayerReference(String payerReference) {
        this.payerReference = payerReference;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPayerExists() {
        return payerExists;
    }

    public void setPayerExists(String payerExists) {
        this.payerExists = payerExists;
    }

    public String getValidateCardOnly() {
        return validateCardOnly;
    }

    public void setValidateCardOnly(String validateCardOnly) {
        this.validateCardOnly = validateCardOnly;
    }

    public String getDccEnable() {
        return dccEnable;
    }

    public void setDccEnable(String dccEnable) {
        this.dccEnable = dccEnable;
    }

    public String getSha1Hash() {
        return sha1Hash;
    }

    public void setSha1Hash(String sha1Hash) {
        this.sha1Hash = sha1Hash;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    @SerializedName(MERCHANT_ID)
    protected String merchantId;
    @SerializedName(ACCOUNT)
    protected String account;
    @SerializedName(ORDER_ID)
    protected String orderId;
    @SerializedName(AMOUNT)
    protected String amount;
    @SerializedName(CURRENCY)
    protected String currency;
    @SerializedName(TIMESTAMP)
    protected String timestamp;
    @SerializedName(AUTO_SETTLE_FLAG)
    protected String autoSettleFlag;
    @SerializedName(COMMENT1)
    protected String commentOne;
    @SerializedName(COMMENT2)
    protected String commentTwo;
    @SerializedName(RETURN_TSS)
    protected String returnTss;
    @SerializedName(SHIPPING_CODE)
    protected String shippingCode;
    @SerializedName(SHIPPING_CO)
    protected String shippingCountry;
    @SerializedName(BILLING_CODE)
    protected String billingCode;
    @SerializedName(BILLING_CO)
    protected String billingCountry;
    @SerializedName(CUST_NUM)
    protected String customerNumber;
    @SerializedName(VAR_REF)
    protected String variableReference;
    @SerializedName(PROD_ID)
    protected String productId;
    @SerializedName(HPP_LANG)
    protected String language;
    @SerializedName(CARD_PAYMENT_BUTTON)
    protected String cardPaymentButtonText;
    @SerializedName(CARD_STORAGE_ENABLE)
    protected String cardStorageEnable;
    @SerializedName(OFFER_SAVE_CARD)
    protected String offerSaveCard;
    @SerializedName(PAYER_REF)
    protected String payerReference;
    @SerializedName(PMT_REF)
    protected String paymentReference;
    @SerializedName(PAYER_EXIST)
    protected String payerExists;
    @SerializedName(VALIDATE_CARD_ONLY)
    protected String validateCardOnly;
    @SerializedName(DCC_ENABLE)
    protected String dccEnable;

    @SerializedName(SHA1_HASH)
    protected String sha1Hash;

    @SerializedName(HPP_TEMPLATE_TYPE)
    protected String templateType;

    @SerializedName(HPP_ORIGIN)
    protected String origin;

    public HashMap<String, String> getMap() {
        HashMap<String, String> parameters = new HashMap<>();

        parameters.put(MERCHANT_ID, merchantId);
        parameters.put(ACCOUNT, account);
        parameters.put(ORDER_ID, orderId);
        parameters.put(AMOUNT, amount);
        parameters.put(CURRENCY, currency);
        parameters.put(TIMESTAMP, timestamp);
        parameters.put(AUTO_SETTLE_FLAG, autoSettleFlag);
        parameters.put(COMMENT1, commentOne);
        parameters.put(COMMENT2, commentTwo);
        parameters.put(RETURN_TSS, returnTss);
        parameters.put(SHIPPING_CODE, shippingCode);
        parameters.put(SHIPPING_CO, shippingCountry);
        parameters.put(BILLING_CODE, billingCode);
        parameters.put(BILLING_CO, billingCountry);
        parameters.put(CUST_NUM, customerNumber);
        parameters.put(VAR_REF, variableReference);
        parameters.put(PROD_ID, productId);
        parameters.put(HPP_LANG, language);
        parameters.put(CARD_PAYMENT_BUTTON, cardPaymentButtonText);
        parameters.put(CARD_STORAGE_ENABLE, cardStorageEnable);
        parameters.put(OFFER_SAVE_CARD, offerSaveCard);
        parameters.put(PAYER_REF, payerReference);
        parameters.put(PMT_REF, paymentReference);
        parameters.put(PAYER_EXIST, payerExists);
        parameters.put(VALIDATE_CARD_ONLY, validateCardOnly);
        parameters.put(DCC_ENABLE, dccEnable);
        parameters.put(SHA1_HASH, sha1Hash);
        parameters.put(HPP_TEMPLATE_TYPE, templateType);
        parameters.put(HPP_ORIGIN, origin);

        return parameters;
    }
}
