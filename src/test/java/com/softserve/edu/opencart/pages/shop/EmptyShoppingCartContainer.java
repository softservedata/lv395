package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.pages.common.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyShoppingCartContainer extends AShoppingCartPage {

    private WebElement emptyShoppingCartMessage;
    private WebElement continueButton;

    public EmptyShoppingCartContainer(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        emptyShoppingCartMessage = driver.findElement(By.cssSelector("#content > p"));
        continueButton = driver.findElement(By.xpath("//*[@id='content']/div/div/a"));
    }

    // Page Object

    public WebElement getEmptyCartMessage() {
        return emptyShoppingCartMessage;
    }

    public String getEmptyCartMessageText() {
        return getEmptyCartMessage().getText();
    }

    // Continue button

    // cartProductName
    public WebElement getContinueButton() {
        return continueButton;
    }

    public void clickCartProductName() {
        getContinueButton().click();
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    // Functional

    // Business Logic

    public HomePage clickContinueButtonOnEmptyShoppingCartPage() {
        clickContinueButton();
        return new HomePage(driver);
    }
}
