package com.ca.pages;

import com.ca.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPopUpPage extends BasePage {

    public LogInPopUpPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By loginBtn = By.xpath("//button[contains(text(),'Log in')]");
    private final By registerBtn = By.xpath("//button[contains(text(),'Create Account')]");

    //Actions
    public SignInPage clickOnLoginBtnFromPopUp() {
        Utilities.waitAndClickOnElement(loginBtn, wait);
        return new SignInPage(driver);
    }

    public CreateAccountPage clickOnCreateAccountBtnFromPopUp() {
        Utilities.waitAndClickOnElement(registerBtn, wait);
        return new CreateAccountPage(driver);
    }

}
