package com.realexpayments.remote;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ValidateExpiryDateNotInPastTest extends ApplicationTestCase<Application> {

    public ValidateExpiryDateNotInPastTest() {
        super(Application.class);
    }

    @SmallTest
    public void testDateInPast() {
        assertFalse(RealexRemote.validateExpiryDateNotInPast("0615"));
    }

    @SmallTest
    public void testCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MMyy");
        assertTrue(RealexRemote.validateExpiryDateNotInPast(format.format(calendar.getTime())));
    }

}