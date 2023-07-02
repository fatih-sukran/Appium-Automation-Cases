package com.fatih.utility;

import com.fatih.config.Config;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.proxy.NotImplementedException;

import java.io.File;

public final class DriverFactory {

    private DriverFactory() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static AppiumDriver createDriver() {

        return switch (Config.platformName) {
            case "Android" -> createAndroidDriver();
            case "iOS" -> throw new NotImplementedException();
            default ->  throw new UnsupportedOperationException("Platform not supported");
        };
    }

    private static AppiumDriver createAndroidDriver() {
        var capabilities = getCapabilitiesOfAndroid();
        var appiumServerUrl = AppiumServer.getServerUrl();
        return new AndroidDriver(appiumServerUrl, capabilities);
    }

    private static UiAutomator2Options getCapabilitiesOfAndroid() {
        var appPath = new File(Config.androidAppPath).getAbsolutePath();

        return new UiAutomator2Options()
                .setApp(appPath)
                .setDeviceName(Config.deviceName)
                .setPlatformVersion(Config.platformVersion)
                .setNoReset(Config.noReset)
                .autoGrantPermissions()
                .amend("autoAcceptAlerts", Config.autoAcceptAlerts)
                .amend("autoDismissAlerts", Config.autoDismissAlerts);
    }
}
