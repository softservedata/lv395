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

public class ChooseCategoriesTest {

    //------------------------------------AllCategoriesTest-------------------------------------------------------------
    private WebDriver driver;
    final static private String ip = "192.168.36.134";

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

    @DataProvider
    public Object[][] dataForPositiveTestingCategory() {
        return new Object[][]{
                {"%", ".//option[@value='20']", true},
                {"iphone", ".//option[@value='20']",true},
                {"inch", ".//option[@value='18']", true},
                {"%", ".//option[@value='20']",false},
                {"iphone", ".//option[@value='20']",false},

        };
    }

    @Test(dataProvider = "dataForPositiveTestingCategory")
    public void positiveTestingCategoryUseSearchInProductDescription(String inputData, String category, Boolean useSearchInProductDescription) {
        if (useSearchInProductDescription == true) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("category_id")).click();


        driver.findElement(By.xpath(category)).click();
        System.out.println("ok");
        driver.findElement(By.id("input-search")).sendKeys(inputData + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<String> listOfWebEL = new ArrayList<>();
        for (int i = 1; i < webElements.size(); i++) {
            listOfWebEL.add(webElements.get(i).getText());
        }
        FindAllProductsAndTheirCategories productsAndTheirCategories = new FindAllProductsAndTheirCategories();
        List<Product> products = productsAndTheirCategories.findingAllProductsAndTheirCategories();
        String categoryName = driver.findElement(By.xpath(category)).getText();
        for (int i = 0; i < listOfWebEL.size(); i++) {
            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getName().equals(listOfWebEL.get(i))) {
                    Assert.assertTrue(products.get(j).getCategory().contains(categoryName));
                }
            }

        }


    }


    @DataProvider
    public Object[][] dataForNegativeTestingCategory() {
        return new Object[][]{
                //product description
                {"new", ".//option[@value='20']",false},
                //empty field
                {"", ".//option[@value='20']",false},
                //incorrect data
                {"hdsgk897?<>", ".//option[@value='20']",false},
                //correct data, but product with this category and name do not exist
                {"iPhone", ".//option[@value='26']",false},
                // all products, but there is no products with this category
                {"%", ".//option[@value='26']",false},
                //empty field
                {"", ".//option[@value='20']",true},
                //incorrect data
                {"hdsgk897?<>", ".//option[@value='20']",true},
                //correct data, but product with this category and name do not exist
                {"iPhone", ".//option[@value='26']",true},
                // all products, but there is no products with this category
                {"%", ".//option[@value='26']",true},
                //product description
                {"new", ".//option[@value='26']",true},

        };
    }

    @Test(dataProvider = "dataForNegativeTestingCategory")
    public void negativeTestingCategoryTestDoNotUseSearchInProductDescription(String inputData, String category,Boolean useSearchInProductDescription) {
        if (useSearchInProductDescription == true) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.id("input-search")).sendKeys(inputData + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual, "There is no product that matches the search criteria.");


    }

}
