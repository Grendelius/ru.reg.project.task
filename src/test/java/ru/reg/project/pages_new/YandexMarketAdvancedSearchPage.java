package ru.reg.project.pages_new;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.reg.project.blocks.AdvancedSearchPageChoosingMakersBlock;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

@Name("Yandex Market Advanced search page")
public class YandexMarketAdvancedSearchPage {

    @Name("Parameters block")
    @FindBy(xpath = "//span[@class='title__content']")
    private ElementsCollection parametersBlocks;

    @Name("Checkboxes")
    @FindBy(xpath = ".//label[@class='checkbox__label']")
    private ElementsCollection checkboxes;

    @Name("Makers block")
    @FindBy(xpath = "//body/div[1]/div[4]/div/div[1]/div[1]/div[2]")
    private AdvancedSearchPageChoosingMakersBlock makersBlock;

    @Name("'Show filtered' link")
    @FindBy(xpath = "//a[contains(@class, 'button_action_show-filtered')]")
    private SelenideElement showFilteredLink;

    private SelenideElement searchParameterBlock(String parameterName) {
        return $$(parametersBlocks).filter(exactText(parameterName)).first();
    }

    private void openTheBlock(String parameterName) {
        searchParameterBlock(parameterName).click();
    }

    private void clickOnTheCheckbox(String name) {
        $$(checkboxes).filter(exactText(name)).first().click();
    }

    public void setUpPrice(Integer from, Integer to) {
        if (from == null) $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        if (to == null) $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
        if (from != null && to != null) {
            $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
            $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        }
    }

    public void setUpPhoneScreenDiagonalPrecisely(Float from, Float to) {
        openTheBlock("Диагональ экрана (точно), \"");
        if (from == null) $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        if (to == null) $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
        if (from != null && to != null) {
            $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
            $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        }
    }

    public void chooseMakers(List<String> makersList) {
        makersBlock.setUpMakers(makersList);
    }

    public YandexMarketResultPage clickOnShowFiltered() {
        $(showFilteredLink).click();
        return page(YandexMarketResultPage.class);
    }

}
