package ru.reg.project.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.popups.YandexCitySettingsPagePopup;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.*;

@PageEntry(title = "Страница выбора города")
public class YandexCitySettingsPage extends Page {

    @ElementTitle("Поле ввода города")
    @FindBy(xpath = "//input[@class='input__control input__input']")
    private SelenideElement inputCityField;

    @ElementTitle("Выбрать город автоматически")
    @FindBy(xpath = "//input[@class='checkbox__control']")
    private SelenideElement autoCitySelectCheckBox;

    @ElementTitle("Сохранить")
    @FindBy(xpath = "//button[@type='submit']")
    private SelenideElement saveBtn;

    @ElementTitle("Выплывающий список городов")
    @FindBy(xpath = "//div[@class='popup__content']")
    private YandexCitySettingsPagePopup citiesPopup;

    public YandexCitySettingsPage() {
        page(this);
    }

    @ActionTitle(value = "ищет и выбирает город")
    public YandexMainPage searchAndChooseTheCity(String cityName) {
        executeJavaScript("arguments[0].click()", $(autoCitySelectCheckBox));
        $(inputCityField).setValue(cityName);
        citiesPopup.chooseCity(cityName);
        $(saveBtn).submit();
        return page(YandexMainPage.class);
    }


}

