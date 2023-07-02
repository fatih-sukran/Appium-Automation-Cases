package com.fatih.pages;

import com.fatih.utility.AppiumActions;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ScrollablePage extends BasePage<ScrollablePage> {

    private static final By lastTab = By.xpath("//*[@text='TAB 30']");

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/tabcontent']//android.widget.TextView")
    WebElement tabMessage;

    @Step("Scroll to Last Tab")
    public ScrollablePage scrollToLastTab() {
        var horizontalScrollArea = findElement(By.xpath("//*[@resource-id='android:id/tabs']"));
        var areaRect = horizontalScrollArea.getRect();


        var startX = (int) (areaRect.getX() + areaRect.width * 0.81);
        var endX = (int) (areaRect.getX() + areaRect.width * 0.21);
        var startY = (int) (areaRect.getY() + areaRect.getHeight() * 0.5);

        while (!isElementVisible(lastTab, 3)) {
            AppiumActions.scrollTo(startX, startY, endX, startY);
        }

        return this;
    }

    @Step("Click Last Tab")
    public ScrollablePage clickLastTab() {
        click(lastTab);

        return this;
    }

    @Step("Assert Tab Message")
    public void assertTabMessage() {
        var text = getText(tabMessage);
        Assert.assertEquals(text, "Content for tab with tag Tab 30");
    }
}
