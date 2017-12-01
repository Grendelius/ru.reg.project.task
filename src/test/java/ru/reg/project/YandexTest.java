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
                .chooseMarketCategory() // Go to Yandex
                .selectProductsCategory("электроника") // Choose category
                .selectProductsSubCategory("мобильные телефоны") // Choose phones
                .goToAdvancedSearch() // go to Advanced Search
                .setUpPrice(null, 20000) // Set up price
                .setUpPhoneScreenDiagonalPrecisely(3f, null) // Set up phone screen diagonal
                .chooseMakers(makers) // Select makers
                .clickToAccept() // Accept
                .assertSizeOfBlock(12); // Size assert (count of products on the market page)
    }

    @Test(dependsOnMethods = "goAndAssert")
    public void checkAfter() {
        marketPage
                .setFirstProduct() // Set and remember the first phone on the page
                .sortClick("по новизне") // Change type of sorting
                .getFirstAndClickOnIt() // Click on @setFirstProduct
                .ratingShow(); // Show the phone rating
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