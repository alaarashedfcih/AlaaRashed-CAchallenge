package com.ca.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class ExtentManager {
    public static final String reportFileName =
            "CA_E2E_API_ExecutionReport" + "_" + new SimpleDateFormat("dd-MM-yyyy hh-mm-ss-ms").format(new Date())
                    + ".html";
    public static String filePathAndName;
    private static ExtentReports extent;
    private static String platform;
    private static final String macPath = System.getProperty("user.dir") + "/TestReport";
    private static final String linuxPath = System.getProperty("user.dir") + "/TestReport";
    private static final String windowsPath = System.getProperty("user.dir") + "\\TestReport";
    private static final String macReportFileLoc = macPath + "/" + reportFileName;
    private static final String linuxReportFileLoc = linuxPath + "/" + reportFileName;
    private static final String winReportFileLoc = windowsPath + "\\" + reportFileName;

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
        htmlReporter.config().setDocumentTitle("CA E2E API Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config()
                .setReportName("CA E2E API Execution Report on " + Constants.ENVIRONMENT_NAME + " Environment");
        htmlReporter.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.setSystemInfo("OS", getCurrentPlatform());

        extent.attachReporter(htmlReporter);

        return extent;
    }

    // Select the extent report file location based on platform
    private static String getReportFileLocation(String platform) {
        String reportFileLocation = null;
        switch (platform) {
            case "MAC":
                reportFileLocation = macReportFileLoc;
                createDirectoryPath(macPath);
                log.debug("ExtentReport Path for MAC: " + macPath + "\n");
                break;
            case "WINDOWS":
                reportFileLocation = winReportFileLoc;
                createDirectoryPath(windowsPath);
                log.debug("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
                break;
            case "LINUX":
                reportFileLocation = linuxReportFileLoc;
                createDirectoryPath(linuxPath);
                log.debug("ExtentReport Path for LINUX: " + linuxPath + "\n");
                break;
            default:
                log.debug("ExtentReport path has not been set! There is a problem!\n");
                break;
        }
        return reportFileLocation;
    }

    // Create the report path if it does not exist
    private static void createDirectoryPath(String path) {
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                log.debug("Directory: " + path + " is created!");
            } else {
                log.debug("Failed to create directory: " + path);
            }
        } else {
            log.debug("Directory already exists: " + path);
        }
    }

    // Get current platform
    public static String getCurrentPlatform() {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = "WINDOWS";
            } else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
                platform = "LINUX";
            } else if (operSys.contains("mac")) {
                platform = "MAC";
            }
        }
        return platform;
    }

}
