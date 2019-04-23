/*
 * AddFunctionality
 *
 * v. 1.0
 *
 * Copyright (c) 2019 Maksym Burko.
 */
package com.softserve.edu.functional;

import com.softserve.edu.DatabaseConnector;
import com.softserve.edu.DatabaseOperator;
import com.softserve.edu.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class which provides common functionality
 * for all Opencart testing methods.
 */
public class AddFunctionality extends DatabaseConnector {

    protected WebDriver driver; // Selenium WebDriver creation.
    private Session session;    // Hibernate session.
    private DatabaseOperator operator = new DatabaseOperator(); // Operations with DB

    private final String URL = "192.168.239.129";                   // Opencart URL.
    private final String DUMP_DATABASE = "/home/backupdb.sh";       // Script for dumping DB.
    private final String RESTORE_DATABASE = "/home/restoredb.sh";   // Script for restoring DB.

    /**
     * Test suite {@link BeforeSuite} method,
     * which dumps DB and create DB connection
     */
    @BeforeSuite
    public void dumpDb() {
        dbConnect();
        operator.remoteServerConnect();
        operator.runShellScript(DUMP_DATABASE);
    }

    /**
     * Test suite {@link AfterSuite} method,
     * which drops DB, restore it and closes DB connection.
     */
    @AfterSuite
    public void restoreDb() {
        dropDatabase();
        dbClose();
        operator.runShellScript(RESTORE_DATABASE);
        operator.remoteServerDisconnect();
    }

    /**
     * Test suite {@link BeforeClass} method,
     * establish WebDriving settings.
     */
    @BeforeClass
    public void setUp() {
        String webDriverPath =  this.getClass().getResource("/").toString();
        webDriverPath = webDriverPath.substring(webDriverPath.indexOf("/"));
        System.setProperty("webdriver.chrome.driver",
                webDriverPath + "chromedriver-windows-32bit.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Test suite {@link AfterClass}
     * which quites Selenium.
     */
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /**
     * Test suite {@link BeforeMethod}
     * Getting connection to Opencart site.
     */
    @BeforeMethod
    public void webServiceStart() {
        driver.get("http://" + URL + "/opencart/upload/");
    }

    /**
     * Test suite {@link AfterMethod}
     * cleans product cart after tests.
     */
    @AfterMethod
    public void cleanCart() {
        cartCleaner(driver, getURL());
    }

    /**
     * Method for cleaning cart after test running,
     * deletes all items from the cart.
     *
     * @param driver - Selenium WebDriver object.
     * @param URL - Opencart link.
     */
    public void cartCleaner(WebDriver driver, String URL) {
        driver.get("http://" + URL + "/opencart/upload/");
        // Set explicitly wait
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if (driver.findElements(By.cssSelector("[title^='Remove'")).size() > 0) {
            List<WebElement> cartElementsTableRows;
            WebElement cartElementsTable = driver
                    .findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody"));
            cartElementsTableRows = cartElementsTable.findElements(By.tagName("tr"));
            //Cycle which deletes elements from cart
            for (int countOfElements = cartElementsTableRows.size(); countOfElements > 0; countOfElements--) {
                driver.findElement(By.id("cart")).click();
                driver.findElement(By.cssSelector("button[class*='btn-danger']")).click();
                cartElementsTableRows.remove(countOfElements - 1);
                driver.navigate().refresh();
            }
        } else {
            driver.navigate().refresh();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * Simple getter for URL.
     *
     * @return opencart URl.
     */
    public String getURL() {
        return URL;
    }

    /**
     * Press button to adding item with
     * following index to cart.
     *
     * @param index - index of desired product.
     * @return - WebElement of desired item.
     */
    public WebElement addProduct(int index){
    List<WebElement> addButtons = driver
            .findElements(By.cssSelector("div[class*='button-group']"
                    + " button[onclick*='cart.add']"));
    return addButtons.get(index);
    }

    /**
     * Method for opening product cart.
     */
    public void openCart(){
        driver.findElement(By.id("cart")).click();
    }

    /**
     * Method for getting plate of goods
     * from the cart.
     *
     * @return product plate.
     */
    public WebElement getGoodPlate() {
       WebElement goodPlate =
               driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody/tr"));
       return goodPlate;
    }

    /**
     * Method for signing-up on Opencart.
     *
     * @param email user email.
     * @param password user password.
     */
    public void logIn(String email, String password) {
        driver.findElement(By
                .cssSelector("li[class*='dropdown'] a[title*='My Account']")).click();
        driver.findElement(By
                .cssSelector("ul[class^='dropdown-menu'] li:last-child a")).click();
        WebElement loginField = driver
                .findElement(By.cssSelector("input[name='email']"));
        loginField.clear();
        loginField.sendKeys(email);
        WebElement passField = driver
                .findElement(By.cssSelector("input[name='password']"));
        passField.clear();
        passField.sendKeys(password + Keys.RETURN);
        driver.findElement(By.id("logo")).click();
    }

    /**
     * Method for logging out from Opencart.
     */
    public void logOut() {
        driver.findElement(By
                .cssSelector("li[class*='dropdown'] a[title*='My Account']")).click();
        driver.findElement(By
                .cssSelector("ul[class^='dropdown-menu'] li:last-child a")).click();
        driver.findElement(By.id("logo")).click();
    }

    /**
     * Method for getting quantity of following
     * product from database.
     *
     * @param product_id id of desired product.
     * @return quantity of desired product.
     */
    public int getProductQuantity(int product_id) {
        session = getFactory().openSession();
        Criteria userCriteria = session.createCriteria(Product.class);
        userCriteria.add(Restrictions.eq("product_id", product_id));
        Product product = (Product) userCriteria.uniqueResult();
        session.close();
        return product.getQuantity();
    }

    /**
     * Method which deletes database.
     */
    public void dropDatabase() {
        session = getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.createSQLQuery("DROP DATABASE opencart").executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
