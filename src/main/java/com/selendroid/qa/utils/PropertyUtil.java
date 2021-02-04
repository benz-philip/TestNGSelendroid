package com.selendroid.qa.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class that loads and caches property files by name
 * @author sanjay
 *
 */
@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtil {

    private static final String DEFAULT_PROPERTY_FILE = "application.properties";

    private static final Map<String, Properties> CACHE = new ConcurrentHashMap<>();

    /**
     * Load default properties
     * @return default properties
     */
    public static Properties loadProperties() {
        return loadProperties(DEFAULT_PROPERTY_FILE);
    }

    /**
     * Load properties from the provided file
     * @param filename - the filename of the properties to load
     * @return the properties for the provided filename
     */
    public static Properties loadProperties(String filename) {

        Validate.notNull(filename, "filename is required");

        if(CACHE.containsKey(filename)) {

            log.debug("Serving properties from cache for file: {}", filename);
            return CACHE.get(filename);

        } else {
            InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream != null)    {

                Properties properties = new Properties();
                try {
                    properties.load(inputStream);
                } catch (IOException e) {
                    log.error("Failed to load properties for filename: " + filename, e);
                }
                CACHE.put(filename, properties);
                return properties;

            } else {
                log.info("Unable to find properties for file: {}", filename);
            }

        }
        return new Properties();
    }

    /**
     * Get a string property from the default property file by key
     * @param key - the key for the property
     * @return the value of the property
     */
    public static String get(String key) {
        return loadProperties().getProperty(key);
    }

    /**
     * Get a string property from the default property file by key
     * @param key - the key for the property
     * @param filename - the properties filename
     * @return the value of the property
     */
    public static String get(String key, String filename) {
        return loadProperties(filename).getProperty(key);
    }

    /**
     * Get an integer property from the default property file by key
     * @param key - the key for the property
     * @return the value of the property
     */
    public static int getInt(String key) {
        return Integer.parseInt(loadProperties().getProperty(key));
    }

    /**
     * Get an integer property from the default property file by key
     * @param key - the key for the property
     * @param filename - the properties filename
     * @return the value of the property
     */
    public static int getInt(String key, String filename) {
        return Integer.parseInt(loadProperties(filename).getProperty(key));
    }

    /**
     * Get an long property from the default property file by key
     * @param key - the key for the property
     * @return the value of the property
     */
    public static long getLong(String key) {
        return Long.parseLong(loadProperties().getProperty(key));
    }

    /**
     * Get an long property from the default property file by key
     * @param key - the key for the property
     * @param filename - the properties filename
     * @return the value of the property
     */
    public static long getLong(String key, String filename) {
        return Long.parseLong(loadProperties(filename).getProperty(key));
    }
}
