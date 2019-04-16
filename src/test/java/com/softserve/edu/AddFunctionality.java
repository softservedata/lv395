package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddFunctionality {

    protected WebDriver driver;
    private final String URL = "192.168.239.129";

    @BeforeClass
    public void setUp() {
        String webDriverPath =  this.getClass().getResource("/").toString();
        webDriverPath = webDriverPath.substring(webDriverPath.indexOf("/"));
        System.setProperty("webdriver.chrome.driver",
                webDriverPath + "chromedriver-windows-32bit.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    public void webServiceStart() {
        driver.get("http://" + URL + "/opencart/upload/");
    }

    public void cartCleaner(WebDriver driver, String URL) {
        driver.get("http://" + URL + "/opencart/upload/");
        //WebElements initialization
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(driver, 3);
        if (driver.findElements(By.cssSelector("[title^='Remove'")).size() > 0) {
            List<WebElement> cartElementsTableRows;
            WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody"));
            cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
            //Cycle which deleting elements from cart
            for (int countOfElements = cartElementsTableRows.size(); countOfElements > 0; countOfElements--) {
                driver.findElement(By.id("cart")).click();
                driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
                cartElementsTableRows.remove(countOfElements - 1);
                driver.navigate().refresh();
            }
        } else {
            driver.navigate().refresh();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String getURL() {
        return URL;
    }

    public WebElement getAddButtons(int index){
    List<WebElement> addButtons = driver
            .findElements(By.cssSelector("div[class*='button-group']"
                    + " button[onclick*='cart.add']"));
    return addButtons.get(index);
    }

    public void openCart(){
        driver.findElement(By.id("cart")).click();
    }

    public WebElement getGoodPlate() {
       WebElement goodPlate = driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody/tr"));
       return goodPlate;
    }

}
