package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.pages.common.AStatusPart;
import com.softserve.edu.opencart.pages.common.CheckoutPage;
import com.softserve.edu.opencart.pages.common.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class ShoppingCartPage extends AStatusPart {

    public static final String SHOPPING_CART_LABEL_TEXT = "Shopping Cart";
    public static final String SHOPPING_CART_ERROR_QUANTITY_TEXT = "not available";
    private static final String PRICE_TABLE_CSSSELECTOR = ".col-sm-4.col-sm-offset-8 .table.table-bordered";
    private ShoppingCartProductsContainer shoppingCartProductsContainer;
    private TotalPriceTableComponent totalPriceTableComponent;

    //
    public WebElement shoppingCartLabel;
    public WebElement continueButton;
    public WebElement checkoutButton;
    public WebElement errorLabel;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        shoppingCartLabel = driver.findElement(By.cssSelector(".col-sm-12 > h1"));
        shoppingCartProductsContainer = new ShoppingCartProductsContainer(driver);
        continueButton = driver.findElement(By.xpath("//*[@class='pull-left']/a"));
        checkoutButton = driver.findElement(By.xpath("//*[@class='pull-right']/a"));
    }

    // Page Object

    public ShoppingCartProductsContainer getShoppingCartProductsContainer() {
        return shoppingCartProductsContainer;
    }

    public TotalPriceTableComponent getTotalPriceTableComponent(){
        return new TotalPriceTableComponent(driver.findElement(By.cssSelector(PRICE_TABLE_CSSSELECTOR)));
    }

    public WebElement getContinueButton () {
        return continueButton;
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    public WebElement getCheckoutButton () {
        return checkoutButton;
    }

    public void clickCheckoutButton1() {
        getCheckoutButton().click();
    }

    public WebElement getShoppingCartLabel() {
        return shoppingCartLabel;
    }

    public String getShoppingCartLabelText() {
        return getShoppingCartLabel().getText();
    }

    private void initErrorLabel(){
        errorLabel = driver.findElement(By.xpath("//*[@class='alert alert-danger']"));
    }

    public String getErrorQuantityLabel(){
        try {
            initErrorLabel();
            return errorLabel.getText();
        }  catch (Exception e) {
            throw new NotFoundException("Page do not exist!!!");
        }
    }



    // Functional

    public ShoppingCartPage setQuantityProductsByName(IProduct product, String quantity) {
        getShoppingCartProductsContainer().quantityProductsByName(product, quantity);
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage removeProductByName(IProduct product) {
        getShoppingCartProductsContainer().removeProductFromShoppingCartByName(product);
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new ShoppingCartPage(driver);
    }



    public BigDecimal getUnitProductPriceByCurrency(Currencies currency, IProduct product) {
        return chooseCurrency(currency).getShoppingCartProductsContainer().getUnitPriceByName(product);
    }

    public BigDecimal getTotalProductPriceByCurrency(Currencies currency, IProduct product) {
        return chooseCurrency(currency).getShoppingCartProductsContainer().getTotalPriceByName(product);
    }

    public String getShoppingCartCurrencySymbol(IProduct product) {
        return getShoppingCartProductsContainer().getCurrencyByProduct(product);
    }

   /* public TotalPriceTableComponent getTotalPriceTableComponent() {
        return new TotalPriceTableComponent(driver.findElement(By.cssSelector(PRICE_TABLE_CSSSELECTOR)));
    }*/

    public BigDecimal getTableSubTotalByCurrency(Currencies currency) {
        return chooseCurrency(currency).getTotalPriceTableComponent().getSubTotalValue();
    }

    public BigDecimal getTableEcoTaxByCurrency(Currencies currency) {
        return chooseCurrency(currency).getTotalPriceTableComponent().getTableEcoTaxValue();
    }

    public BigDecimal getTableVATByCurrency(Currencies currency) {
        return chooseCurrency(currency).getTotalPriceTableComponent().getTableVATValue();
    }

    public BigDecimal getTableTotalByCurrency(Currencies currency) {
        return chooseCurrency(currency).getTotalPriceTableComponent().getTableTotalValue();
    }

    // Business Logic

    public HomePage gotoHomePageByContinueButton() {
        clickContinueButton();
        return new HomePage(driver);
    }

    public CheckoutPage gotoCheckoutPageByCheckoutButton() {
        clickCheckoutButton1();
        return new CheckoutPage(driver);
    }

}
