package com.softserve.createuserpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

/**
 * In this class we have one test
 * which check that we can't create
 * user with all entered incorrect data.
 */
public class InputIncorrectDataTest {
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
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
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
     * incorrect data with all boundary
     * values.
     * @return Object[][].
     */
    @DataProvider(name = "incorrectTestData")
    public static Object[][] incorrectTestData() {
        return new Object[][]{
                {new TestData("", "",
                        "lopata@lpnuua", "45",
                        "St", "L", "1",
                        "qwe", "qwe")},
                {new TestData("iamlordvoldemortiamlordvoldemort!",
                        "iamlordvoldemortiamlordvoldemort!",
                        "grabli2016l@pnuua",
                        "123456789012345678901234567890123",
                        "alqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqa"
                                + "lqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqa"
                                + "lqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqq",
                        "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvl",
                        "11", "qwe", "qwe")}
        };
    }

    /**
     * In this test we entered all invalid data
     * with boundary values.
     * @param data data;
     */
    @Test(dataProvider = "incorrectTestData")
    public void checkEnterIncorrectData(TestData data)
            throws InterruptedException {
        driver.get("http://192.168.11.129/opencart/upload/");
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.id("input-firstname")).sendKeys(data.get(0));
        driver.findElement(By.id("input-lastname")).sendKeys(data.get(1));
        driver.findElement(By.id("input-email")).sendKeys(data.get(2));
        driver.findElement(By.id("input-telephone")).sendKeys(data.get(3));
        driver.findElement(By.id("input-address-1")).sendKeys(data.get(4));
        driver.findElement(By.id("input-city")).sendKeys(data.get(5));
        driver.findElement(By.id("input-postcode")).sendKeys(data.get(6));
        driver.findElement(By.id("input-country")).click();
        driver.findElement(By.cssSelector("#input-country >"
                + " option:nth-child(1)")).click();
        driver.findElement(By.id("input-password")).sendKeys(data.get(7));
        driver.findElement(By.id("input-confirm")).sendKeys(data.get(8));
        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(1000);//for presentation only

        /**
         * Here we check if actualFirstNameError
         * equals expected.
         */
        String actualFirstNameError =
                driver.findElement(By.cssSelector("#account "
                        + "> div:nth-child(3) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualFirstNameError,
                "First Name must be between 1 and 32 characters!");

        /**
         * Here we check if actualLastNameError
         * equals expected.
         */
        String actualLastNameError =
                driver.findElement(By.cssSelector("#account "
                        + "> div:nth-child(4) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualLastNameError,
                "Last Name must be between 1 and 32 characters!");

        /**
         * Here we check if actualEmailError
         * equals expected.
         */
        String actualEmailError =
                driver.findElement(By.cssSelector("#account "
                        + "> div:nth-child(5) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualEmailError,
                "E-Mail Address does not appear to be valid!");

        /**
         * Here we check if actualTelephoneError
         * equals expected.
         */
        String actualTelephoneError =
                driver.findElement(By.cssSelector("#account "
                        + "> div:nth-child(6) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualTelephoneError,
                "Telephone must be between 3 and 32 characters!");

        /**
         * Here we check if actualAddressNameError
         * equals expected.
         */
        String actualAddressError =
                driver.findElement(By.cssSelector("#address "
                        + "> div:nth-child(3) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualAddressError,
                "Address 1 must be between 3 and 128 characters!");

        /**
         * Here we check if actualCityError
         * equals expected.
         */
        String actualCityError =
                driver.findElement(By.cssSelector("#address "
                        + "> div:nth-child(5) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualCityError,
                "City must be between 2 and 128 characters!");

        /**
         * Here we check if actualCountryError
         * equals expected.
         */
        String actualCountryError =
                driver.findElement(By.cssSelector("#address "
                        + "> div:nth-child(7) > div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualCountryError, "Please select a country!");

        /**
         * Here we check if actualPasswordError
         * equals expected.
         */
        String actualPasswordError =
                driver.findElement(By.cssSelector("#content > form >"
                        + " fieldset:nth-child(3) >"
                        + " div.form-group.required.has-error >"
                        + " div > div")).getText();
        Thread.sleep(1000);//for presentation only
        assertEquals(actualPasswordError,
                "Password must be between 4 and 20 characters!");

    }

    /**
     * This @AfterClass
     * closes driver.
     */
    @AfterClass
    public void closeDriver() throws ClassNotFoundException, SQLException {

        String connectionUrl = "jdbc:mysql://192.168.11.129:3306/opencart";
        String userName = "lv395";
        String password = "Lv395_Taqc";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager
                .getConnection(connectionUrl, userName, password);
             Statement statement = conn.createStatement(ResultSet
                             .TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connection success!");
            String selectTwoUsersSQL = "SELECT * FROM opencart.oc_customer\n"
                    + "WHERE email IN ('lopata@lpnuua', 'grabli2016l@pnuua');";
            ResultSet res = statement.executeQuery(selectTwoUsersSQL);
            if (res.next()) {
                System.out.println("Error");
            } else {
                System.out.println("Users do not created");
            }

        }
        driver.quit();
    }
}
