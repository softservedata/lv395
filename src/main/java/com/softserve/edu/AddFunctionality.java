package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AddFunctionality {

    public void getCartCleaner(WebDriver driver, String URL) {
        driver.get("http://" + URL + "/opencart/upload/");
        //WebElements initialization
        if (driver.findElements(By.cssSelector("[title^='Remove'")).size() > 0) {
            List<WebElement> cartElementsTableRows;
            WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody"));
            cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
            //Cycle which deleting elements from cart
            for (int countOfElements = cartElementsTableRows.size(); countOfElements > 0; countOfElements--) {
                driver.findElement(By.id("cart")).click();
                driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
                cartElementsTableRows.remove(countOfElements - 1);
                driver.navigate().refresh();
            }
        } else {
            driver.navigate().refresh();
        }
    }
}
