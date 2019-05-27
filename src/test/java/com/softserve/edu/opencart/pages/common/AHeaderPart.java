package com.softserve.edu.opencart.pages.common;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.pages.account.*;
import com.softserve.edu.opencart.pages.shop.CartProductContainer;
import com.softserve.edu.opencart.pages.shop.EmptyCartComponent;
import com.softserve.edu.opencart.pages.shop.ShoppingCartPage;
import com.softserve.edu.opencart.pages.shop.ShoppingCartProductsContainer;

import com.softserve.edu.opencart.tools.StringHandler;
import com.softserve.edu.opencart.tools.utils_for_search_field.PageDoesNotExistException;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.LoggedMyAccount;
import com.softserve.edu.opencart.data.UnloggedMyAccount;
import com.softserve.edu.opencart.tools.LeaveUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AHeaderPart {
    protected final Logger log = Logger.getLogger(this.getClass());
    protected final String OPTION_NULL_MESSAGE = "DropdownOption is null";
    protected final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    protected final String PAGE_DO_NOT_EXIST="Page do not exist!!!";
    //
    protected final String TAG_ATTRIBUTE_VALUE = "value";
    protected final String TAG_ATTRIBUTE_SRC = "src";
    //
    protected final String LIST_CURENCIES_CSSSELECTOR = "div.btn-group.open ul.dropdown-menu li";
    protected final String DROPDOWN_MYACCOUNT_CSSSELECTOR = ".dropdown-menu-right li";
    //
    protected WebDriver driver;
    protected WebDriverWait wait;
    //

    private WebElement currency;
    private WebElement myAccount;
    private WebElement wishList;
    private WebElement shoppingCart;
    private WebElement checkout;
    private WebElement logo;
    private WebElement searchField;
    private WebElement searchButton;
    private WebElement cartButton;
    //
    private DropdownComponent dropdownOptions;

    protected AHeaderPart(WebDriver driver) {
        this.driver = driver;
        waitElements();
        initElements();
    }

    private void initElements() {
        currency = driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        myAccount = driver.findElement(By.cssSelector(".list-inline > li > a.dropdown-toggle"));
        wishList = driver.findElement(By.id("wishlist-total"));
        shoppingCart = driver.findElement(By.cssSelector("a[title='Shopping Cart']"));
        checkout = driver.findElement(By.cssSelector("a[title='Checkout']"));
        logo = driver.findElement(By.cssSelector("#logo a"));
        searchField = driver.findElement(By.name("search"));
        searchButton = driver.findElement(By.cssSelector("button.btn.btn-default"));
        cartButton = driver.findElement(By.cssSelector("#cart > button"));
    }

    private void waitElements() {
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#logo a")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cart > button")));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Page Object

    // currency;
    public WebElement getCurrency() {
        return currency;
    }

    public String getCurrencyText() {
        return getCurrency().getText();
    }

    public void clickCurrency() {
        getCurrency().click();
    }

    // myAccount;
    public WebElement getMyAccount() {
        return myAccount;
    }

    public String getMyAccountText() {
        return getMyAccount().getText();
    }

    public void clickMyAccount() {
        getMyAccount().click();
    }

    // wishList;
    public WebElement getWishList() {
        return wishList;
    }

    public String getWishListText() {
        return getWishList().getText();
    }

    public void clickWishList() {
        getWishList().click();
    }

    public int getWishListNumber() {
        return StringHandler.extractFirstNumber(getWishListText());
    }

    // shoppingCart;
    public WebElement getShoppingCart() {
        return shoppingCart;
    }

    public String getShoppingCartText() {
        return getShoppingCart().getText();
    }

    public void clickShoppingCart() {
        getShoppingCart().click();
    }

    // checkout;
    public WebElement getCheckout() {
        return checkout;
    }

    public String getCheckoutText() {
        return getCheckout().getText();
    }

    public void clickCheckout() {
        getCheckout().click();
    }

    // logo
    public WebElement getLogo() {
        return logo;
    }

    public void clickLogo() {
        getLogo().click();
    }

    // searchField
    public WebElement getSearchField() {
        return searchField;
    }

    public String getSearchFieldText() {
        return getSearchField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearSearchField() {
        getSearchField().clear();
    }

    public void clickSearchField() {
        getSearchField().click();
    }

    public void setSearchField(String text) {
        getSearchField().sendKeys(text);
    }

    // searchButton
    public WebElement getSearchButton() {
        return searchButton;
    }

    public void clickSearchButton() {
        getSearchButton().click();
    }

    // cartButton
    public WebElement getCartButton() {
        return cartButton;
    }

    public String getCartButtonText() {
        return getCartButton().getText();
    }

    public void clickCartButton() {
        getCartButton().click();
    }

    // dropdownOptions
    protected DropdownComponent getDropdownOptions() {
        LeaveUtils.castExceptionByCondition(dropdownOptions == null, OPTION_NULL_MESSAGE);
        return dropdownOptions;
    }

    private DropdownComponent createDropdownOptions(By searchLocator) {
        dropdownOptions = new DropdownComponent(driver, searchLocator);
        return getDropdownOptions();
    }

    private void clickDropdownOptionByPartialName(String optionName) {
        LeaveUtils.castExceptionByCondition(!getDropdownOptions().isExistDropdownOptionByPartialName(optionName),
                String.format(OPTION_NOT_FOUND_MESSAGE, optionName, dropdownOptions.getListOptionsText().toString()));
        getDropdownOptions().clickDropdownOptionByPartialName(optionName);
        dropdownOptions = null;
    }

    private void closeDropdownOption() {
        clickSearchField();
        dropdownOptions = null;
    }

    // Functional

    // currency
    private void openCurrencyDropdownOption() {
        clickSearchField();
        clickCurrency();
        createDropdownOptions(By.cssSelector(LIST_CURENCIES_CSSSELECTOR));
    }

    protected void clickCurrencyByPartialName(Currencies optionName) {
        openCurrencyDropdownOption();
        clickDropdownOptionByPartialName(optionName.toString());
    }

    public List<String> getListCurrencyNames() {
        openCurrencyDropdownOption();
        List<String> result = getDropdownOptions().getListOptionsText();
        closeDropdownOption();
        return result;
    }

    // myAccount
    private void clickDropdownMyAccountByPartialName(String componentName) {
        clickSearchField();
        clickMyAccount();
        createDropdownOptions(By.cssSelector(DROPDOWN_MYACCOUNT_CSSSELECTOR));
        clickDropdownOptionByPartialName(componentName);
    }

    protected void clickUnloggedMyAccountByPartialName(UnloggedMyAccount optionName) {
        // TODO Check if Unlogged
        clickDropdownMyAccountByPartialName(optionName.toString());
    }

    protected void clickLoggedMyAccountByPartialName(LoggedMyAccount optionName) {
        // TODO Check if loggined
        clickDropdownMyAccountByPartialName(optionName.toString());
    }

    // searchField
    private void fillSearchField(String name) {
        clickSearchField();
        clearSearchField();
        setSearchField(name);
    }

    // Business Logic
//    public boolean tryGotoHomePage() {
//        boolean weAreOnHomePage = true;
//        try {
//            gotoHomePage();
//        } catch (Exception e) {
//            weAreOnHomePage = false;
//        }
//        return weAreOnHomePage;
//    }

    public WishListPage gotoWishListPage() {
        return new WishListPage(driver);
    }

    public WishListEmptyPage gotoEmptyWishListPage() {
        return new WishListEmptyPage(driver);
    }
//
    public void fillField(String text) {
        fillSearchField(text);
        clickSearchButton();
    }

    @Step("Go to HomePage")
    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    public SuccessfulSearchPage searchProducts(SearchFilter searchItems) {
        try {
            fillSearchField(searchItems.getProductSearchName());
            clickSearchButton();
            return new SuccessfulSearchPage(driver);
        } catch (Exception e) {
            log.error(PAGE_DO_NOT_EXIST);
            throw new PageDoesNotExistException(PAGE_DO_NOT_EXIST);
        }
    }

    @Step("Step: search some product")
    public SuccessfulSearchPage searchProducts(String searchItem) {
        try {
            fillSearchField(searchItem);
            clickSearchButton();
            return new SuccessfulSearchPage(driver);
        } catch (Exception e) {
            log.error(PAGE_DO_NOT_EXIST);
            throw new PageDoesNotExistException(PAGE_DO_NOT_EXIST);
        }
    }

    @Step("Step: goto search page")
    public UnsuccessfulSearchPage gotoSearchPageWithFilters() {
        try {
            fillSearchField("");
            clickSearchButton();
            return new UnsuccessfulSearchPage(driver);
        } catch (Exception e) {
            log.error(PAGE_DO_NOT_EXIST);
            throw new PageDoesNotExistException(PAGE_DO_NOT_EXIST);
        }
    }

    @Step("Step: search some incorrect thing")
    public UnsuccessfulSearchPage unsuccessfulSearch(String text){
        try {
            fillSearchField(text);
            clickSearchButton();
            return new UnsuccessfulSearchPage(driver);
        } catch (Exception e) {
            log.error(PAGE_DO_NOT_EXIST);
            throw new PageDoesNotExistException(PAGE_DO_NOT_EXIST);
        }
    }

    public UnsuccessfulSearchPage unsuccessfulSearch(SearchFilter invalidSearchItems)  {
        try {
            return unsuccessfulSearch(invalidSearchItems.getProductSearchName());
        } catch (Exception e) {
            log.error(PAGE_DO_NOT_EXIST);
            throw new PageDoesNotExistException(PAGE_DO_NOT_EXIST);
        }
    }

    public ShoppingCartPage gotoShoppingCartPage() {
        clickShoppingCart();
        return new ShoppingCartPage(driver);
    }

    @Step("Go to LoginPage")
    public LoginPage gotoLoginPage() {
        clickUnloggedMyAccountByPartialName(UnloggedMyAccount.LOGIN);
        return new LoginPage(driver);
    }

    @Step("Go to register page")
    public RegisterPage gotoRegisterPage() {
        clickUnloggedMyAccountByPartialName(UnloggedMyAccount.REGISTER);
        return new RegisterPage(driver);
    }

    @Step("Logout")
    public AccountLogoutPage logout() {
        clickLoggedMyAccountByPartialName(LoggedMyAccount.LOGOUT);
        return new AccountLogoutPage(driver);
    }

    @Step("Open Cart")
    public CartProductContainer openCartProductContainer() {
        clickCartButton();
        return new CartProductContainer(driver);
    }

    @Step("Open empty cart")
    public EmptyCartComponent openEmptyCart() {
        clickCartButton();
        return new EmptyCartComponent(driver);
    }

    // ShoppingCart

    @Step("Open cart")
    public ShoppingCartProductsContainer openShoppingCartProductsContainer() {
        clickShoppingCart();
        return new ShoppingCartProductsContainer(driver);
    }

    @Step("Close cart")
    public HomePage closeCartProductsContainer() {
        clickShoppingCart();
        return new HomePage(driver);
    }

}