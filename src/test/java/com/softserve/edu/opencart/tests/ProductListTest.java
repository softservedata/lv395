package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.pages.common.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductListTest extends ATestRunner {

    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook()},
                {ProductRepository.getIPhone3()}
        };
    }

    @Test(dataProvider = "productData")
    public void checkAddToCartButton(Product product) {
        // Steps
        CartProductContainer cartProductContainer = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        // Step
        HomePage homePage = cartProductContainer
                .removeProductByName(product);
    }

    @DataProvider
    public Object[][] secondProductData() {
        return new Object[][] {
                {ProductRepository.getMacBook(), ProductRepository.getIPhone3()}
        };
    }

    @Test(dataProvider = "secondProductData")
    public void increaseQuantityOfItemsTest(Product firstProduct,Product secondProduct) {
        // Steps
        CartProductContainer cartProductContainer = loadApplication()
                .addProductToCart(firstProduct)
                .gotoHomePage()
                .addProductToCart(secondProduct)
                .gotoHomePage()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(1).getCartProductNameText(),
                firstProduct.getName());
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                secondProduct.getName());
        // Steps
        cartProductContainer
                .removeProductByName(firstProduct)
                .openCartProductContainer()
                .removeProductByName(secondProduct);

    }

    @DataProvider
    public Object[][] thirdProductData() {
        return new Object[][] {
                {ProductRepository.getMacBook(), "x 2"}
        };
    }

    @Test(dataProvider = "thirdProductData")
    public void addSameItemMultipleTimesTest(Product product, String productQuantity) {
       // Steps
        CartProductContainer cartProductContainer = loadApplication()
                .addProductToCart(product)
                .gotoHomePage()
                .addProductToCart(product)
                .gotoHomePage()
                .openCartProductContainer();
        List<CartProductComponent> cartProductComponents = cartProductContainer
                .getCartProductComponents();
        // Check
        Assert.assertEquals(cartProductComponents.get(0).getCartProductNameText(),
                product.getName());
        Assert.assertEquals(cartProductComponents.get(0).getCartProductQuantityText(),
                productQuantity);
        // Steps
        cartProductContainer
                .removeProductByName(product);

    }

}
