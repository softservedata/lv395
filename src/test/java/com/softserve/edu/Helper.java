package com.softserve.edu;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Helper {

    protected ChromeDriver driver;
    protected List<String> total;
    protected final String OPEN_CART_URL = "http://192.168.234.131/opencart/upload/";
    private final String OPENCART_ADMIN_URL = "http://192.168.234.131/opencart/upload/admin/index.php?";
    protected String tableCountRowXPath = ".//form/div/table[@class='table table-bordered']/tbody/tr";
    protected int tableCountRow = 0;
    protected double unitPrice;



    public Helper(){
    }

    protected ChromeDriver driverAdmin;
    protected List<Double> totalList = new ArrayList<>();
    protected List<String> productNameList = new ArrayList<>();
    protected List<String> quantityList = new ArrayList<>();
    protected List<Integer> quantityOnStockList = new ArrayList<>();

    public List<Double> fillTotalList(int size){
        for (int i=0; i < size; i++) {
            totalList.add(getFloatNumber(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr["+ (i+1) +"]/td[6]")).getText()));
        }
        return totalList;
    }

    public void fillProductNameList(){
        productNameList.clear();
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(tableCountRowXPath)).size();
        for (int i=0; i<tableCountRow; i++) {
            productNameList.add(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (i+1) +"]/td[2]/a")).getText());
        }
    }

    public void fillQuantityList(){
        quantityList.clear();;
        driver.findElement(By.cssSelector("i[class='fa fa-shopping-cart']")).click();
        tableCountRow = driver.findElements(By.xpath(tableCountRowXPath)).size();
        for (int i=0; i<tableCountRow; i++) {
            quantityList.add(driver.findElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr[" + (i+1) +"]/td[4]/div/input")).getAttribute("value"));
        }
    }

    public double getFloatNumber(String s) {
        s = s.replaceAll("[^0-9?!\\.]+", "");
        double d = Double.parseDouble(s);
        return (double) Math.round(d * 100) / 100;
    }

    public void fillLists(){
        fillQuantityList();
        fillProductNameList();
        fillQuantityOnStockList();
    }

    public void fillQuantityOnStockList(){
        quantityOnStockList.clear();
        driverAdmin = new ChromeDriver();
        driverAdmin.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverAdmin.get(OPENCART_ADMIN_URL);
        driverAdmin.findElement(By.id("input-username")).sendKeys("admin" + Keys.TAB + "admin" + Keys.ENTER);
        driverAdmin.findElement(By.id("button-menu")).click();
        driverAdmin.findElement(By.linkText("Catalog")).click();
        driverAdmin.findElement(By.xpath("(//a[contains(text(),'Products')])[1]")).click();
        for (int i=0; i<tableCountRow; i++){
            quantityOnStockList.add(Integer.parseInt(driverAdmin.findElement(By.xpath
                    ("//tbody/tr/td[text()='" + productNameList.get(i)+"']/following-sibling::td[*]/span[@class='label label-success']")).getText()));
        }
        driverAdmin.quit();
        //return quantityOnStockList;
    }

    public WebElement webElementInit(WebElement we, String xPath){
        we = driver.findElement(By.xpath(xPath));
        return we;
    }

    public void logIn() {
        driver.get(OPEN_CART_URL + "index.php?route=account/login");
        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).sendKeys("yuriykril7773@gmail.com");
        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).sendKeys("qwerty" + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
}
