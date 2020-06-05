package com.realexpayments.remote;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/*
 * Unit tests for validateCvn
 * CVN non-Amex validation (validateCvn)
 */

public class ValidateCvnTest {

    @Test
    public void testValidNonAmexCVN() {
        assertTrue(RealexRemote.validateCvn("123"));
    }

    @Test
    public void testEmptyCVN() {
        assertFalse(RealexRemote.validateCvn(""));
    }

    @Test
    public void testUndefinedCVN() {
        assertFalse(RealexRemote.validateCvn(null));
    }

    @Test
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateCvn("   "));
    }

    @Test
    public void testNonAmexCVNof4Numbers() {
        assertFalse(RealexRemote.validateCvn("1234"));
    }

    @Test
    public void testNonAmexCVNof2Numbers() {
        assertFalse(RealexRemote.validateCvn("12"));
    }

    @Test
    public void testNonNumericNonAmexCVNof3Characters() {
        assertFalse(RealexRemote.validateCvn("12a"));
    }

}