package com.ca.tests;

import com.ca.listeners.TestListener;
import com.ca.utilities.Constants;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.time.Duration;

public class BaseTest extends TestListener {
    public static WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException {

        ChromeOptions chromeOptions = getChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(Constants.APPLICATION_HOST);
    }

    private static @NotNull ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled"); // Disable WebDriver detection
        chromeOptions.addArguments("--no-sandbox"); // Useful for running on different environments
        chromeOptions.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
        chromeOptions.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
        chromeOptions.addArguments("--disable-infobars"); // Disables the "Chrome is being controlled by automated software" notification
        chromeOptions.addArguments("--disable-extensions"); // Disable extensions that might indicate automated behavior
        return chromeOptions;
    }

}
