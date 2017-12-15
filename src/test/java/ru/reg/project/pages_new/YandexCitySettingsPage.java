package ru.reg.project.pages_new;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.pages_old.MainPage;
import ru.reg.project.popups.YandexCitySettingsPagePopup;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.*;

@PageEntry(title = "Yandex set city page")
public class YandexCitySettingsPage {

    @ElementTitle("Input city field")
    @FindBy(xpath = "//input[@class='input__control input__input']")
    private SelenideElement inputCityField;

    @ElementTitle("Select the city automatically checkbox")
    @FindBy(xpath = "//input[@class='checkbox__control']")
    private SelenideElement autoCitySelectCheckBox;

    @ElementTitle("Save button")
    @FindBy(xpath = "//button[@type='submit']")
    private SelenideElement saveBtn;

    @ElementTitle("Cities popup")
    @FindBy(xpath = "//div[@class='popup__content']")
    private YandexCitySettingsPagePopup citiesPopup;

    @ActionTitle(value = "Поиск и выбор города")
    public MainPage searchAndChooseTheCity(String cityName) {
        executeJavaScript("arguments[0].click()", $(autoCitySelectCheckBox));
        $(inputCityField).setValue(cityName);
        citiesPopup.chooseCity(cityName);
        $(saveBtn).submit();
        return page(MainPage.class);
    }


}

