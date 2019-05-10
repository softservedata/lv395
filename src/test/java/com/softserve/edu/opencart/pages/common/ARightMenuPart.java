package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.IUser;

public class ARightMenuPart extends AStatusPart {

    private WebElement myAccountBar;
    private WebElement addressBookBar;
    private WebElement wishListBar;
    private WebElement orderHistoryBar;
    private WebElement downloadsBar;
    private WebElement recurringpaymentsBar;
    private WebElement rewardPointsBar;
    private WebElement returnsBar;
    private WebElement transactionsBar;
    private WebElement newsletterBar;

    public ARightMenuPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        myAccountBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/account']"));
        addressBookBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/address']"));
        wishListBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/wishlist']"));
        orderHistoryBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/order']"));
        downloadsBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/download']"));
        recurringpaymentsBar = driver
                .findElement(By.cssSelector("div.list-group > a[href*='route=account/recurring']"));
        rewardPointsBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/reward']"));
        returnsBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/return']"));
        transactionsBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/transaction']"));
        newsletterBar = driver.findElement(By.cssSelector("div.list-group > a[href*='route=account/newsletter']"));
    }

    // Page Object

    // myAccount
    public WebElement getMyAccountBar() {
        return myAccountBar;
    }

    public String getMyAccountBarText() {
        return getMyAccountBar().getText();
    }

    public void clickMyAccountBar() {
        getMyAccountBar().click();
    }

    // addressBook
    public WebElement getAddressBookBar() {
        return addressBookBar;
    }

    public String getAddressBookBarText() {
        return getAddressBookBar().getText();
    }

    public void clickAddressBookBar() {
        getAddressBookBar().click();
    }

    // wishList
    public WebElement getWishListBar() {
        return wishListBar;
    }

    public String getWishListBarText() {
        return getWishListBar().getText();
    }

    public void clickWishListBar() {
        getWishListBar().click();
    }

    // orderHistory
    public WebElement getOrderHistoryBar() {
        return orderHistoryBar;
    }

    public String getOrderHistoryBarText() {
        return getOrderHistoryBar().getText();
    }

    public void clickOrderHistoryBar() {
        getOrderHistoryBar().click();
    }

    // downloads
    public WebElement getDownloadsBar() {
        return downloadsBar;
    }

    public String getDownloadsBarText() {
        return getDownloadsBar().getText();
    }

    public void clickDownloadsBar() {
        getDownloadsBar().click();
    }

    // recurringpayments
    public WebElement getRecurringpaymentsBar() {
        return recurringpaymentsBar;
    }

    public String getRecurringpaymentsBarText() {
        return getRecurringpaymentsBar().getText();
    }

    public void clickRecurringpaymentsBar() {
        getRecurringpaymentsBar().click();
    }

    // rewardPoints
    public WebElement getRewardPointsBar() {
        return rewardPointsBar;
    }

    public String getRewardPointsBarText() {
        return getRewardPointsBar().getText();
    }

    public void clickRewardPointsBar() {
        getRewardPointsBar().click();
    }

    // returns
    public WebElement getReturnsBar() {
        return returnsBar;
    }

    public String getReturnsBarText() {
        return getReturnsBar().getText();
    }

    public void clickReturnsBar() {
        getReturnsBar().click();
    }

    // transactions
    public WebElement getTransactionsBar() {
        return transactionsBar;
    }

    public String getTransactionsBarText() {
        return getTransactionsBar().getText();
    }

    public void clickTransactionsBar() {
        getTransactionsBar().click();
    }

    // newsletter
    public WebElement getNewsletterBar() {
        return newsletterBar;
    }

    public String getNewsletterBarText() {
        return getNewsletterBar().getText();
    }

    public void clickNewsletterBar() {
        getNewsletterBar().click();
    }

    // Functional

    protected void loginUser(IUser user) {
        new LoginPage(driver).fillLoginForm(user);
    }

    // Business Logic

    public MyAccountPage gotoMyAccountPage(IUser user) {
        clickMyAccountBar();
        loginUser(user);
        return new MyAccountPage(driver);
    }

    public AddressBookPage gotoAddressBookPage(IUser user) {
        clickAddressBookBar();
        loginUser(user);
        return new AddressBookPage(driver);
    }

    public WishListPage gotoWishListPage(IUser user) {
        clickWishListBar();
        loginUser(user);
        return new WishListPage(driver);
    }

    public OrderHistoryPage gotoOrderHistoryPage(IUser user) {
        clickOrderHistoryBar();
        loginUser(user);
        return new OrderHistoryPage(driver);
    }

    public DownloadsPage gotoDownloadsPage(IUser user) {
        clickDownloadsBar();
        loginUser(user);
        return new DownloadsPage(driver);
    }

    public RecurringPaymentsPage gotoRecurringPaymentsPage(IUser user) {
        clickRecurringpaymentsBar();
        loginUser(user);
        return new RecurringPaymentsPage(driver);
    }

    public RewardPointsPage gotoRewardPointsPage(IUser user) {
        clickRewardPointsBar();
        loginUser(user);
        return new RewardPointsPage(driver);
    }

    public ReturnsPage gotoReturnsPage(IUser user) {
        clickReturnsBar();
        loginUser(user);
        return new ReturnsPage(driver);
    }

    public TransactionPage gotoTransactionPage(IUser user) {
        clickTransactionsBar();
        loginUser(user);
        return new TransactionPage(driver);
    }

    public NewsletterPage gotoNewsletterPage(IUser user) {
        clickNewsletterBar();
        loginUser(user);
        return new NewsletterPage(driver);
    }
}
