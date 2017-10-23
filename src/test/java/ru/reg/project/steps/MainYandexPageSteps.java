package ru.reg.project.steps;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.reg.project.settings.BrowserSettings.setUpChrome;
import static ru.reg.project.settings.BrowserSettings.setUpFireFox;

public class MainYandexPageSteps {

    public void openYandexSearchPage(final String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            setUpChrome();
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            setUpFireFox();
        }
        open("https://www.yandex.ru/");
    }

    public void chooseCity(String city) {
        $(byText("Настройка")).click();
        $(byText("Изменить город")).waitUntil(enabled, 1000).click();
        $(byText("Город")).waitUntil(visible, 1500);
        executeJavaScript("arguments[0].click()", $x("//input[@class='checkbox__control']"));
        $x(Xpath.INPUT_COUNTRY_FIELD.getDomString()).val(city).$x(Xpath.COUNTRIES_POPUP.getDomString())
                .$$x(Xpath.COUNTRIES_FIELDS_POPUP.getDomString()).forEach(element -> {
            if (city.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
                $(byText("Сохранить")).submit();
            }
        });
    }

    public void chooseMarketCategory() {
        $(byText("Маркет")).click();
        $x("/html/body/div[1]").waitUntil(enabled, 4000);
    }

    public void chooseMusicCategory() {
        $(byText("Музыка")).click();
        $x("//*[@id='nb-1']/body").waitUntil(enabled, 3000);
    }

    public void chooseVideoCategory() {
        $(byText("Видео")).click();
        $x("/html/body/div[4]").waitUntil(enabled, 4000);
    }

    enum Xpath {
        INPUT_COUNTRY_FIELD("//input[@id='city__front-input']"),
        COUNTRIES_POPUP("//div[@class='popup__content']"),
        COUNTRIES_FIELDS_POPUP(".//ul/li//div[@class='b-autocomplete-item__reg']");

        private String domString;

        Xpath(String s) {
            this.domString = s;
        }

        public String getDomString() {
            return domString;
        }
    }
}
