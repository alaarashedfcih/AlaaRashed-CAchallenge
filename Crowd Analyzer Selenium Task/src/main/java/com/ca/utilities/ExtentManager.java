package com.ca.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class ExtentManager {
    private static ExtentReports extent;
    private static Platform platform;
    public static String reportFileName =
            "CA_E2E_UI_ExecutionReport" + "_" + new SimpleDateFormat("dd-MM-yyyy hh-mm-ss-ms").format(new Date())
                    + ".html";
    private static final String windowsPath = System.getProperty("user.dir") + "\\TestReport";
    private static final String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public static String filePathAndName;

    private static final String windowsScreenshotsPath = System.getProperty("user.dir") + "\\TestReport\\Screenshots";

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    // Create an extent report instance
    public static ExtentReports createInstance() {
        platform = getCurrentPlatform();
        filePathAndName = getReportFileLocation(platform);
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePathAndName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("CA E2E UI Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config()
                .setReportName("CA E2E UI Execution Report on " + Constants.ENVIRONMENT_NAME + " Environment");
        htmlReporter.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.setSystemInfo("OS", getCurrentPlatform().toString());

        // For Testing Locally
        extent.attachReporter(htmlReporter);


        return extent;
    }

    // Select the extent report file location based on platform
    private static String getReportFileLocation(Platform platform) {
        String reportFileLocation = null;
        if (Objects.requireNonNull(platform) == Platform.WINDOWS) {
            reportFileLocation = winReportFileLoc;
            createDirectoryPath(windowsPath);
            createDirectoryPath(windowsScreenshotsPath);
            log.info("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
        } else {
            log.info("ExtentReport path has not been set! There is a problem!\n");
        }
        return reportFileLocation;
    }

    // Create the report path if it does not exist
    private static void createDirectoryPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                log.info("Directory: {} is created!", path);
            } else {
                log.info("Failed to create directory: {}", path);
            }
        } else {
            log.info("Directory already exists: {}", path);
        }
    }

    // Select the extent report file location based on platform
    public static String getScreenshotFileLocation(Platform platform, String screenshotTitle) {
        String screenshotFileLocation = null;
        if (Objects.requireNonNull(platform) == Platform.WINDOWS) {
            screenshotFileLocation = windowsScreenshotsPath + "\\" + screenshotTitle + ".png";
            log.info("Screenshot Path for WINDOWS: {}\n", windowsPath);
        } else {
            log.info("Screenshot path has not been set! There is a problem!\n");
        }
        return screenshotFileLocation;
    }

    // Get current platform
    public static Platform getCurrentPlatform() {
        if (platform == null) {
            String operaSys = System.getProperty("os.name").toLowerCase();
            if (operaSys.contains("win")) {
                platform = Platform.WINDOWS;
            }
        }
        return platform;
    }

}
