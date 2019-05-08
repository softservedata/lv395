package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.shop.TotalPriceTableComponent;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PriceTableTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Test(dataProvider = "productData")
    public void checkTotalPriceTable(IProduct product) {
        // Steps
        TotalPriceTableComponent totalPriceTableComponent = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer()
                .getTotalPriceTableComponent();
        // Check
        Assert.assertEquals(totalPriceTableComponent.getSubTotalValue(),
                product.getSubTotal());
        Assert.assertEquals(totalPriceTableComponent.getTableEcoTaxValue(),
                product.getEcoTax());
        Assert.assertEquals(totalPriceTableComponent.getTableVATValue(),
                product.getVat());
        Assert.assertEquals(totalPriceTableComponent.getTableTotalValue(),
                product.getTotalPrice());
    }

}
