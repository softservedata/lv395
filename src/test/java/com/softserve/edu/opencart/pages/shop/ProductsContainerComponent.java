package com.softserve.edu.opencart.pages.shop;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.opencart.data.IProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.LeaveUtils;

public class ProductsContainerComponent {

    private final String PRODUCTNAME_NOT_FOUND = "ProductName: %s not Found.";
    //
    private WebDriver driver;
    //
    private List<ProductComponent> productComponents;

    public ProductsContainerComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }
    
    private void initElements() {
        productComponents = new ArrayList<>();
        for (WebElement current : getProductWebElements())
        {
            productComponents.add(new ProductComponent(current));
        }
    }

    private List<WebElement> getProductWebElements() {
        return driver.findElements(By.cssSelector(".product-layout"));
    }
    
    // Page Object

    // productComponents
    public List<ProductComponent> getProductComponents() {
        return productComponents;
    }
    public List<String> getProductComponentsName() {
        List<String> productComponentsNames =new ArrayList<>();
        for (ProductComponent productComponent:productComponents){
            productComponentsNames.add(productComponent.getNameText());
        }
        return productComponentsNames;
    }
    
    // Functional
    public Boolean isProductOnThePage(String productName){
        return getProductComponentNames().contains(productName);

    }
    public List<String> getProductComponentNames()
    {
        List<String> productComponentNames = new ArrayList<>();
        for (ProductComponent current : getProductComponents())
        {
            productComponentNames.add(current.getNameText());
        }
        return productComponentNames;
    }
    
    public ProductComponent getProductComponentByName(String productName)
    {
        ProductComponent result = null;
        for (ProductComponent current : getProductComponents())
        {
            if (current.getNameText().toLowerCase()
                    .equals(productName.toLowerCase()))
            {
                result = current;
                break;
            }
        }
        LeaveUtils.castExceptionByCondition(result == null,
                String.format(PRODUCTNAME_NOT_FOUND, productName));
        return result;
    }

    public String getProductComponentPriceByName(String productName)
    {
        return getProductComponentByName(productName).getPriceText();
    }

    public String getProductComponentDescriptionByName(String productName)
    {
        return getProductComponentByName(productName).getPartialDescriptionText();
    }

    public void clickProductComponentAddToCartButtonByName(String productName)
    {
        getProductComponentByName(productName).clickAddToCartButton();
    }

    public void clickProductComponentAddToWishButtonByName(String productName)
    {
        getProductComponentByName(productName).clickAddToWishButton();
    }

    public void clickProductComponentName(String productName) {
        getProductComponentByName(productName).clickName();
    }

    public int getProductComponentsSize()
    {
        return getProductComponents().size();
    }

    // Business Logic

}
