package com.ca.tests.login;

import com.ca.dataproviderobjects.LoginUsersData;
import com.ca.pages.HomePage;
import com.ca.pages.LogInPopUpPage;
import com.ca.pages.MyAccountPopUpPage;
import com.ca.pages.SignInPage;
import com.ca.tests.BaseTest;
import com.ca.utilities.DataProviderSource;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Objects;

public class ValidateSignInWithInValidCredentialsTest extends BaseTest {
    HomePage homePage;
    LogInPopUpPage logInPopUpPage;
    SignInPage signInPage;
    MyAccountPopUpPage myAccountPopUpPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser"})
    public synchronized void setUp(@Optional("chrome") String browser, Method method, Object[] testData, ITestContext ctx)
            throws MalformedURLException {

        if (!Objects.equals(((LoginUsersData) testData[0]).getTestCaseName(), "")) {
            ctx.setAttribute(method.getName(),
                    "On " + browser + ": " + ((LoginUsersData) testData[0]).getTestCaseName());
        } else {
            ctx.setAttribute(method.getName(), "On " + browser + ": "
                    + "TC #00: Login with InValid Credentials");
        }
        softAssert = new SoftAssert();
    }

    @Test(alwaysRun = true, dataProvider = "LoginUsersDataFeed", dataProviderClass = DataProviderSource.class)
    public void SignInWithInValidCredentialsTest(LoginUsersData data) {
        try {

            homePage = new HomePage(driver);
            logInPopUpPage = homePage.openLoginPopUp();
            signInPage = logInPopUpPage.clickOnLoginBtnFromPopUp();
            signInPage.signIn(data.getUserName(), data.getPassword());
            softAssert.assertEquals(
                    signInPage.getErrorMessage(),
                    data.getErrorMessage());
            softAssert.assertAll();
        } catch (Exception e) {
            // Logging the error and capturing a screenshot for better debugging
            System.err.println("An error occurred during the sign-in process: " + e.getMessage());
            throw e; // Rethrowing the exception to ensure the test is marked as failed
        } finally {
            // Ensure the browser is properly closed after test execution
            if (driver != null) {
                driver.quit();
            }
        }

    }

}
