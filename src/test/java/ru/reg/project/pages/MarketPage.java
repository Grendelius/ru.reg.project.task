package ru.reg.project.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MarketPage {
    private String PAGE_URL = "https://market.yandex.ru/";
    private SelenideElement product;
    private AdvancedSearchPage advancedSearchPageSteps;

    public MarketPage() {
    }

    public MarketPage(String browser) {
        Configuration.browser = browser;
        open(PAGE_URL);
    }

    private SelenideElement getProduct() {
        return product;
    }

    private void setProduct(SelenideElement product) {
        this.product = product;
    }

    private List<SelenideElement> getProductBlocks() {
        List<SelenideElement> list = new ArrayList<>();
        List<SelenideElement> upperBlock = $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[1]/div/a");
        List<SelenideElement> bottomBlock = $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[3]/div/a");
        list.addAll(upperBlock);
        list.addAll(bottomBlock);
        return $$(list);
    }

    public MarketPage getFirstProduct() {
        setProduct(getProductBlocks().get(1));
        System.out.println("The fist element in list is: " + getProduct().getAttribute("title").toUpperCase());
        return this;
    }

    public MarketPage sortClick(String sortName) {
        $$x("/html/body/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div/a")
                .filter(exactText(sortName)).first().doubleClick();
        return this;
    }

    public ProductPage getAndClickOnProduct() {
        $(product).click();
        $x("/html/body/div[1]/div[4]/div[2]/div[2]/div/div[1]/div[1]/h1").waitUntil(visible, 3000);
        return (new ProductPage());
    }

    public MarketPage selectProductsCategory(String category) throws NoSuchElementException {
        try {
            $$x(MarketPageXpaths.categoryDom).filter(exactText(category)).first().doubleClick();
            $x("//div//h1").waitUntil(visible, 8000).shouldHave(exactText(category));
        } catch (NoSuchElementException exc) {
            System.out.print("Element by text: " + category + "not found");
            System.out.println(exc.getMessage());
            refresh();
            $(byText(category)).waitUntil(visible, 8000).doubleClick();
        }
        return this;
    }

    public MarketPage selectProductsSubCategory(String subcategory) throws NoSuchElementException {
        try {
            $$x(MarketPageXpaths.subCategoryDom).filter(exactText(subcategory)).first().doubleClick();
            $x("//div//h1").waitUntil(visible, 3000).shouldHave(exactText(subcategory));
        } catch (NoSuchElementException exc) {
            System.out.print("Element by text: " + subcategory + "not found");
            System.out.println(exc.getMessage());
            refresh();
            $(byText(subcategory)).waitUntil(visible, 8000).doubleClick();
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

    public void assertSize(int size) {
        ElementsCollection list = (ElementsCollection) getProductBlocks();
        list.shouldHaveSize(size);
        if (list.size() == size) {
            System.out.println("Number of elements on this page is: " + size);
        } else {
            System.out.println("List size is: " + list.size());
        }
    }

    static class MarketPageXpaths {
        static String categoryDom = ("//a[@class='link topmenu__link']");
        static String subCategoryDom = ("//div[@class='catalog-menu__list']/a");
    }
}
