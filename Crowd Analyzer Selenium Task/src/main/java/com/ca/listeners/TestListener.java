package com.ca.listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ca.utilities.Constants;
import com.ca.utilities.EncodeToBase64Utils;
import com.ca.utilities.ExtentManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {

    // Extent Report Declarations
    static final ExtentReports extent = ExtentManager.createInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public synchronized void onStart(ITestContext context) {
        logger.info("Test Suite '{}' started!", context.getName());
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        logger.info("Test Suite '{}' ended!", context.getName());
    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test '{}' started!", testName);

        ExtentTest extentTest = extent.createTest(testName, result.getMethod().getDescription())
                .assignCategory(result.getMethod().getXmlTest().getParameter("browser"));

        test.set(extentTest);
    }


    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        handleTestResult(result, "Test Passed", true);
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        handleTestResult(result, "Test Failed", false);
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test '{}' Skipped!", testName);
        test.get().skip(result.getThrowable());
    }

    private void handleTestResult(ITestResult result, String statusMessage, boolean isSuccess) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test '{}' {}", testName, statusMessage);

        if (Constants.PASS_SCREENSHOTS_FLAG.equalsIgnoreCase("TRUE") || !isSuccess) {
            takeScreenshot(result, statusMessage);
        }

        if (isSuccess) {
            test.get().pass(statusMessage);
        } else {
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test '{}' failed but within success percentage", testName);
    }

    private void takeScreenshot(ITestResult result, String statusMessage) {
        try {
            WebDriver driver = getWebDriverFromTestClass(result);
            if (driver != null) {
                Screenshot screenshot = new AShot()
                        .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                        .takeScreenshot(driver);

                if (screenshot != null) {
                    String screenshotPath = saveScreenshot(result, screenshot);
                    String screenshotEncoded = new EncodeToBase64Utils().encodeFileToBase64Binary(new File(screenshotPath));
                    test.get().log(Status.valueOf(statusMessage.equals("Test Passed") ? "pass" : "fail"),
                            MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotEncoded).build());
                }
            }
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: ", e);
        }
    }

    private WebDriver getWebDriverFromTestClass(ITestResult result) {
        try {
            Field field = result.getTestClass().getRealClass().getDeclaredField("driver");
            field.setAccessible(true);
            return (WebDriver) field.get(result.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("Unable to access 'driver' field in test class: ", e);
            return null;
        }
    }

    private String saveScreenshot(ITestResult result, Screenshot screenshot) throws IOException {
        String screenshotTitle = result.getMethod().getMethodName() + "_" +
                new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss-SSS").format(new Date());
        String screenshotPath = ExtentManager.getScreenshotFileLocation(ExtentManager.getCurrentPlatform(), screenshotTitle);
        ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
        return screenshotPath;
    }

}
