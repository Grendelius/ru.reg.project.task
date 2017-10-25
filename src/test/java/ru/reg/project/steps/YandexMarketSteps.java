package ru.reg.project.steps;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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

    public void setUpPrice(String priceFrom, String priceTo) {
        $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(priceFrom);
        $x(MarketPageXpaths.searchResultDiv).waitUntil(enabled, 2500);
        $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(priceTo);
        $x(MarketPageXpaths.searchResultDiv).waitUntil(enabled, 2500);
    }

    public void setUpOnSaleCheckBox(boolean decision) {
        SelenideElement checkbox = $(byText("В продаже")).shouldBe(enabled);
        executeJavaScript(decision ? "arguments[1].click()" : "arguments[0].click()", $(checkbox));
    }

    static class MarketPageXpaths {
        //Elements xpath on the MarketPage
        private static String searchResultDiv = ("//div[@class='layout__col layout__col_search-results_normal i-bem']");
    }

}
