package ru.reg.project.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchSteps extends AbstractSteps {
    private List<String> screenSizeValues = new ArrayList<>(Arrays.asList("до 2\"", "2.1\"-2.9\"", "3\"-4\""));
    private SelenideElement divBlock;

    public void pullOutSearchParametr(String pName) {
        ElementsCollection pBlocks = $$x("//h4[@class='title']");
        pBlocks.forEach(element -> {
            if (pName.equalsIgnoreCase(element.getText())) {
                element.setSelected(true);
            }
        });
    }

    public AdvancedSearchSteps setUpPrice(Integer from, Integer to) {
        if (from == null) $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        if (to == null) $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
//        if (from != null && to != null) {
//            $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
//            $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
//        }
        return this;
    }

    public AdvancedSearchSteps setUpPhoneScreenDiagonal(String size) throws NoSuchElementException {
        searchParametrBlock("Диагональ экрана").click();
        try {
            $$x("//label[@class='checkbox__label']").forEach(element -> {
                for (String p : screenSizeValues)
                    if (size.equalsIgnoreCase(p) && p.equalsIgnoreCase(element.getText())) {
                        element.click();
                    }
            });
        } catch (NoSuchElementException exc) {
            $$x("//span[@class='n-filter-enum-sorted__value']").forEach(element -> {
                for (String p : screenSizeValues) {
                    if (size.equalsIgnoreCase(p) && p.equalsIgnoreCase(element.getText())) {
                        element.click();
                    }
                }
            });
        }
        return this;
    }

    public AdvancedSearchSteps setUpPhoneScreenDiagonalPrecisely(Float from, Float to) {
        searchParametrBlock("Диагональ экрана (точно), \"").click();
        if (from == null) $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        if (to == null) $("glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
        return this;
    }

    public AdvancedSearchSteps setUpOnSaleCheckBox(boolean click) {
        SelenideElement checkbox = $(byText("В продаже")).shouldBe(enabled);
        executeJavaScript(click ? "arguments[1].click()" : "arguments[0].click()", $(checkbox));
        return this;
    }

    public AdvancedSearchSteps chooseMakers(List<String> makers) {
        SelenideElement parentDiv = $x("/html/body/div[1]/div[4]/div/div[1]/div[1]/div[2]");
        parentDiv.find(byXpath(".//button")).click();
        SelenideElement inputfield = parentDiv.find(byXpath(".//input[@class='input__control']"));
        ElementsCollection checkboxes = parentDiv.findAll(byXpath(".//input[@class='checkbox__control']"));
        checkboxes.forEach(element -> {
            for (String s : makers) {
                zoom(2);
                inputfield.val(s);
                actions().pause(Duration.ofSeconds(2)).build().perform();
                if (element.isEnabled()) {
                    actions().moveToElement(element).click().build().perform();
                }
            }
        });
        return this;
    }

    public YandexMarketSteps clickAccept() {
        $x("/html/body/div[1]/div[4]/div/div[1]/div[5]/a[2]/span").click();
        return (new YandexMarketSteps());
    }


}
