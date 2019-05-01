package com.softserve.createuserpage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends PageObject {

    @FindBy(id = "identifierId")
    private WebElement loginField;

    @FindBy(css = "span[class*='RveJvd']")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "div[id='passwordNext'] > content > span")
    private WebElement confirmButton;

    /**
     * Confirms that user received
     * confirmation message.
     */
    @FindBy(css = ("div[class*='y6'] > span"))
    private WebElement messageHeader;

    public EmailPage(WebDriver driver) {
        super(driver);
    }

    //Buttons

    /**
     * NextButton;
     */
    public WebElement getNextButton() {
        return nextButton;
    }

    public void clickNextButton() {
        getNextButton().click();
    }

    /**
     * ConfirmButton;
     */
    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public void clickConfirmButton() {
        getConfirmButton().click();
    }

    //Functionality

    /**
     * Confirmation Header.
     */
    public String confirmationHeader(){
        return messageHeader.getText();
    }

    /**
     * login;
     */
    public WebElement getLoginField() {
        return loginField;
    }

    public void setLoginField() {
        getLoginField().sendKeys("testuser395lv@gmail.com");
    }

    /**
     * password.
     */
    public WebElement getPassword() {
        return password;
    }

    public void setPassword() {
        getPassword().sendKeys("lv395testuser");
    }

    //Business Logic

    /**
     * Login in Email.
     */
    public void loginEmail() {
        setLoginField();
        clickNextButton();
        setPassword();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("div[id='passwordNext'] > content > span")));
        clickConfirmButton();
    }
}
