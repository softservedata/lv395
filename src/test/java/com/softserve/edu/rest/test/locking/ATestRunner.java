package com.softserve.edu.rest.test.locking;

import com.softserve.edu.rest.data.User;
import com.softserve.edu.rest.data.UserRepository;
import com.softserve.edu.rest.service.AdminService;
import com.softserve.edu.rest.service.GuestService;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This class are related with locking functionality tests
 * The class include BeforeClass and AfterClass
 * Another words: initialize and cleaning before and after locking tests.
 *
 * @author Yurii Kril
 */
public abstract class ATestRunner {
    /** Variable, that will be controlling all process in tests.*/
    AdminService adminService;
    /** Logger.*/
    protected final Logger log = Logger.getLogger(this.getClass());

    /**
     * Creating new admin and initialize adminService
     */
    @BeforeClass
    public void beforeClass() {
        log.info("Test suite started!");
        log.info("Before class started!");
        GuestService guestService = new GuestService();
        guestService.resetServiceToInitialState();
        adminService = guestService.
                SuccessfulAdminLogin(UserRepository.getAdmin());
        User newAdmin = UserRepository.newUserWithAdminRights();
        adminService.createUser(newAdmin);
        log.info("Before class finished!\n");
    }

    /**
     * Cleaning process after tests ran.
     */
    @AfterClass
    public void afterClass() {
        log.info("AfterClass started! ");
        adminService.unlockAllUsers();
        adminService.getAllLoggedUsers();
        adminService.logoutUser();
        adminService.resetServiceToInitialState();
        log.info("Test suite finished!");
    }
}
