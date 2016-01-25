package com.realexpayments.remote;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/*
 * Expiry date format validation (validateExpiryDateFormat)
 */
public class ValidateExpiryDateFormatTest extends ApplicationTestCase<Application> {

    public ValidateExpiryDateFormatTest() {
        super(Application.class);
    }

    @SmallTest
    public void testValidDate1299() {
        assertTrue(RealexRemote.validateExpiryDateFormat("1299"));
    }

    @SmallTest
    public void testValidDate0199() {
        assertTrue(RealexRemote.validateExpiryDateFormat("0199"));
    }

    @SmallTest
    public void testNonNumericDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat("a199"));
    }

    @SmallTest
    public void testDateWithSpaces() {
        assertFalse(RealexRemote.validateExpiryDateFormat("1 99"));
    }

    @SmallTest
    public void testEmptyDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat(""));
    }

    @SmallTest
    public void testUndefinedDate() {
        assertFalse(RealexRemote.validateExpiryDateFormat(null));
    }

    @SmallTest
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateExpiryDateFormat("    "));
    }

    @SmallTest
    public void testLengthGt4() {
        assertFalse(RealexRemote.validateExpiryDateFormat("12099"));
    }

    @SmallTest
    public void testLengthLt4() {
        assertFalse(RealexRemote.validateExpiryDateFormat("199"));
    }

    @SmallTest
    public void testInvalidMonth00() {
        assertFalse(RealexRemote.validateExpiryDateFormat("0099"));
    }

    @SmallTest
    public void testInvalidMonth13() {
        assertFalse(RealexRemote.validateExpiryDateFormat("1399"));
    }

}