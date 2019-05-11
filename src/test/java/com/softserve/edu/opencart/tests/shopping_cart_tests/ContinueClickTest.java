package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.HomePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContinueClickTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()}
        };
    }

    @Test(dataProvider = "productData")
    public void checkContinueButton(IProduct product) {
        // Steps
        HomePage homePage = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .gotoHomePageByContinueButton();
        Assert.assertTrue(homePage.getSlideshow0FirstImageAttributeSrcText().contains(HomePage.IPHONE_IMAGE));
    }
}
