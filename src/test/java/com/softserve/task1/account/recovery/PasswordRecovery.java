package com.softserve.task1.account.recovery;

import com.softserve.edu.Customer;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PasswordRecovery {
    private WebDriver driver;

    private final String URL = "http://192.168.227.130/opencart/upload";

    private final String correctEmail = "john.wick.test@ukr.net";
    private final String incorrectEmail = "not.john.wick.test@ukr.net";
    private final String emptyEmail = "";

    private final String password = "qwerty";
    private final String successMessage = "An email with a confirmation link has been sent your email address.";
    private final String failMessage = "Warning: The E-Mail Address was not found in our records, please try again!";
    private final String changePasswordMessage = "Success: Your password has been successfully updated.";


    @DataProvider
    public Object[][] email() {
        return new Object[][]{
                //TODO add verification for null
                {correctEmail, successMessage}, // correct Email --> Success Message
                {emptyEmail, failMessage}, // empty Email --> Error Message
                {incorrectEmail, failMessage} // incorrect Email --> Error Message
        };
    }

    @BeforeClass
    public void openBrowser() {
        //Set Properties
        System.setProperty("webdriver.chrome.driver", "./lib/drivers/chromedriver.exe");
        System.getProperty("webdriver.chrome.driver");
        //Create new WebDriver object
        driver = new ChromeDriver();
        //Set window --> maximize
        driver.manage().window().maximize();
        //Set timeout 20 sec
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void openLoginPage() {
        //Open url --> opencart
        driver.get(URL);
        //
        //Open login page
        driver.findElement(By.xpath("//a[contains(text(),'My Account')]")).click();
        //
        //Click "Login" button
        driver.findElement(By.linkText("Login")).click();
    }

    @Test(dataProvider = "email", priority = 2)
    public void sendEmailAndCheckMessage(String email, String message) {
        //Click on "Forgotten Password" button
        driver.findElement(By.xpath("(//a[contains(text(),'Forgotten Password')])[1]")).click();
        //
        //Input email into field
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(email);
        //
        //Click "Continue" button
        driver.findElement(By.cssSelector("input[class*='btn-primary']")).click();
        //
        //Get WebElement object
        WebElement seleniumServerVersion = driver.findElement(By.cssSelector("div[class*='alert']"));
        //
        //Get text from WebElement
        String actual = seleniumServerVersion.getText();
        //
        //Assert message
        Assert.assertEquals(actual, message);
    }

    @Test(priority = 3)
    public void openEmail() {
        //Open new empty tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.open(\"https://accounts.ukr.net/login\")");
        //
        //Create array of tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        //
        //Switches to new tab
        driver.switchTo().window(tabs.get(1));
        //
        //Input login
        driver.findElement(By.id("id-l")).click();
        driver.findElement(By.id("id-l")).clear();
        driver.findElement(By.id("id-l")).sendKeys("john.wick.test@ukr.net" + Keys.ENTER);
        //
        //Input password
        driver.findElement(By.id("id-p")).click();
        driver.findElement(By.id("id-p")).clear();
        driver.findElement(By.id("id-p")).sendKeys("qwerty123123");
        //Click login button
        driver.findElement(By.cssSelector("button[class*='button']")).click();
    }

    @Test(priority = 4)
    public void getListOfMails() throws InterruptedException {
        List<WebElement> messages = driver.findElements(By.partialLinkText("Password reset request"));

        messages.get(0).click();

        Thread.sleep(3000);

        String text = driver.findElement(By.xpath("//a[contains(text(),"
                + "'http://192.168.227.129/opencart/upload/index.php?route=account/reset')]")).getText();

        driver.get(text);
        Thread.sleep(3000);
    }

    @Test
    public void changePassword(){
        //Input new password
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);
        //
        //Confirm new password
        driver.findElement(By.id("input-confirm")).click();
        driver.findElement(By.id("input-confirm")).clear();
        driver.findElement(By.id("input-confirm")).sendKeys(password);
        //
        //Click on 'Continue' button
        driver.findElement(By.cssSelector("button[class*='btn-primary']")).click();
        //
        //Get message
        String text = driver.findElement(By.cssSelector("div[class*='alert']")).getText();
        //
        //Assert
        Assert.assertEquals(changePasswordMessage, text);
    }

    @Test
    public void login(){
        //TODO add login logic
    }

    @Test(priority = 5)
    public void closeMailTab() {
        //
        //Close current tab using javascript
        ((JavascriptExecutor) driver).executeScript("window.close()");
    }

    @Test(priority = 6)
    public void loginWithNewCredentials() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
