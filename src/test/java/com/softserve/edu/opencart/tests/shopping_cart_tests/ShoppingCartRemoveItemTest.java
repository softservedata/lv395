package com.softserve.edu.opencart.tests.shopping_cart_tests;

import com.softserve.edu.opencart.data.IProduct;
import com.softserve.edu.opencart.data.Product;
import com.softserve.edu.opencart.data.ProductRepository;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.shop.ShoppingCartPage;
import com.softserve.edu.opencart.pages.shop.ShoppingCartProductComponent;
import com.softserve.edu.opencart.pages.shop.ShoppingCartProductsContainer;
import com.softserve.edu.opencart.tests.ATestRunner;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class ShoppingCartRemoveItemTest extends ATestRunner {
    @DataProvider
    public Object[][] productData() {
        return new Object[][] {
                {ProductRepository.getMacBook(), ProductRepository.getIPhone3()}
        };
    }

    @Test(dataProvider = "productData")
    public void checkViewShoppingCartButton(IProduct Mac, IProduct iPhone) {
        //Steps
        ShoppingCartProductsContainer shoppingCartProductsContainer = loadApplication()
                .addProductToCart(Mac)
                .addProductToCart(iPhone)
                .gotoHomePage()
                .gotoShoppingCartPage()
                .getShoppingCartProductsContainer();
        int beforeRemovingRowCountSize = shoppingCartProductsContainer
                .getShoppingCartComponents().size();

        shoppingCartProductsContainer.removeProductFromShoppingCartByName((Product) Mac);

        int afterRemovingRowCountSize = shoppingCartProductsContainer
                .getShoppingCartComponents()
                .size();

        for (ShoppingCartProductComponent i : shoppingCartProductsContainer.getShoppingCartComponents()) {
            System.out.println("afterRemove "+ i.getProductNameText());
        }
        Assert.assertNotEquals(beforeRemovingRowCountSize, afterRemovingRowCountSize);
        Assert.assertEquals(shoppingCartProductsContainer.getShoppingCartComponents().get(0).getProductNameText(), iPhone.getName());
    }
}
