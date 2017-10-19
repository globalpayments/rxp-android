package com.realexpayments.hpp;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * HPPResponse contains predefined properties
 */
public class HPPResponse {

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
    public static final String HPP_VERSION = "HPP_VERSION";
    public static final String HPP_POST_RESPONSE = "HPP_POST_RESPONSE";

    /**
     * Getter for merchant ID.
     *
     * @return String
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * Getter for account.
     *
     * @return String
     */
    public String getAccount() {
        return account;
    }

    /**
     * Getter for order ID.
     *
     * @return String
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Getter for amount.
     *
     * @return String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Getter for currency.
     *
     * @return String
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Getter for time stamp.
     *
     * @return String
     */
    public String getTimeStamp() {
        return timestamp;
    }

    /**
     * Getter for hash.
     *
     * @return String
     */
    public String getHash() {
        return sha1Hash;
    }

    /**
     * Getter for auto settle flag.
     *
     * @return String
     */
    public String getAutoSettleFlag() {
        return autoSettleFlag;
    }

    /**
     * Getter for comment one.
     *
     * @return String
     */
    public String getCommentOne() {
        return commentOne;
    }

    /**
     * Getter for comment two.
     *
     * @return String
     */
    public String getCommentTwo() {
        return commentTwo;
    }

    /**
     * Getter for return TSS flag.
     *
     * @return String
     */
    public String getReturnTss() {
        return returnTss;
    }

    /**
     * Getter for shipping code.
     *
     * @return String
     */
    public String getShippingCode() {
        return shippingCode;
    }

    /**
     * Getter for shipping country.
     *
     * @return String
     */
    public String getShippingCountry() {
        return shippingCountry;
    }

    /**
     * Getter for billing code.
     *
     * @return String
     */
    public String getBillingCode() {
        return billingCode;
    }

    /**
     * Getter for billing country.
     *
     * @return String
     */
    public String getBillingCountry() {
        return billingCountry;
    }

    /**
     * Getter for customer number.
     *
     * @return String
     */
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     * Getter for variable reference.
     *
     * @return String
     */
    public String getVariableReference() {
        return variableReference;
    }

    /**
     * Getter for product ID.
     *
     * @return String
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Getter for language.
     *
     * @return String
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Getter for card payment button text.
     *
     * @return String
     */
    public String getCardPaymentButtonText() {
        return cardPaymentButtonText;
    }

    /**
     * Getter for card storage enable flag.
     *
     * @return String
     */
    public String getCardStorageEnable() {
        return cardStorageEnable;
    }

    /**
     * Getter for offer to save card.
     *
     * @return String
     */
    public String getOfferSaveCard() {
        return offerSaveCard;
    }

    /**
     * Getter for payer reference.
     *
     * @return String
     */
    public String getPayerReference() {
        return payerReference;
    }

    /**
     * Getter for payment reference.
     *
     * @return String
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * Getter for payer exists.
     *
     * @return String
     */
    public String getPayerExists() {
        return payerExists;
    }

    /**
     * Getter for validate card only.
     *
     * @return String
     */
    public String getValidateCardOnly() {
        return validateCardOnly;
    }

    /**
     * Getter for DCC enable flag.
     *
     * @return String
     */
    public String getDccEnable() {
        return dccEnable;
    }

    public String getTemplateType() {
        return templateType;
    }

    public String getOrigin() {
        return origin;
    }


    /**
     * Setter for merchant ID.
     *
     * @param merchantId
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * Setter for account.
     *
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Setter for order ID.
     *
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Setter for amount.
     *
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Setter for currency.
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Setter for time stamp.
     *
     * @param timeStamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timestamp = timeStamp;
    }

    /**
     * Setter for hash.
     *
     * @param hash
     */
    public void setHash(String hash) {
        this.sha1Hash = hash;
    }

    /**
     * Setter for auto settle flag.
     *
     * @param autoSettleFlag
     */
    public void setAutoSettleFlag(String autoSettleFlag) {
        this.autoSettleFlag = autoSettleFlag;
    }

