package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Categories block on main page of YandexMarket")
public class YandexMarketPageCategoriesBlock extends ElementsContainer {

    @Name("Categories links")
    @FindBy(xpath = ".//li/a[@class='link topmenu__link']")
    private ElementsCollection categoriesNames;


    public void navigateToCategoryAndClick(String category) {
        categoriesNames.filter(exactText(category)).first().doubleClick();
    }
}
