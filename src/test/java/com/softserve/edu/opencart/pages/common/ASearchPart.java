package com.softserve.edu.opencart.pages.common;

import java.util.List;

import com.softserve.edu.opencart.data.ISearchFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.softserve.edu.opencart.tools.LeaveUtils;

public abstract class ASearchPart extends AStatusPart {

    public final String ALL_CATEGORIES = "All Categories";
    private final String SUBCATEGORY_NOT_SELECTED = "SubCategory is not selected";
    //
    private WebElement searchCriteriaField;
    private WebElement descriptionCheckbox;
    private Select categoryDropdown;
    private WebElement subcategoryCheckbox;
    private WebElement searchCriteriaButton;

    public ASearchPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        searchCriteriaField = driver.findElement(By.id("input-search"));
        descriptionCheckbox = driver.findElement(By.name("description"));
        categoryDropdown = new Select(driver.findElement(By.name("category_id")));
        subcategoryCheckbox = driver.findElement(By.name("sub_category"));
        searchCriteriaButton = driver.findElement(By.id("button-search"));
    }

    // Page Object

    // searchCriteriaField
    public WebElement getSearchCriteriaField() {
        return searchCriteriaField;
    }

    public String getSearchCriteriaFieldText() {
        return getSearchCriteriaField().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clearSearchCriteriaField() {
        getSearchCriteriaField().clear();
    }

    public void clickSearchCriteriaField() {
        getSearchCriteriaField().click();
    }

    public void setSearchCriteriaField(String text) {
        getSearchCriteriaField().sendKeys(text);
    }

    // descriptionCheckbox
    public WebElement getDescriptionCheckbox() {
        return descriptionCheckbox;
    }

    public void clickDescriptionCheckbox() {
        getDescriptionCheckbox().click();
    }

    // categoryDropdown
    public Select getCategoryDropdown() {
        return categoryDropdown;
    }

    public WebElement getCategoryDropdownAsWebElement() {
        return getCategoryDropdown().getWrappedElement();
    }

    public List<WebElement> getCategoryDropdownOptions() {
        return getCategoryDropdown().getOptions();
    }

    public WebElement getCategoryDropdownSelectedOption() {
        return getCategoryDropdown().getAllSelectedOptions().get(0);
    }

    public String getCategoryDropdownSelectedOptionText() {
        return getCategoryDropdownSelectedOption().getText().trim();
    }

    public void clickCategoryDropdown() {
        getCategoryDropdownAsWebElement().click();
    }

    // subcategoryCheckbox
    public WebElement getSubcategoryCheckbox() {
        return subcategoryCheckbox;
    }

    private void clickSubcategoryCheckbox() {
        LeaveUtils.castExceptionByCondition(getCategoryDropdownSelectedOptionText().equals(ALL_CATEGORIES),
                SUBCATEGORY_NOT_SELECTED);
        getSubcategoryCheckbox().click();
    }

    // searchCriteriaButton
    public WebElement getSearchCriteriaButton() {
        return searchCriteriaButton;
    }

    public String getSearchCriteriaButtonText() {
        return getSearchCriteriaButton().getAttribute(TAG_ATTRIBUTE_VALUE);
    }

    public void clickSearchCriteriaButton() {
        getSearchCriteriaButton().click();
    }

    // Functional

    // searchField
    private void fillSearchCriteriaField(String name) {
        clickSearchCriteriaField();
        clearSearchCriteriaField();
        setSearchCriteriaField(name);
    }
    
    // categoryDropdown
    public void selectCategoryDropdownByName(String name) {
        clickCategoryDropdown();
        getCategoryDropdown().selectByVisibleText(name);
    }

    public boolean selectCategoryDropdownByPartialName(String partialName) {
        boolean isSelected = false;
        clickCategoryDropdown();
        for (WebElement current : getCategoryDropdownOptions()) {
            if (current.getText().trim().toLowerCase().contains(partialName.trim().toLowerCase())) {
                current.click();
                isSelected = true;
                break;
            }
        }
        return isSelected;
    }

    // subcategoryCheckbox
    public void clickSubcategoryCheckboxByName(String categoryName) {
        if (selectCategoryDropdownByPartialName(categoryName)) {
            getSubcategoryCheckbox().click();
        } else {
            LeaveUtils.castExceptionByCondition(true, SUBCATEGORY_NOT_SELECTED);
        }
    }

    // Business Logic
    
    public SuccessfulSearchPage searchProductsByFilter(ISearchFilter searchItems) {
        fillSearchCriteriaField(searchItems.getProductSearchName());
        if (searchItems.isUseDescription()) {
            clickDescriptionCheckbox();
        }
        if ((searchItems.getCategoryName() != null)
                && (!searchItems.getCategoryName().isEmpty())) {
            if (searchItems.isUseSubcategory()) {
                clickSubcategoryCheckboxByName(searchItems.getCategoryName());
            } else {
                selectCategoryDropdownByPartialName(searchItems.getCategoryName());
            }    
        }
        clickSearchCriteriaButton();
        return new SuccessfulSearchPage(driver);
    }
}
