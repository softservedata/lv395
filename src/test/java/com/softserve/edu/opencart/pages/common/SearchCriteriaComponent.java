package com.softserve.edu.opencart.pages.common;

import java.util.List;

import com.softserve.edu.opencart.pages.shop.ProductsContainerComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchCriteriaComponent {

    private WebDriver driver;
    //
    private WebElement listViewButton;
    private WebElement gridViewButton;
    private Select sortDropdown;
    private Select limitDropdown;
    // TODO Pagination
    //
    private ProductsContainerComponent productsContainerComponent;

    public SearchCriteriaComponent(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        listViewButton = driver.findElement(By.id("list-view"));
        gridViewButton = driver.findElement(By.id("grid-view"));
        sortDropdown = new Select(driver.findElement(By.id("input-sort")));
        limitDropdown = new Select(driver.findElement(By.id("input-limit")));
        productsContainerComponent = new ProductsContainerComponent(driver);
    }

    // Page Object

    // listViewButton
    public WebElement getListViewButton() {
        return listViewButton;
    }

    public void clickListViewButton() {
        getListViewButton().click();
    }

    // gridViewButton
    public WebElement getGridViewButton() {
        return gridViewButton;
    }

    public void clickGridViewButton() {
        getGridViewButton().click();
    }

    // sortDropdown
    public Select getSortDropdown() {
        return sortDropdown;
    }

    public WebElement getSortDropdownAsWebElement() {
        return getSortDropdown().getWrappedElement();
    }
    
    public List<WebElement> getSortDropdownOptions() {
        return getSortDropdown().getOptions();
    }

    public WebElement getSortDropdownSelectedOption() {
        return getSortDropdown().getAllSelectedOptions().get(0);
    }

    public String getSortDropdownSelectedOptionText() {
        return getSortDropdownSelectedOption().getText().trim();
    }

    public void clickSortDropdown() {
        getSortDropdownAsWebElement().click();
    }

    // limitDropdown
    public Select getLimitDropdown() {
        return limitDropdown;
    }

    public WebElement getLimitDropdownAsWebElement() {
        return getLimitDropdown().getWrappedElement();
    }
    
    public List<WebElement> getLimitDropdownOptions() {
        return getLimitDropdown().getOptions();
    }

    public WebElement getLimitDropdownSelectedOption() {
        return getLimitDropdown().getAllSelectedOptions().get(0);
    }

    public String getLimitDropdownSelectedOptionText() {
        return getLimitDropdownSelectedOption().getText().trim();
    }

    public void clickLimitDropdown() {
        getLimitDropdownAsWebElement().click();
    }

    // Functional

    // sortDropdown
    public void selectSortDropdownByName(String name) {
        clickSortDropdown();
        getSortDropdown().selectByVisibleText(name);
    }

    public boolean selectSortDropdownByPartialName(String partialName) {
        boolean isSelected = false;
        clickSortDropdown();
        for (WebElement current : getSortDropdownOptions()) {
            if (current.getText().trim().toLowerCase().contains(partialName.trim().toLowerCase())) {
                current.click();
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }
    
    // productsContainerComponent
    public ProductsContainerComponent getProductsContainerComponent() {
        return productsContainerComponent;
    }
    
    // limitDropdown
    public void selectLimitDropdownByName(String name) {
        clickLimitDropdown();
        getLimitDropdown().selectByVisibleText(name);
    }

    public boolean selectLimitDropdownByPartialName(String partialName) {
        boolean isSelected = false;
        clickLimitDropdown();
        for (WebElement current : getLimitDropdownOptions()) {
            if (current.getText().trim().toLowerCase().contains(partialName.trim().toLowerCase())) {
                current.click();
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }
    
    // Business Logic
}
