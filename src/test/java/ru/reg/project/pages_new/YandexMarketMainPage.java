package ru.reg.project.pages_new;

import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMarketPageCategoriesBlock;
import ru.reg.project.blocks.YandexMarketPageSubCategoriesBlock;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.page;

@Name("Yandex Market main page")
public class YandexMarketMainPage {
    public static final String PAGE_URL = "https://market.yandex.ru/";

    @FindBy(xpath = "//ul[@class='topmenu__list']")
    private YandexMarketPageCategoriesBlock yandexMarketPageCategoriesBlock;

    @FindBy(xpath = "//div[@class='catalog-menu__list']")
    private YandexMarketPageSubCategoriesBlock yandexMarketPageSubCategoriesBlock;


    public YandexMarketResultPage goToCategoryAndToSubCategory(String category, String subcategory) {
        yandexMarketPageCategoriesBlock.navigateToCategoryAndClick(category);
        yandexMarketPageSubCategoriesBlock.navigateToSubCatAndClick(subcategory);
        return page(YandexMarketResultPage.class);
    }

}
