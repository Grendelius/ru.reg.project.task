package ru.reg.project.pages_new;

import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMarketPageCategoriesBlock;
import ru.reg.project.blocks.YandexMarketPageSubCategoriesBlock;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.page;

@PageEntry(title = "Яндек Маркет")
public class YandexMarketMainPage {
    public static final String PAGE_URL = "https://market.yandex.ru/";

    @FindBy(xpath = "//ul[@class='topmenu__list']")
    private YandexMarketPageCategoriesBlock yandexMarketPageCategoriesBlock;

    @FindBy(xpath = "//div[@class='catalog-menu__list']")
    private YandexMarketPageSubCategoriesBlock yandexMarketPageSubCategoriesBlock;

    public YandexMarketMainPage() {
        page(YandexMarketMainPage.class);
    }

    @ActionTitle(value = "выбирает категорию и подкатегорию товара")
    public YandexMarketResultPage goToCategoryAndToSubCategory(String category, String subcategory) {
        yandexMarketPageCategoriesBlock.navigateToCategoryAndClick(category);
        yandexMarketPageSubCategoriesBlock.navigateToSubCatAndClick(subcategory);
        return page(YandexMarketResultPage.class);
    }

}
