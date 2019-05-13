package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.shop.TotalPriceTableComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * This class includes test cases for
 * price table of product cart.
 */
@Epic("Functional Testing")
@Feature("PriceTableTest")
public class PriceTableTest extends ATestRunner {

    /**
     * Data provider for PriceTable tests.
     *
     * @return product from product repository.
     */
    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    /**
     * This test checks if all data in the
     * cart table is displayed correctly.
     *
     * Add the item to the cart and check if all
     * data in the cart table is displayed correctly.
     *
     * @param product product from product repository.
     */
    @Description("Test Description: This test checks if all data in the cart table is displayed correctly")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Add the item to the cart and check if all data in the cart table is displayed correctly")
    @Test(dataProvider = "productData", description = "Checking data in cart table")
    public void checkTotalPriceTable(IProduct product) {
        log.debug("checkTotalPriceTable test started");
        // Steps
        TotalPriceTableComponent totalPriceTableComponent = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer()
                .getTotalPriceTableComponent();
        saveImageAttach("prise_table");
        // Check
        Assert.assertEquals(totalPriceTableComponent.getSubTotalValue(),
                product.getSubTotal());
        Assert.assertEquals(totalPriceTableComponent.getTableEcoTaxValue(),
                product.getEcoTax());
        Assert.assertEquals(totalPriceTableComponent.getTableVATValue(),
                product.getVat());
        Assert.assertEquals(totalPriceTableComponent.getTableTotalValue(),
                product.getTotalPrice());
        log.debug("checkTotalPriceTable test finished");
    }

}
