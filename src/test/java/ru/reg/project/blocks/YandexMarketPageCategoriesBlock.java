package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Блок категорий товаров")
public class YandexMarketPageCategoriesBlock extends ElementsContainer {

    @Name("Категории")
    @FindBy(xpath = ".//li/a[@class='link topmenu__link']")
    private ElementsCollection categoriesNames;


    public void navigateToCategoryAndClick(String category) {
        categoriesNames.filter(exactText(category)).first().doubleClick();
    }
}
