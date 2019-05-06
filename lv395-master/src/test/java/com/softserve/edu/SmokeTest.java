package com.softserve.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest {

    @Test
    public void checkSearch() throws Exception {
        // Precondition
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://10.26.34.95/opencart/upload/");
        Thread.sleep(1000); // For Presentation Only
        //
        // Steps
        // Type Search Text
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("mac");
        Thread.sleep(1000); // For Presentation Only
        //
        // Click Button
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        Thread.sleep(1000); // For Presentation Only
        //
        // Check
        String actual = driver.findElement(By.xpath("//a[text()='MacBook']/../following-sibling::p[@class='price']")).getText();
        Assert.assertTrue(actual.contains("602.00"));
        Thread.sleep(4000); // For Presentation Only
        //
        // Return to Previous State
        driver.quit();
    }
}
