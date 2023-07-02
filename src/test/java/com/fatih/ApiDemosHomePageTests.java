package com.fatih;

import com.fatih.pages.*;
import org.testng.annotations.Test;

public class ApiDemosHomePageTests extends BaseTest {

    @Test
    public void testActionBarTabs() {

        new ActionBarTabsPage()
                .navigate("App", "Action Bar", "Action Bar Tabs")
                .checkToggleTabModeEnable(false)
                .clickToggleTabs()
                .addTab(3)
                .assertTabSize(3)
                .removeTab()
                .assertTabSize(2)
                .removeAllTab()
                .assertTabSize(0);
    }

    @Test
    public void testCustomTitleActivity() {
        var leftText = "New Left Text";
        var rightText = "New Right Text";

        new CustomTitleActivityPage()
                .navigate("App", "Activity", "Custom Title")
                .checkDefaultLeftText("Left is best")
                .checkDefaultRightText("Right is always right")
                .changeLeftTextBox(leftText)
                .checkDefaultLeftText(leftText)
                .changeRightTextBox(rightText)
                .checkDefaultRightText(rightText);
    }

    @Test
    public void testAlertDialog() {

        new AlertDialogPage()
                .navigate("App", "Alert Dialogs")
                .selectRandomElementFromList()
                .checkAlertMessage();
    }

    @Test
    public void testContextMenu() {
        new ContextMenuPage()
                .navigate("App", "Fragment", "Context Menu")
                .clickLongPressButton()
                .checkIsMenuOpened();
    }

    @Test
    public void testHideAndShow() {
        new HideAndShowPage()
                .navigate("App", "Fragment", "Hide and Show")
                .checkAreButtonsPresident()
                .clickHide2()
                .assertHideButton2Text("Show")
                .checkVisibilityOfFragment2(false)
                .clickHide2()
                .assertHideButton1Text("Hide")
                .checkVisibilityOfFragment2(true);
    }

    @Test
    public void testNotification() {
        new IncomingMessagePage()
                .navigate("App", "Notification", "IncomingMessage")
                .clickShowNotification()
                .openNotificationArea()
                .assertNotificationAppear()
                .clickNotification()
                .assertNotificationMessage();
    }

    @Test
    public void testScrollable() {
        new ScrollablePage()
                .navigate("Views", "Tabs", "5. Scrollable")
                .scrollToLastTab()
                .clickLastTab()
                .assertTabMessage();
    }
}
