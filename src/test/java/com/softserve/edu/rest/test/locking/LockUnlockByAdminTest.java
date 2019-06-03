package com.softserve.edu.rest.test.locking;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.GuestService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LockUnlockByAdminTest extends ATestRunner {

    @DataProvider
    public Object[][] userData() {
        return new Object[][]{
                { UserRepository.getUser1() }, //user
        };
    }

    @Test(dataProvider = "userData")
    public void lockUserByAdminTest(User user) {
        // Steps
        Assert.assertTrue(adminService.lockUser(user));

        Assert.assertTrue(adminService.getLockedUsers()
                .contains(user.getName()));
    }

    @Test(dataProvider = "userData")
    public void unlockUserByAdminTest(User user) {
        lockUserByAdminTest(user);
        Assert.assertTrue(adminService.unlockUser(user));
        Assert.assertFalse(adminService.getLockedUsers()
                .contains(user.getName()));
    }

}
