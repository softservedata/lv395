package com.softserve.edu.opencart.tests;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tools.LeaveUtils;

public abstract class ATestRunner {
    private final String DRIVER_ERROR = "ERROR: Chromedriver not Found";
    // private final String SERVER_URL = "http://taqc-opencart.epizy.com";
    private final String SERVER_URL = "http://192.168.36.134/opencart/upload/";
    protected WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        URL url = this.getClass().getResource("/chromedriver-windows-32bit.exe");
        LeaveUtils.castExceptionByCondition(url == null, DRIVER_ERROR);
        System.setProperty("webdriver.chrome.driver", url.getPath());
        // this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        driver = new ChromeDriver();
        LeaveUtils.castExceptionByCondition(driver == null, DRIVER_ERROR);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(SERVER_URL);
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            // TODO Add to Loggers
            //saveImageAttach(prepareImageName());
            driver.get(SERVER_URL);
        }
    }

    public HomePage loadApplication() {
        // logger.debug("loadApplication() start");
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
        FileUtils.copyFile(scrFile, new File("/home/milnik/Pictures/cartscreenshot.png"));
    }

    public void takeScreenshot(String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new
                File("./Screenshots/"+name+".png"));
    }
}
