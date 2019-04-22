package com.softserve.edu;

import com.softserve.edu.tools.FindAllProductsAndTheirCategories;
import com.softserve.edu.tools.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class is created for
 * testing choose category option and for search in sub category check box
 * (here is used admin page).
 *
 * @author Iryna Ratushniak
 */
public class ChooseCategoriesTest {

    /**
     * Driver to have accesses to web page.
     */
    private WebDriver driver;
    /**
     * ip address (ip address could be changed).
     */
    final private String ip = "192.168.36.134";

    /**
     * Before class.
     * (action in this class should happens before all other action)
     */
    @BeforeClass
    public void beforeClass() {
        String webDriverPath = this.getClass().getResource("/").toString();
        webDriverPath = webDriverPath.substring(webDriverPath.indexOf("/"));
        System.setProperty("webdriver.chrome.driver",
                webDriverPath + "chromedriver-windows-32bit.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://" + ip + "/opencart/upload/");
    }

    /**
     * In the end for all action driver should be "quit".
     */
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    /**
     * Before all method next action should be done.
     */
    @BeforeMethod
    public void beforeMethod() {


        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("" + Keys.ENTER);
        driver.findElement(By.id("input-search")).click();
        driver.findElement(By.id("input-search")).clear();
    }

    /**
     * Here are data for positive testing category.
     * first parameter - data for search field;
     * second parameter - category, what will be chosen
     * third parameter - will be used search in product description or not.
     *
     * @return object with three parameters described above
     */
    @DataProvider
    public Object[][] dataForPositiveTestingCategory() {
        FindAllProductsAndTheirCategories productsAndTheirCategories
                = new FindAllProductsAndTheirCategories();
        List<Product> productsFromStorage = productsAndTheirCategories.
                findProductsAndTheirCategories();
        return new Object[][]{

                {"iphone", ".//option[@value='20']", true, productsFromStorage},
                {"inch", ".//option[@value='18']", true, productsFromStorage},
                {"%", ".//option[@value='20']", false, productsFromStorage},
                {"iphone", ".//option[@value='20']", false,
                        productsFromStorage},
                {"%", ".//option[@value='20']", true, productsFromStorage},

        };
    }

    /**
     * Positive testing category.
     *
     * @param inputData                     data for search field
     * @param category                      category, what will be chosen
     * @param useSearchInProductDescription -will be used search in product
     *                                      description or not
     * @param productsFromStorage           - products from storage
     */
    @Test(dataProvider = "dataForPositiveTestingCategory")
    public void chooseCategoriesPositiveTest(
            final String inputData, final String category,
            final boolean useSearchInProductDescription,
            final List<Product> productsFromStorage) {
        if (useSearchInProductDescription) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.id("input-search")).
                sendKeys(inputData + Keys.ENTER);

        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<String> productsFromWeb = new ArrayList<>();
        for (WebElement webElement : webElements) {
            productsFromWeb.add(webElement.getText());
        }

        System.out.println(productsFromStorage.toString());
        System.out.println(productsFromWeb.toString());
        String categoryName = driver.findElement(By.xpath(category)).getText();

