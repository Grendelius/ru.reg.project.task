package ru.reg.project;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.reg.project.pages.YandexMarketMainPage;
import ru.reg.project.pages.YandexMarketResultPage;
import ru.reg.project.settngs.Browser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.open;


@Listeners(TextReport.class)

public class YandexTestNG {

    @DataProvider(name = "TestData")
    public Object[][] data() {
        return new Object[][]{
                {new ArrayList<>(Arrays.asList("Apple", "Samsung", "LG", "OnePlus", "Motorola", "Xiaomi", "Ulefone"))},
        };
    }

    @BeforeClass
    public void beforeClass() {
        Browser.setChrome();
        open(YandexMarketMainPage.PAGE_URL, YandexMarketMainPage.class);
    }

    @Test(dataProvider = "TestData")
    public void yandexMainPageTest(List<String> makersList) {
        new YandexMarketMainPage()
                .goToCategoryAndToSubCategory("Электроника", "Мобильные телефоны")
                .goToAllFilters()
                .setUpPrice(null, 20000)
                .setUpPhoneScreenDiagonalPrecisely(3f, null)
                .chooseMakers(makersList)
                .clickOnShowFiltered()
                .getSizeOfProductsList().shouldBe(sizeGreaterThanOrEqual(10));
        new YandexMarketResultPage()
                .setLinkFromFirstInList()
                .sortFilterSet("По новизне")
                .goToFirstProduct()
                .showTheProductInfo();
    }
}