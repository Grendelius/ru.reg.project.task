package ru.reg.project;

import org.junit.Before;
import org.junit.Test;
import ru.reg.project.steps.MainYandexPageSteps;
import ru.reg.project.steps.YandexMarketSteps;


public class YandexTest {
    private MainYandexPageSteps mainYandexPageSteps;
    private YandexMarketSteps yandexMarketSteps;


    @Before
    public void init() throws Exception {
        mainYandexPageSteps = new MainYandexPageSteps();
    }

    @Test
    public void test() {
        mainYandexPageSteps
                .openYandexRu("chrome")
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch()
                .chooseSearchParametr("оптическая стабилизация", true);
    }

}
