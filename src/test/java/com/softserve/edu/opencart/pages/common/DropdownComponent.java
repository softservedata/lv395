package com.softserve.edu.opencart.pages.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class DropdownComponent {

    private WebDriver driver;
    //
    private List<WebElement> listOptions;

    public DropdownComponent(WebDriver driver, By searchLocator) {
        this.driver = driver;
        initListOptions(searchLocator);
    }

    private void initListOptions(By searchLocator) {
        listOptions = driver.findElements(searchLocator);
        // listOptions = search.getWebElements(searchLocator);
    }

    // Page Object

    // listOptions
    public List<WebElement> getListOptions() {
        return listOptions;
    }

    // Functional

    // listOptions
    public WebElement getDropdownOptionByPartialName(String optionName) {
        WebElement result = null;
        for (WebElement current : getListOptions()) {
            if (current.getText().toLowerCase().contains(optionName.toLowerCase())) {
                result = current;
                break;
            }
        }
        return result;
    }

    public List<String> getListOptionsText() {
        List<String> result = new ArrayList<>();
        for (WebElement current : getListOptions()) {
            result.add(current.getText());
        }
        return result;
    }

    public boolean isExistDropdownOptionByPartialName(String optionName) {
        boolean isFound = false;
        for (String current : getListOptionsText()) {
            if (current.toLowerCase().contains(optionName.toLowerCase())) {
                isFound = true;
            }
        }
        return isFound;
    }

    public void clickDropdownOptionByPartialName(String optionName) {
        getDropdownOptionByPartialName(optionName).click();
    }

    // Business Logic

}
