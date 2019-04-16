package com.softserve.createuserpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class PolicyTest {
    /**
     * Simple webdriver.
     */
    private WebDriver driver;

    /**
     * Url of home page.
     */
    private final String URL = "http://192.168.11.129/opencart/upload/";

    /**
     * In this @BeforeClass we
     * set chromedriver maximize our
     * window and set implicitlyWait
     * 20 seconds.
     */
    @BeforeClass
    public void atStart() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        System.getProperty("webdriver.chrome.driver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * This @BeforeMethod open
     * browser before test.
     */
    @BeforeMethod
    public void openBrowser() {
        driver.get(URL);
    }

    /**
     * This dataprovider provides all
     * correct data.
     * @return Object[][].
     */
    @DataProvider(name = "correctTestData")
    public static Object[][] correctTestData() {
        return new Object[][]{
                {new TestData("Lord", "Voldemort",
                        "nazaru.bakuko.kn.2016@lpnu.ua", "4565464",
                        "Varshavska st.", "Lviv", "486454",
                        "qwerty", "qwerty")}
        };
    }

    /**
     * In this test we enter all valid data
     * without accepting privacy policy.
     * @param data data;
     */
    @Test(dataProvider = "correctTestData")
    public void checkEnterCorrectData(TestData data)
            throws InterruptedException {
        driver.get("http://192.168.11.129/opencart/upload/");
        driver.findElement(By.linkText("My Account")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys(data.get(0));
        driver.findElement(By.id("input-lastname")).sendKeys(data.get(1));
        driver.findElement(By.id("input-email")).sendKeys(data.get(2));
        driver.findElement(By.id("input-telephone")).sendKeys(data.get(3));
        driver.findElement(By.id("input-address-1")).sendKeys(data.get(4));
        driver.findElement(By.id("input-city")).sendKeys(data.get(5));
        driver.findElement(By.id("input-postcode")).sendKeys(data.get(6));
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.cssSelector("#input-country "
                + "> option:nth-child(8)")).click();
        driver.findElement(By.id("input-zone")).click();
        driver.findElement(By.cssSelector("#input-zone "
                + "> option:nth-child(11)")).click();
        driver.findElement(By.id("input-password")).sendKeys(data.get(7));
        driver.findElement(By.id("input-confirm")).sendKeys(data.get(8));
        Thread.sleep(5000);
        //driver.findElement(By.cssSelector("input[type='checkbox']")).click();
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(5000);
        //Check if we receive correct error message
        String actualText =
        driver.findElement(By
                .cssSelector("div[class*='alert']")).getText();
        Thread.sleep(5000);
        assertEquals(actualText,
                "Warning: You must agree to the Privacy Policy!");
    }

    /**
     * This @AfterClass
     * closes driver.
     */
    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

}
