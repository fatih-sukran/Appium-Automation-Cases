package com.fatih.utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public final class AppiumActions {

    private static final PointerInput TOUCH = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    private AppiumActions() {
        throw new UnsupportedOperationException();
    }

    public static void longPress(WebElement element) {
        var sequence = new Sequence(TOUCH, 0);

        sequence.addAction(TOUCH.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY()));
        sequence.addAction(TOUCH.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(TOUCH, Duration.ofMillis(2000)));
        sequence.addAction(TOUCH.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singletonList(sequence));
    }

    public static void scrollTo(int startX, int startY, int endX, int endY) {
        var sequence = new Sequence(TOUCH, 0);

        sequence.addAction(TOUCH.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        sequence.addAction(TOUCH.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(TOUCH, Duration.ofMillis(200)));
        sequence.addAction(TOUCH.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        sequence.addAction(TOUCH.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singletonList(sequence));
    }
}
