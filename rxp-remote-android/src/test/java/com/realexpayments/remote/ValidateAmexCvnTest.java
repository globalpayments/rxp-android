package com.realexpayments.remote;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateAmexCvnTest {

    @Test
    public void testValidAmexCVN() {
        assertTrue(RealexRemote.validateAmexCvn("1234"));
    }

    @Test
    public void testEmptyCVN() {
        assertFalse(RealexRemote.validateAmexCvn(""));
    }

    @Test
    public void testUndefinedCVN() {
        assertFalse(RealexRemote.validateAmexCvn(null));
    }

    @Test
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateAmexCvn("   "));
    }

    @Test
    public void testAmexCVNof5Numbers() {
        assertFalse(RealexRemote.validateAmexCvn("12345"));
    }

    @Test
    public void testAmexCVNof3Numbers() {
        assertFalse(RealexRemote.validateAmexCvn("123"));
    }

    @Test
    public void testNonNumericAmexCVNof4Characters() {
        assertFalse(RealexRemote.validateAmexCvn("123a"));
    }
}