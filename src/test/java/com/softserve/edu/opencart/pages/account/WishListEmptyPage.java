package com.softserve.edu.opencart.pages.account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.pages.common.AStatusPart;
import com.softserve.edu.opencart.pages.account.MyAccountPage;

public class WishListEmptyPage extends AStatusPart{

    public final String EXPECT_EMPTY_WISH_TEXT = "Your wish list is empty.";

    private WebElement EmptyWishListPage;
    private WebElement EmptyWishListButtonContinue;

    public WishListEmptyPage(WebDriver driver) {
        super(driver);
        initEmptyWishListPage();
    }

    private void initEmptyWishListPage() {
        EmptyWishListPage = driver.findElement(By.cssSelector("#content p"));
        EmptyWishListButtonContinue = driver.findElement(By.cssSelector(".btn.btn-primary"));
    }

    public WebElement getEmptyWishListPage() {
        return EmptyWishListPage;
    }

    public WebElement getEmptyWishListButtonContinue() {
        return EmptyWishListButtonContinue;
    }

    public String getEmptyWishListText() {
        return getEmptyWishListPage().getText();
    }


    public MyAccountPage clickEmptyWishListButtonContinue() {
        getEmptyWishListButtonContinue().click();
        return new MyAccountPage(driver);
    }
}