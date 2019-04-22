package com.softserve.edu;

import com.softserve.edu.tools.FindAllProductsAndTheirCategories;
import com.softserve.edu.tools.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * This class is for checkbox "Search in product description"
 *
 * @author Iryna Ratushniak
 */
public class SearchInProductDescriptionTest {
    /**
     * Driver to have access to web page.
     */
    private WebDriver driver;
    /**
     * ip address.
     */
    final private String ip = "192.168.36.134";

    /**
     * Before class.
     * (action in this class should happens before all other action)
     */
    @BeforeClass
    public void beforeClass() {
        String webDriverPath =  this.getClass().getResource("/").toString();
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
        driver.findElement(By.id("description")).click();
    }


    //--------CheckSearchInProductDescription-----

    /**
     * DataProvider for positive tests
     * "search in product description" checkbox.
     *
     * @return - data we input into keywords field.
     */
    @DataProvider
    public Object[][] dataForSearchInProductDescription() {

        return new Object[][]{
                //EP
                //positive testing
                {"GB"},
                {"1"},
                {"The 30-inch Apple Cinema HD Display delivers"
                        + " an amazing 2560 x 1600 pixel resolution."},
                {"u"},
                {"iphone"},
                //full name of element we want to find in
                {"iPhone"},
                //full name of element we want to find in upper case
                {"IPHONE"},
                //word, what we are looking for, begin with this characters
                {"ip"},
                //word, what we are looking for, includes with this characters
                {"po"},
                //word, what we are looking for, end with this characters
                {"n"},
                //word, what we are looking for, begin with this characters
                // and before this we have too many spaces
                {"    ip"},
                //word, what we are looking for, end with this characters
                // and before this we have too many spaces
                {"n     "},
        };
    }

    /**
     * Positive tests for checkbox "search in product description".
     *
     * @param inputInTheSearchField - data that we input into the search field
     */
    @Test(dataProvider = "dataForSearchInProductDescription")
    public void searchInProductDescriptionPositiveTest(
            final String inputInTheSearchField) {
        String wayToPageWithInform = "http://"
                + ip + "/opencart/upload/index.php?route=product/search&search="
                + inputInTheSearchField + "&description=true";
        driver.findElement(By.id("input-search")).
                sendKeys(inputInTheSearchField);
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        for (int i = 0; i < webElements.size(); i++) {
            List<WebElement> webElements1 = driver.findElements(By.xpath(
                    ".//div[@class='row']/div/div/div"
                            + "/div[@class='caption']/h4/a"));
            webElements1.get(i).click();
            String description = driver.findElement(By.xpath(
                    ".//div[@id='tab-description']")).getText();
            Assert.assertTrue(description.toLowerCase().contains(
                    inputInTheSearchField.toLowerCase().trim()));
            driver.get(wayToPageWithInform);

        }
    }

    /**
     * Positive tests for checkbox "search in product description".
     * Use "%" to see all possible products.
     */
    @Test
    public void searchInProductDescriptionAllProductsPositiveTesting() {
        driver.findElement(By.id("input-search")).sendKeys("%");
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-limit")).click();
        driver.findElement(By.xpath(".//select[@id='input-limit']/option[3]")).
                click();
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        FindAllProductsAndTheirCategories findAllProductsAndTheirCategories
                = new FindAllProductsAndTheirCategories();
        List<String> products = findAllProductsAndTheirCategories.
                findAllProductsOnAdminPage();
        for (int i = 0; i < webElements.size(); i++) {
            Assert.assertTrue(products.contains( webElements.get(i).
                    getText()));
        }
    }

    /**
     * This is data for negative tests for checkbox
     * "search in product description".
     *
     * @return data for search field
     */
    @DataProvider
    public Object[][] negativeDataForSearchInProductDescription() {
        return new Object[][]{
                //EP
                //first argument is text we want to input in search
                // field and second is way to the element we will
                // use to check test and third - text we expect to see

                //empty field
                {""},
                //incorrect data
                {"iphane"},
                //incorrect data
                {"<<?><?$%^%&&*("},
                //incorrect word
                {"ks44i34gfd"}
        };
    }

    /**
     * Negative tests  for checkbox "search in product description".
     *
     * @param inputValue data, that we input into search field
     */
    @Test(dataProvider = "negativeDataForSearchInProductDescription")
    public void searchInProductDescriptionNegativeTest(final String inputValue) {
        driver.findElement(By.id("input-search")).sendKeys(inputValue);
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath
                (".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual,
                "There is no product that matches the search criteria.");
    }


}
