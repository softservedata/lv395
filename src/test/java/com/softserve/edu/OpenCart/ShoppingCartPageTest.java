package com.softserve.edu.OpenCart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * The main class for testing Shopping Cart page.
 */
public class ShoppingCartPageTest extends Helper {

    /**
     * Selectors String:
     * cssSelector to first edit(quantity) field.
     */
    private String editFieldSelector1 = "#content > form > div > table "
            + "> tbody > tr:nth-child(1) > td:nth-child(4) > div > input";
    /**cssSelector to second edit(quantity) field.*/
    private String editFieldSelector2 = "#content > form > div > table "
            + "> tbody > tr:nth-child(2) > td:nth-child(4) > div > input";
    /**cssSelector to third edit(quantity) field.*/
    private String editFieldSelector3 = "#content > form > div > table "
            + "> tbody > tr:nth-child(3) > td:nth-child(4) > div > input";
    /**cssSelector to unit price label.*/
    private String unitPriceFieldSelector3 = "#content > form > div > table "
            + "> tbody > tr:nth-child(3) > td:nth-child(5)";
    /** Variable, price of one product. */
    private double unitPrice3;
    /**cssSelector to total label.*/
    private String totalFieldSelector3 = "#content > form > div > table "
            + "> tbody > tr:nth-of-type(3) > td:nth-of-type(6)";

    /**
     * Declaring webElements:
     * webElement refresh Button.
     */
    private WebElement refreshButton;
    /**webElement checkOut button.*/
    private WebElement checkoutButton;
    /**xPathSelector to refresh Button.*/
    private String refreshButtonXPath =
            "//*[@id='content']/form/div/table"
                    + "/tbody/tr[1]/td[4]/div/span/button[1]";
    /**xPathSelector to checkout Button.*/
    private String checkoutButtonXPath =
            "//*[@id='content']/div/div/a[text()='Checkout']";
    /**Flag for checking are lists filled or not, false by default.*/
    private Boolean isListsFilled = false;
    /**The int constants number equals 100.*/
    private final int HUNDRED = 100;

