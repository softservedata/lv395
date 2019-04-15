package com.softserve.edu;

import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestForSearchFieldAndButton {
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
    }

    @AfterMethod
    public void afterMethod() {

    }

    @DataProvider
    public Object[][] dataForTestForSearchField() {
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

    //search field plus enter
    @Test(dataProvider = "dataForTestForSearchField")
    public void checkSearchField(String valueWeInputInSearchField, String wayToElement, String textWeExpectToSee) throws Exception {
        //Write in the search field something we want to find
        driver.findElement(By.name("search")).sendKeys(valueWeInputInSearchField + Keys.ENTER);
        //Find element we are looking for
        WebElement isElementOnThePage = driver.findElement(By.xpath(wayToElement));
        //Get text from element, what were found
        String actual = isElementOnThePage.getText();
        System.out.println(actual);
        // Check
        System.out.println(actual.toLowerCase() + textWeExpectToSee.toLowerCase());
        Assert.assertEquals(actual.toLowerCase(), textWeExpectToSee.toLowerCase());
    }

    //positive testing
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
        driver.findElement(By.name("search")).sendKeys(whatWeAreLookingFor + Keys.ENTER);
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
        driver.findElement(By.name("search")).sendKeys("ip   h o n     e" + Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        System.out.println(webElements.size());
        for (int i = 0; i < webElements.size(); i++) {
            Assert.assertTrue(webElements.get(i).getText().contains("iPhone"));
        }
    }


    //search field plus button
    @Test(dataProvider = "dataForTestForSearchField")
    public void checkSearchFieldPlusButton(String valueWeInputInSearchField, String wayToElement, String textWeExpectToSee) throws Exception {
        //Write in the search field something we want to find
        driver.findElement(By.name("search")).sendKeys(valueWeInputInSearchField);

        driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']")).click();
        //Find element we are looking for
        WebElement isElementOnThePage = driver.findElement(By.xpath(wayToElement));
        //Get text from element, what were found
        String actual = isElementOnThePage.getText();
        System.out.println(actual);
        // Check
        Assert.assertTrue(actual.contains(textWeExpectToSee));
    }


//    @DataProvider
//    public Object[][] dataForTestIsMessageAboutWhatWeWantToFindCorrect() {
//        return new Object[][]{
//                //first argument is text we want to input in search field and second is way to the element we will use to check test
//                //full name of element we want to find
//                {"iphone"},
//                //first letters of element we want to find
//                {"ip"},
//                //incorrect data
//                {"jhdskksd873493"},
//                //last letters of element we want to find
//                {"on"},
//                //letters from inside word we want to find
//                {"ph"},
//                //use small and big letters in different places
//                {"IpHOne"},
//                //space before word
//                {"     iphone"},
//                //space after  word
//                {"iphone "},
//                //space inside word
//                {"ip      hon   e"}
//        };
//    }
//
//    @Test(dataProvider = "dataForTestIsMessageAboutWhatWeWantToFindCorrect")
//    public void isMessageAboutWhatWeWantToFindCorrect(String valueWeInputInSearchField) throws Exception {
//        driver.findElement(By.name("search")).sendKeys(valueWeInputInSearchField + Keys.ENTER);
//        //Find message about what are we looking for
//        WebElement findMessageAboutWhatWeWantToFind = driver.findElement(By.xpath(".//div[@id='content']/h1"));
//        //To get message
//        String actual = findMessageAboutWhatWeWantToFind.getText();
//        // Check
//        String expected = "Search - " + valueWeInputInSearchField;
//        Assert.assertEquals(actual, expected);
//    }
//
//    @Test
//    public void isMessageAboutWhatWeWantToFindCorrectForEmptyField() throws Exception {
//        driver.findElement(By.name("search")).sendKeys("" + Keys.ENTER);
//        //Find message about what are we looking for
//        WebElement findMessageAboutWhatWeWantToFind = driver.findElement(By.xpath(".//div[@id='content']/h1"));
//        //To get message
//        String actual = findMessageAboutWhatWeWantToFind.getText();
//        // Check
//        String expected = "Search";
//        Assert.assertEquals(actual, expected);
//    }


}
