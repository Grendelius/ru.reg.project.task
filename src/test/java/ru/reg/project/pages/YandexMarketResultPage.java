package ru.reg.project.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMarketFilterBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@PageEntry(title = "Страница со списком товаров")
public class YandexMarketResultPage extends Page {
    private String product_url;

    @ElementTitle("Сортировка по:")
    @FindBy(xpath = "//div[@class='n-filter-block_pos_left i-bem']")
    private YandexMarketFilterBlock yandexMarketFilterBlock;

    @ElementTitle("Местные предложения")
    @FindBy(xpath = "//input[@id='local-offers-first']")
    private SelenideElement localOffersCheckbox;

    @ElementTitle("Товары")
    @FindBy(xpath = "//a[contains(@class, 'n-snippet-cell2__image')]")
    private ElementsCollection productsBlocks;

    @ElementTitle("Расширенный фильтр товаров")
    @FindBy(xpath = "//a[contains(text(), 'Перейти ко всем фильтрам')]")
    private SelenideElement allFiltersLink;

    public YandexMarketResultPage() {
        page(this);
    }

    private ElementsCollection getProductsOnThePage() {
        return $$(productsBlocks);
    }

    @ActionTitle(value = "запоминает первый товар в списке")
    public YandexMarketResultPage setLinkFromFirstInList() {
        System.out.println("THE FIRST IS: " + getProductsOnThePage().first()
                .getAttribute("title").toUpperCase());
        product_url = getProductsOnThePage().first().getAttribute("href");
        return this;
    }

    @ActionTitle(value = "переходит к товару, который запомнили")
    public YandexMarketProductPage goToFirstProduct() {
        getWebDriver().get(product_url);
        return page(YandexMarketProductPage.class);
    }

    @ActionTitle(value = "получает список товаров")
    public ElementsCollection getSizeOfProductsList() {
        System.out.println(getProductsOnThePage().size());
        return getProductsOnThePage();
    }

    @ActionTitle(value = "Нажать на сортировку по интересующему признаку")
    public YandexMarketResultPage sortFilterSet(String filterName) {
        yandexMarketFilterBlock.clickOnFilterLink(filterName);
        return this;
    }

    @ActionTitle(value = "Перейти к расширенным фильтрам")
    public YandexMarketAdvancedSearchPage goToAllFilters() {
        $(allFiltersLink).click();
        return page(YandexMarketAdvancedSearchPage.class);
    }


}
