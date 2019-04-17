package com.softserve.createuserpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.sql.*;
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
     * correct data with all boundary
     * values.
     * @return Object[][].
     */
    @DataProvider(name = "correctTestData")
    public static Object[][] correctTestData() {
        return new Object[][]{
                {new TestData("Nazar", "Baks",
                        "testuser395lv@gmail.com", "45655654",
                        "Varshavska", "Lviv", "485468",
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
     * In this @AfterMethod we
     * logout from created account.
     */
    @AfterMethod
    public void deleteUserFromDb() throws ClassNotFoundException, SQLException {
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
            String selectUserSQL = "SELECT * FROM opencart.oc_customer \n"
                    + "WHERE email LIKE 'testuser395lv@gmail.com';";
            ResultSet rs = statement.executeQuery(selectUserSQL);
            while (rs.next()) {
                String email = rs.getString("email");
                System.out.println("email : " + email);
                rs.absolute(1);
                rs.deleteRow();
                System.out.println("Deleted!");
            }
        }
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
