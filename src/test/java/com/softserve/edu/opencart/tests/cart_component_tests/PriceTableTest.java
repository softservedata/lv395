package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.TotalPriceTableComponent;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceTableTest extends ATestRunner {

    //
    private static final int SCALE = 2;
    //
    private final BigDecimal EXPECTED_SUB_TOTAL = new BigDecimal(1000.00).setScale(SCALE, RoundingMode.HALF_DOWN);
    private final BigDecimal EXPECTED_ECO_TAX = new BigDecimal(4.00).setScale(SCALE, RoundingMode.HALF_DOWN);
    private final BigDecimal EXPECTED_VAT = new BigDecimal(200.00).setScale(SCALE, RoundingMode.HALF_DOWN);
    private final BigDecimal EXPECTED_TOTAL = new BigDecimal(1204.00).setScale(SCALE, RoundingMode.HALF_DOWN);
    //
    WebDriver driver;

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
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer()
                .getTotalPriceTableComponent();
        // Check
        Assert.assertEquals(totalPriceTableComponent.getSubTotalValue(),
                EXPECTED_SUB_TOTAL);
        Assert.assertEquals(totalPriceTableComponent.getTableEcoTaxValue(),
                EXPECTED_ECO_TAX);
        Assert.assertEquals(totalPriceTableComponent.getTableVATValue(),
                EXPECTED_VAT);
        Assert.assertEquals(totalPriceTableComponent.getTableTotalValue(),
                EXPECTED_TOTAL);
    }

}