        for (Product product : productsFromStorage) {
            if (productsFromWeb.contains(product.getName())) {
                Assert.assertTrue(product.getCategory().contains(categoryName));
                System.out.println(true);
            }
        }


    }

    /**
     * Here are data for negative testing category
     * first parameter - data for search field;
     * second parametr - category, what will be chosen
     * third parametr - will be used search in product description or not.
     *
     * @return object with three parameters described above
     */
    @DataProvider
    public Object[][] dataForNegativeTestingCategory() {
        return new Object[][]{
                //product description
                {"new", ".//option[@value='20']", false},
                //empty field
                {"", ".//option[@value='20']", false},
                //incorrect data
                {"hdsgk897?<>", ".//option[@value='20']", false},
                //correct data, but product with
                // this category and name do not exist
                {"iPhone", ".//option[@value='26']", false},
                // all products, but there is no products with this category
                {"%", ".//option[@value='26']", false},
                //empty field
                {"", ".//option[@value='20']", true},
                //incorrect data
                {"hdsgk897?<>", ".//option[@value='20']", true},
                //correct data, but product
                // with this category and name do not exist
                {"iPhone", ".//option[@value='26']", true},
                // all products, but there is no products with this category
                {"%", ".//option[@value='26']", true},
                //product description
                {"new", ".//option[@value='26']", true},

        };
    }

    /**
     * Negative testing category.
     *
     * @param inputData                     data for search field
     * @param category                      category, what will be chosen
     * @param useSearchInProductDescription -will be used search in product
     *                                      description or not
     */

    @Test(dataProvider = "dataForNegativeTestingCategory")
    public void chooseCategoriesNegativeTest(
            final String inputData, final String category,
            final Boolean useSearchInProductDescription) {
        if (useSearchInProductDescription) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.id("input-search")).sendKeys(inputData
                + Keys.ENTER);
        WebElement webElement = driver.findElement(
                By.xpath(".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual,
                "There is no product that matches the search criteria.");


    }

    /**
     * Negative testing "search in sub categories" checkbox.
     *
     * @param inputData                     data for search field
     * @param category                      category, what will be chosen
     * @param useSearchInProductDescription -will be used search in product
     *                                      description or not
     */
    @Test(dataProvider = "dataForNegativeTestingCategory")
    public void searchInSubCategoriesNegativeTest(
            final String inputData, final String category,
            final Boolean useSearchInProductDescription) {
        if (useSearchInProductDescription) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("sub_category")).click();
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.id("input-search")).sendKeys(inputData
                + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(
                ".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual,
                "There is no product that matches the search criteria.");


    }

    /**
     * Here are data for positive testing "search in sub categories" checkbox.
     * first parameter - data for search field;
     * second parameter - category, what will be chosen
     * third parameter - will be used search in product description or not.
     * fourth parameter - name of category
     *
     * @return object with three parameters described above
     */

    @DataProvider
    public Object[][] dataForPositiveTestingCategorySearchInSubCategory() {
        FindAllProductsAndTheirCategories productsAndTheirCategories
                = new FindAllProductsAndTheirCategories();
        List<Product> productsFromStorage = productsAndTheirCategories.
                findProductsAndTheirCategories();
        return new Object[][]{
                {"%", ".//option[@value='28']",
                        true, "Monitors", productsFromStorage},
                {"iphone", ".//option[@value='20']", true,
                        "Desktops", productsFromStorage},
                {"inch", ".//option[@value='20']", true,
                        "Desktops", productsFromStorage},
                {"%", ".//option[@value='28']", true,
                        "Monitors", productsFromStorage},
                {"iphone", ".//option[@value='20']", false,
                        "Desktops", productsFromStorage},

        };
    }

    /**
     * Here are data for positive testing "search in sub categories" checkbox.
     * first parameter - data for search field;
     *
     * @param inputData                     - category, what will be chosen
     * @param category                      parameter - will be used search in
     *                                      product description or not.
     * @param categoryWeAreLookingFor       - name of category
     * @param useSearchInProductDescription - search in
     *                                      product description or not
     * @param productsFromStorage
     */
    @Test(dataProvider = "dataForPositiveTestingCategorySearchInSubCategory")
    public void searchInSubCategoriesPositiveTest(
            final String inputData, final String category,
            final Boolean useSearchInProductDescription,
            final String categoryWeAreLookingFor,
            final List<Product> productsFromStorage) {
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.name("sub_category")).click();
        if (useSearchInProductDescription) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.id("input-search")).sendKeys(inputData
                + Keys.ENTER);

        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<String> productsFromWeb = new ArrayList<>();
        for (WebElement webElement : webElements) {
            productsFromWeb.add(webElement.getText());
        }
        List<Product> products = new ArrayList<>();
        for (Product product : productsFromStorage) {
            if (productsFromWeb.contains(product.getName())) {
                products.add(product);
            }
        }
        FindAllProductsAndTheirCategories productsAndTheirCategories
                = new FindAllProductsAndTheirCategories();
        List<String> categories = productsAndTheirCategories.
                findCategories(webElements, categoryWeAreLookingFor);
        for (Product product : products) {
            boolean actual = false;
            for (int j = 0; j < product.getCategory().size(); j++) {
                if (categories.toString().contains(product.getCategory()
                        .get(j))) {
                    actual = true;
                }
            }
            Assert.assertTrue(actual);
        }
    }
}
