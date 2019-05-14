package com.softserve.edu;

//import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.byCssSelector;
//import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Screenshots.takeScreenShot;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

public class SelenideAjaxTest {
 
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
        		this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath().substring(1));
        //ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //options.addArguments("--no-proxy-server");
        //options.addArguments("--headless");
        //WebDriver driver = new ChromeDriver(options);
        //Configuration.browser. = "chrome";
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 30;
        //
        open("https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/paging/");
    }
 
    @Test
    public void AjaxIframePage() throws Exception {
    	executeJavaScript("arguments[0].scrollIntoView(true);",
    			$("#use-paging-with-other-data-processing-plugins"));
    	switchTo().frame($("#grid-paging-remote-paging-demo-pane-preview iframe"));
    	//switchTo().innerFrame(frames)
    	//Thread.sleep(3000);
    	$(byXpath("//td[text()='Nevada']")).waitUntil(text("Nevada"), 4000);
    	//Thread.sleep(3000);
    	//
    	SelenideElement tdNevadaFirstData = $(byXpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaFirstData.getText() = " + tdNevadaFirstData.getText());
        String removeText = $(byXpath("//td[text()='Nevada']/preceding-sibling::td[3]")).getText();
        System.out.println("removeText = " + removeText);
        //
        // Goto to Page 2
        //$(byXpath("//span[text()='2']")).waitUntil(text("2"), 10000);
        $(byXpath("//span[text()='2']/..")).click();
        //
        //driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        //(new WebDriverWait(driver, 10)).until(
        //		ExpectedConditions.invisibilityOfElementLocated(
        //				By.xpath("//td[text()='" + removeText + "']")));
        //Thread.sleep(5000);
        //$(byXpath("//td[text()='Nevada']/preceding-sibling::td[3]")).shouldNotHave(text(removeText));
        $(byXpath("//td[text()='Nevada']/preceding-sibling::td[3]")).waitWhile(text(removeText), 5000);
        //
        SelenideElement tdNevadaSecondData = $(byXpath("//td[text()='Nevada']/preceding-sibling::td[2]"));
        System.out.println("tdNevadaSecondData.getText() = " + tdNevadaSecondData.getText());
        //
        $(byXpath("//td[text()='Nevada']/preceding-sibling::td[3]")).shouldHave(text("36987"));
        //
    	//$(byText("Incoming Tests")).click();
    	//$(".login a[href*='login']").click();
        //$("#username").setValue("username");
        //$("#password").setValue("Hello");
        //takeScreenShot("complex-form.png");
        //$("#password").shouldHave(text("Hello"));
        //$("#password").shouldHave(value("Hello")); // Assert
        //assertTrue($$("#paymentScheduleTable").size() == 0);
    	//
    	Thread.sleep(2000);
    } 
 
}
