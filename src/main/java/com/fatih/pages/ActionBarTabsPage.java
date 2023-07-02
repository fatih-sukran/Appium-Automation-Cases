package com.fatih.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ActionBarTabsPage extends BasePage<ActionBarTabsPage> {

    @AndroidFindBy(id = "android:id/action_bar")
    WebElement actionBar;

    @AndroidFindBy(id = "com.hmh.api:id/btn_add_tab")
    WebElement btnAddTab;

    @AndroidFindBy(id = "com.hmh.api:id/btn_remove_tab")
    WebElement btnRemoveTab;

    @AndroidFindBy(id = "com.hmh.api:id/btn_toggle_tabs")
    WebElement btnToggleTabs;

    @AndroidFindBy(id = "com.hmh.api:id/btn_remove_all_tabs")
    WebElement btnRemoveAllTabs;

    @AndroidFindBy(className = "android.app.ActionBar$Tab")
    List<WebElement> allTabs;

    @Step("Check Toggle Tab Mode Enable -> Expected: {0}")
    public ActionBarTabsPage checkToggleTabModeEnable(boolean isEnable) {
        Assert.assertEquals(isEnable, !isElementVisible(actionBar, 3));

        return this;
    }

    @Step("Click Toggle Tabs")
    public ActionBarTabsPage clickToggleTabs() {
        click(btnToggleTabs);

        return this;
    }

    @Step("Add {0} Tab")
    public ActionBarTabsPage addTab(int times) {
        for (int i = 0; i < times; i++) {
            addTab();
        }

        return this;
    }

    @Step("Click Add Tab")
    public ActionBarTabsPage addTab() {
        click(btnAddTab);

        return this;
    }

    @Step("Click Remove Tab")
    public ActionBarTabsPage removeTab() {
        click(btnRemoveTab);

        return this;
    }

    @Step("Click Remove All Tab")
    public ActionBarTabsPage removeAllTab() {
        click(btnRemoveAllTabs);

        return this;
    }

    @Step("Assert Tab Size - Expected Size {0}")
    public ActionBarTabsPage assertTabSize(int size) {
        Assert.assertEquals(allTabs.size(), size,
                "Expected tab size: " + size + ". Actual tab size: " + allTabs.size());

        return this;
    }
}
