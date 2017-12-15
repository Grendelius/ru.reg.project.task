package ru.reg.project;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.reg.project.pages_new.YandexMarketAdvancedSearchPage;
import ru.reg.project.pages_new.YandexMarketMainPage;
import ru.reg.project.pages_new.YandexMarketProductPage;
import ru.reg.project.pages_new.YandexMarketResultPage;
import ru.reg.project.settngs.Browser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.open;


@Listeners(TextReport.class)

public class YandexTest {
    private YandexMarketMainPage yandexMarketMainPage;
    private YandexMarketResultPage yandexMarketResultPage;
    private YandexMarketAdvancedSearchPage yandexMarketAdvancedSearchPage;
    private YandexMarketProductPage yandexMarketProductPage;

    @DataProvider(name = "TestData")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList("Apple", "Samsung", "LG", "OnePlus", "Motorola", "Xiaomi", "Ulefone"))},
        };
    }

    @BeforeClass
    public void beforeClass() {
        Browser.setChrome();
        yandexMarketMainPage = open(YandexMarketMainPage.PAGE_URL, YandexMarketMainPage.class);
    }

    @Test(dataProvider = "TestData")
    public void yandexMainPageTest(List<String> makersList) {
        yandexMarketResultPage = yandexMarketMainPage
                .goToCategoryAndToSubCategory("Электроника", "Мобильные телефоны");
        yandexMarketAdvancedSearchPage = yandexMarketResultPage.goToAllFilters();
        yandexMarketAdvancedSearchPage.setUpPrice(null, 20000);
        yandexMarketAdvancedSearchPage.setUpPhoneScreenDiagonalPrecisely(3f, null);
        yandexMarketAdvancedSearchPage.chooseMakers(makersList);
        yandexMarketAdvancedSearchPage.clickOnShowFiltered();
        yandexMarketResultPage.getSizeOfProductsList().shouldBe(sizeGreaterThanOrEqual(10));
        yandexMarketResultPage.setLinkFromFirstInList();
        yandexMarketResultPage.sortFilterSet("По новизне");
        yandexMarketProductPage = yandexMarketResultPage.goToFirstProduct();
        yandexMarketProductPage.showTheProductInfo();
    }

//    @Test(dataProvider = "TestData", enabled = false)
//    public void goAndAssert(List<String> makers) {
//        mainPage
//                .chooseMarketCategory() // Go to Yandex
//                .selectProductsCategory("электроника") // Choose category
//                .selectProductsSubCategory("мобильные телефоны") // Choose phones
//                .goToAdvancedSearch() // go to Advanced Search
//                .setUpPrice(null, 20000) // Set up price
//                .setUpPhoneScreenDiagonalPrecisely(3f, null) // Set up phone screen diagonal
//                .chooseMakers(makers) // Select makers
//                .clickToAccept() // Accept filters
//                .assertSizeOfBlock(10); // Size assert (count of products on the market page)
//    }
//
//    @Test(dependsOnMethods = "goAndAssert", enabled = false)
//    public void checkAfter() {
//        marketPage
//                .setFirstProduct() // Set and remember the first phone on the page
//                .sortClick("по новизне") // Change type of sorting
//                .getFirstAndClickOnIt() // Click on @setFirstProduct
//                .ratingShow(); // Show the phone rating
//    }
//
//    @Test(enabled = false)
//    public void blocksUploadTest() {
//        marketPage
//                .selectProductsCategory("электроника")
//                .selectProductsSubCategory("мобильные телефоны")
//                .assertSizeOfBlock(10);
//        marketPage
//                .setFirstProduct();
//        marketPage
//                .sortClick("по новизне")
//                .getFirstAndClickOnIt()
//                .ratingShow();
//    }

}