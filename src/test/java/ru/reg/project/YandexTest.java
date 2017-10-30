package ru.reg.project;

import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.DataProvider;
import ru.reg.project.pages.MainPage;
import ru.reg.project.pages.MarketPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YandexTest {
    private MainPage mainPage;
    private MarketPage marketPage;


    @DataProvider(name = "TestData")
    public Object[][] makers() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList(
                        "Apple", "Samsung", "LG", "OnePlus", "Motorola"))},
        };
    }


    @Before
    public void init() throws Throwable {
        mainPage = new MainPage();
        marketPage = new MarketPage();
    }

    @Test
    public void test(List<String> makers) {
        mainPage.openYandexRu("chrome")
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch()
                .setUpPrice(10000, 20000)
                .setUpPhoneScreenDiagonalPrecisely(1f, 3f)
                .chooseMakers(makers)
                .clickAccept()
                .assertSize(12);
        marketPage.getFirstProduct()
                .sortClick("по новизне")
                .getAndClickOnProduct();
        marketPage.ratingShow();

    }

}
