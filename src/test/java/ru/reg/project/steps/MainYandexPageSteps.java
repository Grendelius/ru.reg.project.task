package ru.reg.project.steps;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.reg.project.settings.BrowserSettings.setUpChrome;
import static ru.reg.project.settings.BrowserSettings.setUpFireFox;

public class MainYandexPageSteps {

    public void openYandexRu(final String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            setUpChrome();
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            setUpFireFox();
        }
        open("https://yandex.ru/");
    }

    public void chooseCity(String city) {
        $(byText("Настройка")).click();
        $(byText("Изменить город")).waitUntil(enabled, 1000).click();
        $(byText("Город")).waitUntil(visible, 1500);
        executeJavaScript("arguments[0].click()", $x("//input[@class='checkbox__control']"));
        $x(MainPageXpaths.INPUT_COUNTRY_FIELD).val(city).$x(MainPageXpaths.COUNTRIES_POPUP)
                .$$x(MainPageXpaths.POPUP_COUNTRIES_FIELDS).forEach(element -> {
            if (city.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
                $(byText("Сохранить")).submit();
            }
        });
    }

    public void chooseMarketCategory() {
        $(byText("Маркет")).click();
        $x("/html/body/div[1]").waitUntil(enabled, 4000);
        switchTo().alert().dismiss();
    }

    public void chooseMusicCategory() {
        $(byText("Музыка")).click();
        $x("//*[@id='nb-1']/body").waitUntil(enabled, 3000);
    }

    public void chooseVideoCategory() {
        $(byText("Видео")).click();
        $x("/html/body/div[4]").waitUntil(enabled, 4000);
    }

    static class MainPageXpaths {
        //MainPage elements Xpaths
        static String INPUT_COUNTRY_FIELD = "//input[@id='city__front-input']";
        static String COUNTRIES_POPUP = "//div[@class='popup__content']";
        static String POPUP_COUNTRIES_FIELDS = ".//ul/li//div[@class='b-autocomplete-item__reg']";

    }
}
