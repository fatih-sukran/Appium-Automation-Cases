package com.fatih.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomTitleActivityPage extends BasePage<CustomTitleActivityPage> {
    @AndroidFindBy(id = "com.hmh.api:id/left_text")
    WebElement leftText;

    @AndroidFindBy(id = "com.hmh.api:id/left_text_edit")
    WebElement leftTextEdit;

    @AndroidFindBy(id = "com.hmh.api:id/left_text_button")
    WebElement leftTextButton;

    @AndroidFindBy(id = "com.hmh.api:id/right_text")
    WebElement rightText;

    @AndroidFindBy(id = "com.hmh.api:id/right_text_edit")
    WebElement rightTextEdit;

    @AndroidFindBy(id = "com.hmh.api:id/right_text_button")
    WebElement rightTextButton;

    @Step("Check Default Text - Expected Text: {2}")
    private void checkDefaultText(WebElement navigationTextElement, WebElement editTextElement, String defaultText) {
        String navigationText = getText(navigationTextElement);
        String editText = getText(editTextElement);

        Assert.assertEquals(defaultText, navigationText, "Default Navigation Text must be " + defaultText);
        Assert.assertEquals(defaultText, editText, "Default Edit Text Value must be " + defaultText);
    }

    @Step("Change Left Text Box - New Text: {0}")
    public CustomTitleActivityPage changeLeftTextBox(String text) {
        sendKeys(leftTextEdit, text);
        click(leftTextButton);

        return this;
    }

    @Step("Change Right Text Box - New Text: {0}")
    public CustomTitleActivityPage changeRightTextBox(String text) {
        sendKeys(rightTextEdit, text);
        click(rightTextButton);

        return this;
    }

    @Step("Check Default Left Text - Expected Text: {0}")
    public CustomTitleActivityPage checkDefaultLeftText(String expectedText) {
        checkDefaultText(leftText, leftTextEdit, expectedText);

        return this;
    }

    @Step("Check Default Right Text - Expected Text: {0}")
    public CustomTitleActivityPage checkDefaultRightText(String expectedText) {
        checkDefaultText(rightText, rightTextEdit, expectedText);

        return this;
    }
}
