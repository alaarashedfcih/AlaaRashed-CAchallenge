package com.ca.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.BeforeSuite;


/**
 * The BaseTest class serves as the base class for all test classes in the application.
 * It contains setup and common functionality for test execution.
 */
public class BaseTest {


    @BeforeSuite(alwaysRun = true)
    public void setupLog() {

        // Enable logging of request and response if validation fails.
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

}
