package ru.reg.project;

import com.codeborne.selenide.testng.TextReport;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.reg.project.pages.MainPage;
import ru.reg.project.pages.MarketPage;
import ru.reg.project.pages.ProductPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Listeners(TextReport.class)

public class YandexTest {
    private MainPage mainPage;
    private MarketPage marketPage;
    private ProductPage productPage;

    @DataProvider(name = "TestData", parallel = true)
    public Object[][] data() {
        return new Object[][]{
                {(new ArrayList<>(Arrays.asList("Apple", "Samsung", "LG", "OnePlus", "Motorola", "Xiaomi", "Ulefone")))},
                {(new ArrayList<>(Arrays.asList("Meizu", "LG", "Nokia", "Motorola", "Xiaomi", "Sony")))}
        };
    }

    @BeforeClass
    public void beforeClass() {
        mainPage = new MainPage();
        marketPage = new MarketPage();
        productPage = new ProductPage();
    }

    @AfterClass
    public void afterClass() {
        mainPage = null;
        marketPage = null;
        productPage = null;
    }


    @Test(dataProvider = "TestData")
    public void goAndAssert(List<String> makers) {
        mainPage
                .openYandexRu()
                .chooseMarketCategory()
                .selectProductsCategory("электроника")
                .selectProductsSubCategory("мобильные телефоны")
                .goToAdvancedSearch() //Initialized new AdvancedSearchPage
                .setUpPrice(null, 20000)
                .setUpPhoneScreenDiagonalPrecisely(3f, null)
                .chooseMakers(makers)
                .clickToAccept()
                .assertSize(12);
        marketPage
                .getFirstProduct()
                .sortClick("по новизне")
                .getAndClickOnProduct();
        Assert.assertNotNull(productPage.ratingShow());

    }
}
