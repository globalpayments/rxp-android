package com.realexpayments.remote;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/*
 * Expiry date format validation (validateExpiryDateFormat)
 */
public class ValidateExpiryDateFormatTest {

    @Test
    public void testValidDate1299() {
        assertTrue(RealexRemote.validateExpiryDateFormat("1299"));
    }

    @Test
    public void testValidDate0199() {
        assertTrue(RealexRemote.validateExpiryDateFormat("0199"));
    }

    @Test
    public void testNonNumericDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat("a199"));
    }

    @Test
    public void testDateWithSpaces() {
        assertFalse(RealexRemote.validateExpiryDateFormat("1 99"));
    }

    @Test
    public void testEmptyDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat(""));
    }

    @Test
    public void testUndefinedDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat(null));
    }

    @Test
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateExpiryDateFormat("    "));
    }

    @Test
    public void testLengthGt4() {
        assertFalse(RealexRemote.validateExpiryDateFormat("12099"));
    }

    @Test
    public void testLengthLt4() {
        assertFalse(RealexRemote.validateExpiryDateFormat("199"));
    }

    @Test
    public void testInvalidMonth00() {
        assertFalse(RealexRemote.validateExpiryDateFormat("0099"));
    }

    @Test
    public void testInvalidMonth13() {
        assertFalse(RealexRemote.validateExpiryDateFormat("1399"));
    }

}