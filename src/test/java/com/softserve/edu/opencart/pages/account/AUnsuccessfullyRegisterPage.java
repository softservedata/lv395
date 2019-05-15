package com.softserve.edu.opencart.pages.account;

import com.softserve.edu.opencart.pages.common.AStatusPart;
import org.openqa.selenium.WebDriver;

public abstract class AUnsuccessfullyRegisterPage extends AStatusPart {

    public AUnsuccessfullyRegisterPage(WebDriver driver) {
        super(driver);
    }

    abstract void initElements();
}
