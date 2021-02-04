package com.selendroid.qa.components;

import com.selendroid.qa.appium.DriverWrapper;
import com.selendroid.qa.enums.PlatformType;
import com.selendroid.qa.exception.FrameworkException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.lang.Validate;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Optional;

/**
 * A Base Journey implementation that includes the initialization of the elements
 * using the Appium driver. The initialization works this way:
 * 1- Upon creation the {@link Elements} annotation
 *    is read
 * 2- The target Elements class is initialized with the Appium driver
 * 3- Elements are read and wrapped in Component class and stored in the component dictionary
 *
 * @author sanjay
 *
 */
@Log4j2
@Getter
@SuppressWarnings("rawtypes")
public abstract class BaseJourney {

    private final AppiumDriver driver;

    private BaseElements<Component> elements;

    protected final boolean isIOS;

    protected final boolean isAndroid;

    /**
     * Create and initialize a new journey instance
     */
    public BaseJourney() {
        this.driver = DriverWrapper.getDriver();
        this.initElements();
        // load properties here
        this.isIOS = driver instanceof IOSDriver;
        this.isAndroid = driver instanceof AndroidDriver;
    }

    /**
     * Initialize this journey instance
     */
    @SuppressWarnings("unchecked")
    private void initElements() {

        try {

            Elements marker = null;

            for (Annotation annotation : this.getClass().getDeclaredAnnotations()) {
                if(annotation instanceof Elements) {
                    marker = (Elements) annotation;
                }
            }

            if(marker == null) {
                String msg = "Expected @Elements annotation on Screen class";
                log.error(msg);
                throw new FrameworkException(msg);
            }

            PlatformType platform = DriverWrapper.getActivePlatform();
            Validate.notNull(platform);

            Class<? extends BaseElements> elementsClass = null;

            if(marker.elements().equals(BaseElements.class)) {
                // do we have individual iOS or Android elements?
                if(PlatformType.ANDROID == platform) {
                    elementsClass = marker.androidElements();

                } else if(PlatformType.IOS == platform) {
                    elementsClass = marker.iosElements();
                } else {
                    throw new IllegalArgumentException("Un-supported platform");
                }

            } else {
                // just take combined merged elements
                elementsClass = marker.elements();
            }

            if(marker.elements().equals(BaseElements.class)) {
                throw new IllegalArgumentException("Expected that you provide an elements class");
            }

            Constructor<? extends BaseElements> constructor = elementsClass.getConstructor();
            this.elements = constructor.newInstance();

            PageFactory.initElements(new AppiumFieldDecorator(driver), elements);

            this.elements.init(driver);

        } catch(Exception e) {

            log.error("Failed to initialize page elements", e);
            throw new FrameworkException(e);
        }
    }

    /**
     * Get a component by name. The name is the actual attribute name in the Elements implementation
     * class
     * @param name - the name of the component
     * @return the component with the provided name or the test fails if not found
     */
    public Component getComponent(String name) {
        Optional<Component> component = elements.getComponent(name);

        if(component.isPresent()) {
            return component.get();
        } else {
            Assert.fail("Missing component " + name);
            return null;
        }

    }
}
