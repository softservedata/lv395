package com.softserve.edu;

import com.beust.jcommander.Parameter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @DataProvider
    public Object[][] credentials() {
        return new Object[][]{
                {"correctEmail", "incorrectPassword"}, // correct email | incorrect --> Error Message
                {"incorrectEmail", "correctPassword"}, // incorrect email | correct --> Error Message

        };
    }

    @Test(dataProvider = "credentials")
    public void testApp1(String arg1, String arg2) {
        System.out.println(this.getClass().getClass().getName() + " class start, with parameters : " + arg1 + " and " + arg2);
    }

    @Test
    @Parameters({"arg0", "arg1"})
    public void testApp2(String arg0, String arg2) {
        System.out.println(this.getClass().getName() + " class start, with parameters : " + arg0 + " and " + arg2);


    }

    @Test
    @Parameters("arg3")
    public void testApp3(String arg2) {
        System.out.println(this.getClass().getName() + " class start, with parameter : " + arg2);
    }
    
}
