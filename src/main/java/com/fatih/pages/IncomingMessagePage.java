package com.fatih.pages;

import com.fatih.utility.Driver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;

public class IncomingMessagePage extends BasePage<IncomingMessagePage> {

    private static final By notificationCard = By.xpath("//*[@resource-id='com.android.systemui:id/expanded' and .//*[@resource-id='android:id/app_name_text' and @text='API Demos']]");
    private static final By notificationMessage = By.xpath(".//*[@resource-id='android:id/text']");

    @AndroidFindBy(id = "com.hmh.api:id/notify")
    WebElement showNotificationButton;

    @AndroidFindBy(xpath = "//*[@resource-id='com.hmh.api:id/message']")
    WebElement message;

    @Step("Click Show Notification")
    public IncomingMessagePage clickShowNotification() {
        click(showNotificationButton);

        return this;
    }

    @Step("Assert Notification Appear")
    public IncomingMessagePage assertNotificationAppear() {
        Assert.assertTrue(isElementVisible(notificationCard));

        var messageElement = new ByChained(notificationCard, notificationMessage).findElement(Driver.getDriver());
        variables.setNotificationMessage(getText(messageElement));

        return this;
    }

    @Step("Click Notification")
    public IncomingMessagePage clickNotification() {
        click(notificationCard);

        return this;
    }

    @Step("Assert Notification Message")
    public void assertNotificationMessage() {
        Assert.assertTrue(isElementVisible(message));
        var messageText = getText(message);
        Assert.assertTrue(messageText.contains(variables.getNotificationMessage()));
    }
}
