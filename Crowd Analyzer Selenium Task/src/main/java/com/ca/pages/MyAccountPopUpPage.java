package com.ca.pages;

import com.ca.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPopUpPage extends BasePage {

    public MyAccountPopUpPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By logoutBtn = By.xpath("//div[contains(text(),'Log out')]");

    //Actions


    public Boolean isLogOutDisplayed() {
        return Utilities.isFieldDisplayed(logoutBtn, wait);
    }

}
