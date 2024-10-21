package com.ca.pages;

import com.ca.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    private final By signInUserName = By.xpath("(//input[@id='signInFormUsername'])[2]");
    private final By signInPassword = By.xpath("(//input[@id='signInFormPassword'])[2]");
    private final By signInBtn = By.xpath("(//input[@name='signInSubmitButton' and @type='Submit'])[2]");
    private final By errorMsg = By.xpath("(//p[@id='loginErrorMessage'])[2]");

    //Actions
    public void setSignInUserName(String value) {
        Utilities.waitClearAndSetInputField(signInUserName, value, wait);
    }

    public void setSignInPassword(String value) {
        Utilities.waitClearAndSetInputField(signInPassword, value, wait);
    }

    public void clickOnSignInBtn() {
        Utilities.waitAndClickOnElement(signInBtn, wait);
    }


    public HomePage signIn(String userId, String password) {
        setSignInUserName(userId);
        setSignInPassword(password);
        clickOnSignInBtn();
        return new HomePage(driver);
    }

    public String getErrorMessage() {
        return Utilities.getWebElementText(errorMsg, wait);
    }


}
