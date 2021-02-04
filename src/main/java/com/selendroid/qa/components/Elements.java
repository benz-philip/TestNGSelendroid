package com.selendroid.qa.components;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marker interface for the concrete implementation of the elements for a particular journey.
 * Example:
 *
 * <pre>
 * Elements(elements = LoginElements.class)
 * public class LoginJourney extends BaseJourney {
 * }
 * </pre>
 * @author sanjay
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SuppressWarnings("rawtypes")
public @interface Elements {

    /**
     * Combined elements specific elements
     */
    Class<? extends BaseElements> elements() default BaseElements.class;

    /**
     * Android specific elements
     */
    Class<? extends BaseElements> androidElements() default BaseElements.class;

    /**
     * iOS specific elements
     */
    Class<? extends BaseElements> iosElements() default BaseElements.class;
}
