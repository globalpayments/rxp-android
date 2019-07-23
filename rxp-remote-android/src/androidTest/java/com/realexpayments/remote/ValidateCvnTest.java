package com.realexpayments.remote;

import androidx.test.filters.SmallTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/*
 * Unit tests for validateCvn
 * CVN non-Amex validation (validateCvn)
 */

public class ValidateCvnTest {

    @SmallTest
    public void testValidNonAmexCVN() {
        assertTrue(RealexRemote.validateCvn("123"));
    }

    @SmallTest
    public void testEmptyCVN() {
        assertFalse(RealexRemote.validateCvn(""));
    }

    @SmallTest
    public void testUndefinedCVN() {
        assertFalse(RealexRemote.validateCvn(null));
    }

    @SmallTest
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateCvn("   "));
    }

    @SmallTest
    public void testNonAmexCVNof4Numbers() {
        assertFalse(RealexRemote.validateCvn("1234"));
    }

    @SmallTest
    public void testNonAmexCVNof2Numbers() {
        assertFalse(RealexRemote.validateCvn("12"));
    }

    @SmallTest
    public void testNonNumericNonAmexCVNof3Characters() {
        assertFalse(RealexRemote.validateCvn("12a"));
    }

}