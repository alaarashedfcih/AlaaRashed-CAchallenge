package com.ca.pages;

import com.ca.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPage extends BasePage {
    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    // Locators

    private final By registerEmailAddress = By.id("emailAddress");
    private final By continueBtn = By.xpath("//button[@type='submit' and contains(@class, 'pds-bg-primary')]");
    private final By secondContinueBtn = By.xpath("//button[contains(text(),'Continue')]");
    private final By closePopUP = By.xpath("//button[contains(@class, 'onetrust-close-btn-handler') and @aria-label='Close']");
    private final By dismissBtn = By.xpath("//button[contains(text(),'Dismiss')]");
    private final By errorMsg = By.cssSelector("span.error-text");
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By postalCode = By.id("postalCode");
    private final By dogCount = By.xpath("//div[@class='pds-w-full']//select[@name='dogCount']");
    private final By catCount = By.xpath("//div[@class='pds-w-full']//select[@name='catCount']");
    private final By password = By.cssSelector("#password");
    private final By confirmPassword = By.cssSelector("#confirmPassword");
    private final By createAccountBtn = By.xpath("//button[contains(text(),'Create Account')]");
    private final By agreeBtn = By.cssSelector("#terms");
    //Actions
    public void setRegisterEmailAddress(String value) {
        Utilities.waitClearAndSetInputField(registerEmailAddress, value, wait);
    }

    public void setFirstName(String value) {
        Utilities.waitClearAndSetInputField(firstName, value, wait);
    }

    public void setLastName(String value) {
        Utilities.waitClearAndSetInputField(lastName, value, wait);
    }

    public void setPostalCode(String value) {
        Utilities.waitClearAndSetInputField(postalCode, value, wait);
    }


    public void clickOnContinueBtn() {
        Utilities.waitAndClickOnElement(continueBtn, wait);
    }

    public void clickOnSecondContinueBtn() {
        Utilities.waitAndClickOnElement(closePopUP, wait);
        Utilities.waitAndClickOnElement(secondContinueBtn, wait);
    }

    public void clickOnDismissBtn() {
        Utilities.waitAndClickOnElement(dismissBtn, wait);
    }


    public void addEmailAndContinue(String emailAddress)  {
        setRegisterEmailAddress(emailAddress);
        clickOnContinueBtn();
    }

    public void SetPasswordAndConfirmIt(String value) {
        Utilities.waitClearAndSetInputField(password, value, wait);
        Utilities.waitClearAndSetInputField(confirmPassword, value, wait);
    }

    public void clickOnCreateAccountBtn() {
        Utilities.waitAndClickOnElement(agreeBtn, wait);
        Utilities.waitAndClickOnElement(createAccountBtn, wait);
    }

    public String getErrorMessage() {
        return Utilities.getWebElementText(errorMsg, wait);
    }

    public void selectDogCount(String value) {
        WebElement dogCountDropDown = driver.findElement(dogCount);
        Utilities.waitAndSelectByValue(dogCountDropDown, value, wait);
    }

    public void selectCatCount(String value) {
        WebElement catCountDropDown = driver.findElement(catCount);
        Utilities.waitAndSelectByValue(catCountDropDown, value, wait);
    }
}
