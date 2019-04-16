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

public class TestForSearchCriteria {
    private WebDriver driver;

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
        driver.get("http://192.168.36.129/opencart/upload/");
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
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

    //----------------------------------------TestingSearchField--------------------------------------------------------
    @DataProvider
    public Object[][] negativeDataForTestingSearchField() {
        return new Object[][]{
                //EP
                //first argument is text we want to input in search field and second is way to the element we will use to check test and third - text we expect to see
                //negative testing
                //empty field
                {"", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"&*^", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."}
        };


    }


    @Test(dataProvider = "negativeDataForTestingSearchField")
    public void keywordsTest(String inputValue, String wayToValueWeExpectToSee, String expectedResult) {
        driver.findElement(By.id("input-search")).sendKeys(inputValue + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(wayToValueWeExpectToSee));
        String actual = webElement.getText();
        Assert.assertEquals(actual, expectedResult);
    }


    @DataProvider
    public Object[][] positiveDataForTestingSearchField() {
        return new Object[][]{
                //EP
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
                //word, what we are looking for, begin with this characters and before this we have too many spaces
                {"    ip"},
                //word, what we are looking for, end with this characters and before this we have too many spaces
                {"n     "}
        };

    }

    @Test(dataProvider = "positiveDataForTestingSearchField")
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
    //---------------------------------------------CheckButtonTest-------------------------------------------------------

    //check button
    @DataProvider
    public Object[][] dataForCheckButtonTest() {
        return new Object[][]{
                //negative testing
                //empty field
                {"", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"*&&(*^", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //positive testing
                //full name of element we want to find in lower case
                {"iphone", ".//div[@class='caption']/h4/a", "iPhone"},
        };
    }

    @Test(dataProvider = "dataForCheckButtonTest")
    public void CheckButtonSearchTest(String valueForSearchField, String wayToWebElement, String expectedResult) {
        driver.findElement(By.id("input-search")).sendKeys(valueForSearchField);
        driver.findElement(By.id("button-search")).click();
        String actual = driver.findElement(By.xpath(wayToWebElement)).getText();
        Assert.assertEquals(actual, expectedResult);
    }


    //---------------------------------------------CheckSearchInProductDescription--------------------------------------
    @DataProvider
    public Object[][] dataForCheckSearchInProductDescription() {
        return new Object[][]{
                {"in", ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a", ".//div[@id='tab-description']", "http://192.168.36.129/opencart/upload/index.php?route=product/search&search=in&description=true"},
                {"1", ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a", ".//div[@id='tab-description']", "http://192.168.36.129/opencart/upload/index.php?route=product/search&search=1&description=true"},
                {"Canon's press material for the EOS", ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a", ".//div[@id='tab-description']", "http://192.168.36.129/opencart/upload/index.php?route=product/search&search=Canon's press material for the EOS&description=true"}
        };
    }

    //positive testing
    @Test(dataProvider = "dataForCheckSearchInProductDescription")
    public void checkSearchInProductDescription(String inputInTheSearchField, String wayToElements, String wayToElementWithTextWeWantToCheck, String wayToPageWithInform) {
        driver.findElement(By.id("input-search")).clear();
        driver.findElement(By.id("input-search")).sendKeys(inputInTheSearchField);
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        List<WebElement> webElements = driver.findElements(By.xpath(wayToElements));
        for (int i = 0; i < webElements.size(); i++) {
            System.out.println(i);
            List<WebElement> webElements1 = driver.findElements(By.xpath(wayToElements));
            System.out.println(webElements1.get(i));
            System.out.println(webElements1.get(i).getText());
            webElements1.get(i).click();
            String webElementI = driver.findElement(By.xpath(wayToElementWithTextWeWantToCheck)).getText();
            System.out.println(webElementI);
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
                {"", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"sh7483274hdsfj", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."},
                //incorrect data
                {"&*^", ".//div[@id='content']/p[2]", "There is no product that matches the search criteria."}
        };
    }

    //negative testing
    @Test(dataProvider = "negativeDataForCheckSearchInProductDescription")
    public void negativeCheckSearchInProductDescription(String inputValue, String wayToValueWeExpectToSee, String expectedResult) {
        driver.findElement(By.id("input-search")).sendKeys(inputValue);
        driver.findElement(By.id("description")).click();
        driver.findElement(By.id("input-search")).sendKeys(Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(wayToValueWeExpectToSee));
        String actual = webElement.getText();
        Assert.assertEquals(actual, expectedResult);
    }
    //------------------------------------AllCategoriesTest-------------------------------------------------------------


    @Test
    public void AllCategoriesTest() {

        driver.findElement(By.id("description")).click();
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(".//option[@value='20']")).click();
        driver.findElement(By.id("input-search")).sendKeys("%" + Keys.ENTER);
        WebElement webElement = driver.findElement(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<WebElement> webElements = driver.findElements(By.xpath(".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<String> listOfWebEL=new ArrayList<>();
        for (int i = 1; i < webElements.size(); i++) {
            listOfWebEL.add(webElements.get(i).getText());
        }
        System.out.println(webElements.size());

        driver.get("http://192.168.36.129/opencart/upload/admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.id("menu-catalog")).click();
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).click();
        List<WebElement> productsFromWeb = driver.findElements(By.xpath(".//tr/td[3]"));
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < productsFromWeb.size(); i++) {
            Product product = new Product();
            product.setName(productsFromWeb.get(i).getText());
            products.add(product);
        }

        for (int i = 1; i < productsFromWeb.size(); i++) {
            System.out.println(i);
            List<WebElement> edit = driver.findElements(By.xpath(".//tr/td[8]"));
            edit.get(i).click();
            driver.findElement(By.xpath(".//ul[@class='nav nav-tabs']/li[3]")).click();
            List<WebElement> productCategoryWeb = driver.findElements(By.xpath(".//div[@id='product-category']/div"));
            List<String> productCateggry = new ArrayList<>();
            for (int j = 0; j < productCategoryWeb.size(); j++) {
                productCateggry.add(productCategoryWeb.get(j).getText());

            }
            System.out.println(productCateggry.toString());

            products.get(i - 1).setCategory(productCateggry);

            System.out.println(products.get(i-1).toString());
            driver.findElement(By.id("menu-catalog")).click();
            driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).click();

        }
        System.out.println("done");


        for (int i = 0; i < listOfWebEL.size(); i++) {
            System.out.println(listOfWebEL.get(i));
            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getName().equals(listOfWebEL.get(i))) {
                    System.out.println(products.get(j).getName() );
                    System.out.println(true);
                    Assert.assertTrue(products.get(j).getCategory().contains("Desktops"));
                }
            }

        }



    }


}
