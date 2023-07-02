package com.fatih.pages;

import com.fatih.data.Variables;
import com.fatih.utility.AppiumActions;
import com.fatih.utility.Driver;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@SuppressWarnings("unchecked")
public abstract class BasePage<T extends BasePage<T>> {

    public static final Random random = new SecureRandom();

    public final Variables variables = Variables.getInstance();

    BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver(), Duration.ofSeconds(30)), this);
    }

    @Step("Navigate to {0}")
    public T navigate(String... paths) {
        var xpath = "//*[@text='%s']";
        var listBy = By.xpath("//*[@resource-id='android:id/list']");
        var listView = Driver.getDriver().findElement(listBy);
        var listViewRect = listView.getRect();
        var startX = (int) (listViewRect.getX() + listViewRect.width * 0.5);
        var startY = listViewRect.getY() + listViewRect.getHeight() - 21;
        var endY = listViewRect.getY() + 21;

        for(var path : paths) {
            By by = By.xpath(String.format(xpath, path));
            for (int i = 0; i < 5; i++) {
                if (isElementVisible(by, 1)) {
                    WebElement element = Driver.getDriver().findElement(by);
                    wait(121);
                    click(element);
                    wait(210);
                    break;
                }
                AppiumActions.scrollTo(startX, startY, startX, endY);
            }

        }
        return (T) this;
    }
    @SneakyThrows
    void wait(int milliseconds) {
        Thread.sleep(milliseconds);
    }

    String getText(By by) {
        var element = findElement(by);
        return getText(element);
    }

    String getText(WebElement element) {
        return element.getText();
    }

    WebElement findElement(By by) {
        return Driver.getDriver().findElement(by);
    }

    void click(By by) {
        var element = findElement(by);
        click(element);
    }

    void click(WebElement element) {
        element.click();
    }

    void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    boolean isElementVisible(By by) {
        return isElementVisible(by, 10);
    }

    boolean isElementVisible(By by, int time) {
        try {
            Driver.setImplicitlyWait(1);
            var webDriverWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            Driver.setDefaultImplicitlyWait();
        }
    }

    boolean isElementVisible(WebElement element) {
        return isElementVisible(element, 10);
    }

    boolean isElementVisible(WebElement element, int time) {
        try {
            var webDriverWait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Open Notification Area")
    public T openNotificationArea() {
        var screenSize = Driver.getDriver().manage().window().getSize();

        int startX = screenSize.width / 2;
        int startY = 10;
        int endY = (int) (screenSize.height * 0.9);

        AppiumActions.scrollTo(startX, startY, startX, endY);

        return (T) this;
    }
}
