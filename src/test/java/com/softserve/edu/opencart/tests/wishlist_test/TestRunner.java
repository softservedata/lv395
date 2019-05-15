package com.softserve.edu.opencart.tests.wishlist_test;

import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tools.LeaveUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

/**
 * Class for main Selenium WebDriver operations
 * and test functional.
 */
public abstract class TestRunner {
    private final String DRIVER_ERROR = "ERROR: Chromedriver not Found";
    // private final String SERVER_URL = "http://taqc-opencart.epizy.com";
    private final String SERVER_URL = "http://192.168.155.134/opencart/upload";
    protected final Logger log = Logger.getLogger(this.getClass());
    private WebDriver driver;

    /**
     * Method loads Selenium WebDriver.
     */
    @BeforeClass
    public void beforeClass() {
        log.info("ShoppingCart test suite start!");
        URL url = this.getClass().getResource("/chromedriver.exe");
        LeaveUtils.castExceptionByCondition(url == null, DRIVER_ERROR);
        System.setProperty("webdriver.chrome.driver", url.getPath());
        driver = new ChromeDriver();
        LeaveUtils.castExceptionByCondition(driver == null, DRIVER_ERROR);
        log.info("ChromeDriver loaded!");
        //driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Close web-driver after test run.
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        log.info("ChromeDriver quite!");
    }

    /**
     * Method loads web-site.
     */
    @BeforeMethod
    public void beforeMethod() {
        driver.get(SERVER_URL);
        log.info("Web Application loaded!");
    }

    /**
     * If something in tests goes wrong -
     * take a screenshot and load web-site again.
     *
     * @param testResult results of test running.
     */
    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            log.error("Test failed!");
            try {
                takeScreenShot();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("Web Application loaded!");
            driver.get(SERVER_URL);
        }
    }

    /**
     * Method for loading Home Page of our application.
     *
     * @return opencart Home Page.
     */
    @Step("Load HomePage")
    public HomePage loadApplication() {
        log.debug("loadApplication() start");
        return new HomePage(driver);
    }

    /**
     * Save image that will be copied and shown in the report as a attachment
     *
     * @return byte array containing the bytes read from the file
     */
    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveImageAttach(String attachName) {
        byte[] result = null;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            result = Files.readAllBytes(scrFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Make screenshot of page
     */
    @Step("ScreenShot of Cart STEP")
    private void takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("img/screenshot.png"));
        log.info("Screenshot was taken");
    }

}