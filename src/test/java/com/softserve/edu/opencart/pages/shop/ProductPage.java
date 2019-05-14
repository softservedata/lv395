package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.pages.common.AStatusPart;
import com.softserve.edu.opencart.pages.common.HomePage;
import com.softserve.edu.opencart.pages.common.SuccessfulSearchPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends AStatusPart {

    private ProductComponent product;
    private List<WebElement> thumbnailsImages;
    private WebElement descriptionButton;
    private WebElement specificationButton;
    private WebElement reviewsButton;
    private WebElement description;

    private ProductRightComponent rightMenu;

    public ProductPage(WebDriver driver) {
        super(driver);
        initElements();
    }

    public ProductPage(WebDriver driver, ProductComponent productComponent) {
        super(driver);
        product = productComponent.setPartialDescriptionToFull(driver.findElement(By.id("tab-description")));
    }

    private void initElements() {
        thumbnailsImages = driver.findElements(By.className("thumbnail"));
        descriptionButton = driver.findElement(By.cssSelector("a[href='#tab-description']"));
        specificationButton = driver.findElement(By.cssSelector("a[href='#tab-specification']"));
        reviewsButton = driver.findElement(By.cssSelector("a[href='#tab-review']"));
        description = driver.findElement(By.id("tab-description"));
    }

    // Page Object

    // descriptionButton

    public WebElement getDescriptionButton() {
        return descriptionButton;
    }

    public String getDescriptionButtonText() {
        return getDescriptionButton().getText();
    }

    public void clickDescriptionButton() {
        getDescriptionButton().click();
    }

    // specification

    public WebElement getSpecificationButton() {
        return specificationButton;
    }

    public String getSpecificationButtonText() {
        return getSpecificationButton().getText();
    }

    public void clickSpecificationButton() {
        getSpecificationButton().click();
    }

    // reviews

    public WebElement getReviewsButton() {
        return reviewsButton;
    }

    public String getReviewsButtonText() {
        return getReviewsButton().getText();
    }

    public void clickReviewsButton() {
        getReviewsButton().click();
    }

    // description

    public WebElement getDescription() {
        return description;
    }

    public String getDescriptionText() {
        return getDescription().getText();
    }

    // rightMenu

    private ProductRightComponent getRightMenu() {
        return new ProductRightComponent(driver);
    }

    // Functional

    public ProductComponent getProduct() {
        return product;
    }

    @Step("Set quantity of items")
    public ProductPage setQuantity(int quantity) {
        getRightMenu().clickQuantityField();
        getRightMenu().clearQuantityField();
        getRightMenu().setQuantityField(quantity);
        return new ProductPage(driver);
    }

    @Step("Add product to cart")
    public ProductPage addProductToCart() {
        getRightMenu().clickAddToCartButton();
        return new ProductPage(driver);
    }

    @Step("Go to HomePage")
    public HomePage gotoHomePage() {
        clickLogo();
        return new HomePage(driver);
    }

    // dropdownOptions

    // Business Logic

    public SuccessfulSearchPage gotoSearchPage() {
        driver.findElement(By.xpath(".//ul[@class='breadcrumb']/li[2]")).click();
        return new SuccessfulSearchPage(driver);

    }

}
