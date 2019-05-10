package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressBookPage extends ARightLoginPart {

    private WebElement tableEntries;
    
    public AddressBookPage(WebDriver driver) {
        super(driver);
        initElements();
    }
    
    private void initElements() {
        tableEntries = driver.findElement(By.cssSelector("table.table-bordered.table-hover td:first-child"));
    }

    // Page Object

    // tableEntries
    public WebElement getTableEntries() {
        return tableEntries;
    }

    public String getTableEntriesText() {
        return getTableEntries().getText();
    }

    // Functional

    // Business Logic
}

