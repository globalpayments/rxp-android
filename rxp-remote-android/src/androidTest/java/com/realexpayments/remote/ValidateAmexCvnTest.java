package com.realexpayments.remote;


import androidx.test.filters.SmallTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidateAmexCvnTest {

    @SmallTest
    public void testValidAmexCVN() {
        assertTrue(RealexRemote.validateAmexCvn("1234"));
    }

    @SmallTest
    public void testEmptyCVN() {
        assertFalse(RealexRemote.validateAmexCvn(""));
    }

    @SmallTest
    public void testUndefinedCVN() {
        assertFalse(RealexRemote.validateAmexCvn(null));
    }

    @SmallTest
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateAmexCvn("   "));
    }

    @SmallTest
    public void testAmexCVNof5Numbers() {
        assertFalse(RealexRemote.validateAmexCvn("12345"));
    }

    @SmallTest
    public void testAmexCVNof3Numbers() {
        assertFalse(RealexRemote.validateAmexCvn("123"));
    }

    @SmallTest
    public void testNonNumericAmexCVNof4Characters() {
        assertFalse(RealexRemote.validateAmexCvn("123a"));
    }
}