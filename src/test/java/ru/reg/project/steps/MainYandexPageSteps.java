package ru.reg.project.steps;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.reg.project.settings.BrowserSettings.setUpChrome;
import static ru.reg.project.settings.BrowserSettings.setUpFireFox;

public class MainYandexPageSteps {

    //it works
    public MainYandexPageSteps openYandexSearchPage(final String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            setUpChrome();
        }
        if (browserName.equalsIgnoreCase("firefox")) {
            setUpFireFox();
        }

        open("https://www.yandex.ru/");
        return this;
    }

    //it works
    public void chooseCity(String city) {
        $(byText("Настройка")).click();
        $(byText("Изменить город")).waitUntil(enabled, 1500).click();
        $(byText("Город")).waitUntil(visible,1500);
        executeJavaScript("arguments[0].click()", $x("//input[@class='checkbox__control']"));
        $x("//span[@class='input__box']")
                .setValue(city)
                .findElement(By.className("popup__content"))
                .findElements(By.linkText(city)).stream().findFirst().get().click();
    }

    //it works
    public void chooseMarketCategory() {
        $(byText("Маркет")).click();
        $x("/html/body/div[1]").waitUntil(enabled, 4000);
    }
    //it works
    public void chooseMusicCategory() {
        $(byText("Музыка")).click();
        $x("//*[@id='nb-1']/body").waitUntil(enabled, 3000);
    }
    //it works
    public void chooseVideoCategory() {
        $(byText("Видео")).click();
        $x("/html/body/div[4]").waitUntil(enabled, 4000);
    }
}
