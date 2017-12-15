package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;

@Name("Subcategories block of YandexMarketMainPage")
public class YandexMarketPageSubCategoriesBlock extends ElementsContainer {

    @ElementTitle("SubCategory name")
    @FindBy(xpath = ".//a[contains(@class, 'link catalog-menu__list-item')]")
    private ElementsCollection subCategories;

    public void navigateToSubCatAndClick(String subCategoryName) {
        subCategories.filter(exactText(subCategoryName)).first().click();
    }
}
