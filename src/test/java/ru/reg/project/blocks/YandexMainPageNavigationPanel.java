package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Навигационная панель основной страницы Яндекс")
public class YandexMainPageNavigationPanel extends ElementsContainer {

    @Name("Секция")
    @FindBy(xpath = ".//a")
    private ElementsCollection category;

    public void sectionClick(String sectionName) {
        category.filter(exactText(sectionName)).first().click();
    }
}
