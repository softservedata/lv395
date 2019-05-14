package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.shop.ShoppingCartPage;
import com.softserve.edu.opencart.pages.shop.TotalPriceTableComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static com.softserve.edu.opencart.tools.PriceUtils.*;

//@Epic("Functional Testing")
//@Feature("PriceTableTest")
public class ShoppingCartTotalTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {
                    ProductRepository.getMacBook(), ProductRepository.getIPhone3()
                }
        };
    }

  //  @Description("Test Description: This test checks if all data in the cart table is displayed correctly")
  //  @Severity(SeverityLevel.BLOCKER)
 //   @Story("Add the item to the cart and check if all data in the cart table is displayed correctly")
    @Test(dataProvider = "productData", description = "Checking data in total price table", priority = 1)
    public void checkSuperTotalTest(IProduct Mac, IProduct iPhone) {
       // log.debug("checkTotalPriceTable test started");
        // Steps
        TotalPriceTableComponent totalPriceTableComponent = loadApplication()
                .addProductToCart(Mac)
                .addProductToCart(iPhone)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .getTotalPriceTableComponent();
        // Check
        Assert.assertEquals(totalPriceTableComponent.getSubTotalValue(),
                Mac.getSubTotal().add(iPhone.getSubTotal()));
        Assert.assertEquals(totalPriceTableComponent.getTableEcoTaxValue(),
                Mac.getEcoTax().add(iPhone.getEcoTax()));
        Assert.assertEquals(totalPriceTableComponent.getTableVATValue(),
                Mac.getVat().add(iPhone.getVat()));
        Assert.assertEquals(totalPriceTableComponent.getTableTotalValue(),
                Mac.getTotalPrice().add(iPhone.getTotalPrice()));
  //      log.debug("checkTotalPriceTable test finished");
    }

    @Test(dataProvider = "productData", description = "Checking data in shopping cart table", priority = 2)
    public void checkProductTotalTest(IProduct Mac, IProduct iPhone) throws InterruptedException {
        log.debug("checkTotalPriceTable test started");
        // Steps
        ShoppingCartPage shoppingCartPage = loadApplication()
                .addProductToCart(Mac)
                .addProductToCart(iPhone)
                .gotoHomePage()
                .gotoShoppingCartPage();

        shoppingCartPage
                .getShoppingCartProductsContainer()
                .getShoppingCartComponentByName(Mac.getName())
                .setQuantity("5");

        // Check
        BigDecimal actualMacTotalPrice = shoppingCartPage
                .getShoppingCartProductsContainer()
                .getTotalPriceByName(Mac);
        BigDecimal expectedMacTotalPrice = calculateProductTotal(
                        shoppingCartPage
                                .getShoppingCartProductsContainer()
                                .getShoppingCartComponentByName(Mac.getName())
                                .getQuantityFieldValue(),
                        shoppingCartPage
                                .getShoppingCartProductsContainer()
                                .getUnitPriceByName(Mac));
        Assert.assertEquals(convertBigDecimalToDouble(actualMacTotalPrice),
                            convertBigDecimalToDouble(expectedMacTotalPrice));
        Assert.assertEquals(
                convertBigDecimalToDouble(actualMacTotalPrice
                        .add(shoppingCartPage
                        .getShoppingCartProductsContainer()
                        .getTotalPriceByName(iPhone))),
                convertBigDecimalToDouble(shoppingCartPage
                        .getTotalPriceTableComponent()
                        .getTableTotalValue()));
        //      log.debug("checkTotalPriceTable test finished");
    }

}

