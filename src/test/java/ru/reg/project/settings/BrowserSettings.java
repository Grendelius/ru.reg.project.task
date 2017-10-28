package ru.reg.project.settings;

import static com.codeborne.selenide.Configuration.*;

public class BrowserSettings {

    public static void setUpChrome() {
        browser = "chrome";
        holdBrowserOpen = true;
        clickViaJs = true;
        fastSetValue = true;
        timeout = 10000;
    }

    public static void setUpFireFox() {
        browser = "marionette";
        holdBrowserOpen = true;
        clickViaJs = true;
        timeout = 10000;
    }

}
