package ru.reg.project.pages_old;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Deprecated
public class MarketPage extends Page {
    private final String PAGE_URL = "https://market.yandex.ru/";
    private SelenideElement product;
    private AdvancedSearchPage advancedSearchPageSteps;

    public MarketPage() {
    }

    public MarketPage(String browser) {
        super(browser);
        open(PAGE_URL);
    }

    private SelenideElement getProduct() {
        return product;
    }

    private void setProduct(SelenideElement product) {
        this.product = product;
    }

    private String getProductUrl() {
        return getProduct().getAttribute("href");
    }

    private ElementsCollection getProductBlocks() {
        return $$x("//a[contains(@class, 'n-snippet-cell2__image')]");
    }

    public MarketPage sortClick(String sortName) {
        $$x("//a[contains(@class,'link link_theme_major')]")
                .filter(exactText(sortName)).first().doubleClick();
        return this;
    }

    public MarketPage setFirstProduct() {
        setProduct(getProductBlocks().get(0));
        System.out.println("The fist product in list is: " + getProduct().getAttribute("title").toUpperCase());
        return this;
    }

    public ProductPage getFirstAndClickOnIt() {
        getWebDriver().get(getProductUrl());
        $x("//div[1]/h1").waitUntil(visible, 3000);
        return (new ProductPage());
    }

    public MarketPage selectProductsCategory(String category) throws NoSuchElementException {
        try {
            $$x("//a[@class='link topmenu__link']").filter(exactText(category)).first().doubleClick();
            $x("//div//h1").waitUntil(visible, 4000).shouldHave(exactText(category));
        } catch (NoSuchElementException exc) {
            System.out.print("Element by text: " + category + "not found");
            refresh();
            $(byText(category)).waitUntil(visible, 3000).doubleClick();
        }
        return this;
    }

    public MarketPage selectProductsSubCategory(String subcategory) throws NoSuchElementException {
        try {
            $$x("//a[contains(@class, 'link catalog-menu__list-item')]")
                    .filter(exactText(subcategory)).first().doubleClick();
            $x("//div//h1").waitUntil(visible, 3000).shouldHave(exactText(subcategory));
        } catch (NoSuchElementException exc) {
            System.out.print("Element by text: " + subcategory + "not found");
            refresh();
            $(byText(subcategory)).waitUntil(visible, 3000).doubleClick();
        }
        return this;
    }

    public void setPrice(Integer from, Integer to) {
        advancedSearchPageSteps = new AdvancedSearchPage();
        advancedSearchPageSteps.setUpPrice(from, to);
    }

    public void setScreenDiagonal(String size) {
        advancedSearchPageSteps = new AdvancedSearchPage();
        advancedSearchPageSteps.setUpPhoneScreenDiagonal(size);
    }

    public AdvancedSearchPage goToAdvancedSearch() {
        $x("//div[@class='n-filter-panel-aside__show-more']/a").shouldBe(visible).click();
        $x("//body/div[1]/div[3]/div/h1").shouldBe(visible).shouldHave(exactText("Все фильтры"));
        return advancedSearchPageSteps = new AdvancedSearchPage();
    }

    public void assertSizeOfBlock(int size) {
        System.out.println("Count of products on the page is: " + getProductBlocks().size());
        getProductBlocks().shouldHave(sizeGreaterThanOrEqual(size));
    }
}