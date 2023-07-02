package com.fatih.pages;

import com.fatih.utility.AppiumActions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ContextMenuPage extends BasePage<ContextMenuPage> {

    @AndroidFindBy(id = "com.hmh.api:id/long_press")
    WebElement longPressButton;

    @AndroidFindBy(xpath = "//*[@resource-id= 'android:id/title']")
    List<WebElement> menuItems;

    @Step("Click Long Press Button")
    public ContextMenuPage clickLongPressButton() {
        AppiumActions.longPress(longPressButton);

        return this;
    }

    @Step("Check is Menu Opened")
    public void checkIsMenuOpened() {
        int expectedMenuItems = 2;
        Assert.assertEquals(menuItems.size(), expectedMenuItems, "Unexpected number of menu items");
        Assert.assertEquals(getText(menuItems.get(0)), "Menu A");
        Assert.assertEquals(getText(menuItems.get(1)), "Menu B");
    }
}
