package com.softserve.edu;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddFunctionality extends DatabaseConnector {

    protected WebDriver driver;
    private Session session;
    /*private DatabaseOperator operator;*/

    private final String URL = "192.168.239.129";

/*    @BeforeSuite
    public void dumpDb() {
        operator = new DatabaseOperator();
        operator.remoteServerConnect();
        operator.dumpDatabase();
        operator.remoteServerDisconnect();
    }*/

/*    @AfterSuite
    public void restoreDb() {
        operator.remoteServerConnect();
        dropDatabase();
        operator.restoreDatabase();
        operator.remoteServerDisconnect();
    }*/

    @BeforeClass
    public void setUp() {
        String webDriverPath =  this.getClass().getResource("/").toString();
        webDriverPath = webDriverPath.substring(webDriverPath.indexOf("/"));
        System.setProperty("webdriver.chrome.driver",
                webDriverPath + "chromedriver-windows-32bit.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        dbConnect();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        dbClose();
    }

    @BeforeMethod
    public void webServiceStart() {
        driver.get("http://" + URL + "/opencart/upload/");
    }

    public void cartCleaner(WebDriver driver, String URL) {
        driver.get("http://" + URL + "/opencart/upload/");
        //WebElements initialization
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        new WebDriverWait(driver, 3);
        if (driver.findElements(By.cssSelector("[title^='Remove'")).size() > 0) {
            List<WebElement> cartElementsTableRows;
            WebElement cartElementsTable = driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody"));
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String getURL() {
        return URL;
    }

    public WebElement addProduct(int index){
    List<WebElement> addButtons = driver
            .findElements(By.cssSelector("div[class*='button-group']"
                    + " button[onclick*='cart.add']"));
    return addButtons.get(index);
    }

    public void openCart(){
        driver.findElement(By.id("cart")).click();
    }

    public WebElement getGoodPlate() {
       WebElement goodPlate = driver.findElement(By.xpath("//*[@id='cart']/ul/li[1]/table/tbody/tr"));
       return goodPlate;
    }

    public void logIn(String email, String password) {
        driver.findElement(By.cssSelector("li[class*='dropdown'] a[title*='My Account']")).click();
        driver.findElement(By.cssSelector("ul[class^='dropdown-menu'] li:last-child a")).click();
        WebElement loginField = driver.findElement(By.cssSelector("input[name='email']"));
        loginField.clear();
        loginField.sendKeys(email);
        WebElement passField = driver.findElement(By.cssSelector("input[name='password']"));
        passField.clear();
        passField.sendKeys(password + Keys.RETURN);
        driver.findElement(By.id("logo")).click();
    }

    public void logOut() {
        driver.findElement(By.cssSelector("li[class*='dropdown'] a[title*='My Account']")).click();
        driver.findElement(By.cssSelector("ul[class^='dropdown-menu'] li:last-child a")).click();
        driver.findElement(By.id("logo")).click();
    }

    public int getProductQuantity(int product_id) {
        session = getFactory().openSession();
        Criteria userCriteria = session.createCriteria(Product.class);
        userCriteria.add(Restrictions.eq("product_id", product_id));
        Product product = (Product) userCriteria.uniqueResult();
        session.close();
        return product.getQuantity();
    }

    public void dropDatabase() {
        session = getFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            session.createSQLQuery("DROP DATABASE restaurantapp").executeUpdate();
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