    /**
     * Setter for comment one.
     *
     * @param commentOne
     */
    public void setCommentOne(String commentOne) {
        this.commentOne = commentOne;
    }

    /**
     * Setter for comment two.
     *
     * @param commentTwo
     */
    public void setCommentTwo(String commentTwo) {
        this.commentTwo = commentTwo;
    }

    /**
     * Setter for return TSS.
     *
     * @param returnTss
     */
    public void setReturnTss(String returnTss) {
        this.returnTss = returnTss;
    }

    /**
     * Setter for shipping code.
     *
     * @param shippingCode
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    /**
     * Setter for shipping country.
     *
     * @param shippingCountry
     */
    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    /**
     * Setter for billing code.
     *
     * @param billingCode
     */
    public void setBillingCode(String billingCode) {
        this.billingCode = billingCode;
    }

    /**
     * Setter for billing country.
     *
     * @param billingCountry
     */
    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    /**
     * Setter for customer number.
     *
     * @param customerNumber
     */
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    /**
     * Setter for variable reference.
     *
     * @param variableReference
     */
    public void setVariableReference(String variableReference) {
        this.variableReference = variableReference;
    }

    /**
     * Setter for product ID.
     *
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Setter for language.
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Setter for card payment button text.
     *
     * @param cardPaymentButtonText
     */
    public void setCardPaymentButtonText(String cardPaymentButtonText) {
        this.cardPaymentButtonText = cardPaymentButtonText;
    }

    /**
     * Setter for card storage enable flag.
     *
     * @param cardStorageEnable
     */
    public void setCardStorageEnable(String cardStorageEnable) {
        this.cardStorageEnable = cardStorageEnable;
    }

    /**
     * Setter for offer to save card.
     *
     * @param offerSaveCard
     */
    public void setOfferSaveCard(String offerSaveCard) {
        this.offerSaveCard = offerSaveCard;
    }

    /**
     * Setter for payer reference.
     *
     * @param payerReference
     */
    public void setPayerReference(String payerReference) {
        this.payerReference = payerReference;
    }

    /**
     * Setter for payment reference.
     *
     * @param paymentReference
     */
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    /**
     * Setter for payer exists.
     *
     * @param payerExists
     */
    public void setPayerExists(String payerExists) {
        this.payerExists = payerExists;
    }

    /**
     * Setter for validate card only.
     *
     * @param validateCardOnly
     */
    public void setValidateCardOnly(String validateCardOnly) {
        this.validateCardOnly = validateCardOnly;
    }

