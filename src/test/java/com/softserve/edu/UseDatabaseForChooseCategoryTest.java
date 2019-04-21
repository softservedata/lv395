package com.softserve.edu;


import com.softserve.edu.tools.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class UseDatabaseForChooseCategoryTest {

    /**
     * Driver to have accesses to web page.
     */
    private WebDriver driver;
    /**
     * ip address (ip address could be changed).
     */
    final private String ip = "192.168.36.134";

    /**
     * Before class.
     * (action in this class should happens before all other action)
     */
    @BeforeClass
    public void beforeClass() {
//        System.out.println("res");
//        System.out.println(this.getClass().getResource("/chromedriver-windows-32bit.exe"));
//        System.out.println("PATH to WebDriver + " + this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        System.setProperty("webdriver.chrome.driver",
//                this.getClass().getResource("/chromedriver-windows-32bit.exe").getPath());
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get("http://192.168.36.129/opencart/upload/");
//        Thread.sleep(1000); // For Presentation Only
        System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://" + ip + "/opencart/upload/");
    }

    /**
     * In the end for all action driver should be "quit".
     */
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    /**
     * Before all method next action should be done.
     */
    @BeforeMethod
    public void beforeMethod() {
        driver.findElement(By.name("search")).click();
        driver.findElement(By.name("search")).clear();
        driver.findElement(By.name("search")).sendKeys("" + Keys.ENTER);
        driver.findElement(By.id("input-search")).click();
        driver.findElement(By.id("input-search")).clear();
    }

    /**
     * Here are data for positive testing category.
     * first parameter - data for search field;
     * second parameter - category, what will be chosen
     * third parameter - will be used search in product description or not.
     *
     * @return object with three parameters described above
     */
    @DataProvider
    public Object[][] dataForPositiveTestingCategory() {

        return new Object[][]{

                {"iphone", ".//option[@value='20']", true},
                {"inch", ".//option[@value='18']", true},
                {"%", ".//option[@value='20']", false},
                {"iphone", ".//option[@value='20']", false},
                {"%", ".//option[@value='20']", true},

        };
    }

    /**
     * Positive testing category.
     *
     * @param inputData                     data for search field
     * @param category                      category, what will be chosen
     * @param useSearchInProductDescription -will be used search in product
     *                                      description or not
     * @throws SQLException - we are using mysql, this is
     *                      why this exception could happen
     */
    @Test(dataProvider = "dataForPositiveTestingCategory")
    public void chooseCategoriesPositiveTest(
            final String inputData, final String category,
            final Boolean useSearchInProductDescription) throws SQLException {
        if (useSearchInProductDescription) {
            driver.findElement(By.id("description")).click();
        }
        driver.findElement(By.name("category_id")).click();
        driver.findElement(By.xpath(category)).click();
        driver.findElement(By.id("input-search")).
                sendKeys(inputData + Keys.ENTER);

        List<WebElement> webElements = driver.findElements(By.xpath(
                ".//div[@class='row']/div/div/div/div[@class='caption']/h4/a"));
        List<String> productsOnThePage = new ArrayList<>();
        for (WebElement webElement : webElements) {
            productsOnThePage.add(webElement.getText());
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://192.168.36.134/opencart?"
                        + "useUnicode=true&useJDBCCompliantTimezoneShift=true&"
                        + "useLegacyDatetimeCode=false&serverTimezone=UTC",
                "lv395", "lv395taqc<>LV395TAQC");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT opencart.oc_product_description.name,"
                        + " opencart.oc_category_description.name"
                        + " FROM opencart.oc_product_description"
                        + "  join opencart.oc_product_to_category"
                        + " on opencart.oc_product_to_category.product_id"
                        + "=opencart.oc_product_description.product_id "
                        + "join  opencart.oc_category_description  "
                        + "on opencart.oc_product_to_category.category_id"
                        + "=opencart.oc_category_description.category_id "
                        + "order by opencart.oc_product_description.name ASC;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            boolean productAlreadyExist = false;
            Product product = new Product();
            for (Product productInList : products) {
                if (productInList.getName().contains(resultSet.getString(1))) {
                    productInList.getCategory().add(resultSet.getString(2).
                            replace("&amp;", "&"));
                    productAlreadyExist = true;
                }
            }
            if ((!productAlreadyExist) && (productsOnThePage.
                    contains(resultSet.getString(1).
                            replace("&quot", "")))) {
                product.setName(resultSet.getString(1).replace("&quot", ""));
                List<String> categories = new ArrayList<>();
                categories.add(resultSet.getString(2).replace("&amp;", "&"));
                product.setCategory(categories);
                products.add(product);
            }
        }
        String categoryName = driver.findElement(By.xpath(category)).getText();
        for (Product product : products) {
            Assert.assertTrue(product.getCategory().contains(categoryName));
        }
        System.out.println(products.toString());
        System.out.println(products.size());
        connection.close();
    }

}
