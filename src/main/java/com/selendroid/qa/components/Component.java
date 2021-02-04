package com.selendroid.qa.components;

import com.selendroid.qa.enums.VerifyType;
import com.selendroid.qa.utils.TestUtil;
import com.selendroid.qa.utils.WaitUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.selendroid.qa.utils.WaitUtil.waitForElementToBeVisible;

/**
 * The base class for all components. A component is a wrapper for WebElement and its subclasses
 * A component has access to the underlying Appium driver. A component is aware of its platform
 * e.g. Android or iOS
 *
 * @author sanjay
 *
 */
@SuppressWarnings("rawtypes")
public class Component<T extends WebElement> {

    private T element;

    private AppiumDriver driver;

    protected final boolean isIOS;

    protected final boolean isAndroid;

    /**
     * Initialize a new component without a wrapped element, this is ideal for
     * subclasses that implements complex components
     * @param driver - the current Appium driver
     */
    public Component(AppiumDriver driver) {
        super();
        this.driver = driver;
        this.isIOS = driver instanceof IOSDriver;
        this.isAndroid = driver instanceof AndroidDriver;
    }

    /**
     * Initialize a new component that wraps an instance of WebElement
     * @param element - the instance of WebElement to be wrapped
     * @param driver - the current Appium driver
     */
    public Component(T element, AppiumDriver driver) {
        this(driver);
        this.element = element;
        this.driver = driver;

    }

    /**
     * Get the wrapped element
     * @return instance of WebElement
     */
    public T getElement() {
        return element;
    }

    /**
     * Get the current driver
     * @return - the current Appium driver
     */
    public AppiumDriver getDriver() {
        return driver;
    }

    /**
     * The name of the component which is simply the class name
     * @return the class name
     */
    public String getName() {
        return getClass().getName();
    }

    /**
     * Platform friendly displayed verification
     * Android uses displayed whilst iOS uses visible
     * @param expected value for the verification
     */
    public void verifyDisplayed(String expected) {
        if(this.isAndroid) {
            verify(VerifyType.DISPLAYED, expected);

        } else if(this.isIOS) {
            verify(VerifyType.VISIBLE, expected);

        } else {
            Assert.fail("Unsupported platform");
        }
    }

    /**
     * Generic verifications related to the current component. e.g. checking visibility, type, etc.
     * The verification types are included in {@link VerifyType}
     * @param type - the verification type
     * @param expected - the expected value
     * @return
     */
    public Component<T> verify(VerifyType type, String expected) {

        try {
            WaitUtil.waitForElementToBeVisible(element, driver);
            if (element.isDisplayed()) {

                switch(type) {
                    case DISPLAYED:
                        Assert.assertEquals(element.getAttribute("displayed"), expected);
                        break;
                    case PASSWORD:
                        Assert.assertEquals(element.getAttribute("password"), expected);
                        break;
                    case TYPE:
                        Assert.assertEquals(element.getAttribute("type"), expected);
                        break;
                    case VISIBLE:
                        Assert.assertEquals(element.getAttribute("visible"), expected);
                        break;
                    case VALUE:
                        Assert.assertEquals(element.getAttribute("value"), expected);
                        break;
                    case CHECKED:
                        Assert.assertEquals(element.getAttribute("checked"), expected);
                        break;
                    case ENABLED:
                        Assert.assertEquals(element.isEnabled(), Boolean.getBoolean(expected));
                        break;
                    case TEXT:
                        Assert.assertEquals(element.getText(), expected);
                        break;
                    case TAG:
                        Assert.assertEquals(element.getTagName(), expected);
                        break;
                    default:
                        break;

                }

            } else {
                TestUtil.failWithScreenshot(driver);
            }
        } catch (NoSuchElementException e) {
            TestUtil.failWithScreenshot(e, driver);
        }
        return this;
    }

    /**
     * Check if this element is displayed without verification
     * @return true if the element is displayed, false otherwise
     */
    public boolean isDisplayed() {
        return TestUtil.isElementDisplayed(element);
    }

    // actions
    /**
     * Enter text for this element, uses sendkeys and underlying
     * set value on the element
     * @param text - the text to set
     * @return
     */
    public Component<T> enterText(String text) {
        return enterText(text,true);
    }

    /**
     * Enter text for this element, uses sendkeys and underlying
     * set value on the element
     * @param text - the text to set
     * @param clickAndEnter - set true if textbox needs to be clicked first
     * @return
     */
    public Component<T> enterText(String text, boolean clickAndEnter) {
        try {
            WaitUtil.waitForElementToBeVisible(element, driver);
            if (element.isDisplayed()) {
                if(clickAndEnter) {
                    element.click();
                }
                element.sendKeys(text);
            } else {
                TestUtil.failWithScreenshot(driver);
            }
        } catch (NoSuchElementException e) {
            TestUtil.failWithScreenshot(e, driver);
        }
        return this;
    }

    /**
     * Get the current text/value of the element
     * @return - the value or text shown on the element
     */
    public String getText() {
        try {
            WaitUtil.waitForElementToBeVisible(element, driver);
            if (element.isDisplayed()) {
                return element.getText();
            } else {
                TestUtil.failWithScreenshot(driver);
            }
        } catch (NoSuchElementException e) {
            TestUtil.failWithScreenshot(e, driver);
        }
        return null;
    }

    /**
     * Click the element
     * @return
     */
    public Component<T> click() {
        try {
            WaitUtil.waitForElementToBeVisible(element, driver);
            if (element.isDisplayed()) {
                element.click();
            } else {
                TestUtil.failWithScreenshot(driver);
            }
        } catch (NoSuchElementException e) {
            TestUtil.failWithScreenshot(e, driver);
        }
        return this;
    }

    /**
     * Clear the data in the text field
     * @return
     */
    public Component<T> clear() {
        try {
            WaitUtil.waitForElementToBeVisible(element, driver);
            if (element.isDisplayed()) {
                element.clear();
            } else {
                TestUtil.failWithScreenshot(driver);
            }
        } catch (NoSuchElementException e) {
            TestUtil.failWithScreenshot(e, driver);
        }
        return this;
    }

    /**
     * Accept the user input an proceed with the conversational ui
     * @return
     */
    public Component<T> acceptInput() {
        if(this.isIOS) {
            element.click();
        } else {
            TestUtil.enterKeyboard(getDriver());
        }
        return this;
    }

    /**
     * Dismiss the keyboard
     * @return
     */
    public Component<T> dismissKeyboard() {
        if(this.isIOS) {
            element.click();
        } else {
            TestUtil.hideKeyboard(getDriver());
        }
        return this;
    }

}
