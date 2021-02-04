package com.selendroid.qa.enums;

/**
 * Type of the picker wheel values
 *
 * @author Sanjay
 */
public enum LocatorType {

    ID("id"),
    XPATH("xpath"),
    CLASSNAME("className"),
    TAGNAME("tagName"),
    LINKTEXT("linkText"),
    ACCESSIBILITYID("AccessibilityId");

    private String locator;

    LocatorType(String locator) {
        this.locator = locator;
    }

    public String getLocatorType() {
        return locator;
    }
}
