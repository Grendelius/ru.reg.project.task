package ru.reg.project;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeClass;
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
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList("Apple", "Samsung", "LG", "OnePlus", "Motorola", "Xiaomi", "Ulefone"))}
        };
    }

    @BeforeClass
    public void beforeClass() {
        mainPage = new MainPage("chrome");
        marketPage = new MarketPage();
    }

    @Test(dataProvider = "TestData")
    public void goAndAssert(List<String> makers) {
        mainPage
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch() //Initialized new AdvancedSearchPage
                .setUpPrice(null, 20000)
                .setUpPhoneScreenDiagonalPrecisely(3f, null)
                .chooseMakers(makers)
                .clickToAccept()
                .assertSizeOfBlock(12);
    }

    @Test(dependsOnMethods = "goAndAssert")
    public void checkAfter() {
        marketPage
                .setFirstProduct()
                .sortClick("по новизне")
                .getFirstAndClickOnIt()
                .ratingShow();
    }

    @Test(enabled = false)
    public void blocksUploadTest() {
        marketPage
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .assertSizeOfBlock(12);
        marketPage
                .setFirstProduct();
        marketPage
                .sortClick("по новизне")
                .getFirstAndClickOnIt()
                .ratingShow();
    }

}