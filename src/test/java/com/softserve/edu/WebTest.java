package com.softserve.edu;
import org.testng.Assert;
import java.lang.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebTest {

    public ChromeDriver driver;
    public int quanityValue;
    public double total;
    public double unitPrice;
    String editField = "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    public String selectorOfDeleteButton = "#content > h2";// "#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > span > button.btn.btn-danger";

    public String removeDollar(String s){
        s = s.replace(",", "");
        return s.substring(1);
    }
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @BeforeClass
    public void beforeClass(){
        // Precondition
        System.setProperty("webdriver.chrome.driver",
                "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //**************************** A D D ********************************************************************
        driver.get("http://192.168.234.129/opencart/upload/index.php?");
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#content > div.row > div:nth-child(4) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector("#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#input-quantity")).click();
        driver.findElement(By.cssSelector("#input-quantity")).sendKeys(Keys.BACK_SPACE + "2" + Keys.ENTER);
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("a[href*='http://192.168.234.129/opencart/upload/index.php?route=common/home']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Phones & PDAs')]")).click();
        driver.findElement(By.linkText("Palm Treo Pro")).click();
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("input-quantity")).sendKeys(Keys.BACK_SPACE + "3");
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("button-cart")).click();
    }

    // div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(2)
    //*[@id="content"]/form/div/table/tbody/tr*/td
    //*[@id="content"]/form/div/table/tbody/tr[1]/td[3]
    ////*[@id="content"]/form/div/table/tbody/tr[1]/td[4]/div/input
    @Test
    public void checkRefreshButtonOnShoppingCartTest() throws Exception {
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        quanityValue = Integer.parseInt(driver.findElement(By.cssSelector(editField)).getAttribute("value"));
        total = Double.parseDouble(removeDollar(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-of-type(1) > td:nth-of-type(6)")).getText()));
        unitPrice = Double.parseDouble(removeDollar(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(5)")).getText()));
        double expectedTotal = total + unitPrice + unitPrice;
        driver.findElement(By.cssSelector(editField)).clear();
        String newQuanityValue = String.valueOf(quanityValue + 2);
        driver.findElement(By.cssSelector(editField)).sendKeys(Keys.BACK_SPACE + newQuanityValue);
        driver.findElement(By.cssSelector("div[class*='table-responsive'] > table > tbody > tr:nth-of-type(1) > td:nth-of-type(4) > div > span > button:nth-of-type(1)")).click();
        double actualTotal = Double.parseDouble(removeDollar(driver.findElement(By.cssSelector("#content > form > div > table > tbody > tr:nth-child(1) > td:nth-child(6)")).getText()));
        System.out.println("delete: " + driver.findElement(By.cssSelector("#content > h1")).getText());
        Assert.assertEquals(actualTotal, expectedTotal);

    }

    @AfterClass
    public void afterClass() throws Exception{
        // Precondition
        System.out.println("AfterClass");
        driver.quit();
    }
}


//driver.findElement(By.cssSelector("a[href*='http://192.168.234.129/opencart/upload/index.php?route=common/home']")).click();
        /*
        driver.findElement(By.partialLinkText("Free Online Chess")).click();
        Thread.sleep(1000); // For Presentation Only
        //
        driver.findElement(By.linkText("Download")).click();
        Thread.sleep(1000); // For Presentation Only
        //
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("a[href*='bit.ly/2TlkRyu']"));
        //
        String actual = seleniumServerVersion.getText();
        Assert.assertTrue(actual.contains("141.59"));
        Thread.sleep(1000); // For Presentation Only
        //
        // Return to Previous State
        */
//******************* L O G I N ******************************************************************
        /*driver.get("http://192.168.234.128/opencart/upload/index.php?route=account/login");
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys("yuriykril7773@gmail.com");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys("qwerty" + Keys.ENTER);
        System.out.println("BeforeMethod");*/
//***************DELETE***

        /*while (driver.findElement(By.cssSelector(selectorOfDeleteButton)).isDisplayed())
        {
            System.out.println("driver.findElement(By.cssSelector(selectorOfDeleteButton)).isDisplayed()" + driver.findElement(By.cssSelector(selectorOfDeleteButton)).isDisplayed());
            driver.findElement(By.cssSelector(selectorOfDeleteButton)).click();
            Thread.sleep(2000);
        }*/