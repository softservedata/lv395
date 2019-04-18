package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchInProductDescription {
    private WebDriver driver;
    final private String ip = "192.168.36.134";

    @BeforeClass
    public void beforeClass() throws InterruptedException {
//        System.out.println("res");
//        System.out.println(this.getClass().getResource("/chromedriver-windows-32bit.exe"));
//        System.out.println("PATH to WebDriver + " + this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        System.setProperty("webdriver.chrome.driver",
//                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://192.168.36.129/opencart/upload/");
//        Thread.sleep(1000); // For Presentation Only
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://" + ip + "/opencart/upload/");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("" + Keys.ENTER);
        driver.findElement(By.id("input-search")).click();
        driver.findElement(By.id("input-search")).clear();
    }

    @AfterMethod
    public void afterMethod() {

    }


    //---------------------------------------------CheckSearchInProductDescription--------------------------------------
    @DataProvider
    public Object[][] dataForCheckSearchInProductDescription() {

        return new Object[][]{
                //EP
                //positive testing
                {"GB"},
                {"1"},
                {"The 30-inch Apple Cinema HD Display delivers an amazing 2560 x 1600 pixel resolution."},
                {"u"}
        };
    }

    //positive testing
    @Test(dataProvider = "dataForCheckSearchInProductDescription")
    public void checkSearchInProductDescription(String inputInTheSearchField) {
        String wayToPageWithInform="http://"+ip+"/opencart/upload/index.php?route=product/search&search="+inputInTheSearchField+"&description=true";
        driver.findElement(By.id("input-search")).sendKeys(inputInTheSearchField);
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        System.out.println(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            List<WebElement> webElements1 = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
            System.out.println(webElements1.get(i).getText());
            webElements1.get(i).click();
            String webElementI = driver.findElement(By.xpath(".//div[@id='tab-description']")).getText();
            Assert.assertTrue(webElementI.contains(inputInTheSearchField));
            driver.get(wayToPageWithInform);

        }
    }

    @DataProvider
    public Object[][] negativeDataForCheckSearchInProductDescription() {
        return new Object[][]{
                //EP
                //first argument is text we want to input in search field and second is way to the element we will use to check test and third - text we expect to see
                //negative testing
                //empty field

                {""},
                //incorrect data
                {"iphane"},
                //incorrect data
                {"<<?><?$%^%&&*("}
        };
    }

    //negative testing
    @Test(dataProvider = "negativeDataForCheckSearchInProductDescription")
    public void negativeCheckSearchInProductDescription(String inputValue) {
        driver.findElement(By.id("input-search")).sendKeys(inputValue);
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual, "There is no product that matches the search criteria.");
    }




}
