package com.realexpayments.hpp;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

import static com.realexpayments.hpp.HPPResponse.ACCOUNT;
import static com.realexpayments.hpp.HPPResponse.AMOUNT;
import static com.realexpayments.hpp.HPPResponse.AUTO_SETTLE_FLAG;
import static com.realexpayments.hpp.HPPResponse.BILLING_CO;
import static com.realexpayments.hpp.HPPResponse.BILLING_CODE;
import static com.realexpayments.hpp.HPPResponse.CARD_PAYMENT_BUTTON;
import static com.realexpayments.hpp.HPPResponse.CARD_STORAGE_ENABLE;
import static com.realexpayments.hpp.HPPResponse.COMMENT1;
import static com.realexpayments.hpp.HPPResponse.COMMENT2;
import static com.realexpayments.hpp.HPPResponse.CURRENCY;
import static com.realexpayments.hpp.HPPResponse.CUST_NUM;
import static com.realexpayments.hpp.HPPResponse.DCC_ENABLE;
import static com.realexpayments.hpp.HPPResponse.HPP_LANG;
import static com.realexpayments.hpp.HPPResponse.HPP_ORIGIN;
import static com.realexpayments.hpp.HPPResponse.HPP_POST_RESPONSE;
import static com.realexpayments.hpp.HPPResponse.HPP_TEMPLATE_TYPE;
import static com.realexpayments.hpp.HPPResponse.HPP_VERSION;
import static com.realexpayments.hpp.HPPResponse.MERCHANT_ID;
import static com.realexpayments.hpp.HPPResponse.OFFER_SAVE_CARD;
import static com.realexpayments.hpp.HPPResponse.ORDER_ID;
import static com.realexpayments.hpp.HPPResponse.PAYER_EXIST;
import static com.realexpayments.hpp.HPPResponse.PAYER_REF;
import static com.realexpayments.hpp.HPPResponse.PMT_REF;
import static com.realexpayments.hpp.HPPResponse.PROD_ID;
import static com.realexpayments.hpp.HPPResponse.RETURN_TSS;
import static com.realexpayments.hpp.HPPResponse.SHA1_HASH;
import static com.realexpayments.hpp.HPPResponse.SHIPPING_CO;
import static com.realexpayments.hpp.HPPResponse.SHIPPING_CODE;
import static com.realexpayments.hpp.HPPResponse.TIMESTAMP;
import static com.realexpayments.hpp.HPPResponse.VALIDATE_CARD_ONLY;
import static com.realexpayments.hpp.HPPResponse.VAR_REF;
import static junit.framework.TestCase.assertEquals;

public class HPPManagerTest {

    @Test
    public void testGetDataMap() {
        HPPManager hppManager = new HPPManager();
        hppManager.setSupplementaryData("SupplementaryDataKey", "SupplementaryDataValue");
        hppManager.setMerchantId("merchantId");
        hppManager.setAccount("account");
        hppManager.setOrderId("orderId");
        hppManager.setAmount("amount");
        hppManager.setCurrency("currency");
        hppManager.setTimeStamp("timestamp");
        hppManager.setAutoSettleFlag("autoSettleFlag");
        hppManager.setCommentOne("commentOne");
        hppManager.setCommentTwo("commentTwo");
        hppManager.setReturnTss("returnTss");
        hppManager.setShippingCode("shippingCode");
        hppManager.setShippingCountry("shippingCountry");
        hppManager.setBillingCode("billingCode");
        hppManager.setBillingCountry("billingCountry");
        hppManager.setCustomerNumber("customerNumber");
        hppManager.setVariableReference("variableReference");
        hppManager.setProductId("productId");
        hppManager.setLanguage("language");
        hppManager.setCardPaymentButtonText("cardPaymentButtonText");
        hppManager.setCardStorageEnable("cardStorageEnable");
        hppManager.setOfferSaveCard("offerSaveCard");
        hppManager.setPayerReference("payerReference");
        hppManager.setPaymentReference("paymentReference");
        hppManager.setPayerExists("payerExists");
        hppManager.setValidateCardOnly("validateCardOnly");
        hppManager.setDccEnable("dccEnable");
        hppManager.setHash("sha1Hash");
        hppManager.setTemplateType("templateType");
        hppManager.setOrigin("origin");
        setValue(HPPManager.class, "hppVersion", hppManager, "hppVersion");
        setValue(HPPManager.class, "hppPostResponse", hppManager, "hppPostResponse");

        HashMap<String, String> actualResult = hppManager.getMap();

        HashMap<String, String> expectedResult = new HashMap<>();
        expectedResult.put(MERCHANT_ID, "merchantId");
        expectedResult.put(ACCOUNT, "account");
        expectedResult.put(ORDER_ID, "orderId");
        expectedResult.put(AMOUNT, "amount");
        expectedResult.put(CURRENCY, "currency");
        expectedResult.put(TIMESTAMP, "timestamp");
        expectedResult.put(AUTO_SETTLE_FLAG, "autoSettleFlag");
        expectedResult.put(COMMENT1, "commentOne");
        expectedResult.put(COMMENT2, "commentTwo");
        expectedResult.put(RETURN_TSS, "returnTss");
        expectedResult.put(SHIPPING_CODE, "shippingCode");
        expectedResult.put(SHIPPING_CO, "shippingCountry");
        expectedResult.put(BILLING_CODE, "billingCode");
        expectedResult.put(BILLING_CO, "billingCountry");
        expectedResult.put(CUST_NUM, "customerNumber");
        expectedResult.put(VAR_REF, "variableReference");
        expectedResult.put(PROD_ID, "productId");
        expectedResult.put(HPP_LANG, "language");
        expectedResult.put(CARD_PAYMENT_BUTTON, "cardPaymentButtonText");
        expectedResult.put(CARD_STORAGE_ENABLE, "cardStorageEnable");
        expectedResult.put(OFFER_SAVE_CARD, "offerSaveCard");
        expectedResult.put(PAYER_REF, "payerReference");
        expectedResult.put(PMT_REF, "paymentReference");
        expectedResult.put(PAYER_EXIST, "payerExists");
        expectedResult.put(VALIDATE_CARD_ONLY, "validateCardOnly");
        expectedResult.put(DCC_ENABLE, "dccEnable");
        expectedResult.put(SHA1_HASH, "sha1Hash");
        expectedResult.put(HPP_TEMPLATE_TYPE, "templateType");
        expectedResult.put(HPP_ORIGIN, "origin");
        expectedResult.put(HPP_VERSION, "hppVersion");
        expectedResult.put(HPP_POST_RESPONSE, "hppPostResponse");
        expectedResult.put("SupplementaryDataKey", "SupplementaryDataValue");

        assertEquals(expectedResult, actualResult);
    }

    private void setValue(Class clazz, String fieldName, Object obj, Object value) {
        try {
            Field field = Objects.requireNonNull(clazz.getSuperclass()).getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (NoSuchFieldException | IllegalAccessException var3) {
            //ignore
        }
    }
}
