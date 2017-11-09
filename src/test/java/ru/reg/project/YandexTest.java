package ru.reg.project;


import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.reg.project.pages.MainPage;
import ru.reg.project.pages.MarketPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Listeners(TextReport.class)

public class YandexTest {
    private MainPage mainPage;
    private MarketPage marketPage;

    @DataProvider(name = "TestData")
    public Object[][] makers() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList(
                        "Apple", "Samsung", "LG", "OnePlus", "Motorola", "Xiaomi", "Ulefone"))},
        };
    }

    @BeforeTest
    public void initialization() {
        mainPage = new MainPage();
        marketPage = new MarketPage();
    }

    @Test(dataProvider = "TestData")
    public void goAndAssert(List<String> makers) {
        mainPage
                .openYandexRu("chrome")
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch() //Initialized new AdvancedSearchPage
                .setUpPrice(null, 20000)
                .setUpPhoneScreenDiagonalPrecisely(3f, null)
                .chooseMakers(makers)
                .clickToAccept()
                .assertSize(12);
    }

    @Test
    public void showProductRating() {
        marketPage
                .getFirstProduct()
                .sortClick("по новизне")
                .getAndClickOnProduct()
                .ratingShow();
    }
}
