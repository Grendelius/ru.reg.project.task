package ru.reg.project.popups;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("City input field popup")
public class YandexCitySettingsPagePopup extends ElementsContainer {

    @Name("City menu item")
    @FindBy(xpath = ".//ul/li//div[@class='b-autocomplete-item__reg']")
    private ElementsCollection citiesLink;

    public void chooseCity(String cityName) {
        citiesLink.filter(exactText(cityName)).first().click();
    }
}
