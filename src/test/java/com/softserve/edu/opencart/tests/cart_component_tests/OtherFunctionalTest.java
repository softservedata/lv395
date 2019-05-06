package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.tests.ATestRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OtherFunctionalTest extends ATestRunner {

    @Test
    public void checkPriceTextButton() {
        // Steps
        HomePage homePage = loadApplication()
                .addProductToCart(ProductRepository.getMacBook())
                .gotoHomePage();
        // Check
        Assert.assertTrue(homePage.getCartButtonText().contains(" 1 item(s) - $602.00"));
        // Steps
        homePage
                .openCartProductContainer()
                .removeProductByName(ProductRepository.getMacBook());
    }

}
