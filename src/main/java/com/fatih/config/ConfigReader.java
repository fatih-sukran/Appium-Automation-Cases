package com.fatih.config;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private static final Map<String, Properties> PROPERTIES_MAP = new HashMap<>();

    private ConfigReader() {
    }

    /**
     * Reads configuration values from a file and sets them as static fields in a class.
     *
     * @param configFile the name of the configuration file
     * @param clazz      the class containing the fields to set
     */
    public static void readConfig(String configFile, Class<?> clazz) {
        // Loop over all declared fields in the class
        for (Field field : clazz.getDeclaredFields()) {
            // Get the name of the current field
            String fieldName = field.getName();
            try {
                // Get the value from the configuration file
                Object value = getPropValue(configFile, fieldName);
                Class<?> fieldType = field.getType();
                if (value == null) continue;

                // Set the value of the field if it exists in the configuration file
                field.setAccessible(true); // Allow access to private fields
                field.set(null, convertValueToFieldType(value, fieldType)); // Set the field value on the class (not an instance)

            } catch (IllegalAccessException e) {
                // Log an error message if the field could not be set
                throw new RuntimeException(configFile + " dosyası okunurken hata alındı", e);
            }
        }
    }

    public static String getPropValue(String fileName, String key) {
        if (!PROPERTIES_MAP.containsKey(fileName)) {
            try (FileInputStream fis = new FileInputStream(fileName + ".properties")) {
                Properties properties = new Properties();
                properties.load(fis);
                PROPERTIES_MAP.put(fileName, properties);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return PROPERTIES_MAP.get(fileName).getProperty(key);
    }

    private static Object convertValueToFieldType(Object value, Class<?> fieldType) {
        return switch (fieldType.getSimpleName()) {
            case "boolean", "Boolean" -> Boolean.valueOf(value.toString());
            case "int", "Integer" -> Integer.valueOf(value.toString());
            case "double", "Double" -> Double.valueOf(value.toString());
            case "float", "Float" -> Float.valueOf(value.toString());
            case "long", "Long" -> Long.valueOf(value.toString());
            case "short", "Short" -> Short.valueOf(value.toString());
            case "byte", "Byte" -> Byte.valueOf(value.toString());
            case "String" -> value.toString();
            default -> throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        };
    }
}
