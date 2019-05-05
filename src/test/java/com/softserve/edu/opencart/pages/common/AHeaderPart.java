package com.softserve.edu.opencart.pages.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.LoggedMyAccount;
import com.softserve.edu.opencart.data.SearchFilter;
import com.softserve.edu.opencart.data.UnloggedMyAccount;
import com.softserve.edu.opencart.tools.LeaveUtils;

public abstract class AHeaderPart {

    protected final String OPTION_NULL_MESSAGE = "DropdownOption is null";
    protected final String OPTION_NOT_FOUND_MESSAGE = "Option %s not found in %s";
    //
    protected final String TAG_ATTRIBUTE_VALUE = "value";
    protected final String TAG_ATTRIBUTE_SRC = "src";
    //
    protected final String LIST_CURENCIES_CSSSELECTOR = "div.btn-group.open ul.dropdown-menu li";
    protected final String DROPDOWN_MYACCOUNT_CSSSELECTOR = ".dropdown-menu-right li";
    //
    protected WebDriver driver;
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
    private CartProductContainer cartProductContainer;

    protected AHeaderPart(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        currency = driver.findElement(By.cssSelector(".btn.btn-link.dropdown-toggle"));
        myAccount = driver.findElement(By.cssSelector(".list-inline > li > a.dropdown-toggle"));
        wishList = driver.findElement(By.id("wishlist-total"));
        shoppingCart = driver.findElement(By.cssSelector("a[title='Shopping Cart']"));
        checkout = driver.findElement(By.cssSelector("a[title='Checkout']"));
        logo = driver.findElement(By.id("logo"));
        searchField = driver.findElement(By.name("search"));
        searchButton = driver.findElement(By.cssSelector("button.btn.btn-default"));
        cartButton = driver.findElement(By.cssSelector("#cart > button"));
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

    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    public SuccessfulSearchPage searchProducts(SearchFilter searchItems) {
        fillSearchField(searchItems.getProductSearchName());
        clickSearchButton();
        return new SuccessfulSearchPage(driver);
    }

    public UnsuccessfulSearchPage unsuccessfulSearch(String text) {
        fillSearchField(text);
        clickSearchButton();
        return new UnsuccessfulSearchPage(driver);
    }

    public UnsuccessfulSearchPage unsuccessfulSearch(SearchFilter invalidSearchItems) {
        return unsuccessfulSearch(invalidSearchItems.getProductSearchName());
    }

    public LoginPage gotoLoginPage() {
        clickUnloggedMyAccountByPartialName(UnloggedMyAccount.LOGIN);
        return new LoginPage(driver);
    }

    public RegisterPage gotoRegisterPage() {
        clickUnloggedMyAccountByPartialName(UnloggedMyAccount.REGISTER);
        return new RegisterPage(driver);
    }

    public AccountLogoutPage logout() {
        clickLoggedMyAccountByPartialName(LoggedMyAccount.LOGOUT);
        return new AccountLogoutPage(driver);
    }

}