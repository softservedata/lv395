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
 * This class is for testing search field and button
 * and for testing keywords field and button.
 *
 * @author Iryna Ratushniak
 */
public class SearchFieldAndButtonTest {
    /**
     * Driver to have access to web page.
     */
    private WebDriver driver;
    /**
     * ip adress.
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
//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }

    /**
     * Before all method next action should be done.
     */

    @BeforeMethod
    public void beforeMethod() {
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();

    }
    //----Test Search Field-----

    /**
     * Negative data for search field.
     *
     * @return data we input in search field
     */
    @DataProvider
    public Object[][] dataForTestForSearchFieldNegativeTesting() {
        return new Object[][]{
                //EP
                //first argument is text we want to input in search
                // field and second is way to the element we will
                // use to check test and third - text we expect to see
                //negative testing
                //empty field
                {""},
                //incorrect data
                {"sh7483274hdsfj"},
                //incorrect data
                {"&*<>)))_+!@"},
                //data from product description
                {"GB"},
                //incorrect word
                {"iphane"},
                //some elements
                {"\uF04A"}
        };
    }

    /**
     * Positive data for search field.
     *
     * @return data we input in search field.
     */
    @DataProvider
    public Object[][] dataForTestingSearchFieldPositiveTesting() {
        return new Object[][]{
                //positive testing
                //full name of element we want to find in lower case
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
     * Negative tests for search field + ENTER.
     *
     * @param valueWeInputInSearchField - data we input in search field.
     */
    @Test(dataProvider = "dataForTestForSearchFieldNegativeTesting")
    public void checkSearchFieldNegativeTest(
            final String valueWeInputInSearchField) {
        //Write in the search field something we want to find
        driver.findElement(By.name("search")).sendKeys(
                valueWeInputInSearchField + Keys.ENTER);
        //Find element we are looking for
        WebElement isElementOnThePage = driver.findElement(By.xpath(
                ".//div[@id='content']/p[2]"));
        //Get text from element, what were found
        String actual = isElementOnThePage.getText();
        // Check
        Assert.assertEquals(actual,
                "There is no product that matches the search criteria.");
    }


    /**
     * Positive tests for search field + ENTER.
     *
     * @param valueWeInputInSearchField - data we input in search field.
     */
    @Test(dataProvider = "dataForTestingSearchFieldPositiveTesting")
    public void checkSearchFieldPositiveTest(
            final String valueWeInputInSearchField) {
        driver.findElement(By.name("search")).sendKeys(
                valueWeInputInSearchField + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        for (WebElement webElement : webElements) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains(
                    valueWeInputInSearchField.trim().toLowerCase()));
        }
    }

    /**
     * Positive tests for search field + ENTER.
     * Word, what we input into the search field,
     * have too many spaces before, after and inside.
     */
    @Test
    public void checkSearchFieldFieldSpaceBetweenCharactersTest() {
        driver.findElement(By.name("search")).sendKeys(
                "ip   h o n     e" + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        for (WebElement webElement : webElements) {
            Assert.assertTrue(webElement.getText().contains("iPhone"));
        }
    }



    /**
     * Positive tests for search field + ENTER.
     * Use "%" to see all products.
     */
    @Test
    public void checkSearchFieldToSeeAllProductsPositiveTest() {
        driver.findElement(By.name("search")).sendKeys("%" + Keys.ENTER);
        driver.findElement(By.id("input-limit")).click();
        driver.findElement(By.xpath(".//select[@id='input-limit']/option[3]")).
                click();
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        FindAllProductsAndTheirCategories findAllProductsAndTheirCategories =
                new FindAllProductsAndTheirCategories();
        List<String> products = findAllProductsAndTheirCategories.
                findAllProductsOnAdminPage();
        System.out.println(products.toString());
        for (WebElement webElement : webElements) {
            Assert.assertTrue(products.contains(
                    webElement.getText()));
        }
    }

    /**
     * Negative test for search field.
     * We input value longer then 9000 characters
     */
    @Test
    public void stressSearchFieldTest() {
        String str = "a";
        for (int i = 0; i < 9000; i++) {
            str += "a";
        }
        driver.findElement(By.name("search")).sendKeys(str + Keys.ENTER);
        Boolean elamantOnThePage = true;
        try {
            driver.findElement(By.id("logo"));
        } catch (Exception e) {
            elamantOnThePage = false;
        }
        Assert.assertTrue(elamantOnThePage);
    }
    /**
     * Negative test for keyword field.
     * We input value longer then 9000 characters
     */
    @Test
    public void stressKeywordFieldTest() {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        String str = "a";
        for (int i = 0; i < 9000; i++) {
            str += "a";
        }
        driver.findElement(By.id("input-search")).sendKeys(
                str + Keys.ENTER);
        Boolean elamantOnThePage = true;
        try {
            driver.findElement(By.id("logo"));
        } catch (Exception e) {
            elamantOnThePage = false;
        }
        Assert.assertTrue(elamantOnThePage);
    }

    /**
     * Negative tests for keywords field.
     *
     * @param inputValue - data we input into keywords field
     */
    @Test(dataProvider = "dataForTestForSearchFieldNegativeTesting")
    public void checkKeywordsFieldNegativeTest(final String inputValue) {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-search")).sendKeys(
                inputValue + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(
                ".//div[@id='content']/p[2]"));
        String actual = webElement.getText();
        Assert.assertEquals(actual,
                "There is no product that matches the search criteria.");
    }

    /**
     * Negative tests for keywords field.
     *
     * @param valueWeInputInKeywordsField - data we input into keywords field
     */
    //positive testing
    @Test(dataProvider = "dataForTestingSearchFieldPositiveTesting")
    public void checkKeywordsFieldPositiveTest(
            final String valueWeInputInKeywordsField) {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-search")).sendKeys(
                valueWeInputInKeywordsField + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        for (WebElement webElement : webElements) {
            Assert.assertTrue(webElement.getText().toLowerCase().contains(
                    valueWeInputInKeywordsField.trim().toLowerCase()));
        }
    }

    /**
     * Positive tests for keywords field + ENTER.
     * Word, what we input into the search field,
     * have too many spaces before, after and inside.
     */
    @Test
    public void checkKeywordsFieldFieldSpaceBetweenCharactersTest() {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-search")).sendKeys(
                "ip   h o n     e" + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        for (WebElement webElement : webElements) {
            Assert.assertTrue(webElement.getText().contains("iPhone"));
        }
    }

    /**
     * Positive tests for search field + ENTER.
     * Use "%" to see all products.
     */
    @Test
    public void checkKeywordsFieldToSeeAllProductsPositiveTest() {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-search")).sendKeys("%" + Keys.ENTER);

        driver.findElement(By.id("input-limit")).click();
        driver.findElement(By.xpath(".//select[@id='input-limit']/option[3]")).
                click();
        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));


