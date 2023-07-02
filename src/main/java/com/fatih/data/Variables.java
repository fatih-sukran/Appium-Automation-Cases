package com.fatih.data;

import lombok.Data;
import org.testng.ITestResult;

@Data
public class Variables {

    private static final ThreadLocal<Variables> VARIABLES_TL = new ThreadLocal<>();

    // Alert Dialog Tests Variables
    private int alertDialogIndex;
    private String alertDialogMessage;

    // Incoming Message Tests Variables
    private String notificationMessage;

    private Variables() {}

    public static Variables getInstance() {
        if (VARIABLES_TL.get() == null) {
            VARIABLES_TL.set(new Variables());
        }
        return VARIABLES_TL.get();
    }

    public static void remove() {
        VARIABLES_TL.remove();
    }
}
