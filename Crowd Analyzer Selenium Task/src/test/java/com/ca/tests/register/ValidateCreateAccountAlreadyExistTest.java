package com.ca.tests.register;

import com.ca.dataproviderobjects.LoginUsersData;
import com.ca.pages.CreateAccountPage;
import com.ca.pages.HomePage;
import com.ca.pages.LogInPopUpPage;
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

public class ValidateCreateAccountAlreadyExistTest extends BaseTest {
    HomePage homePage;
    LogInPopUpPage logInPopUpPage;
    CreateAccountPage createAccountPage;
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    @Parameters(value = {"browser"})
    public synchronized void setUp(@Optional("chrome") String browser, Method method, Object[] testData, ITestContext ctx) throws MalformedURLException {
        if (testData.length > 0 && testData[0] instanceof LoginUsersData) {
            String testCaseName = ((LoginUsersData) testData[0]).getTestCaseName();
            ctx.setAttribute(method.getName(), "On " + browser + ": " + (testCaseName.isEmpty() ? "TC #00: Register" : testCaseName));
        } else {
            ctx.setAttribute(method.getName(), "On " + browser + ": TC #00: Register Already Exist");
        }
        softAssert = new SoftAssert();
    }

    @Test(alwaysRun = true, dataProvider = "LoginUsersDataFeed", dataProviderClass = DataProviderSource.class)
    public void CreateAccountAlreadyExistTest(LoginUsersData data) throws InterruptedException {
        try {
            homePage = new HomePage(driver);
            logInPopUpPage = homePage.openLoginPopUp();
            createAccountPage = logInPopUpPage.clickOnCreateAccountBtnFromPopUp();
            createAccountPage.addEmailAndContinue(data.getUserName());
            createAccountPage.clickOnDismissBtn();
            softAssert.assertEquals(createAccountPage.getErrorMessage(), data.getErrorMessage());
        } catch (Exception e) {
            // Log the error and take a screenshot for debugging purposes
            System.err.println("Error occurred while creating account: " + e.getMessage());
            throw e; // Rethrow the exception to make sure test is marked as failed
        } finally {
            // Any cleanup code can go here
            if (driver != null) {
                driver.quit();
            }
        }

    }

}
