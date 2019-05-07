package com.softserve.edu.opencart.pages.common;

import com.softserve.edu.opencart.data.Currencies;
import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.pages.common.ShoppingCart.ShoppingCartProductsContainer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class ShoppingCartPage extends AStatusPart {

    public static final String SHOPPING_CART_LABEL_TEXT = "Shopping Cart";
    private static final String PRICE_TABLE_CSSSELECTOR = ".col-sm-4.col-sm-offset-8 .table.table-bordered";
    private ShoppingCartProductsContainer shoppingCartProductsContainer;
    //
    WebElement shoppingCartLabel;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void initElements() {
        shoppingCartLabel = driver.findElement(By.cssSelector(".col-sm-12 > h1"));
        shoppingCartProductsContainer = new ShoppingCartProductsContainer(driver);
    }

    // Page Object

    public ShoppingCartProductsContainer getShoppingCartProductsContainer() {
        return shoppingCartProductsContainer;
    }

    public WebElement getShoppingCartLabel() {
        return shoppingCartLabel;
    }

    public String getShoppingCartLabelText() {
        return getShoppingCartLabel().getText();
    }

    // Functional

    public ShoppingCartPage setQuantityProductsByName(Product product, String quantity) {
        getShoppingCartProductsContainer().quantityProductsByName(product, quantity);
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage removeProductByName(Product product) {
        getShoppingCartProductsContainer().removeProductFromShoppingCartByName(product);
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency);
        return new ShoppingCartPage(driver);
    }

    public BigDecimal getUnitProductPriceByCurrency(Currencies currency, Product product) {
        return chooseCurrency(currency).getShoppingCartProductsContainer().getUnitPriceByName(product);
    }

    public BigDecimal getTotalProductPriceByCurrency(Currencies currency, Product product) {
        return chooseCurrency(currency).getShoppingCartProductsContainer().getTotalPriceByName(product);
    }

    public String getShoppingCartCurrencySymbol(Product product) {
        return getShoppingCartProductsContainer().getCurrencyByProduct(product);
    }

    public TotalPriceTableComponent getTotalPriceTableComponent() {
        return new TotalPriceTableComponent(driver.findElement(By.cssSelector(PRICE_TABLE_CSSSELECTOR)));
    }

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

}
