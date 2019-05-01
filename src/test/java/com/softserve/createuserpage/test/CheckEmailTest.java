package com.softserve.createuserpage.test;

import com.softserve.createuserpage.data.TestData;
import com.softserve.createuserpage.page.EmailPage;
import com.softserve.createuserpage.page.ReceiptPage;
import com.softserve.createuserpage.page.RegistrationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;

/**
 * In this test we check if user receives
 * confirmation message on his email.
 */
public class CheckEmailTest extends BaseTest {

    /**
     * This dataprovider provides all
     * correct data.
     * @return Object[][].
     */
    @DataProvider(name = "correctTestData")
    public static Object[][] correctTestData() {
        return new Object[][]{
                {new TestData("Nazar", "Baks",
                        "testuser395lv@gmail.com", "45655654",
                        "Varshavska", "Lviv", "485468",
                        "qwerty", "qwerty")}
        };
    }

    /**
     * In this test we enter all valid data
     * with boundary values.
     * @param data data;
     */
    @Test(dataProvider = "correctTestData")
    public void checkEmail(TestData data) {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.registerUserSuccessfully(data);
        ReceiptPage rcp = rp.clickSubmitButton();
        assertEquals(rcp.confirmationHeader(),
                "Your Account Has Been Created!");
        rcp.gotoEmail();
        EmailPage em = new EmailPage(driver);
        em.loginEmail();
        WebDriverWait wait2 = new WebDriverWait(driver, 15, 500);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By
                .cssSelector("div[class*='y6'] > span")));
        assertEquals(em.confirmationHeader(),
                "Your Store - Thank you for registering");
    }

    /**
     * This @AfterClass
     * check if user creates in db.
     */
    @AfterClass
    public void checkJdbc() throws ClassNotFoundException, SQLException {
        String connectionUrl = "jdbc:mysql://192.168.11.129:3306/opencart";
        String userName = "lv395";
        String password = "Lv395_Taqc";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager
                        .getConnection(connectionUrl, userName, password);
                Statement statement = conn.createStatement(ResultSet
                                .TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE)) {
            System.out.println("Connection success!");
            String selectFirstUserSQL = "SELECT * FROM opencart.oc_customer\n"
                    + "WHERE email LIKE 'testuser395lv@gmail.com';";

            ResultSet rs1 = statement.executeQuery(selectFirstUserSQL);
            ////check if customer is registered in db and remove him
            while (rs1.next()) {
                String firstName = rs1.getString("firstname");
                String email = rs1.getString("email");
                System.out.println("firstName : " + firstName);
                System.out.println("email : " + email);
                assertEquals(firstName, "Nazar");
                assertEquals(email, "testuser395lv@gmail.com");
                rs1.absolute(1);
                rs1.deleteRow();
                System.out.println("Deleted!");
            }
        }
    }
}
