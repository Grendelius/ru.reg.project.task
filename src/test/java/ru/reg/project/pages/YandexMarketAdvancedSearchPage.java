package ru.reg.project.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.AdvancedSearchPageChoosingMakersBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

@PageEntry(title = "Yandex Market Advanced search page")
public class YandexMarketAdvancedSearchPage extends Page {

    @ElementTitle("Блоки с параметрами")
    @CacheLookup
    @FindBy(xpath = "//span[@class='title__content']")
    private ElementsCollection parametersBlocks;

    @ElementTitle("Чекбоксы на странице")
    @CacheLookup
    @FindBy(xpath = ".//label[@class='checkbox__label']")
    private ElementsCollection checkboxes;

    @ElementTitle("Блок с выбором производителей")
    @FindBy(xpath = "//body/div[1]/div[4]/div/div[1]/div[1]/div[2]")
    private AdvancedSearchPageChoosingMakersBlock makersBlock;

    @ElementTitle("Показать подходящие")
    @FindBy(xpath = "//a[contains(@class, 'button_action_show-filtered')]")
    private SelenideElement showFilteredLink;

    public YandexMarketAdvancedSearchPage() {
        page(this);
    }

    private SelenideElement searchParameterBlock(String parameterName) {
        return parametersBlocks.filter(exactText(parameterName)).first();
    }

    private void openTheBlock(String parameterName) {
        searchParameterBlock(parameterName).click();
    }

    private void clickOnTheCheckbox(String name) {
        checkboxes.filter(exactText(name)).first().click();
    }

    @ActionTitle(value = "устанавливает цену в диапазоне")
    public YandexMarketAdvancedSearchPage setUpPrice(Integer from, Integer to) {
        if (from == null) $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        if (to == null) $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
        if (from != null && to != null) {
            $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
            $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        }
        return this;
    }

    @ActionTitle(value = "устанавливает размер диагонали экрана точно в диапазоне")
    public YandexMarketAdvancedSearchPage setUpPhoneScreenDiagonalPrecisely(Float from, Float to) {
        openTheBlock("Диагональ экрана (точно), \"");
        if (from == null) $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        if (to == null) $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
        if (from != null && to != null) {
            $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
            $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        }
        return this;
    }

    @ActionTitle(value = "выбирает производителей")
    public YandexMarketAdvancedSearchPage chooseMakers(List<String> makersList) {
        makersBlock.setUpMakers(makersList);
        return this;
    }

    @ActionTitle(value = "показывает подходящие")
    public YandexMarketResultPage clickOnShowFiltered() {
        $(showFilteredLink).click();
        return page(YandexMarketResultPage.class);
    }

}
