package ru.reg.project.pages_new;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMainPageAuthBlock;
import ru.reg.project.blocks.YandexMainPageNavigationPanel;
import ru.reg.project.popups.YandexMainPageSettingsLinkPopup;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

@Name("Yandex main page")
public class YandexMainPage {

    public static final String PAGE_URL = "https://yandex.ru/";

    @FindBy(xpath = "//div[@class='domik3__slider']")
    private YandexMainPageAuthBlock yandexMainPageAuthBlock;

    @FindBy(xpath = "//div[@role='navigation']")
    private YandexMainPageNavigationPanel yandexMainPageNavigationPanel;

    @FindBy(xpath = "//body/div[4]/div[2]")
    private YandexMainPageSettingsLinkPopup yandexMainPageSettingsLinkPopup;

    @Name("Settings link")
    @FindBy(xpath = "//span[@class='link__inner'][contains(text(), 'Настройка')]")
    private SelenideElement settingsLink;

    public YandexMarketMainPage goToYandexMarket() {
        yandexMainPageNavigationPanel.sectionClick("Маркет");
        return page(YandexMarketMainPage.class);
    }

    public YandexCitySettingsPage setCity() {
        $(settingsLink).click();
        yandexMainPageSettingsLinkPopup.clickOnPopupMenuItemLink("Изменить город");
        return page(YandexCitySettingsPage.class);
    }

}
