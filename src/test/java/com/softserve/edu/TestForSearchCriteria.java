package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestForSearchCriteria {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() throws InterruptedException {
//        System.out.println("PATH to WebDriver + " + this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        System.setProperty("webdriver.chrome.driver",
//                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://192.168.36.130/opencart/upload/");
//        Thread.sleep(1000); // For Presentation Only
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://192.168.36.131/opencart/upload/");
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

    @DataProvider
    public Object[][] dataForKeywordsTest() {
        return new Object[][]{
                //EP
                //first argument is text we want to input in search field and second is way to the element we will use to check test and third - text we expect to see
                //negative testing
                //empty field
                {"", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //positive testing
                //full name of element we want to find in lower case
                {"iphone", ".//div[@class='caption']/h4/a[contains(@href,'192.168.36.131/opencart/upload/index.php?route=product/product&product_id=40&search=iphone')]", "iPhone"},
                //full name of element we want to find in
                {"iPhone", ".//div[@class='caption']/h4/a[contains(@href,'192.168.36.131/opencart/upload/index.php?route=product/product&product_id=40&search=iPhone')]", "iPhone"},
                //full name of element we want to find in upper case
                {"IPHONE", ".//div[@class='caption']/h4/a[contains(@href,'192.168.36.131/opencart/upload/index.php?route=product/product&product_id=40&search=IPHONE')]", "iPhone"},
        };


    }


    @Test(dataProvider = "dataForKeywordsTest")
    public void keywordsTest(String inputValue, String wayToValueWeExpectToSee, String expectedResult) {

        driver.findElement(By.id("input-search")).sendKeys(inputValue + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(wayToValueWeExpectToSee));
        String actual = webElement.getText();
        Assert.assertEquals(actual, expectedResult);
    }


    @DataProvider
    public Object[][] dataForTestingSearchFieldResultIsArray() {
        return new Object[][]{
                //positive testing
                //word, what we are looking for, begin with this characters
                {"ip"},
                //word, what we are looking for, includes with this characters
                {"po"},
                //word, what we are looking for, end with this characters
                {"n"},
                //word, what we are looking for, begin with this characters and before this we have too many spaces
                {"    ip"},
                //word, what we are looking for, end with this characters and before this we have too many spaces
                {"n     "}
        };

    }

    @Test(dataProvider = "dataForTestingSearchFieldResultIsArray")
    public void chekSearchFieldResultArrayOfElement(String whatWeAreLookingFor) {
        driver.findElement(By.id("input-search")).sendKeys(whatWeAreLookingFor + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        System.out.println(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            Assert.assertTrue(webElements.get(i).getText().toLowerCase().contains(whatWeAreLookingFor.trim().toLowerCase()));
        }
        ;
    }

    //positive testing
    //word, what we are looking for, includes with this characters and before this we have too many spaces
    @Test
    public void chekSearchFieldResultArrayOfElementSpaceBetweenCharacters() {
        driver.findElement(By.id("input-search")).sendKeys("ip   h o n     e" + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        System.out.println(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            Assert.assertTrue(webElements.get(i).getText().contains("iPhone"));
        }
    }

    //check button
    @DataProvider
    public Object[][] dataForCheckButtonTest() {
        return new Object[][]{
                //negative testing
                //empty field
                {"", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //positive testing
                //full name of element we want to find in lower case
                {"iphone", ".//div[@class='caption']/h4/a[contains(@href,'192.168.36.131/opencart/upload/index.php?route=product/product&product_id=40&search=iphone')]", "iPhone"},
        };
    }

    @Test(dataProvider = "dataForCheckButtonTest")
    public void CheckButtonSearchTest(String valueForSearchField, String wayToWebElement, String expectedResult) {
        driver.findElement(By.id("input-search")).sendKeys(valueForSearchField);
        driver.findElement(By.id("button-search")).click();
        String actual = driver.findElement(By.xpath(wayToWebElement)).getText();
        Assert.assertEquals(actual, expectedResult);
    }


//    @Test
//    public void checkSearchInProductDescription() throws InterruptedException {
//        driver.findElement(By.id("input-search")).sendKeys("in");
//        driver.findElement(By.id("description")).click();
//        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
//        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
//        System.out.println(webElements.size());
//        for (int i = 0; i < webElements.size(); i++) {
//            System.out.println(webElements.get(i).getText());
//
//        }
//        for (int i = 0; i < webElements.size(); i++) {
//            System.out.println(i + "");
//            System.out.println(webElements.get(i));
//            webElements.get(i).click();
//            System.out.println("done 1");
//            String webElement = driver.findElement(By.xpath(".//div[@id='tab-description']/p")).getText();
//            System.out.println("done 2");
//            Assert.assertTrue(webElement.contains("in"));
//            System.out.println("yes");
//            driver.findElement(By.xpath(".//a[contains(@href,'192.168.36.130/opencart/upload/index.php?route=product/search&search=in&description=true')]")).click();
//
//
//        }
//    }
//    @Test
//    public void AllCategoriesTest(){
//        driver.findElement(By.xpath(".//option[@value='18']")).click();
//        driver.findElement(By.id("description")).click();
//        driver.findElement(By.id("input-search")).sendKeys("power"+Keys.ENTER);
//        driver.findElement(By.name("category_id")).click();
//
//
//    }


}
