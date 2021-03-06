package ru.reg.project.pages_old;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

@Deprecated
public class AdvancedSearchPage extends Page {
    private SelenideElement parameter;

    private SelenideElement getParameter() {
        return parameter;
    }

    private void setParameter(SelenideElement parameter) {
        this.parameter = parameter;
    }

    private SelenideElement searchParameterBlock(String name) {
        setParameter($$x("//span[@class='title__content']").filter(exactText(name)).first());
        return getParameter();
    }

    private void clickOnCheckbox(String pName) throws NoSuchElementException {
        try {
            SelenideElement checkbox = $$x("//label[@class='checkbox__label']")
                    .filter(exactText(pName)).first();
            executeJavaScript("arguments[1].click()", checkbox);
        } catch (NoSuchElementException exc) {
            SelenideElement checkbox = $$x("//span[@class='n-filter-enum-sorted__value']")
                    .filter(exactText(pName)).first();
            actions().moveToElement(checkbox).click().build().perform();
        }
    }

    public AdvancedSearchPage setUpPrice(Integer from, Integer to) {
        if (from == null) $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        if (to == null) $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
        if (from != null && to != null) {
            $x("//input[@id='glf-pricefrom-var']").shouldBe(enabled).val(Integer.toString(from));
            $x("//input[@id='glf-priceto-var']").shouldBe(enabled).val(Integer.toString(to));
        }
        return this;
    }

    AdvancedSearchPage setUpPhoneScreenDiagonal(String size) throws NoSuchElementException {
        List<String> sizesList = new ArrayList<>(Arrays.asList("до 2\"", "2.1\"-2.9\"", "3\"-4\"", "4.1\"-4.9\""));
        try {
            searchParameterBlock("Диагональ экрана").click();
            $$x(".//label[@class='checkbox__label']").forEach(element -> {
                if (sizesList.stream().anyMatch(p -> size.equalsIgnoreCase(p) && p.equalsIgnoreCase(element.getText())))
                    actions().moveToElement(element).click().pause(Duration.ofSeconds(1)).build().perform();
            });
        } catch (NoSuchElementException exc) {
            $$x("//span[@class='n-filter-enum-sorted__value']").forEach(element -> {
                if (sizesList.stream().anyMatch(p -> size.equalsIgnoreCase(p) && p.equalsIgnoreCase(element.getText())))
                    actions().moveToElement(element).click().pause(Duration.ofSeconds(1)).build().perform();
            });
        }
        return this;
    }

    public AdvancedSearchPage setUpPhoneScreenDiagonalPrecisely(Float from, Float to) {
        searchParameterBlock("Диагональ экрана (точно), \"").click();
        if (from == null) $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        if (to == null) $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
        if (from != null && to != null) {
            $("#glf-4925721-from").shouldBe(enabled).val(Float.toString(from));
            $("#glf-4925721-to").shouldBe(enabled).val(Float.toString(to));
        }
        return this;
    }

    public AdvancedSearchPage setUpOnSaleCheckBox(boolean click) {
        SelenideElement checkbox = $(byText("В продаже")).shouldBe(enabled);
        executeJavaScript(click ? "arguments[1].click()" : "arguments[0].click()", $(checkbox));
        return this;
    }

    public AdvancedSearchPage chooseMakers(List<String> makers) {
        SelenideElement parentDiv = $x("/html/body/div[1]/div[4]/div/div[1]/div[1]/div[2]");
        parentDiv.find(byXpath(".//button")).click();
        SelenideElement inputfield = parentDiv.find(byXpath(".//input[@class='input__control']"));
        ElementsCollection checkboxes = parentDiv.findAll(byXpath(".//input[@class='checkbox__control']"));
        checkboxes.forEach(element -> {
            makers.forEach(s -> {
                zoom(1.5);
                inputfield.val(s);
                actions().pause(Duration.ofMillis(500)).build().perform();
                if (element.isEnabled())
                    actions().moveToElement(element).click().build().perform();
            });
        });
        return this;
    }

    public MarketPage clickToAccept() {
        $x("/html/body/div[1]/div[4]/div/div[1]/div[5]/a[2]")
                .waitUntil(enabled, 4000).click();
        $x("/html/body/div[1]/div[3]/div[2]/div[2]/h1")
                .waitUntil(visible, 4000);
        return (new MarketPage());
    }


}
