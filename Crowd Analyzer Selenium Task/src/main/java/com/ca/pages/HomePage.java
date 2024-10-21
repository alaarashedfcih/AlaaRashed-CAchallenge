package com.ca.pages;

import com.ca.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Locators

    private final By loginButton = By.xpath("//span[contains(text(),'Sign In')]");
    private final By myAccountBtn = By.xpath("//button[@type='button' and @aria-label='A - My Account' and contains(@class, 'ptw-bg-primary-base')]");

    //Actions

    public LogInPopUpPage openLoginPopUp() {
        Utilities.waitAndClickOnElement(loginButton, wait);
        return new LogInPopUpPage(driver);
    }

    public MyAccountPopUpPage openMyAccountPopUp() {
        Utilities.waitAndClickOnElement(myAccountBtn, wait);
        return new MyAccountPopUpPage(driver);
    }


}
