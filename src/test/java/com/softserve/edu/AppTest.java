package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    @Test
    public void testApp1() {
        System.out.println("testApp1() start");
        System.out.println("surefire.reports.directory = " + System.getProperty("surefire.reports.directory"));
        System.out.println("selenium.version = " + System.getProperty("selenium.version"));
        System.out.println("System.getenv().database.password = " + System.getenv().get("DATABASE_PASSWORD"));
        System.out.println("is_continues_integration = " + System.getenv().get("IS_CONTINUES_INTEGRATION"));
        Assert.assertTrue(true);

    }
    
}
