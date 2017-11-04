package ru.reg.project.pages;

import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.reg.project.settings.BrowserSettings.setUpChrome;
import static ru.reg.project.settings.BrowserSettings.setUpFireFox;

public class MainPage {

    public MainPage openYandexRu(final String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) setUpChrome();
        if (browserName.equalsIgnoreCase("firefox")) setUpFireFox();
        open("https://yandex.ru/");
        return (new MainPage());
    }

    public MainPage chooseCity(String city) throws NoSuchElementException {
        $(byText("Настройка")).click();
        $(byText("Изменить город")).waitUntil(enabled, 1000).click();
        $(byText("Город")).waitUntil(visible, 1500);
        executeJavaScript("arguments[0].click()", $x("//input[@class='checkbox__control']"));
        $x(MainPageXpaths.input_country_field).shouldBe(enabled).val(city)
                .$x(MainPageXpaths.countries_popup).shouldBe(enabled);
        try {
            $x(MainPageXpaths.countries_popup).$$x(MainPageXpaths.popup_countries_field).forEach(element -> {
                if (city.contentEquals(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                    $(byText("Сохранить")).submit();
                }
            });
        } catch (NoSuchElementException exc) {
            screenshot("popup_not_found");
            $(byText("Сохранить")).submit();
        }
        return this;
    }

    public MarketPage chooseMarketCategory() {
        $(byText("Маркет")).click();
        sleep(2000);
        return (new MarketPage());
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
        static String input_country_field = "//input[@id='city__front-input']";
        static String countries_popup = "//div[@class='popup__content']";
        static String popup_countries_field = ".//ul/li//div[@class='b-autocomplete-item__reg']";

    }
}
