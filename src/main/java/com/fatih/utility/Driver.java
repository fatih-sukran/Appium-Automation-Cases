package com.fatih.utility;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;

public final class Driver {

    private static final ThreadLocal<AppiumDriver> DRIVER_TL = new ThreadLocal<>();

    private Driver() {
        throw new UnsupportedOperationException("Utility Class");
    }

    public static AppiumDriver getDriver() {
        return DRIVER_TL.get();
    }

    public static void setDriver(AppiumDriver driver) {
        DRIVER_TL.set(driver);
    }

    public static void setUp() {
        var driver = DriverFactory.createDriver();

        setDriver(driver);
        setDefaultImplicitlyWait();
    }

    public static void setImplicitlyWait(int time) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public static void setDefaultImplicitlyWait() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(21));
    }

    public static void quit() {
        if (DRIVER_TL.get() != null) {
            DRIVER_TL.get().quit();
            DRIVER_TL.remove();
        }
    }
}
