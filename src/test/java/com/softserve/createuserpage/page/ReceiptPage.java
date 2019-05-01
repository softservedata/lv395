package com.softserve.createuserpage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptPage extends PageObject {

    private String EMAIL_URL = "https://accounts.google.com/signin/v2/" +
            "identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%" +
            "2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&" +
            "flowEntry=ServiceLogin";

    @FindBy(css = ("#content > h1"))
    private WebElement header;

    @FindBy(css = ("a[href*='opencart/upload/index.php']:nth-of-type(13)"))
    private WebElement logOutButton;

    @FindBy(css = "a[href*='opencart/upload/index.php']:nth-of-type(2)")
    private WebElement registerButton;

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Confirmation Header;
     */
    public String confirmationHeader(){
        return header.getText();
    }

    /**
     * logOutButton;
     */
    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public void clickLogOutButton() {
        getLogOutButton().click();
    }

    /**
     * RegisterButton;
     */
    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void clickRegisterButton() {
        getRegisterButton().click();
    }

    /**
     * GotoEmail and login.
     */
    public void gotoEmail() {
        driver.get(EMAIL_URL);
    }

}
