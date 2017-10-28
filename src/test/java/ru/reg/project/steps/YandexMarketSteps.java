package ru.reg.project.steps;

// TODO: 1 переход в Расширенный поиск, параметры поиска(1 have) и изменение параметров фильтрации
// TODO: 2 реализовать класс абстрактной корневой страницы(категориальной) маркета с выгрузкой блоков товара в коллекцию

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketSteps extends AdvancedSearchSteps {

    private SelenideElement product;
    private List<SelenideElement> productsBlock;

    public SelenideElement getOneProduct() {
        return product;
    }

    private List<SelenideElement> getProductBlocks() {
        List<SelenideElement> list = new ArrayList<>();
        List<SelenideElement> upBlck =
                $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[1]/div/a");
        List<SelenideElement> bottomBlck =
                $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[3]/div/a");
        upBlck.stream().forEachOrdered(element -> list.add(element));
        bottomBlck.stream().forEachOrdered(element -> list.add(element));
        this.productsBlock = list;
        return productsBlock;
    }

    public YandexMarketSteps searchFirstProduct() {
        SelenideElement element = getProductBlocks().stream().findFirst().get();
        product = element;
        return this;
    }

    public YandexMarketSteps sortClick(String sortName) {
        $$x("/html/body/div[1]/div[4]/div[1]/div[2]/div[1]/div[1]/div/a").forEach(element -> {
            if (sortName.equalsIgnoreCase(element.getText())) {
                actions().moveToElement(element).click().build().perform();
            }
        });
        return this;
    }

    public void getAndClickOnProduct() {
        $(product).click();
        $x("/html/body/div[1]/div[4]/div[2]/div[2]/div/div[1]/div[1]/h1")
                .waitUntil(visible, 3000);

    }

    public void raitingShow() {
        SelenideElement raiting = $x("//div[@class='rating__value'][1]");
        SelenideElement productName = $x("//h1[@class='title title_size_28 title_bold_yes']");
        System.out.println("Raiting value of " + (productName.getText()) + " is: " + (raiting.getText()));
    }

    public YandexMarketSteps selectProductsCategory(String category) throws NoSuchElementException {
        /* //html/body/div[4]/div[1]/div */
        try {
            $$x(MarketPageXpaths.categoryDom).forEach(element -> {
                if (category.equalsIgnoreCase(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                }
            });
//        $x("//div//h1")
//                .waitUntil(visible, 10000).shouldHave(exactText(category));
        } catch (NoSuchElementException exc) {
            SelenideElement el = $x(MarketPageXpaths.ALL_CAT_BTN);
            el.waitUntil(enabled, 3000);
            actions().moveToElement(el).click().build().perform();
            $$x("/html/body/div[4]/div[1]/div").stream().forEachOrdered(element -> {
                if (category.equalsIgnoreCase(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                }
            });
        }
        return this;
    }

    public YandexMarketSteps selectProductsSubCategory(String subcategory) throws NoSuchElementException {
        /* /html/body/div[4]/div[1]/div[7]/div/div/div[2]/div[1]/div/a */
        try {
            $$x(MarketPageXpaths.subCategoryDom).stream().forEach(element -> {
                if (subcategory.equalsIgnoreCase(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                }
            });
//        $x("//div//h1")
//                .waitUntil(visible, 3000).shouldHave(exactText(subcategory));
        } catch (java.util.NoSuchElementException exc) {
            $$x(MarketPageXpaths.SUB_CAT_FIELD).stream().forEach(element -> {
                if (subcategory.equalsIgnoreCase(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                }
            });
        }
        return this;
    }

    public AdvancedSearchSteps goToAdvancedSearch() {
        $x("//div[@class='n-filter-panel-aside__show-more']/a").shouldBe(visible).click();
        $x("//body/div[1]/div[3]/div/h1").shouldBe(visible).shouldHave(exactText("Все фильтры"));
        return (new AdvancedSearchSteps());
    }

    public boolean assertSize(int size) {
        return (getProductBlocks().size()) == size;
    }

    static class MarketPageXpaths {
        static final String ALL_CAT_BTN = ("/html/body/div[1]/div[2]/div[1]/div/div[1]/div/button/span/text()='Все категории'");
        static final String SUB_CAT_FIELD = ("/html/body/div[4]/div[1]/div[15]/div/div/div[2]/div[1]/div/a");
        static String categoryDom = ("//a[@class='link topmenu__link']");
        static String subCategoryDom = ("//div[@class='catalog-menu__list']/a");
    }

}
