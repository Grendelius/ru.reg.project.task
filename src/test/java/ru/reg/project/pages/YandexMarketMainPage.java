package ru.reg.project.pages;

import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMarketPageCategoriesBlock;
import ru.reg.project.blocks.YandexMarketPageSubCategoriesBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.page;

@PageEntry(title = "Яндекc Маркет")
public class YandexMarketMainPage extends Page {
    public static final String PAGE_URL = "https://market.yandex.ru/";

    @ElementTitle("Категории")
    @FindBy(xpath = "//ul[@class='topmenu__list']")
    private YandexMarketPageCategoriesBlock yandexMarketPageCategoriesBlock;

    @ElementTitle("Подкатегории")
    @FindBy(xpath = "//div[@class='catalog-menu__list']")
    private YandexMarketPageSubCategoriesBlock yandexMarketPageSubCategoriesBlock;

    public YandexMarketMainPage() {
        page(this);
    }

    @ActionTitle(value = "выбирает категорию и подкатегорию товара")
    public YandexMarketResultPage goToCategoryAndToSubCategory(String category, String subcategory) {
        yandexMarketPageCategoriesBlock.navigateToCategoryAndClick(category);
        yandexMarketPageSubCategoriesBlock.navigateToSubCatAndClick(subcategory);
        return page(YandexMarketResultPage.class);
    }

}
