package com.selendroid.qa.enums;

/**
 * The mobile platform types
 * @author Sanjay
 *
 */
public enum PlatformType {

    ANDROID("Android"), IOS("iOS");

    public final String platform;

    /**
     * Create a new instance of the enum
     * @param platform - the platform to set
     */
    PlatformType(String platform) {
        this.platform = platform;
    }

    /**
     * Get a platfrom from the enum type
     * @param value - the string value
     * @return PlatformType that corresponds to the value or throws IllegalArgumentException
     */
    public static PlatformType fromString(String value) {
        if(PlatformType.ANDROID.platform.equalsIgnoreCase(value)) {
            return PlatformType.ANDROID;
        } else if(PlatformType.IOS.platform.equalsIgnoreCase(value)) {
            return PlatformType.IOS;
        }

        throw new IllegalArgumentException("Invalid platform " + value);
    }
}
