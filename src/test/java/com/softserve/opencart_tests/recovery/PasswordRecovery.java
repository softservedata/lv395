package com.softserve.opencart_tests.recovery;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PasswordRecovery {
    /**
     * Web driver
     */
    private WebDriver driver;
    /**
     * <code>ArrayList</code> of browser tabs.
     */
    private ArrayList<String> tabs;
    /**
     * URL to opencart home page
     */
    private final String URL = "http://192.168.227.130/opencart/upload";

    /**
     * User email address.
     */
    private final String correctEmail = "john.wick.test@ukr.net";
    /**
     * User password.
     */
    private final String incorrectEmail = "not.john.wick.test@ukr.net";
    /**
     * User incorrect email address.
     */
    private final String emptyEmail = "";
    /**
     * Empty email.
     */
    private final String successMessageForMail = "An email with a confirmation link has been sent your email address.";
    /**
     * Message, shown when user input incorrect email address.
     */
    private final String failMessage = "Warning: The E-Mail Address was not found in our records, please try again!";
    /**
     * Message, shown when user input incorrect email address.
     */
    private final String successMessage = "Success: Your password has been successfully updated.";


    /**
     * <code>@DataProvider</code> with user email address.
     * @return user email address and message
     */
    @DataProvider
    public Object[][] email() {
        return new Object[][]{
                {correctEmail, successMessageForMail}, // correct Email --> Success Message
                {emptyEmail, failMessage}, // empty Email --> Error Message
                {incorrectEmail, failMessage} // incorrect Email --> Error Message
        };
    }

    /**
     * <code>@DataProvider</code> with user password address.
     * @return value for 'password' field and 'confirm password' field
     */
    @DataProvider
    public Object[][] password() {
        return new Object[][]{
                {"111", "111", "Failed"},
                {"123456789123456789123", "123456789123456789123", "Failed"},
                {"1234", "4321", "Failed"},
                {"", "1111", "Failed"},
                {"1111", "", "Failed"},
                {"qwerty", "qwerty", "Success"},
        };
    }

    /**
     * Set properties and initialize the driver.
     */
    @BeforeClass
    public void setPropertiesAndInitializeDriver() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver",
                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Open opencart login page.
     */
    @Test
    public void openLoginPage() {
        //Open url --> opencart
        driver.get(URL);
        //Open login page
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        //Click "Login" button
        driver.findElement(By.linkText("Login")).click();
    }

    /**
     * Click on 'Forgotten password' button and
     * input email.
     * @param email user email address
     * @param message message to assert with web element
     */
    @Test(dataProvider = "email")
    public void sendEmailAndCheckMessage(String email, String message) {
        //Click on "Forgotten Password" button
        driver.findElement(By.xpath("(//a[contains(text(),'Forgotten Password')])[1]")).click();
        //Input email into field
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //Click "Continue" button
        driver.findElement(By.cssSelector("input[class*='btn-primary']")).click();
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //Assert message
        Assert.assertEquals(actual, message);
    }

    /**
     * Using <code>JavascriptExecutor</code> open new tab with email.
     */
    @Test
    public void openEmailInNewTab(){
        //Open new empty tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.open(\"https://accounts.ukr.net/login\")");
        //Create array of tabs
        tabs = new ArrayList<>(driver.getWindowHandles());
        //Switches to new tab
        driver.switchTo().window(tabs.get(1));
    }

    /**
     * Login into email.
     */
    @Test
    public void loginIntoEmail() {
        //Input login
        driver.findElement(By.id("id-l")).click();
        driver.findElement(By.id("id-l")).clear();
        driver.findElement(By.id("id-l")).sendKeys("john.wick.test@ukr.net" + Keys.ENTER);
        //Input password
        driver.findElement(By.id("id-p")).click();
        driver.findElement(By.id("id-p")).clear();
        driver.findElement(By.id("id-p")).sendKeys("qwerty123123");
        //Click login button
        driver.findElement(By.cssSelector("button[class*='button']")).click();
    }

    /**
     * Get list of messages from 'Your Store' which contains link to recover password.
     */
    @Test
    public void getListOfMails() {
        //Get list of mails
        List<WebElement> messages = driver.findElements(By.partialLinkText("Password reset request"));
        //Open last letter
        messages.get(0).click();
        //Get link
        String text = driver.findElement(By.xpath("//a[contains(text(),'http://192.168.227.130/opencart/upload/index.php?route=account/reset')]")).getText();
        //Open link
        driver.get(text);
    }

    /**
     * Change password for user.
     * @param password user password
     * @param confirmPassword confirm password
     * @param messageType type of expected message
     */
    @Test(dataProvider = "password")
    public void changePassword(String password, String confirmPassword, String messageType) {
        //Input new password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //Confirm new password
        driver.findElement(By.id("input-confirm")).click();
        driver.findElement(By.id("input-confirm")).clear();
        driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);
        //Click on 'Continue' button
        driver.findElement(By.cssSelector("button[class*='btn-primary']")).click();

        if (messageType.equals("Failed")) {
            //Get message
            String errorMessage = driver.findElement(By.className("text-danger")).getText();
            //Assert
            Assert.assertTrue(errorMessage.contains("Password"));
        } else {
            //Get message
            String text = driver.findElement(By.cssSelector("div[class*='alert']")).getText();
            //Assert
            Assert.assertEquals(text, successMessage);
        }
    }

    /**
     * Attempt to login under user.
     * @param email user email address
     * @param password user password
     */
    @Test
    @Parameters({"email", "userPassword"})
    public void login(String email, String password) {
        //Input to email field
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //
        //Input to password field
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //
        //Click Login button
        driver.findElement(By.cssSelector("input[value*='Login']")).click();
        //
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.xpath("//div[@id='content']/h2[1]"));
        //
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //
        // Check message
        Assert.assertEquals(actual, "My Account");
    }

    /**
     * Close driver and browser.
     */
    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
