package com.realexpayments.remote;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/*
 * Unit tests for validateCardNumber
 */

public class ValidateCardNumberTest  {

    @Test
    public void testValidCard() {
        assertTrue(RealexRemote.validateCardNumber("424242424242424242"));
    }

    @Test
    public void testNonNumericCard() {
        assertFalse(RealexRemote.validateCardNumber("a24242424242424242"));
    }

    @Test
    public void testCardWithSpaces() {
        assertFalse(RealexRemote.validateCardNumber("4242 424242424242"));
    }

    @Test
    public void testEmptyCard() {
        assertFalse(RealexRemote.validateCardNumber(""));
    }

    @Test
    public void testUndefinedCard() {
        assertFalse(RealexRemote.validateCardNumber(null));
    }

    @Test
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateCardNumber("  "));
    }

    @Test
    public void testLengthLt12() {
        assertFalse(RealexRemote.validateCardNumber("42424242420"));
    }

    @Test
    public void testLengthGt19() {
        assertFalse(RealexRemote.validateCardNumber("42424242424242424242"));
    }

    @Test
    public void testLengthEq12()

    {
        assertTrue(RealexRemote.validateCardNumber("424242424242"));
    }

    @Test
    public void testLengthEq19() {
        assertTrue(RealexRemote.validateCardNumber("4242424242424242428"));
    }

    @Test
    public void testLuhnCheck() {
        assertFalse(RealexRemote.validateCardNumber("4242424242424242427"));
    }

}