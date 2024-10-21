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

public class ValidateSignInWithValidCredentialsTest extends BaseTest {
    HomePage homePage;
    LogInPopUpPage logInPopUpPage;
    SignInPage signInPage;
    MyAccountPopUpPage myAccountPopUpPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser"})
    public synchronized void setUp(@Optional("chrome") String browser, Method method, Object[] testData, ITestContext ctx)
            throws MalformedURLException {

        if (testData.length > 0 && testData[0] instanceof LoginUsersData) {
            String testCaseName = ((LoginUsersData) testData[0]).getTestCaseName();
            ctx.setAttribute(method.getName(), "On " + browser + ": " + (testCaseName.isEmpty() ? "TC #00: Login with Valid Credentials" : testCaseName));
        } else {
            ctx.setAttribute(method.getName(), "On " + browser + ": TC #00: Login with Valid Credentials");
        }
        softAssert = new SoftAssert();
    }

    @Test(alwaysRun = true, dataProvider = "LoginUsersDataFeed", dataProviderClass = DataProviderSource.class)
    public void SignInWithValidCredentialsTest(LoginUsersData data) {

        try {

            homePage = new HomePage(driver);
            logInPopUpPage = homePage.openLoginPopUp();
            signInPage = logInPopUpPage.clickOnLoginBtnFromPopUp();
            homePage = signInPage.signIn(data.getUserName(), data.getPassword());
            myAccountPopUpPage = homePage.openMyAccountPopUp();
            softAssert.assertTrue(
                    myAccountPopUpPage.isLogOutDisplayed(),
                    "LogOut Button is not displayed");

            softAssert.assertAll();
        } catch (Exception e) {
            System.err.println("An error occurred during the sign-in process: " + e.getMessage());
            throw e; // Re-throw exception to mark the test as failed
        } finally {
            if (driver != null) {
                driver.quit(); // Close the browser after the test execution
            }
        }

    }

}
