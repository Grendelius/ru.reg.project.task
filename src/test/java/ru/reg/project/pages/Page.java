package ru.reg.project.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.*;

class Page {

    Page() {
    }

    Page(String browser) {
        switch (browser) {
            case "chrome":
                Configuration.browser = CHROME;
                fastSetValue = true;
                clickViaJs = true;
                timeout = 6000;
                break;
            case "firefox":
                Configuration.browser = MARIONETTE;
                fastSetValue = true;
                clickViaJs = true;
                timeout = 5000;
                break;
            case "firefox_3.6":
                Configuration.browser = FIREFOX;
                fastSetValue = true;
                clickViaJs = true;
                timeout = 8000;
                break;
        }
    }

    SelenideElement waitUntilEnabledByText(String text) {
        SelenideElement element = $(byText(text));
        $(element).waitUntil(enabled, 4000).waitUntil(visible, 4000);
        return element;
    }

    void authorization(String loginName, String password) {
        $("login").shouldBe(enabled).val(loginName);
        $("passwd").shouldBe(enabled).val(password).submit();
    }
}
