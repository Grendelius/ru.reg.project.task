package ru.reg.project.settngs;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.WebDriverRunner.*;

public class Browser {

    public static void setChrome() {
        Configuration.browser = CHROME;
        Configuration.holdBrowserOpen = true;
        Configuration.fastSetValue = false;
    }

    public static void setFireFox_new() {
        Configuration.browser = MARIONETTE;
        Configuration.holdBrowserOpen = true;
        Configuration.fastSetValue = true;
    }

    public static void setFirefox_old() {
        Configuration.browser = FIREFOX;
        Configuration.holdBrowserOpen = true;
        Configuration.fastSetValue = true;
    }

}