    /**
     * Setter for DCC enable flag.
     *
     * @param dccEnable
     */
    public void setDccEnable(String dccEnable) {
        this.dccEnable = dccEnable;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * The merchant ID supplied by Realex Payments – note this is not the merchant number supplied by your bank.
     */
    @SerializedName(MERCHANT_ID)
    protected String merchantId;

    /**
     * The sub-account to use for this transaction. If not present, the default sub-account will be used.
     */
    @SerializedName(ACCOUNT)
    protected String account;

    /**
     * A unique alphanumeric id that’s used to identify the transaction. No spaces are allowed.
     */
    @SerializedName(ORDER_ID)
    protected String orderId;

    /**
     * Total amount to authorise in the lowest unit of the currency – i.e. 100 euro would be entered as 10000.
     * If there is no decimal in the currency (e.g. JPY Yen) then contact Realex Payments. No decimal points are allowed.
     * Amount should be set to 0 for OTB transactions (i.e. where validate card only is set to 1).
     */
    @SerializedName(AMOUNT)
    protected String amount;

    /**
     * A three-letter currency code (Eg. EUR, GBP). A list of currency codes can be provided by your account manager.
     */
    @SerializedName(CURRENCY)
    protected String currency;

    /**
     * Date and time of the transaction. Entered in the following format: YYYYMMDDHHMMSS. Must be within 24 hours of the current time.
     */
    @SerializedName(TIMESTAMP)
    protected String timestamp;

    /**
     * Used to signify whether or not you wish the transaction to be captured in the next batch.
     * If set to "1" and assuming the transaction is authorised then it will automatically be settled in the next batch.
     * If set to "0" then the merchant must use the RealControl application to manually settle the transaction.
     * This option can be used if a merchant wishes to delay the payment until after the goods have been shipped.
     * Transactions can be settled for up to 115% of the original amount and must be settled within a certain period of time agreed with your issuing bank.
     */
    @SerializedName(AUTO_SETTLE_FLAG)
    protected String autoSettleFlag;

    /**
     * A freeform comment to describe the transaction.
     */
    @SerializedName(COMMENT1)
    protected String commentOne;

    /**
     * A freeform comment to describe the transaction.
     */
    @SerializedName(COMMENT2)
    protected String commentTwo;

    /**
     * Used to signify whether or not you want a Transaction Suitability Score for this transaction.
     * Can be "0" for no and "1" for yes.
     */
    @SerializedName(RETURN_TSS)
    protected String returnTss;

    /**
     * The postcode or ZIP of the shipping address.
     */
    @SerializedName(SHIPPING_CODE)
    protected String shippingCode;

    /**
     * The country of the shipping address.
     */
    @SerializedName(SHIPPING_CO)
    protected String shippingCountry;

    /**
     * The postcode or ZIP of the billing address.
     */
    @SerializedName(BILLING_CODE)
    protected String billingCode;

    /**
     * The country of the billing address.
     */
    @SerializedName(BILLING_CO)
    protected String billingCountry;

    /**
     * The customer number of the customer. You can send in any additional information about the transaction in this field,
     * which will be visible under the transaction in the RealControl application.
     */
    @SerializedName(CUST_NUM)
    protected String customerNumber;

    /**
     * A variable reference also associated with this customer. You can send in any additional information about the transaction in this field,
     * which will be visible under the transaction in the RealControl application.
     */
    @SerializedName(VAR_REF)
    protected String variableReference;

    /**
     * A product id associated with this product. You can send in any additional information about the transaction in this field,
     * which will be visible under the transaction in the RealControl application.
     */
    @SerializedName(PROD_ID)
    protected String productId;

    /**
     * Used to set what language HPP is displayed in. Currently HPP is available in English, Spanish and German, with other languages to follow.
     * If the field is not sent in, the default language is the language that is set in your account configuration. This can be set by your account manager.
     */
    @SerializedName(HPP_LANG)
    protected String language;

    /**
     * Used to set what text is displayed on the payment button for card transactions. If this field is not sent in, "Pay Now" is displayed on the button by default.
     */
    @SerializedName(CARD_PAYMENT_BUTTON)
    protected String cardPaymentButtonText;

    /**
     * Enable card storage.
     */
    @SerializedName(CARD_STORAGE_ENABLE)
    protected String cardStorageEnable;

    /**
     * Offer to save the card.
     */
    @SerializedName(OFFER_SAVE_CARD)
    protected String offerSaveCard;

    /**
     * The payer reference.
     */
    @SerializedName(PAYER_REF)
    protected String payerReference;

    /**
     * The payment reference.
     */
    @SerializedName(PMT_REF)
    protected String paymentReference;

    /**
     * Flag to indicate if the payer exists.
     */
    @SerializedName(PAYER_EXIST)
    protected String payerExists;

    /**
     * Used to identify an OTB transaction.
     */
    @SerializedName(VALIDATE_CARD_ONLY)
    protected String validateCardOnly;

    /**
     * Transaction level configuration to enable/disable a DCC request. (Only if the merchant is configured).
     */
    @SerializedName(DCC_ENABLE)
    protected String dccEnable;

    /**
     * A digital signature generated using the SHA-1 algorithm.
     */
    @SerializedName(SHA1_HASH)
    protected String sha1Hash;

    @SerializedName(HPP_TEMPLATE_TYPE)
    protected String templateType;

    @SerializedName(HPP_ORIGIN)
    protected String origin;

    @SerializedName(HPP_VERSION)
    protected String hppVersion;

    @SerializedName(HPP_POST_RESPONSE)
    protected String hppPostResponse;

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
        parameters.put(HPP_VERSION, hppVersion);
        parameters.put(HPP_POST_RESPONSE, hppPostResponse);

        return parameters;
    }
}
