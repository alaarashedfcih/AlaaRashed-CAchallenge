package com.ca.utilities;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

@Slf4j
public class Utilities {

    public static void waitAndClickOnElement(By locator, WebDriverWait wait) {
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        webElement.click();
    }

    public static void waitClearAndSetInputField(By locator, String value, WebDriverWait wait) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webElement.clear();
        webElement.sendKeys(value);
    }

    public static boolean isFieldDisplayed(By locator, WebDriverWait wait) {
        try {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return webElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            log.debug("A Handled Exception is Thrown\n" + e);
            return false;
        }
    }

    public static String getWebElementText(By locator, WebDriverWait wait) {
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return webElement.getText();

    }

    public static void waitUntilSelectHasOptions(WebElement webElement, WebDriverWait wait) {
        final Select dropdown = new Select(webElement);
        wait.until(new ExpectedCondition<Boolean>() {
            @org.jetbrains.annotations.NotNull
            @Override //implementation of apply method??
            public Boolean apply(@Nullable WebDriver driver) {
                return (dropdown.getOptions().size() > 1);
            }
        });
    }


    public static void waitAndSelectByValue(WebElement element, String value, WebDriverWait wait) {
        waitUntilSelectHasOptions(element, wait);
        Select dropdown = new Select(element);
        dropdown.selectByValue(value);
    }

}
