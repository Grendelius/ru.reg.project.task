package ru.reg.project.settings;

import static com.codeborne.selenide.Configuration.*;

public class BrowserSettings {

    public static void setUpChrome() {
        clickViaJs = true;
        timeout = 10000;
    }

    public static void setUpFireFox() {
        browser = "marionette";
        clickViaJs = true;
        timeout = 10000;
    }

}
