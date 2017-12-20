package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Блок подкатегорий товаров")
public class YandexMarketPageSubCategoriesBlock extends ElementsContainer {

    @Name("Подкатегории")
    @FindBy(xpath = ".//a[contains(@class, 'link catalog-menu__list-item')]")
    private ElementsCollection subCategories;

    public void navigateToSubCatAndClick(String subCategoryName) {
        subCategories.filter(exactText(subCategoryName)).first().click();
    }
}
