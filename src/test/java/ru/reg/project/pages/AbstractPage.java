package ru.reg.project.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

abstract class AbstractPage {

    abstract void clickOnCheckbox(String name);

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
