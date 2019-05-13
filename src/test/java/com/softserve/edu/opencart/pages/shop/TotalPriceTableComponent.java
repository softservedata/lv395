package com.softserve.edu.opencart.pages.shop;

import com.softserve.edu.opencart.tools.PriceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

public class TotalPriceTableComponent {

    private WebElement totalTable;
    WebElement tableSubTotal;
    WebElement tableEcoTax;
    WebElement tableVat;
    WebElement tableTotal;

    public TotalPriceTableComponent(WebElement totalTable){
        this.totalTable = totalTable;
        initElements();
    }

    private void initElements() {
        tableSubTotal = totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child>td+td"));
        tableEcoTax = totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child+tr>td+td"));;
        tableVat = totalTable.findElement(By.cssSelector(".table.table-bordered tr:first-child+tr+tr>td+td"));
        tableTotal = totalTable.findElement(By.cssSelector(".table.table-bordered tr:last-child>td+td"));
    }

    // Page Object

    private WebElement getTableSubTotal(){
        return tableSubTotal;
    }

    private WebElement getTableEcoTax(){
        return tableEcoTax;
    }

    private WebElement getTableVAT(){
        return tableVat;
    }

    private WebElement getTableTotal(){
        return tableTotal;
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

    public BigDecimal getSubTotalValue() {
        return PriceUtils.getPrice(getTableSubTotalText());
    }

    public BigDecimal getTableEcoTaxValue() {
        return PriceUtils.getPrice(getTableEcoTaxText());
    }

    public BigDecimal getTableVATValue() {
        return PriceUtils.getPrice(getTableVATText());
    }

    public BigDecimal getTableTotalValue() {
        return PriceUtils.getPrice(getTableTotalText());
    }

}
