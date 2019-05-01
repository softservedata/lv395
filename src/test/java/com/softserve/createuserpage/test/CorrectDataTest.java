package com.softserve.createuserpage.test;

import com.softserve.createuserpage.data.TestData;
import com.softserve.createuserpage.page.ReceiptPage;
import com.softserve.createuserpage.page.RegistrationPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

import static org.testng.Assert.assertEquals;

public class CorrectDataTest extends BaseTest {

    /**
     * This dataprovider provides all
     * correct data with all boundary
     * values.
     * @return Object[][].
     */
    @DataProvider(name = "correctTestData")
    public static Object[][] correctTestData() {
        return new Object[][]{
                {new TestData("L", "V",
                        "nazar.bako.kn.2016@lpnu.ua", "456",
                        "Var", "Lv", "48",
                        "qwer", "qwer")},
                {new TestData("iamlordvoldemortiamlordvoldemort",
                        "iamlordvoldemortiamlordvoldemort",
                        "naz.bibasko.kn.2016@lpnu.ua",
                        "56698765645669876564566987656445",
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv",
                        "4876448764", "qwertqwertqwertqwert",
                        "qwertqwertqwertqwert")},
        };
    }

    /**
     * @param data correct data.
     */
    @Test(dataProvider = "correctTestData")
    public void checkEnterCorrectDataTest(TestData data) {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.registerUserSuccessfully(data);
        ReceiptPage rcp = rp.clickSubmitButton();
        assertEquals(rcp.confirmationHeader(),
                "Your Account Has Been Created!");
        rcp.clickLogOutButton();
        rcp.clickRegisterButton();
    }

    /**
     * This @AfterClass
     * closes driver.
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
                    + "WHERE email LIKE 'nazar.bako.kn.2016@lpnu.ua';";

            ResultSet rs1 = statement.executeQuery(selectFirstUserSQL);
            ////we check if customer is registered in db and remove him
            while (rs1.next()) {
                String firstName = rs1.getString("firstname");
                String email = rs1.getString("email");
                System.out.println("firstName : " + firstName);
                System.out.println("email : " + email);
                assertEquals(firstName, "L");
                assertEquals(email, "nazar.bako.kn.2016@lpnu.ua");
                rs1.absolute(1);
                rs1.deleteRow();
                System.out.println("Deleted!");
            }

            String selectSecondUserSQL = "SELECT * FROM opencart.oc_customer\n"
                    + "WHERE email LIKE 'naz.bibasko.kn.2016@lpnu.ua';";

            ResultSet rs2 = statement.executeQuery(selectSecondUserSQL);
            ////we check if customer is registered in db and remove him
            while (rs2.next()) {
                String firstName = rs2.getString("firstname");
                String email = rs2.getString("email");
                System.out.println("firstName : " + firstName);
                System.out.println("email : " + email);
                assertEquals(firstName, "iamlordvoldemortiamlordvoldemort");
                assertEquals(email, "naz.bibasko.kn.2016@lpnu.ua");
                rs2.absolute(1);
                rs2.deleteRow();
                System.out.println("Deleted!");
            }
        }
    }
}
