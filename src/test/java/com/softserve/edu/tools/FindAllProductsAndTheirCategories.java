package com.softserve.edu.tools;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FindAllProductsAndTheirCategories class.
 * This class was created to help us in tests.
 * In tis class exist three method.
 * Each of them are often use in tests.
 * 1. findAllProducts - this method will find all products on the storage.
 * 2. findProductsAndTheirCategories - this method
 * will find products and their categories(
 * not all, just that, what we will need)
 * 3. findCategories - this method will find categories and
 * their subcategories, for products we will need
 *
 * @author Iryna Ratushniak
 */
public class FindAllProductsAndTheirCategories {
    /**
     * ip address.
     */
    private final String ip = "http://192.168.36.134/opencart/upload/";

    /**
     * findAllProducts - this method will find all products on the storage.
     *
     * @return list of product
     */
    public List<String> findAllProducts() {
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
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).
                click();
        List<WebElement> productsFromWeb = driver.findElements(By.xpath(
                ".//tr/td[3]"));
        List<String> products = new ArrayList<>();
        for (int i = 1; i < productsFromWeb.size(); i++) {
            products.add(productsFromWeb.get(i).getText());

        }
        driver.quit();
        return products;

    }

    /**
     * FindProductsAndTheirCategories - this method
     * * will find products and their categories(
     * * not all, just that, what we will need).
     *
     * @param webElements - products from web page,
     *                    for what we need to find categories
     *                    and create list of objects "products"
     * @return list of products and their categories
     */
    public List<Product> findProductsAndTheirCategories(
            final List<WebElement> webElements) {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ip + "admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.id("menu-catalog")).click();
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[2]")).
                click();
        List<WebElement> productsOnStorage1 = driver.findElements(By.xpath(
                ".//tr/td[3]"));
        List<Product> products = new ArrayList<>();
        List<String> productsFromWebPage = new ArrayList<>();
        for (WebElement webElement : webElements) {
            productsFromWebPage.add(webElement.getText());
        }

        for (int i = 0; i < productsOnStorage1.size(); i++) {
            List<WebElement> productsOnStorage = driver.findElements(By.xpath(
                    ".//tr/td[3]"));
            Product product = new Product();
            if (productsFromWebPage.contains(productsOnStorage.get(i).
                    getText())) {
                product.setName(productsOnStorage.get(i).getText());
                List<WebElement> edit = driver.findElements(By.xpath(
                        ".//tr/td[8]"));
                edit.get(i).click();
                driver.findElement(By.xpath(
                        ".//ul[@class='nav nav-tabs']/li[3]")).click();
                List<WebElement> productCategoryWeb = driver.findElements(
                        By.xpath(".//div[@id='product-category']/div"));
                List<String> productCategory = new ArrayList<>();
                for (WebElement webElement : productCategoryWeb) {
                    productCategory.add(webElement.getText());
                }
                product.setCategory(productCategory);
                products.add(product);
                driver.findElement(By.id("menu-catalog")).click();
                driver.findElement(By.xpath(
                        ".//li[@id='menu-catalog']/ul/li[2]")).click();
            }


        }
        System.out.println(products.toString());
        System.out.println(products.size());
        driver.quit();
        return products;
    }

    /**
     * findCategories - this method will find categories and
     * their subcategories, for products we will need.
     *
     * @param webElements             - products, for what we need to know categories
     * @param categoryWeAreLookingFor - category we chose on the web page
     * @return list of categories
     */
    public List<String> findCategories(
            final List<WebElement> webElements,
            final String categoryWeAreLookingFor) {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ip + "admin/");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        driver.findElement(By.id("menu-catalog")).click();
        driver.findElement(By.xpath(".//li[@id='menu-catalog']/ul/li[1]")).
                click();
        List<WebElement> productsFromWeb = driver.findElements(By.xpath(
                ".//tr/td[2]"));
        List<String> categories = new ArrayList<>();
        for (int i = 1; i < productsFromWeb.size(); i++) {
            if (productsFromWeb.get(i).getText().
                    contains(categoryWeAreLookingFor)) {
                categories.add(productsFromWeb.get(i).getText().
                        replace("  >  ", " > "));
            }
        }
        driver.quit();
        return categories;

    }
}
