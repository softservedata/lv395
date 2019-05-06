package com.softserve.edu.opencart.tests;

import java.net.URL;
import java.util.concurrent.TimeUnit;

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
    private final String SERVER_URL = "http://taqc-opencart.epizy.com/";

    // protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        System.setProperty("webdriver.chrome.driver", "lib/chromedriver-windows-32bit.exe");
//        System.getProperty("webdriver.chrome.driver");
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

}
