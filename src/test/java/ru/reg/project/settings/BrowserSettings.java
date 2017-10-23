package ru.reg.project.settings;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Configuration.*;

public class BrowserSettings {
    private static WebDriver driver = null;

    public static void setUpChrome() {
        browser = "chrome";
        holdBrowserOpen = true;
        clickViaJs = true;
        fastSetValue = false;
        timeout = 8000;
    }

    public static void setUpFireFox() {
        browser = "marionette";
        holdBrowserOpen = true;
        clickViaJs = true;
        timeout = 8000;
    }

}
