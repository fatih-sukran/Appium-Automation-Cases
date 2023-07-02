package com.fatih.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class HideAndShowPage extends BasePage<HideAndShowPage> {
    private static final By fragment1HideButton = By.id("com.hmh.api:id/frag1hide");
    private static final By fragment2HideButton = By.id("com.hmh.api:id/frag2hide");

    @AndroidFindBy(xpath = "//*[@resource-id='com.hmh.api:id/fragment1']//*[@resource-id='com.hmh.api:id/saved']")
    WebElement fragment1TextBox;
    @AndroidFindBy(xpath = "//*[@resource-id='com.hmh.api:id/fragment2']//*[@resource-id='com.hmh.api:id/saved']")
    WebElement fragment2TextBox;

    @Step("Check Buttons President and its Text Hide")
    public HideAndShowPage checkAreButtonsPresident() {
        Assert.assertTrue(isElementVisible(fragment1HideButton));
        Assert.assertTrue(isElementVisible(fragment2HideButton));

        assertHideButton1Text("Hide");
        assertHideButton2Text("Hide");

        checkVisibilityOfFragment1(true);
        checkVisibilityOfFragment2(true);

        return this;
    }

    @Step("Click Hide 1")
    public HideAndShowPage clickHide1() {
        click(fragment1HideButton);

        return this;
    }

    @Step("Assert Button 1 Text. Expected Text: {0}")
    public HideAndShowPage assertHideButton1Text(String expectedText) {
        Assert.assertEquals(getText(fragment1HideButton), expectedText);

        return this;
    }

   @Step("Check Visibilitiy Of Fragment 1 - Expected Visibility : {0}")
    public HideAndShowPage checkVisibilityOfFragment1(boolean isVisible) {
        Assert.assertEquals(isElementVisible(fragment1TextBox, 3), isVisible);

        return this;
    }

    @Step("Click Second Hide Button")
    public HideAndShowPage clickHide2() {
        click(fragment2HideButton);

        return this;
    }

    @Step("Assert Button 2 Text. Expected Text: {0}")
    public HideAndShowPage assertHideButton2Text(String expectedText) {
        Assert.assertEquals(getText(fragment2HideButton), expectedText);

        return this;
    }

    @Step("Check Visibilitiy Of Fragment 2 - Expected Visibility : {0}")
    public HideAndShowPage checkVisibilityOfFragment2(boolean isVisible) {
        Assert.assertEquals(isElementVisible(fragment2TextBox, 3), isVisible);

        return this;
    }
}
