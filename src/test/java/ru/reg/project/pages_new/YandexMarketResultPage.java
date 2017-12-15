package ru.reg.project.pages_new;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.YandexMarketFilterBlock;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Name("Yandex Market products result page")
public class YandexMarketResultPage {
    private String product_url;

    @FindBy(xpath = "//div[@class='n-filter-block_pos_left i-bem']")
    private YandexMarketFilterBlock yandexMarketFilterBlock;

    @ElementTitle("'Local offers first' checkbox")
    @FindBy(xpath = "//input[@id='local-offers-first']")
    private SelenideElement localOffersCheckbox;

    @ElementTitle("Products blocks")
    @FindBy(xpath = "//a[contains(@class, 'n-snippet-cell2__image')]")
    private ElementsCollection productsBlocks;

    @ElementTitle("'Go to all search filters' link")
    @FindBy(xpath = "//a[contains(text(), 'Перейти ко всем фильтрам')]")
    private SelenideElement allFiltersLink;

    private ElementsCollection getProductsOnThePage() {
        return $$(productsBlocks);
    }

    @ActionTitle(value = "Запомнить первый товар в списке")
    public void setLinkFromFirstInList() {
        System.out.println("THE FIRST IS: " + getProductsOnThePage().first()
                .getAttribute("title").toUpperCase());
        product_url = getProductsOnThePage().first().getAttribute("href");
    }

    @ActionTitle(value = "Перейти к товару, который запомнили")
    public YandexMarketProductPage goToFirstProduct() {
        getWebDriver().get(product_url);
        return page(YandexMarketProductPage.class);
    }

    @ActionTitle("Получить список товаров")
    public ElementsCollection getSizeOfProductsList() {
        System.out.println(getProductsOnThePage().size());
        return getProductsOnThePage();
    }

    @ActionTitle(value = "Нажать на сортировку по интересующему признаку")
    public void sortFilterSet(String filterName) {
        yandexMarketFilterBlock.clickOnFilterLink(filterName);
    }

    @ActionTitle(value = "Перейти к расширенным фильтрам")
    public YandexMarketAdvancedSearchPage goToAllFilters() {
        $(allFiltersLink).click();
        return page(YandexMarketAdvancedSearchPage.class);
    }


}
