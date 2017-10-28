package ru.reg.project;

import org.junit.Before;
import org.junit.Test;
import ru.reg.project.steps.MainYandexPageSteps;
import ru.reg.project.steps.YandexMarketSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class YandexTest {
    private MainYandexPageSteps mainYandexPageSteps;
    private YandexMarketSteps yandexMarketSteps;
    public List<String> makers = new ArrayList<>(Arrays.asList(
            "Apple", "Samsung", "LG", "OnePlus", "Motorola"));


    @Before
    public void init() throws Exception {
        mainYandexPageSteps = new MainYandexPageSteps();
        yandexMarketSteps = new YandexMarketSteps();

    }

    @Test
    public void test() {
        mainYandexPageSteps
                .openYandexRu("chrome")
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch()
                .setUpPrice(null, 20000)
                .setUpPhoneScreenDiagonalPrecisely(null, 3f)
                .chooseMakers(makers)
                .clickAccept()
                .assertSize(12);
        yandexMarketSteps
                .searchFirstProduct()
                .sortClick("по новизне")
                .getAndClickOnProduct();
        yandexMarketSteps.raitingShow();

    }

}
