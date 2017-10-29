package ru.reg.project;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Также;
import ru.reg.project.steps.AdvancedSearchSteps;
import ru.reg.project.steps.MainYandexPageSteps;
import ru.reg.project.steps.YandexMarketSteps;

public class Mystepdefs {
    private MainYandexPageSteps mainYandexPageSteps;
    private YandexMarketSteps yandexMarketSteps;
    private AdvancedSearchSteps advancedSearchSteps;

    @Дано("^переходим на веб-сайт Яндекса, используя веб-браузер: (.*)$")
    public void openYandexMainPage(String browserName) {
        this.mainYandexPageSteps = new MainYandexPageSteps();
        mainYandexPageSteps.openYandexRu(browserName);
    }

    @И("^выбираем \"Маркет\"$")
    public void chooseMarketCategory() {
        mainYandexPageSteps.chooseMarketCategory();
    }

    @И("^выбираем раздел товаров: \"[^\\\"]*\"$")
    public void chooseCategory(String categoryName) {
        this.yandexMarketSteps = new YandexMarketSteps();
        yandexMarketSteps.selectProductsCategory(categoryName);
    }

    @Также("выбираем подраздел: \"[^\\\"]*\"")
    public void chooseSubCategory(String subName) {
        yandexMarketSteps.selectProductsSubCategory(subName);
    }

    @И("^переходим в расширенный поисковой фильтр$")
    public void goToAdvancedSearch() {
        yandexMarketSteps.goToAdvancedSearch();
    }

    @И("^устанавливаем ценовой диапазон: от (.*) до (.*)$")
    public void setUpPrice(Integer from, Integer to) {
        this.advancedSearchSteps = new AdvancedSearchSteps();
        advancedSearchSteps.setUpPrice(from, to);
    }

}
