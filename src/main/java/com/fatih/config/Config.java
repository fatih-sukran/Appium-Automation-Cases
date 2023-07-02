package com.fatih.config;

public class Config {

    public static String androidAppPath = "apps/API Demos.apk";
    public static String deviceName = "Pixel 6";
    public static String platformName = "Android";
    public static String platformVersion = "8.1";
    public static String appiumServerIP = "0.0.0.0";
    public static String basePath = "wd/hub";

    // capabilities configs
    public static boolean autoDismissAlerts = true;
    public static boolean autoAcceptAlerts = false;
    public static boolean noReset = false;


    static {
        ConfigReader.readConfig("config", Config.class);
    }

    private Config() {
        throw new UnsupportedOperationException();
    }
}