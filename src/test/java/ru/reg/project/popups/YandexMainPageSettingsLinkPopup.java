package ru.reg.project.popups;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

@Name("YandexMainPage settings link popup")
public class YandexMainPageSettingsLinkPopup extends ElementsContainer {

    @Name("Menu item link")
    @FindBy(xpath = ".//div[@role='menuitem']/a")
    private ElementsCollection popupMenuItemLink;

    public void clickOnPopupMenuItemLink(String menuItemName) {
        actions().moveToElement($$(popupMenuItemLink).filter(exactText(menuItemName)).first())
                .click().build().perform();
    }
}