    /**
     * Before class for start working with Shopping Cart page.
     */
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(OPEN_CART_URL);
        driver.manage().window().maximize();
        logIn();
        addProducts();
    }

    /**
     * After class, 'cleaning' after all tests.
     */
    @AfterClass
    public void afterClass() {
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        while (driver.findElements(By.cssSelector(
                "#content > form")).size() > 0) {
            driver.findElement(By.cssSelector(
                    "#content > form > div > table "
                            + "> tbody > tr:nth-child(1) >"
                            + " td:nth-child(4) > div >"
                            + " span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        logOut();
        driver.quit();
    }

    /**
     * Verify total value test.
     * @return int value, double value;
     */
    @DataProvider
    public Object[][] quantityTotal() {
        fillQuantityList();
        int currentQuantity = Integer.parseInt(quantityList.get(0));
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        unitPrice3 = getFloatNumber(driver.findElement(
                By.cssSelector(unitPriceFieldSelector3)).getText());
        return new Object[][]{
                {currentQuantity, (double) Math.round(
                        (currentQuantity * unitPrice3)  * HUNDRED) / HUNDRED },
                {7, (double) Math.round((7 * unitPrice3) * HUNDRED) / HUNDRED },
                {2, (double) Math.round((2 * unitPrice3) * HUNDRED) / HUNDRED },
        };
    }

    /**
     * Verifying calculations in Total fields,
     * after editing the quantity field of products.
     * @param newQuantity - new value in quantity field.
     * @param expectedResult - expected Total after change.
     */
    @Test(dataProvider = "quantityTotal", priority = 3)
    public void verifyTotalTest(final int newQuantity,
                                final double expectedResult) {
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        WebElement quantityField = driver.findElement(
                By.cssSelector(editFieldSelector3));
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(newQuantity));
        webElementInit(refreshButton, refreshButtonXPath).click();
        fillTotalList(driver.findElements(By.xpath(
                ".//form/div/table[@class='table "
                        + "table-bordered']/tbody/tr")).size());
        double expectedSuperTotal = 0.00;
        for (Double e : totalList) {
            expectedSuperTotal = (double) Math.round(
                    (expectedSuperTotal + e) * HUNDRED) / HUNDRED;
        }
        totalList.clear();
        double actualSuperTotal = getFloatNumber(
                driver.findElement(By.xpath(
                        "//*[@id='content']/div[2]/div/table"
                                + "/tbody/tr[2]/td[2]")).getText());
        Assert.assertEquals(actualSuperTotal, expectedSuperTotal);
        double actual = getFloatNumber(
                driver.findElement(By.cssSelector(
                        totalFieldSelector3)).getText());
        Assert.assertEquals(actual, expectedResult);
    }


    /**
     * Positive data provider for access to Checkout page.
     * @return String value, string value.
     */
    @DataProvider
    public Object[][] accessToCheckout() {
        driver.get(OPEN_CART_URL);
        fillLists();
        isListsFilled = true;
        String h1Text = driver.findElement(
                By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        return new Object[][]{
                {quantityList.get(0), h1Text },
                //positive pv for edit (quantity) field.
                {quantityOnStockList.get(0).toString(), h1Text },
        };
    }

    /**
     * Verify access to Checkout Page by click Checkout button.
     * @param quantityValue - positive quantity value,
     *                        smaller then quantity on the stock.
     * @param expected - String, that will be verified on present
     *                   in h1 tag like a text.
     */
    @Test (dataProvider = "accessToCheckout", priority = 4)
    public void accessToCheckoutPageTest(final String quantityValue,
                                         final String expected) {
        driver.get("http://192.168.234.131/opencart"
                + "/upload/index.php?route=checkout/cart");
        WebElement quanityField = driver.findElement(
                By.cssSelector(editFieldSelector1));
        quanityField.clear();
        quanityField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actual = driver.findElement(
                By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Assert.assertNotEquals(actual, expected);
    }

    // Negative Test

    /**
     * Negative data provider for notAccessToCheckoutPageTest.
     * @return String - quantity on the stock,
     *         String - firsts eight symbols in text h1 tag.
     */
    @DataProvider
    public Object[][] notAccessToCheckout() {
        driver.get(OPEN_CART_URL);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        if (!isListsFilled) {
            fillLists();
        }
        driver.get("http://192.168.234.131/opencart/"
                + "upload/index.php?route=checkout/cart");
        String h1Text = driver.findElement(By.xpath(
                "//*[@id='content']/h1")).getText().substring(0, 7);
        return new Object[][] {
                {String.valueOf(quantityOnStockList.get(0) + 1),
                        editFieldSelector1, h1Text },
                {String.valueOf(quantityOnStockList.get(1) + 1),
                        editFieldSelector2, h1Text },
        };
    }

    /**
     * Verifying, that we can't to access to the Checkout page,
     * when quantity in quantity field exceed the number on the stock.
     * @param quantityValue - a value of quantity field.
     * @param editFieldSelector - cssSelector string on quantity field.
     * @param expected - text "Checkout", after Checkout button click,
     *                   we will be in the same Shopping Cart page.
     */
    @Test (dataProvider = "notAccessToCheckout", priority = 5)
    public void notAccessToCheckoutPageTest(
             final String quantityValue, final String editFieldSelector,
             final String expected) {
        driver.get("http://192.168.234.131/opencart/"
                + "upload/index.php?route=checkout/cart");
        WebElement webElementEditField =
                driver.findElement(By.cssSelector(editFieldSelector));
        webElementEditField.clear();
        webElementEditField.sendKeys(quantityValue);
        webElementInit(refreshButton, refreshButtonXPath).click();
        webElementInit(checkoutButton, checkoutButtonXPath).click();
        String actual = driver.findElement(
                By.xpath("//*[@id='content']/h1")).getText().substring(0, 7);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        Assert.assertEquals(actual, expected);
    }


    /**
     * Data provider for removing product by Remove button.
     * @return String - selector for the product, that will be removed.
     *         int - position removing product in the table.
     *         String - removing product name.
     */
    @DataProvider
    public Object[][] rowDelete() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (!isListsFilled) {
            addProducts();
        }
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        int rowInTable = driver.findElements(By.xpath(
                ".//form/div/table[@class='table "
                        + "table-bordered']/tbody/tr")).size();
        String deletePenultimateItemSelector =
                "//*[@id='content']/form/div/table/tbody/tr["
                        + (rowInTable - 1) + "]/td[4]/div/span/button[2]";
        String productName = driver.findElement(By.xpath(
                "//*[@id='content']/form/div/table/tbody/tr["
                        + (rowInTable - 1) + "]/td[2]/a")).getText();
        return new Object[][]{
                {deletePenultimateItemSelector, rowInTable - 1, productName },
        };
    }

    /**
     * Verifying that product will be successfully removing by Remove button.
     * @param deleteSelector - selector for the product, that will be removed.
     * @param expectedRowCount - the count of rows in a table,
     *                           after remove product.
     * @param expectedNotFindProductName - removing product name.
     */
    @Test (dataProvider = "rowDelete", priority = 6)
    public void deleteItemTest(final String deleteSelector,
                               final int expectedRowCount,
                               final String expectedNotFindProductName) {
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.xpath(deleteSelector)).click();
        driver.navigate().refresh();
        String actual = driver.findElement(By.xpath(
                "//*[@id='content']/form/div/table/tbody/tr["
                        + (expectedRowCount) + "]/td[2]/a")).getText();
        Assert.assertNotEquals(actual, expectedNotFindProductName);
        int actualRowCount = driver.findElements(By.xpath(
                ".//form/div/table[@class='table "
                        + "table-bordered']/tbody/tr")).size();
        Assert.assertEquals(actualRowCount, expectedRowCount);
    }

    /**
     * Verify access to Home page by clicking on Continue Shopping button.
     * Shopping Cart table is not empty.
     */
    @Test (priority = 7)
    public void buttonContinueShoppingTest() {
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector(
                "#content > div.row > div:nth-child(1) > div > "
                        + "div.button-group > button:nth-child(1)")).click();
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        driver.findElement(By.linkText("Continue Shopping")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }

    /**
     * Verify access to Home page by clicking on Continue button.
     * Shopping Cart table is empty.
     */
    @Test (priority = 8)
    public void buttonContinueTest() {
        driver.get(OPEN_CART_URL);
        driver.findElement(By.cssSelector(
                "i[class='fa fa-shopping-cart']")).click();
        driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
        while (driver.findElements(By.cssSelector(
                "#content > form")).size() > 0) {
            driver.findElement(By.cssSelector("#content > form > div > table "
                    + "> tbody > tr:nth-child(1) > td:nth-child(4) >"
                    + " div > span > button.btn.btn-danger")).click();
            driver.navigate().refresh();
        }
        driver.findElement(By.linkText("Continue")).click();
        Assert.assertTrue(driver.findElement(By.id("slideshow0")).isEnabled());
    }

}
