package com.softserve.createuserpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.sql.*;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class SpecialSymbolsTest {
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
                {new TestData("/*-+@#$%^&", "/*-+@#$%^&",
                        "nazar.bako.kn.2016@lpnu.ua", "/*-+@#$%^&",
                        "/*-+@#$%^&", "/*-+@#$%^&", "/*-+@#$%^&",
                        "/*-+@#$%^&", "/*-+@#$%^&")}
        };
    }

    /**
     * In this test we enter all valid data
     * with boundary values.
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
        Select sel = new Select(driver.findElement(By
                .cssSelector("select[id*='input-zone']")));
        sel.selectByIndex(4);
        //driver.findElement(By.cssSelector("select[id*='input-zone'] > option[value*='3519']")).click();
        driver.findElement(By.id("input-password")).sendKeys(data.get(7));
        driver.findElement(By.id("input-confirm")).sendKeys(data.get(8));
        driver.findElement(By.cssSelector("input[type='checkbox']")).click();
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(5000);
        //Check if we create user successfully
        String actualText = driver.findElement(By.cssSelector("#content "
                + "> h1")).getText();
        Thread.sleep(5000);
        assertEquals(actualText, "Your Account Has Been Created!");
    }

//    /**
//     * In this @AfterMethod we
//     * logout from created account.
//     */
//    @AfterMethod
//    public void logOut() throws InterruptedException {
//        Thread.sleep(1000);
//        driver.findElement(By.linkText("My Account")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.cssSelector("a[href*='opencart"
//                + "/upload/index.php']:nth-of-type(13)")).click();
//        Thread.sleep(1000);
//    }

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
            String selectFirstUserSQL = "SELECT * FROM opencart.oc_customer\n"
                    + "WHERE email LIKE 'nazar.bako.kn.2016@lpnu.ua';";

            ResultSet rs1 = statement.executeQuery(selectFirstUserSQL);
            ////we check if customer is registered in db and remove him
            while (rs1.next()) {
                String firstName = rs1.getString("firstname");
                String email = rs1.getString("email");
                System.out.println("firstName : " + firstName);
                System.out.println("email : " + email);
                assertEquals(firstName, "/*-+@#$%^&amp;");
                assertEquals(email, "nazar.bako.kn.2016@lpnu.ua");
                rs1.absolute(1);
                rs1.deleteRow();
                System.out.println("Deleted!");
            }
        }

        //////////////////////////////
        driver.quit();
    }
}
