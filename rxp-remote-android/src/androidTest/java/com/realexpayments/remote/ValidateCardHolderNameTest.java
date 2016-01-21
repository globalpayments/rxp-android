package com.realexpayments.remote;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/*
* Unit tests for validateCardHolderName
* card holder name validation (validateCardHolderName)
*/

public class ValidateCardHolderNameTest extends ApplicationTestCase<Application> {

    public ValidateCardHolderNameTest() {
        super(Application.class);
    }

    @SmallTest
    public void testValidName() {
        assertTrue(RealexRemote.validateCardHolderName("Joe Smith"));
    }

    @SmallTest
    public void testEmptyName() {
        assertFalse(RealexRemote.validateCardHolderName(""));
    }

    @SmallTest
    public void testUndefinedName() {
        assertFalse(RealexRemote.validateCardHolderName(null));
    }

    @SmallTest
    public void testWhiteSpaceOnly() {
        assertFalse(RealexRemote.validateCardHolderName("  "));
    }

    @SmallTest
    public void testNameOf100Characters() {
        assertTrue(RealexRemote.validateCardHolderName("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij"));
    }

    @SmallTest
    public void testNameOver100Characters() {
        assertFalse(RealexRemote.validateCardHolderName("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghija"));
    }

    @SmallTest
    public void testISO_IEC8859_1Characters1() {
        assertTrue(RealexRemote.validateCardHolderName("!\" # $ % & \' ( ) * +  - . / 0 1 2 3 4 5 6 7 8 9 : ; < = > ? @ A B C D E F G H I J K L M N O P Q R"));
    }

    @SmallTest
    public void testISO_IEC8859_1Characters2() {
        assertTrue(RealexRemote.validateCardHolderName("S T U V W X Y Z [ \\ ] ^ _ ` a b c d e f g h i j k l m n o p q r s t u v w x y z { | } ~ ¡ ¢ £ ¤ ¥"));
    }

    @SmallTest
    public void testISO_IEC8859_1Characters3() {
        assertTrue(RealexRemote.validateCardHolderName("¦ § ¨ © ª « ¬ ­ ® ¯ ° ± ² ³ ´ µ ¶ · ¸ ¹ º » ¼ ½ ¾ ¿ À Á Â Ã Ä Å Æ Ç È É Ê Ë Ì Í Î Ï Ð Ñ Ò Ó Ô Õ Ö"));
    }

    @SmallTest
    public void testISO_IEC8859_1Characters4() {
        assertTrue(RealexRemote.validateCardHolderName("× Ø Ù Ú Û Ü Ý Þ ß à á â ã ä å æ ç è é ê ë ì í î ï ð ñ ò ó ô õ ö ÷ ø ù ú û ü ý þ ÿ"));
    }

    @SmallTest
    public void testNonISO_IEC8859_1Characters() {
        assertFalse(RealexRemote.validateCardHolderName("€"));
    }

}