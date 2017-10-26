package ru.reg.project.steps;

// TODO: 1 переход в Расширенный поиск, параметры поиска(1 have) и изменение параметров фильтрации
// TODO: 2 реализовать класс абстрактной корневой страницы(категориальной) маркета с выгрузкой блоков товара в коллекцию

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketSteps extends AbstractSteps {

    public YandexMarketSteps selectProductsCategory(String category) {
        /* //html/body/div[4]/div[1]/div */
        $$x(MarketPageXpaths.categoryDom).forEach(element -> {
            if (category.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
            }
        });
        $x("//div//h1")
                .waitUntil(visible, 3000).shouldHave(exactText(category));
        return this;
    }

    public YandexMarketSteps selectProductsSubCategory(String subcategory) {
        /* /html/body/div[4]/div[1]/div[7]/div/div/div[2]/div[1]/div/a */
        $$x(MarketPageXpaths.subCategoryDom).forEach(element -> {
            if (subcategory.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
            }
        });
        $x("//div//h1")
                .waitUntil(visible, 3000).shouldHave(exactText(subcategory));
        return this;
    }

    static class MarketPageXpaths {
        static String categoryDom = ("//a[@class='link topmenu__link']");
        static String subCategoryDom = ("//div[@class='catalog-menu__list']/a");
    }

}
