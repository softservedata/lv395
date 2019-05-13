package com.softserve.edu.opencart.tests.cart_component_tests;

import com.softserve.edu.opencart.pages.shop.EmptyCartComponent;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class includes just a smoke test.
 */
@Epic("Smoke Test")
@Feature("SmokeTest")
public class SmokeTest extends ATestRunner {

    /**
     * This test check does the cart open when we click on Cart Button.
     *
     * Click Cart Button and check that cart is opened.
     */
    @Description("Test Description: This test check does the cart open when we click on Cart Button")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Click Cart Button and check that cart is opened")
    @Test(description = "Check Cart Button button test")
    public void smokeTest() {
        log.debug("smokeTest test started");
        // Steps
        EmptyCartComponent emptyCartComponent  = loadApplication()
                .openCartProductContainer()
                .openEmptyCart();
        saveImageAttach("smoke");
        // Check
        Assert.assertTrue(emptyCartComponent.getCartContainer().isEnabled());
        log.debug("smokeTest test finished");
    }

}
