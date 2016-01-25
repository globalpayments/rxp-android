package com.realexpayments.remote;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class ValidateAmexCvnTest extends ApplicationTestCase<Application> {

    public ValidateAmexCvnTest() {
        super(Application.class);
    }

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