package ru.reg.project.pages_new;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.MainPageAuthBlock;
import ru.reg.project.blocks.MainPageNavigationBlock;
import ru.reg.project.popups.SettingsLinkPopup;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

@Name("Yandex main page")
public class YandexMainPage {
    public static final String PAGE_URL = "https://yandex.ru/";

    @FindBy(xpath = "//div[@class='domik3__slider']")
    private MainPageAuthBlock mainPageAuthBlock;

    @FindBy(xpath = "//div[@role='navigation']")
    private MainPageNavigationBlock mainPageNavigationBlock;

    @FindBy(xpath = "//body/div[4]/div[2]")
    private SettingsLinkPopup settingsLinkPopup;

    @Name("Settings link")
    @FindBy(xpath = "//span[@class='link__inner'][contains(text(), 'Настройка')]")
    private SelenideElement settingLink;

    public YandexMarketMainPage goToYandexMarket() {
        mainPageNavigationBlock.sectionClick("Маркет");
        return page(YandexMarketMainPage.class);
    }

    public YandexCitySettingsPage setCity() {
        $(settingLink).click();
        settingsLinkPopup.clickOnPopupMenuItemLink("Изменить город");
        return page(YandexCitySettingsPage.class);
    }

}
