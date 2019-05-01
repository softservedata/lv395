package com.softserve.createuserpage.test;

import com.softserve.createuserpage.data.TestData;
import com.softserve.createuserpage.page.RegistrationPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class IncorrectDataTest extends BaseTest {

    /**
     * This dataprovider provides all
     * incorrect data with all boundary
     * values.
     * @return Object[][].
     */
    @DataProvider(name = "incorrectTestData")
    public static Object[][] incorrectTestData() {
        return new Object[][]{
                {new TestData("", "",
                        "lopata@lpnuua", "45",
                        "St", "L", "1",
                        "qwe", "qwe")},
                {new TestData("iamlordvoldemortiamlordvoldemort!",
                        "iamlordvoldemortiamlordvoldemort!",
                        "grabli2016l@pnuua",
                        "123456789012345678901234567890123",
                        "alqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqa"
                                + "lqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqqqa"
                                + "lqqqqqqqqalqqqqqqqqalqqqqqqqqalqqqqqqq",
                        "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlv"
                                + "lvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvlvl",
                        "11", "qwe", "qwe")}
        };
    }

    /**
     * In this test we entered all invalid data
     * with boundary values.
     * @param data data;
     */
    @Test(dataProvider = "incorrectTestData")
    public void checkEnterIncorrectData(TestData data) {
        RegistrationPage rp = new RegistrationPage(driver);
        rp.registerUserUnsuccessfully(data);
        rp.clickSubmitButton();
        rp.checkErrorMessages();
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
            String selectTwoUsersSQL = "SELECT * FROM opencart.oc_customer\n"
                    + "WHERE email IN ('lopata@lpnuua', 'grabli2016l@pnuua');";
            ResultSet res = statement.executeQuery(selectTwoUsersSQL);
            if (res.next()) {
                System.out.println("Error");
            } else {
                System.out.println("Users are not created");
            }
        }
    }
}