        FindAllProductsAndTheirCategories findAllProductsAndTheirCategories =
                new FindAllProductsAndTheirCategories();
        List<String> products = findAllProductsAndTheirCategories.
                findAllProductsOnAdminPage();

        for (int i = 0; i < webElements.size(); i++) {
            Assert.assertTrue(products.contains(
                    webElements.get(i).getText()));
            System.out.println(webElements.get(i).getText());
            System.out.println(i);
        }
    }

    //------------TestingButtonForSearchField------------------

    /**
     * In this dataProvider we have data for checking button.
     *
     * @return - data for check button tests
     */
    @DataProvider
    public Object[][] dataForCheckButtonTest() {
        return new Object[][]{
                //negative testing
                //empty field
                {"", ".//div[@id='content']/p[2]", "There is no"
                        + " product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]",
                        "There is no product that matches the "
                                + "search criteria."},
                //incorrect data
                {"*&&(*^", ".//div[@id='content']/p[2]",
                        "There is no product that matches"
                                + " the search criteria."},
                //positive testing
                //full name of element we want to find in lower case
                {"iphone", ".//div[@class='caption']/h4/a",
                        "iPhone"},
        };
    }

    /**
     * Test for checking button near the search field.
     *
     * @param valueWeInputInSearchField - value that will be imputed into
     *                                  the field
     * @param wayToElement              - way to elements that will be shown on
     *                                  the page
     * @param textWeExpectToSee         - text we expect to see om the page
     */
    @Test(dataProvider = "dataForCheckButtonTest")
    public void buttonForSearchFieldTest(
            final String valueWeInputInSearchField,
            final String wayToElement, final String textWeExpectToSee) {
        //Write in the search field something we want to find
        driver.findElement(By.name("search")).sendKeys(
                valueWeInputInSearchField);
        driver.findElement(By.cssSelector(
                "button[class='btn btn-default btn-lg']")).click();
        //Find element we are looking for
        WebElement isElementOnThePage = driver.findElement(
                By.xpath(wayToElement));
        //Get text from element, what were found
        String actual = isElementOnThePage.getText();
        // Check
        Assert.assertTrue(actual.contains(textWeExpectToSee));
    }


    //---------TestingButtonForKeywordsField--------

    /**
     * Test for checking button near the keywords field.
     *
     * @param valueWeInputInSearchField - value that will be imputed into
     *                                  the field
     * @param wayToElement              - way to elements that will be shown
     *                                  on the page
     * @param textWeExpectToSee         - text we expect to see om the page
     */

    @Test(dataProvider = "dataForCheckButtonTest")
    public void buttonForKeywordsFieldTest(
            final String valueWeInputInSearchField,
            final String wayToElement, final String textWeExpectToSee) {
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        driver.findElement(By.id("input-search")).sendKeys(
                valueWeInputInSearchField);
        driver.findElement(By.id("button-search")).click();
        String actual = driver.findElement(By.xpath(wayToElement)).getText();
        Assert.assertEquals(actual, textWeExpectToSee);
    }


}

