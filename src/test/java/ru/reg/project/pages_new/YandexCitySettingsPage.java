package ru.reg.project.pages_new;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.pages_old.MainPage;
import ru.reg.project.popups.YandexCitySettingsPagePopup;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.*;

@Name("Yandex set city page")
public class YandexCitySettingsPage {

    @Name("Input city field")
    @FindBy(xpath = "//input[@class='input__control input__input']")
    private SelenideElement inputCityField;

    @Name("Select the city automatically checkbox")
    @FindBy(xpath = "//input[@class='checkbox__control']")
    private SelenideElement autoCitySelectCheckBox;

    @Name("Save button")
    @FindBy(xpath = "//button[@type='submit']")
    private SelenideElement saveBtn;

    @FindBy(xpath = "//div[@class='popup__content']")
    private YandexCitySettingsPagePopup citiesPopup;

    public MainPage searchAndChooseTheCity(String cityName) {
        executeJavaScript("arguments[0].click()", $(autoCitySelectCheckBox));
        $(inputCityField).setValue(cityName);
        citiesPopup.chooseCity(cityName);
        $(saveBtn).submit();
        return page(MainPage.class);
    }


}

