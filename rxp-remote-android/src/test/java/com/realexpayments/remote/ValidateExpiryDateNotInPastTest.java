package com.realexpayments.remote;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ValidateExpiryDateNotInPastTest  {

    @Test
    public void testDateInPast() {
        assertFalse(RealexRemote.validateExpiryDateNotInPast("0615"));
    }

    @Test
    public void testCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MMyy");
        assertTrue(RealexRemote.validateExpiryDateNotInPast(format.format(calendar.getTime())));
    }

}