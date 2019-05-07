package com.softserve.edu.opencart.tests;

import com.softserve.edu.opencart.data.IUser;
import com.softserve.edu.opencart.data.UserRepository;
import com.softserve.edu.opencart.pages.account.MyAccountPage;
import com.softserve.edu.opencart.tools.DataBaseUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Yurii Antokhiv
 * @version 1.0
 */
public class EditAccountTest extends ATestRunner {
    private DataBaseUtils dataBaseUtils;

    @DataProvider // (parallel = true)
    public Object[][] validUser() {
        return new Object[][]{
                //TODO add data to data provider
                {UserRepository.get().johnWick()},
                {UserRepository.get().johnWick()},
                {UserRepository.get().johnWick()}
        };
    }

    @Test(dataProvider = "validUser")
    public void editAccountTest(IUser user) throws Exception {
        //Login & Change user data
        MyAccountPage myAccountPage = loadApplication()
                .gotoLoginPage()
                .successLogin(user)
                .clickEditAccountButton()
                .changeUserInfo(user);
        // Check is correct message displayed
        Assert.assertEquals(MyAccountPage.MY_ACCOUNT_UPDATE_MESSAGE
                , myAccountPage.getMessageText());
        //Verify if user data was changed
        verifyIfUserInfoWasUpdated(user);

        //Logout
        myAccountPage
                .logout()
                .continueHomePage();
    }

    public void verifyIfUserInfoWasUpdated(IUser user) throws Exception {
        //Open JDBC connection
        dataBaseUtils = new DataBaseUtils();
        //Retrieve user data from DB
        List<String> userInfo = dataBaseUtils.checkIfUserInfoWasChanged(user.getEmail());
        // Check first name
        Assert.assertEquals(userInfo.get(0), user.getFirstname());
        // Check last name
        Assert.assertEquals(userInfo.get(1), user.getLastname());
        // Check email
        Assert.assertEquals(userInfo.get(2), user.getEmail());
        // Check telephone
        Assert.assertEquals(userInfo.get(3), user.getTelephone());
        // Check fax
        Assert.assertEquals(userInfo.get(4), user.getFax());
        //Close JDBC connection
        dataBaseUtils.closeConnection();
    }
}
