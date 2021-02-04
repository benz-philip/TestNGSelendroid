package com.selendroid.qa.appium;

import com.selendroid.qa.enums.PlatformType;
import com.selendroid.qa.exception.FrameworkException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.selendroid.qa.utils.PropertyUtil.get;
import static com.selendroid.qa.utils.PropertyUtil.getLong;

@Log4j2
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverWrapper {

    private static volatile AppiumDriver driver;

    private static DesiredCapabilities getIosCapabilities(String appPath) {
        log.info("Initializing iOS capabilities for app {}", appPath);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, get("ios.platform.name"));
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, get("ios.platform.version"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, get("ios.device.name"));
        cap.setCapability(MobileCapabilityType.APP, appPath);
        cap.setCapability(MobileCapabilityType.NO_RESET, get("ios.no.reset"));
        cap.setCapability(IOSMobileCapabilityType.CONNECT_HARDWARE_KEYBOARD, true);
        return cap;
    }

    private static DesiredCapabilities getAndroidCapabilities(String appPath) {
        log.info("Initializing Android capabilities for app {}", appPath);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, get("android.platform.name"));
//        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, get("android.platform.version"));
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, get("android.device.name"));
        cap.setCapability(MobileCapabilityType.APP, appPath);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, get("android.automation.name"));
        return cap;
    }

    private static void initDriver(PlatformType platform) {

        String cmdAppPath = System.getProperty("app.path");
        DesiredCapabilities capabilities = null;
        log.info("Initializing driver for platform {}", platform);

        try {
            URL appiumUrl = new URL(get("appium.driver.url"));

            if(PlatformType.ANDROID == platform) {
                capabilities = getAndroidCapabilities(
                    StringUtils.isBlank(cmdAppPath) ? get("android.app.path") : cmdAppPath);
                driver = new AndroidDriver(appiumUrl, capabilities);

            } else if(PlatformType.IOS == platform) {
                capabilities = getIosCapabilities(
                    StringUtils.isBlank(cmdAppPath) ? get("ios.app.path") : cmdAppPath);
                driver = new IOSDriver(appiumUrl, capabilities);

            } else {
                throw new FrameworkException("Unknown platform " + platform);
            }

            driver.manage().timeouts().implicitlyWait(getLong("appium.driver.implicit.timeout"), TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            log.error("Failed to initialize the Appium Driver", e);
            throw new FrameworkException(e);
        }
        log.info("Successfully initialized driver {} for platform {}", driver, platform);
    }

    /**
     * TO DO wire up the values coming from the commandline
     * @return
     */
    public static PlatformType getActivePlatform() {
        String platform = get("default.platform.name");
        return PlatformType.fromString(platform);
    }

    /**
     * Get the Appium driver. We need to add support for parallel execution and multiple drivers
     * @return the initialized Appium driver
     */
    public static AppiumDriver getDriver() {
        if(driver == null) {
            synchronized(DriverWrapper.class) {

                if(driver == null) {

                    String cmdPlatform = System.getProperty("platform.name");

                    PlatformType platform = null;

                    if(StringUtils.isBlank(cmdPlatform)) {
                        platform = getActivePlatform();
                    } else {
                        platform = PlatformType.fromString(cmdPlatform);
                    }

                    if(PlatformType.ANDROID == platform) {
                        launchEmulator();
                    }

                    initDriver(platform);
                }
            }
        }

        return driver;
    }

    /**
     * Quit the appium driver and close any windows
     */
    public static void quitDriver() {
        if(driver != null) {
            synchronized(DriverWrapper.class) {
                if(driver != null) {
                    log.info("Quiting driver {}", driver);
                    driver.quit();
                    driver = null;
                }
            }
        }
    }

    /**
     * Launch the Android virtual device
     */
    public static void launchEmulator() {
        try {
            Runtime.getRuntime().exec("emulator -avd " + get("android.device.name"));
        } catch (IOException e) {
            log.error("Failed to launch the Android emulator", e);
        }
    }

}
