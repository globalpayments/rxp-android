package com.realexpayments.remote;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;


/*
 * Unit tests for validateCardNumber
 */

public class ValidateCardNumberTest extends ApplicationTestCase<Application> {

    public ValidateCardNumberTest() {
        super(Application.class);
    }

    @SmallTest
    public void testValidCard() {
        assertTrue(RealexRemote.validateCardNumber("424242424242424242"));
    }

    @SmallTest
    public void testNonNumericCard() {
        assertFalse(RealexRemote.validateCardNumber("a24242424242424242"));
    }

    @SmallTest
    public void testCardWithSpaces() {
        assertFalse(RealexRemote.validateCardNumber("4242 424242424242"));
    }

    @SmallTest
    public void testEmptyCard() {
        assertFalse(RealexRemote.validateCardNumber(""));
    }

    @SmallTest
    public void testUndefinedCard() {
        assertFalse(RealexRemote.validateCardNumber(null));
    }

    @SmallTest
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateCardNumber("  "));
    }

    @SmallTest
    public void testLengthLt12() {
        assertFalse(RealexRemote.validateCardNumber("42424242420"));
    }

    @SmallTest
    public void testLengthGt19() {
        assertFalse(RealexRemote.validateCardNumber("42424242424242424242"));
    }

    @SmallTest
    public void testLengthEq12()

    {
        assertTrue(RealexRemote.validateCardNumber("424242424242"));
    }

    @SmallTest
    public void testLengthEq19() {
        assertTrue(RealexRemote.validateCardNumber("4242424242424242428"));
    }

    @SmallTest
    public void testLuhnCheck() {
        assertFalse(RealexRemote.validateCardNumber("4242424242424242427"));
    }

}