package ru.reg.project.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

abstract class AbstractSteps {

    private List<ElementsCollection> productsBlocks;
    private SelenideElement productBlock;
    private SelenideElement field;
    private SelenideElement checkbox;

    public List<ElementsCollection> getProductsBlocks() {
        return productsBlocks;
    }

    public void setProductsBlocks(List<ElementsCollection> productsBlocks) {
        ElementsCollection upBlck = $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[1]/div");
        ElementsCollection bottomBlck = $$x("/html/body/div[1]/div[4]/div[2]/div[1]/div[2]/div/div[3]/div");
        productsBlocks.add(upBlck);
        productsBlocks.add(bottomBlck);
        this.productsBlocks = productsBlocks;
    }

    public SelenideElement getProduct() {
        return productBlock;
    }

    public void setProduct(SelenideElement product) {
        this.productBlock = product;
    }

    public SelenideElement getField() {
        return field;
    }

    public void setField(SelenideElement field) {
        this.field = field;
    }

    public SelenideElement getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(SelenideElement checkbox) {
        this.checkbox = checkbox;
    }

    public void setUpPrice(String priceFrom, String priceTo) {
        $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(priceFrom);
        $x("//div[@class='layout__col layout__col_search-results_normal i-bem']")
                .waitUntil(enabled, 2500);
        $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(priceTo);
        $x("//div[@class='layout__col layout__col_search-results_normal i-bem']")
                .waitUntil(enabled, 2500);
    }

    public void setUpOnSaleCheckBox(boolean decision) {
        SelenideElement checkbox = $(byText("В продаже")).shouldBe(enabled);
        executeJavaScript(decision ? "arguments[1].click()" : "arguments[0].click()", $(checkbox));
    }

    public AdvancedSearchSteps goToAdvancedSearch() {
        $x("//div[@class='n-filter-panel-aside__show-more']/a").shouldBe(visible).click();
        $x("//body/div[1]/div[3]/div/h1").shouldBe(visible).shouldHave(exactText("Все фильтры"));
        return (new AdvancedSearchSteps());
    }


}

