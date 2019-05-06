package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void checkSelenium() throws Exception {
        // Precondition
        System.out.println("PATH to WebDriver + " + this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        Thread.sleep(1000); // For Presentation Only
        //
        // Steps
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("selenium home page" + Keys.ENTER);
        Thread.sleep(1000); // For Presentation Only
        //
        driver.findElement(By.partialLinkText("Selenium - Web Browser Automation")).click();
        Thread.sleep(1000); // For Presentation Only
        //
        driver.findElement(By.linkText("Download")).click();
        Thread.sleep(1000); // For Presentation Only
        //
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("a[href*='bit.ly/2TlkRyu']"));
        //
        String actual = seleniumServerVersion.getText();
        Thread.sleep(1000); // For Presentation Only
        //
        // Check
        Assert.assertTrue(actual.contains("141.59"));
        Thread.sleep(1000); // For Presentation Only
        //
        // Return to Previous State
        //
        driver.quit();
    }

}
