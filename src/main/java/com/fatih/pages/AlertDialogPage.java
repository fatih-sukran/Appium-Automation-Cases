package com.fatih.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AlertDialogPage extends BasePage<AlertDialogPage> {

    @AndroidFindBy(id = "com.hmh.api:id/select_button")
    WebElement listDialogButton;

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/select_dialog_listview']//android.widget.TextView")
    List<WebElement> listItems;

    @AndroidFindBy(id = "android:id/message")
    WebElement message;

    @Step("Select Random Element From List")
    public AlertDialogPage selectRandomElementFromList() {
        click(listDialogButton);

        int selectedIndex = random.nextInt(listItems.size());
        var itemText = getText(listItems.get(selectedIndex));

        click(listItems.get(selectedIndex));

        variables.setAlertDialogIndex(selectedIndex);
        variables.setAlertDialogMessage(itemText);

        return this;
    }

    @Step("Check Alert Message")
    public void checkAlertMessage() {
        var expectedText = String.format("You selected: %d , %s",
                variables.getAlertDialogIndex(),
                variables.getAlertDialogMessage());
        var actualMessage = getText(message);

        Assert.assertEquals(expectedText, actualMessage, "Alert message does not match expected text");
    }
}

