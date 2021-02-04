package com.selendroid.qa.utils;

import io.appium.java_client.AppiumDriver;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Utilities to provide waiting features in tests. Some pause the current thread,
 * whilst others use the Appium driver to wait for elements
 * @author sanjay
 *
 */
@Log4j2
@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WaitUtil {

    public static final long DEFAULT_WAIT_MILLIS = 2000;
    public static final long DEFAULT_WAIT_SECONDS = 15;

    /**
     * Pause for 2 seconds
     */
    public static void pause() {
        pause(DEFAULT_WAIT_MILLIS);
    }

    /**
     * Pause for the provided value in milliseconds
     * @param millis - the amount in milliseconds
     */
    public static void pause(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) { //NOSONAR
            log.warn("Interrupted", e);
        }
    }

    /**
     * Wait for a particular element to be visible for the default of 2 seconds
     * @param element - the element to wait on
     * @param driver - the current driver
     */
    public static void waitForElementToBeVisible(WebElement element, AppiumDriver driver) {
        waitForElementToBeVisible(element, DEFAULT_WAIT_SECONDS, driver);
    }

    /**
     * Wait for a particular element to be visible for the number of provided seconds
     * @param element - the element to wait on
     * @param seconds - the number of seconds to wait
     * @param driver - the current driver
     */
    public static void waitForElementToBeVisible(WebElement element, long seconds, AppiumDriver driver) {
        pause(1000);
        new WebDriverWait(driver, seconds)
            .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * To wait for element selected by (By) a locator to be visible
     *
     * @param locator - the locator used to select the element
     * @param driver - the current driver
     */
    public void waitForElementToBeVisible(final By locator, final AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for a particular element to be invisible for the number of provided seconds
     * @param element - the element to wait on
     * @param seconds - the number of seconds to wait
     * @param driver - the current driver
     */
    public static void waitForElementToBeInVisible(WebElement element, long seconds, AppiumDriver driver) {
        pause(1000);
        new WebDriverWait(driver, seconds)
            .until(ExpectedConditions.invisibilityOf(element));
    }


    /**
     * To wait for an element to be clickable
     *
     * @param element - the element to wait on
     * @param driver - the current driver
     */
    public static void waitForElementToBeClickable(final WebElement element, final AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * To wait for element (By) to be invisible
     *
     * @param locator - the locator used to select the element
     * @param driver - the current driver
     */
    public void waitForElementToBeInvisible(final By locator, final AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     *  Wait for a particular elements to be visible for the default of 2 seconds
     * @param elements - the elements to wait on
     * @param driver - the current driver
     */
    public void waitForElementsToBeInvisible(final List<WebElement> elements, final AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    /**
     * To wait for given element (By) to be present
     *
     * @param locator - the locator used to select the element
     * @param driver - the current driver
     */
    public void waitForElementToBePresent(final By locator, final AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait until the nested element is visible
     * @param element - the element to wait on
     * @param locator - the locator used to select the element
     * @param driver - the current driver
     */
    public void waitUntilNestedElementPresent(WebElement element, By locator, AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }

    /**
     * Wait until the elements are not present on
     * @param locator - the locator used to select the element
     * @param driver - the current driver
     */
    public void waitForElementToBeNotPresent(final By locator, AppiumDriver driver) {
        new WebDriverWait(driver, DEFAULT_WAIT_SECONDS)
                .until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)));
    }

}
