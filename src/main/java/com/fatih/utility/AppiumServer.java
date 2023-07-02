package com.fatih.utility;

import com.fatih.config.Config;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.net.URL;

public final class AppiumServer {
    private static final ThreadLocal<AppiumDriverLocalService> APPIUM_SERVER = new ThreadLocal<>();

    private AppiumServer() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static void setUp() {

        AppiumDriverLocalService appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(Config.appiumServerIP)
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withArgument(GeneralServerFlag.BASEPATH, Config.basePath)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withArgument(() -> "--allow-cors")
                .build();

        APPIUM_SERVER.set(appiumDriverLocalService);
    }

    public static void start() {
        APPIUM_SERVER.get().start();
    }

    public static void stop() {
        if (APPIUM_SERVER.get() != null) {
            APPIUM_SERVER.get().stop();
            APPIUM_SERVER.remove();
        }
    }

    public static URL getServerUrl() {
        return APPIUM_SERVER.get().getUrl();
    }
}
