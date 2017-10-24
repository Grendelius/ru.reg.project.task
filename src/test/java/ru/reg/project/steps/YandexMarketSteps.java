package ru.reg.project.steps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//TODO: переход в Расширенный поиск, параметры поиска и изменение параметров фильтрации

public class YandexMarketSteps {

    public void selectCategory(String category) {
        $$x("//*[@class='link topmenu__link']").forEach(element -> {
            if (category.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
            }
        });
        $x("//div/h1")
                .waitUntil(visible, 3000).shouldHave(text(category));
    }

    public void selectSubCategory(String subcategory) {
        $$x("//div[@class='catalog-menu__list']/a").forEach(element -> {
            if (subcategory.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
            }
        });
        $x("//div/h1")
                .waitUntil(visible, 3000).shouldHave(text(subcategory));
    }

    static class MarketPageXpaths {
    }

}
