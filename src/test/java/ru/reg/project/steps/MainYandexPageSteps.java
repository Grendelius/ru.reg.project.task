package ru.reg.project.steps;

import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.reg.project.settings.BrowserSettings.setUpChrome;
import static ru.reg.project.settings.BrowserSettings.setUpFireFox;

public class MainYandexPageSteps {

    public MainYandexPageSteps openYandexRu(final String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) setUpChrome();
        if (browserName.equalsIgnoreCase("firefox")) setUpFireFox();
        open("https://yandex.ru/");
        return this;
    }

    public MainYandexPageSteps chooseCity(String city) throws NoSuchElementException {
        $(byText("Настройка")).click();
        $(byText("Изменить город")).waitUntil(enabled, 1000).click();
        $(byText("Город")).waitUntil(visible, 1500);
        executeJavaScript("arguments[0].click()", $x("//input[@class='checkbox__control']"));
        $x(MainPageXpaths.INPUT_COUNTRY_FIELD).val(city);
        try {
            $x(MainPageXpaths.COUNTRIES_POPUP).$$x(MainPageXpaths.POPUP_COUNTRIES_FIELDS).forEach(element -> {
                if (city.equalsIgnoreCase(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                    $(byText("Сохранить")).submit();
                }
            });
        } catch (NoSuchElementException exc) {
            System.out.println("Popup element not found! City has been determined automatically");
            screenshot("popup_not_found");
            refresh();
            $(byText("Сохранить")).submit();
        }
        return this;
    }

    public YandexMarketSteps chooseMarketCategory() {
        $(byText("Маркет")).click();
        try {
            $x("/html/body/div[1]").waitUntil(enabled, 4000);
        } catch (NoSuchElementException | NullPointerException exc) {
            screenshot("element nor found/null");
        }
        return (new YandexMarketSteps());
    }

    public void chooseMusicCategory() {
        $(byText("Музыка")).click();
        $x("//*[@id='nb-1']/body").waitUntil(enabled, 3000);
//        return (new YandexMusicSteps());
    }

    public void chooseVideoCategory() {
        $(byText("Видео")).click();
        $x("/html/body/div[4]").waitUntil(enabled, 4000);
//        return (new VideoYandexSteps());
    }

    static class MainPageXpaths {
        //Elements xpath on the MainPage
        static String INPUT_COUNTRY_FIELD = "//input[@id='city__front-input']";
        static String COUNTRIES_POPUP = "//div[@class='popup__content']";
        static String POPUP_COUNTRIES_FIELDS = ".//ul/li//div[@class='b-autocomplete-item__reg']";

    }
}
