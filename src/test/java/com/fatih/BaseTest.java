package com.fatih;

import com.fatih.data.Variables;
import com.fatih.utility.AppiumServer;
import com.fatih.utility.Driver;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        AppiumServer.setUp();
        AppiumServer.start();
        Driver.setUp();
    }

    private void allowPermissions() {
        Driver.setImplicitlyWait(3);
        var continueBy = By.id("com.android.permissioncontroller:id/continue_button");
        var continueButton = Driver.getDriver().findElements(continueBy);
        Driver.setDefaultImplicitlyWait();
        if (continueButton.isEmpty()) return;

        continueButton.get(0).click();
        var okButtonBy = By.id("android:id/button1");
        var okButton = Driver.getDriver().findElement(okButtonBy);
        okButton.click();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quit();
        AppiumServer.stop();
        Variables.remove();
    }
}
