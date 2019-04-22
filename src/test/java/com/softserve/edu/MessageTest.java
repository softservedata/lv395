package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * This class is created for
 * testing message about what we want to find
 * (here is used admin page).
 *
 * @author Iryna Ratushniak
 */
public class MessageTest {
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

    }

    /**
     * Data for testing message about what are we looking for.
     *
     * @return - data that we inputinto the search field.
     */
    @DataProvider
    public Object[][] dataForTestIsMessageCorrect() {
        return new Object[][]{
                //first argument is text we want to input in
                // search field and second is way to the
                // element we will use to check test
                //full name of element we want to find
                {"iPhone", "iPhone"},
                //incorrect data
                {"jhdskksd873493", "jhdskksd873493"},
                //letters from element we want to find
                {"on", "on"},
                //use small and big letters in different places
                {"IPHONE", "IPHONE"},
                //space before word
                {"     iphone", "iphone"},
                //space after  word
                {"iphone ", "iphone"},
                //space inside word
                {"ip      hon   e", "ip hon e"}
        };
    }

    /**
     * test for message about what are we looking for.
     *
     * @param valueWeInputInSearchField - value that we input into
     *                                  the search  field.
     * @param valueWeExpectToSee        - value that we expect to see
     */
    @Test(dataProvider = "dataForTestIsMessageCorrect")
    public void isMessageCorrectTest(final String valueWeInputInSearchField, final String valueWeExpectToSee) {
        driver.findElement(By.name("search")).sendKeys(
                valueWeInputInSearchField + Keys.ENTER);
        //Find message about what are we looking for
        WebElement findMessageAboutWhatWeWantToFind = driver.findElement(
                By.xpath(".//div[@id='content']/h1"));
        //To get message
        String actual = findMessageAboutWhatWeWantToFind.getText();
        // Check
        String expected = "Search - " + valueWeExpectToSee;
        Assert.assertEquals(actual, expected);
    }

    /**
     * Test for message about what are we looking for.
     * For empty field.
     */
    @Test
    public void isMessageCorrectForEmptyFieldTest() {
        driver.findElement(By.name("search")).sendKeys("" + Keys.ENTER);
        //Find message about what are we looking for
        WebElement findMessageAboutWhatWeWantToFind = driver.findElement(By.xpath(".//div[@id='content']/h1"));
        //To get message
        String actual = findMessageAboutWhatWeWantToFind.getText();
        // Check
        String expected = "Search";
        Assert.assertEquals(actual, expected);
    }
}
