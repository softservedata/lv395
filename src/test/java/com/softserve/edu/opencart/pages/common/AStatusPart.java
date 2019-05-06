package com.softserve.edu.opencart.pages.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.softserve.edu.opencart.tools.LeaveUtils;

public abstract class AStatusPart extends AHeaderPart {

    private final String BREADCRUMBSNAME_NOT_FOUND = "BreadcrumbsName: %s not Found.";
    //
    private List<WebElement> breadcrumbs;

    public AStatusPart(WebDriver driver) {
        super(driver);
        initElements();
    }

    private void initElements() {
        breadcrumbs = new ArrayList<>();
        for (WebElement current : getBreadcrumbWebElements())
        {
            breadcrumbs.add(current);
        }
    }
    
    private List<WebElement> getBreadcrumbWebElements() {
        return driver.findElements(By.cssSelector("ul.breadcrumb > li > a"));
    }

    // Page Object

    // breadcrumbs
    private List<WebElement> getBreadcrumbs() {
        return breadcrumbs;
    }
    
    // Functional
    public List<String> getBreadcrumbsTexts() {
        List<String> breadcrumbsTexts = new ArrayList<>();
        for (WebElement current : getBreadcrumbs())
        {
            String text = current.getText().trim();
            text = text.length() < 2 ? "Home" : text;
            breadcrumbsTexts.add(text);
        }
        return breadcrumbsTexts;
    }
    
    public WebElement getBreadcrumbByName(String breadcrumbName) {
        WebElement result = null;
        for (WebElement current : getBreadcrumbs())
        {
            String text = current.getText().trim();
            text = text.length() < 2 ? "Home" : text;
            if (text.toLowerCase()
                    .equals(breadcrumbName.toLowerCase()))
            {
                result = current;
                break;
            }
        }
        LeaveUtils.castExceptionByCondition(result == null,
                String.format(BREADCRUMBSNAME_NOT_FOUND, breadcrumbName));
        return result;
    }
    
    public void clickBreadcrumbByName(String breadcrumbName)
    {
        getBreadcrumbByName(breadcrumbName).click();
    }

    public int getBreadcrumbsSize()
    {
        return getBreadcrumbs().size();
    }

    // Business Logic
}
