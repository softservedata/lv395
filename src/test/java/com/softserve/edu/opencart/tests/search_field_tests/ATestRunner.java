package com.softserve.edu.opencart.tests.search_field_tests;

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

abstract class ATestRunner {
    private final String DRIVER_ERROR = "ERROR: Chromedriver not Found";
    private final String SERVER_URL = "http://192.168.36.134/opencart/upload/";
    private final String FILE_HAS_NOT_BEEN_CREATED = "File hasn`t been created";
    protected final Logger log = Logger.getLogger(this.getClass());
    protected WebDriver driver;


    @Step("Step: startBrowser")
    @BeforeClass
    public void beforeClass() {
        log.info("Test suite started.");
        URL url = this.getClass().getResource("/chromedriver-windows-32bit.exe");
        LeaveUtils.castExceptionByCondition(url == null, DRIVER_ERROR);
        System.setProperty("webdriver.chrome.driver", url.getPath());
        // this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        driver = new ChromeDriver();
        log.info("ChromeDriver loaded.");
        LeaveUtils.castExceptionByCondition(driver == null, DRIVER_ERROR);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step("Step: exit browser")
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
            log.info("Driver quite.");
        }
    }

    @Step("Step: goto application")
    @BeforeMethod
    public void beforeMethod() {
        driver.get(SERVER_URL);
        log.info("Application loaded.");
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            // TODO Add to Loggers
            //saveImageAttach(prepareImageName());
            driver.get(SERVER_URL);
            log.info("Application loaded.");

        }
        if(testResult.getName() == "stressSearchFieldTest") {
            String fileName=getFileName("stress_test_result_page");
            takeScreenshot(fileName);
            saveImageAttach(fileName);
        }
    }

    @Step("Step: goto home page")
    public HomePage loadApplication() {
        return new HomePage(driver);

    }

    /**
     * Save image that will be copied and shown in the report as a attachment
     *
     * @return byte array containing the bytes read from the file
     */
    @Step("Step: save screenshot")
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
     * Give name to screenshot
     *
     * @param name
     * @return - screenshot`s full name
     */
    public String getFileName(String name) {
        return "./screenshots/" + name + ".png";
    }

    /**
     * Make screenshot
     *
     * @param name - screenshot`s name
     */

    @Step("Step: take screenshot")
    public void takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(source, new
                    File(getFileName(name)));
            log.info("Screenshot was taken.");
        } catch (IOException e) {
            log.error(FILE_HAS_NOT_BEEN_CREATED);
            e.printStackTrace();
        }

    }
}
