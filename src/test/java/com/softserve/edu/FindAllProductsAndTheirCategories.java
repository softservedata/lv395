package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindAllProductsAndTheirCategories {
    private final String ip = "http://192.168.36.134/opencart/upload/";

    final public List<Product> findAllProducts() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ip);
        driver.get(ip + "admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.id("menu-catalog")).click();
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).click();
        List<WebElement> productsFromWeb = driver.findElements(By.xpath(".//tr/td[3]"));
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < productsFromWeb.size(); i++) {
            Product product = new Product();
            product.setName(productsFromWeb.get(i).getText());
            products.add(product);
        }
        driver.quit();
        return products;

    }


    final public List<Product> findingAllProductsAndTheirCategories() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ip);
        driver.get(ip + "admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.id("menu-catalog")).click();
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).click();
        List<WebElement> productsFromWeb = driver.findElements(By.xpath(".//tr/td[3]"));
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < productsFromWeb.size(); i++) {
            Product product = new Product();
            product.setName(productsFromWeb.get(i).getText());
            products.add(product);
        }

        for (int i = 1; i < productsFromWeb.size(); i++) {

            List<WebElement> edit = driver.findElements(By.xpath(".//tr/td[8]"));
            edit.get(i).click();
            driver.findElement(By.xpath(".//ul[@class='nav nav-tabs']/li[3]")).click();
            List<WebElement> productCategoryWeb = driver.findElements(By.xpath(".//div[@id='product-category']/div"));
            List<String> productCateggry = new ArrayList<>();
            for (int j = 0; j < productCategoryWeb.size(); j++) {
                productCateggry.add(productCategoryWeb.get(j).getText());
            }
            products.get(i - 1).setCategory(productCateggry);
            driver.findElement(By.id("menu-catalog")).click();
            driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).click();

        }
        driver.quit();
        return products;
    }
}
