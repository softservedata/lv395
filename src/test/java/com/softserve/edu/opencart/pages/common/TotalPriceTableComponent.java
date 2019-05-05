package com.softserve.edu.opencart.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TotalPriceTableComponent {

    WebElement totalTable;


    public TotalPriceTableComponent(WebElement totalTable){
        this.totalTable = totalTable;
    }

    // Page Object

    private WebElement getTableSubTotal(){
        return totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child>td"));
    }

    private WebElement getTableEcoTax(){
        return totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child+tr>td+td"));
    }

    private WebElement getTableVAT(){
        return totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child+tr+tr>td+td"));
    }

    private WebElement getTableTotal(){
        return totalTable.findElement(By.cssSelector(".table.table-bordered tr:last-child>td+td"));
    }

    private String getTableSubTotalText(){
        return getTableSubTotal().getText();
    }

    private String getTableEcoTaxText(){
        return getTableEcoTax().getText();
    }

    private String getTableVATText(){
        return getTableVAT().getText();
    }

    private String getTableTotalText(){
        return getTableTotal().getText();
    }

    // Functional

    // Business logic

}