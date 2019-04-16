package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SequenceTest {
    
    @DataProvider // (parallel = true)
    public Object[][] userData() {
        System.out.println("userData() Done");
        return new Object[][] {
            //{ "name", "password1", "password2" },
            {}
        };
    }
    
    @BeforeMethod
    @Parameters(value = "number")
    public void beforeMethod(int number) {
        System.out.println("@BeforeMethod FirstTest");
        System.out.println("Parameterized Number is: " + number);
    }
    
    @Test
    public void workApp() {
        System.out.println("workApp() start");
        Assert.assertTrue(true);
    }
    
    @Test(dataProvider = "userData")
    //@Parameters(value = "number")
    //public void parameterIntTest(String name, String password1, String password2) {
    public void parameterIntTest() {
        //System.out.println("name: " + name);
        //System.out.println("password1: " + password1);
        //System.out.println("password2: " + password2);
        System.out.println("parameterIntTest() DONE");
    }
    
}