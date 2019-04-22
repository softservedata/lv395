package com.softserve.edu.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class Helper has methods, that helping in functionality tests.
 */
public class Helper {

    /**
     * Declaring some main variables.
     * Two chrome drivers:
     * driver - for working with OpenCart as user.
     */
    protected ChromeDriver driver;
    /**driverAdmin - for working with OpenCart data bases as admin.*/
    private ChromeDriver driverAdmin;

    /**
     * Declaring two urls for access to OpenCart website:
     * OPEN_CART_URL - for access to OpenCart home page.
     */
    protected final String OPEN_CART_URL =
            "http://192.168.234.131/opencart/upload/";
    /**OPEN_CART_ADMIN_URL - for access to OpenCart administration.*/
    private final String OPEN_CART_ADMIN_URL =
            "http://192.168.234.131/opencart/upload/admin/index.php?";

    /**
     * Some Lists for working with data:
     * totalList - for working with Total columns in table Shopping Cart.
     */
    protected List<Double> totalList = new ArrayList<>();
    /**productNameList - for working with Product Name in table Shopping Cart.
     * And for working on admin page to find selected products.
     */
    protected List<String> productNameList = new ArrayList<>();
    /**quantityList - for working with quantity in table Shopping Cart.*/
    protected List<String> quantityList = new ArrayList<>();
    /**
     *quantityOnStockList - for working with quantity of product on the stock.
     */
    protected List<Integer> quantityOnStockList = new ArrayList<>();
    /**
     * XPath selector String for calculate count of rows in table Shopping Cart.
     */
    protected String tableCountRowXPath =
            ".//form/div/table[@class='table table-bordered']/tbody/tr";
    /** Variable, number of rows in table Shopping Cart. Default: 0. */
    protected int tableCountRow = 0;
    /** Variable, price of one product. */
    protected double unitPrice;


    /**
     * Constructor for Helper class.
     */
    public Helper() {
        //not used.
    }


    /**
     * The next method fills the total sum of any product.
     * @param size - tableCountRow.
     * @return totalList.
     */
    public List<Double> fillTotalList(final int size) {
        for (int i = 0; i < size; i++) {
            totalList.add(getFloatNumber(driver.findElement(By.xpath(
                    "//*[@id='content']/form/div/table/tbody/tr["
                            + (i + 1) + "]/td[6]")).getText()));
        }
        return totalList;
    }

    /**
     * The next method fills productNameList from Shopping Cart table.
     */
    public void fillProductNameList() {
        productNameList.clear();
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(
                tableCountRowXPath)).size();
        for (int i = 0; i < tableCountRow; i++) {
            productNameList.add(driver.findElement(By.xpath(
                    "//*[@id='content']/form/div/table/tbody/tr["
                            + (i + 1) + "]/td[2]/a")).getText());
        }
    }

    /**
     * The next method fills quantityList from Shopping Cart table.
     */
    public void fillQuantityList() {
        quantityList.clear();;
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(
                tableCountRowXPath)).size();
        for (int i = 0; i < tableCountRow; i++) {
            quantityList.add(driver.findElement(By.xpath(
                    "//*[@id='content']/form/div/table/tbody/tr[" + (i + 1)
                            + "]/td[4]/div/input")).getAttribute("value"));
        }
    }

    /**
     * The next method working with String values.
     * And converts this value to double type.
     * Removing some "$" and ",".
     * @param s - the String, that will be filtered.
     * @return double value, with two numbers after floating point.
     */
    public double getFloatNumber(String s) {
        s = s.replaceAll("[^0-9?!\\.]+", "");
        double d = Double.parseDouble(s);
        return (double) Math.round(d * 100) / 100;
    }

    /**
     * The next method 'fillLists' calling sequentially 3 methods.
     * Three List will be filled.
     * One line of code better than three.
     */
    public void fillLists() {
        fillQuantityList();
        fillProductNameList();
        fillQuantityOnStockList();
    }

    /**
     * The next method fills quantityOnStockList from admin page Open Cart
     * Get the quantity from Products table.
     */
    public void fillQuantityOnStockList() {
        quantityOnStockList.clear();
        driverAdmin = new ChromeDriver();
        driverAdmin.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverAdmin.get(OPEN_CART_ADMIN_URL);
        driverAdmin.findElement(By.id("input-username")).sendKeys("admin" + Keys.TAB + "admin" + Keys.ENTER);
        driverAdmin.findElement(By.id("button-menu")).click();
        driverAdmin.findElement(By.linkText("Catalog")).click();
        driverAdmin.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        for (int i = 0; i < tableCountRow; i++){
            quantityOnStockList.add(Integer.parseInt(driverAdmin.findElement(By.xpath(
                    "//tbody/tr/td[text()='" + productNameList.get(i) +
                            "']/following-sibling::td[*]/span[@class='label label-success']")).getText()));
        }
        driverAdmin.quit();
    }

    public WebElement webElementInit(WebElement we, final String xPath) {
        we = driver.findElement(By.xpath(xPath));
        return we;
    }

    public void logIn() {
        driver.get(OPEN_CART_URL + "index.php?route=account/login");
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys(
                "yuriykril7773@gmail.com");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys(
                "qwerty" + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }

    public void addProducts() {
        driver.get(OPEN_CART_URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(
                "#content > div.row > div:nth-child(1) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector(
                "#content > div.row > div:nth-child(4) > div > div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector(
                "#input-option226 > option:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#input-quantity")).click();
        driver.findElement(By.cssSelector(
                "#input-quantity")).sendKeys(Keys.BACK_SPACE + "2" + Keys.ENTER);
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector("a[href$='index.php?route=common/home']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Phones & PDAs')]")).click();
        driver.findElement(By.linkText("Palm Treo Pro")).click();
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id(
                "input-quantity")).sendKeys(Keys.BACK_SPACE + "4");
        driver.findElement(By.id("input-quantity")).click();
        driver.findElement(By.id("button-cart")).click();
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
    }

}
