package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Yandex MainPage navigation panel")
public class YandexMainPageNavigationPanel extends ElementsContainer {

    @ElementTitle("Section name")
    @FindBy(xpath = ".//a")
    private ElementsCollection category;

    public void sectionClick(String sectionName) {
        category.filter(exactText(sectionName)).first().click();
    }
}
