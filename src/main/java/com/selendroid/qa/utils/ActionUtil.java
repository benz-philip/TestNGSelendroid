package com.selendroid.qa.utils;

import com.selendroid.qa.enums.DirectionType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

/**
 * A utility class for mobile actions that can be performed using the Appium Driver.
 * Actions include tapping, scrolling, clicking and other mobile gestures
 * @author sanjay
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("rawtypes")
public class ActionUtil {

    public static final long DEFAULT_SCROLL_WAIT_MILLIS = 400;

    /**
     * Tap to an element.
     * @param element - element on which to perform the action
     * @param driver - the current driver
     */
    public static void tapOnElement(WebElement element, AppiumDriver driver) {
        new TouchAction(driver)
            .tap(tapOptions().withElement(element(element)))
            .perform();
    }

    /**
     * Tap to an element for particular amount of time.
     * @param element - element on which to perform the action
     * @param driver - the current driver
     * @param millis - delay in milliseconds
     */
    public static void tapOnElementForParticularDuration(WebElement element, AppiumDriver driver, long millis) {
        new TouchAction(driver)
        .tap(tapOptions().withElement(element(element)))
        .waitAction(waitOptions(Duration.ofMillis(millis))).perform();
    }

    /**
     * Tap on coordinates
     * @param driver - the current driver
     * @param x - x coordinates
     * @param y - y coordinates
     */
    public static void tapOnCoordinates(AppiumDriver driver, int x, int y) {
        new TouchAction(driver)
            .tap(point(x, y))
            .perform();
    }

    /**
     * Tap on coordinates for particular amount of time.
     * @param driver - the current driver
     * @param x - x coordinates
     * @param y - y coordinates
     * @param millis - delay in milliseconds
     */
    public static void tapOnCoordinatesForParticularDuration(AppiumDriver driver, int x, int y, long millis) {
        new TouchAction(driver)
        .tap(point(x, y))
        .waitAction(waitOptions(Duration.ofMillis(millis))).perform();
    }

    /**
     * Scroll from one element to another
     * @param fromElement - the source element for the scroll
     * @param toElement - the target element for the scroll
     * @param driver - the current driver
     */
    public static void scrollToElement(WebElement fromElement, WebElement toElement, AppiumDriver driver) {
        scroll(
                fromElement.getLocation().getX(),
                fromElement.getLocation().getY(),
                toElement.getLocation().getX(),
                toElement.getLocation().getY(), driver);
    }

    /**
     * Scroll down to a particular element
     * @param toElement - the target element for the scroll
     * @param driver - the current driver
     */
    public static void scrollDownToElement(WebElement toElement, AppiumDriver driver) {
        Dimension windowSize = driver.manage().window().getSize();
        int startY = (int) (windowSize.height * 0.80);
        int endY = (int) (windowSize.height * 0.20);
        int startX = windowSize.width / 2;
        do {
            scroll(startX, startY, startX, endY, driver);
        } while (!TestUtil.isElementDisplayed(toElement));
    }

    /**
     * Scroll down to a particular element
     * @param driver - the current driver
     */
    public static void scrollDownByPixel(AppiumDriver driver, int pixelValue) {
        Dimension windowSize = driver.manage().window().getSize();
        int startY = (int) (windowSize.height * 0.80);
        int endY = startY - pixelValue;
        int startX = (int) (windowSize.width * 0.80);
        scroll(startX, startY, startX, endY, driver);
    }

    /**
     * Scroll right to a particular element
     * @param toElement - the target element for the scroll
     * @param driver - the current driver
     */
    public static void scrollRightToElement(WebElement toElement, AppiumDriver driver) {
        Dimension windowSize = driver.manage().window().getSize();
        int startY = (int) (windowSize.width * 0.80);
        int endY = (int) (windowSize.width * 0.20);
        int startX = windowSize.height / 2;
        do {
            scroll(startX, startY, startX, endY, driver);
        } while (!TestUtil.isElementDisplayed(toElement));
    }

    /**
     * Scroll from one coordinates point to another
     * @param fromX - from x coordinates
     * @param fromY - from y coordinates
     * @param toX - to x coordinates
     * @param toY - to y coordinates
     * @param driver - the current driver
     * @return TouchAction for the scroll
     */
    public static TouchAction scroll(int fromX, int fromY, int toX, int toY, AppiumDriver driver) {
        return scroll(fromX, fromY, toX, toY, DEFAULT_SCROLL_WAIT_MILLIS, driver);
    }

    /**
     * Scroll from one coordinates point to another
     * @param fromX - from x coordinates
     * @param fromY - from y coordinates
     * @param toX - to x coordinates
     * @param toY - to y coordinates
     * @param millis - delay in milliseconds
     * @param driver - the current driver
     * @return TouchAction for the scroll
     */
    public static TouchAction scroll(int fromX, int fromY, int toX, int toY,
            long millis, AppiumDriver driver) {
        return new TouchAction(driver)
                .press(point(fromX, fromY))
                .waitAction(waitOptions(Duration.ofMillis(millis)))
                .moveTo(point(toX, toY))
                .release()
                .perform();
    }

    /**
     * This will scroll up until element is visible
     *
     * @param element - element on which to perform the action
     * @param driver - the current driver
     */
    public static void scrollUpToElement(WebElement element, AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.getHeight() * 0.80);
        int endY = (int) (size.getHeight() * 0.20);
        int startEndX = (int) (size.getWidth() * 0.50);
        while (!TestUtil.isElementDisplayed(element)) {
            scroll(startEndX, endY, startEndX, startY, driver);
        }
    }

    /**
     * This method will click at particular point
     * @param x - x coordinates
     * @param y - y coordinates
     * @param driver - the current driver
     */
    public static void clickOnPoint(int x, int y, AppiumDriver driver) {
        new TouchAction(driver).press(point(x, y)).release().perform();
    }

    /**
     * Performs a certain action based on the case needed
     * This function will swipe or scroll given the scrollable component
     * @param driver - the current driver
     * @param direction - the direction of the action
     * @param element - the component element which the action will be performed on
     */
    public static void swipeElement(AppiumDriver driver, DirectionType direction, WebElement element) {
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction.getDirection());
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    /**
     * This method will click on element, drag and drop to another element
     * @param fromElement - click on element and start moving the cursor
     * @param toElement - move to element and then release
     * @param driver - the current driver
     */
    public static void dragAndDrop(WebElement fromElement, WebElement toElement, AppiumDriver driver) {
        dragAndDrop(
            fromElement.getLocation().getX(),
            fromElement.getLocation().getY(),
            toElement.getLocation().getX(),
            toElement.getLocation().getY(),
            driver);
    }

    /**
     * This method will click on element, drag and drop to another element
     * @param fromX - x co-ordinates of the element from which drag will start
     * @param fromY - y co-ordinates of the element from which drag will start
     * @param toX - x co-ordinates of the element to which drag will end
     * @param toY - y co-ordinates of the element to which drag will end
     * @param driver - the current driver
     */
    public static void dragAndDrop(int fromX, int fromY, int toX, int toY, AppiumDriver driver) {
        new TouchAction(driver)
            .longPress(point(fromX, fromY))
            .waitAction(waitOptions(Duration.ofMillis(DEFAULT_SCROLL_WAIT_MILLIS)))
            .moveTo(point(toX, toY))
            .perform()
            .release();
    }

    /**
     * This method navigate back to previous screen (normally clicking the back button)
     * @param driver - the current driver
    */
    public static void clickBackButton(AppiumDriver driver) {
        driver.navigate().back();
    }
}
