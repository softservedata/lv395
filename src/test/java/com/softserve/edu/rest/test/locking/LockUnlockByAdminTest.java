package com.softserve.edu.rest.test.locking;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import com.softserve.edu.rest.service.UserService;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This class is used for testing lock/unlock functionality by Admin.
 * And includes in itself 7 tests.
 *
 * @author Yurii Kril
 */

public class LockUnlockByAdminTest extends ATestRunner {
    /**
     * DataProvider, that will be used by two tests.
     * @see #checkLockedAdminsListTest,
     * @see #checkLockedUsersListTest
     * @return User with admin rights,
     *         User without admin rights.
     */
    @DataProvider
    public Object[][] userAndAdminData() {
        return new Object[][] {
                {UserRepository.newUserWithAdminRights(),
                        UserRepository.getUser1() },
        };
    }

    /**
     * This test is used for checking the LockedAdminList.
     * LockedAdminList is the list with locked admins (users with admin rights).
     * @param admin - user with admin rights.
     * @param user - user without admin rights.
     */
    @Test(dataProvider = "userAndAdminData", priority = 3)
    public void checkLockedAdminsListTest(final User admin, final User user) {
        log.info("Test checkLockedAdminList started!");
        // Locking two users.
        adminService.lockUser(admin);
        adminService.lockUser(user);
        // Verify unlocking functional (Admin unlocks another admin).
        boolean isAdminInLockedAdmins = adminService.
                isAdminPresentInLockedAdmins(admin);
        boolean isUserInLockedAdmins = adminService.
                isAdminPresentInLockedAdmins(user);
        adminService.unlockAllUsers();
        // Verify that, is admin present in locked admin list.
        Assert.assertTrue(isAdminInLockedAdmins);
        // Verify that, is user absent in locked admin list.
        Assert.assertFalse(isUserInLockedAdmins);
        log.info("Test checkLockedAdminList finished!\n");
    }

    /**
     * This test is used for checking the LockedUserList.
     * LockedUserList is the list with locked users (without admin rights).
     * @param admin - user with admin rights.
     * @param user - user without admin rights.
     */
    @Test(dataProvider = "userAndAdminData", priority = 4)
    public void checkLockedUsersListTest(final User admin, final User user) {
        log.info("Test checkLockedUsersList started!");
        adminService.lockUser(admin);
        adminService.lockUser(user);
        // Verify unlocking functional (Admin unlocks another admin).
        boolean isAdminInLockedUsers = adminService.
                isUserPresentInLockedUsers(admin);
        boolean isUserInLockedUsers = adminService.
                isUserPresentInLockedUsers(user);
        adminService.unlockAllUsers();
        // Verify that, is admin absent in locked admin list.
        Assert.assertTrue(isUserInLockedUsers);
        Assert.assertFalse(isAdminInLockedUsers);
        log.info("Test checkLockedUsersList finished!\n");
    }

    /**
     * DataProvider with one parameter for two tests.
     * @see #lockUserByAdminTest,
     * @see #unlockUserByAdminTest
     * @return user without admin rights.
     */
    @DataProvider
    public Object[][] userData() {
        return new Object[][] {
                {UserRepository.getUser1() }, // user
        };
    }

    /**
     * This test is used for checking locking functionality by admin.
     * Admin locks a user. Than user tries to login.
     * @param user - user without admin rights.
     */
    @Test (dataProvider = "userData", priority = 5)
    public void lockUserByAdminTest(final User user) {
        log.info("Test lockUserByAdmin started!");
        // Admin locks the user.
        Assert.assertTrue(adminService.lockUser(user));
        // Trying to login via locked user
        GuestService guestService = new GuestService();
        Assert.assertTrue(guestService.isUserLockedAfterTryToLogin(user));
        // Check locked users list.
        Assert.assertTrue(adminService.isUserPresentInLockedUsers(user));
        log.info("Test lockUserByAdmin finished!\n");
    }

    /**
     * This test is used for checking unlocking functionality by admin.
     * Admin locks a user. Than unlocks him.
     * After unlocking, user tries to login.
     * @param user - user without admin rights.
     */
    @Test(dataProvider = "userData", priority = 6)
    public void unlockUserByAdminTest(final User user) {
        log.info("Test unlockUserByAdmin started!");
        // If user didn't lock, we are locking him.
        if (!adminService.isUserPresentInLockedUsers(user)) {
            adminService.lockUser(user);
        }
        // Verify unlocking functional.
        Assert.assertTrue(adminService.unlockUser(user));
        // Verify that, is user absent in locked users list.
        Assert.assertFalse(adminService.isUserPresentInLockedUsers(user));
        // For presentation, that user was be really unlocked.
        GuestService guestService = new GuestService();
        UserService userService = guestService.SuccessfulUserLogin(user);
        Assert.assertTrue(adminService.isUserLogged(user));
        userService.logoutUser();
        log.info("Test unlockUserByAdmin finished\n");
    }

    /**
     * DataProvider with one parameter for three tests.
     * @see #lockAdminByAdminTest,
     * @see #unlockAdminByAdminTest,
     * @see #adminLockHimselfTest
     * @return admin
     */
    @DataProvider
    public Object[][] adminData() {
        return new Object[][] {
                {UserRepository.newUserWithAdminRights() }, // admin
        };
    }

    /**
     * This test checks, that admin can lock another admin.
     * @param admin - user with admin rights.
     */
    @Test (dataProvider = "adminData", priority = 7)
    public void lockAdminByAdminTest(final User admin) {
        log.info("Test lockAdminByAdmin started!");
        // Admin locks another admin.
        Assert.assertTrue(adminService.lockUser(admin));
        // Trying to login via locked admin.
        GuestService guestService = new GuestService();
        Assert.assertTrue(guestService.isUserLockedAfterTryToLogin(admin));
        // Check locked admins list.
        Assert.assertTrue(adminService.isAdminPresentInLockedAdmins(admin));
        log.info("Test lockAdminByAdmin finished!\n");
    }

    /**
     * This test checks, that admin can unlock another admin.
     * @param admin - user with admin rights.
     */
    @Test(dataProvider = "adminData", priority = 8)
    public void unlockAdminByAdminTest(final User admin) {
        log.info("Test unlockAdminByAdmin started!");
        // If user didn't lock, we are locking him.
        if (!adminService.isAdminPresentInLockedAdmins(admin)) {
            adminService.lockUser(admin);
        }
        // Verify unlocking functional (Admin unlocks another admin).
        Assert.assertTrue(adminService.unlockUser(admin));
        // Verify that, is admin absent in locked admin list.
        Assert.assertFalse(adminService.isAdminPresentInLockedAdmins(admin));
        log.info("Test unlockAdminByAdmin finished!\n");
    }

    /**
     * This test checks, that admin can't lock himself.
     * @param admin - user with admin rights.
     */
    @Test (dataProvider = "adminData", priority = 9)
    public void adminLockHimselfTest(final User admin) {
        log.info("Test adminLockHimself started!");
        // If admin locked, we are unlocking him.
        if (adminService.isAdminPresentInLockedAdmins(admin)) {
            adminService.unlockUser(admin);
        }
        GuestService guestService = new GuestService();
        AdminService adminPetro = guestService.SuccessfulAdminLogin(admin);
        adminPetro.getAllLoggedUsers();
        // Expecting, that admin can't lock himself
        Assert.assertFalse(adminPetro.lockUser(admin));
        adminPetro.logoutUser();
        boolean expectedResult = adminService
                .isAdminPresentInLockedAdmins(admin);
        Assert.assertFalse(expectedResult);
        log.info("Test adminLockHimself finished!\n");
    }

}
