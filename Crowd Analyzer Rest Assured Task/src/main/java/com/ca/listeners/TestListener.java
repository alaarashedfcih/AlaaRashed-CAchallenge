package com.ca.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ca.utilities.ExtentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    // Extent Report Declarations
    private static final ExtentReports extent = ExtentManager.createInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(TestListener.class);

    private String testReportFilePath;

    @Override
    public synchronized void onStart(ITestContext context) {
        logger.info("Extent Reports Version 5 Test Suite started!");
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        logger.info(("Extent Reports Version 5  Test Suite is ending!"));
        extent.flush();
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " started!"));

        ExtentTest extentTest =
                extent.createTest(result.getTestContext().getAttribute(result.getMethod().getMethodName()).toString(),
                        result.getMethod().getDescription());

        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " Passed!"));
        test.get().pass("Test Passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " Failed!"));

        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
        test.get().fail(result.getThrowable());
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        logger.info((result.getMethod().getMethodName() + " Skipped!"));
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

}