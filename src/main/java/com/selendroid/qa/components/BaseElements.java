package com.selendroid.qa.components;

import com.selendroid.qa.exception.FrameworkException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class is a container for all the elements on the screen. It's a wrapper of the
 * page object model POM and contains all the fields that should exist on the screen.
 *
 * @author sanjay
 */
@Log4j2
@Getter
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class BaseElements<T extends Component> {

    @Getter(AccessLevel.NONE)
    private Map<String, T> components = new HashMap<>();

    protected boolean isIOS;

    protected boolean isAndroid;

    /**
     * Initialization will get all the private fields in this component, checks
     * if they are of type WebElement before wrapping them into Component
     *
     */
    protected void init(AppiumDriver driver) {
        // load properties here
        this.isIOS = driver instanceof IOSDriver;
        this.isAndroid = driver instanceof AndroidDriver;
        Field[] fields = this.getClass().getDeclaredFields();
        List<Field> privateFields = Arrays.asList(fields).stream()
                .filter(field -> Modifier.isPrivate(field.getModifiers()))
                .collect(Collectors.toList());

        for(Field field: privateFields) {
            String name = field.getName();
            Object value = null;
            try {
                value = FieldUtils.readField(field, this, true);
            } catch (IllegalAccessException e) {
                log.error("Failed initialize elements class for " + this, e);
                throw new FrameworkException(e);
            }

            if(value instanceof WebElement) {
                // web element and mobile elements we call the custom create method
                components.put(name, this.createComponent(name, (WebElement) value, driver));

            } else {
                log.warn("Skipping field {} as it's not an instance of WebElement", name);
            }
        }

        this.createAdditionalComponents(components, driver);


    }

    /**
     * Subclasses to override this method to if they want to register any additional complex components
     * @param components - the components dictionary
     * @param driver -
     */
    protected void createAdditionalComponents(Map<String, T> components, AppiumDriver driver) {

    }

    /**
     * Return the component for the provided field name. Subclasses can override
     * this method to return custom components e.g. subclasses of Component. Default
     * implementation returns instances of {@link Component}
     * @param name - the field name of the element
     * @param element - the Selenium WebElement
     * @return a component that wraps the element
     */
    protected T createComponent(String name, WebElement element, AppiumDriver driver) {
        return (T) new Component(element, driver);
    }

    /**
     * Return the component by name, the name is the field name
     * @param name - the component name
     * @return the component if found, otherwise null
     */
    protected Optional<T> getComponent(String name) {
        return Optional.ofNullable(components.get(name));
    }

}
