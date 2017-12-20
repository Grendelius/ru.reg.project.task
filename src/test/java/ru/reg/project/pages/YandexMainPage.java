package ru.reg.project.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMainPageAuthBlock;
import ru.reg.project.blocks.YandexMainPageNavigationPanel;
import ru.reg.project.popups.YandexMainPageSettingsLinkPopup;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.page;

@PageEntry(title = "Основная страница Яндекс")
public class YandexMainPage extends Page {
    public static final String PAGE_URL = "https://yandex.ru/";

    @ElementTitle("Блок аутентификации")
    @FindBy(xpath = "//div[@class='domik3__slider']")
    private YandexMainPageAuthBlock yandexMainPageAuthBlock;

    @ElementTitle("Навигация по сервису")
    @FindBy(xpath = "//div[@role='navigation']")
    private YandexMainPageNavigationPanel yandexMainPageNavigationPanel;

    @ElementTitle("Всплывающее окно настроек пользователя")
    @FindBy(xpath = "//body/div[4]/div[2]")
    private YandexMainPageSettingsLinkPopup yandexMainPageSettingsLinkPopup;

    @ElementTitle("Настройки")
    @FindBy(xpath = "//span[@class='link__inner'][contains(text(), 'Настройка')]")
    private SelenideElement settingsLink;

    @ActionTitle(value = "переходит на Yandex Market")
    public YandexMarketMainPage goToYandexMarket() {
        yandexMainPageNavigationPanel.sectionClick("Маркет");
        return page(YandexMarketMainPage.class);
    }

    @ActionTitle(value = "изменяет город")
    public YandexCitySettingsPage setCity() {
        settingsLink.click();
        yandexMainPageSettingsLinkPopup.clickOnPopupMenuItemLink("Изменить город");
        return page(YandexCitySettingsPage.class);
    }

}
