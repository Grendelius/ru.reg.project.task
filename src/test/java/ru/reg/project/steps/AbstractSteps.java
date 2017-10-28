package ru.reg.project.steps;

import com.codeborne.selenide.SelenideElement;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;

public abstract class AbstractSteps {
    private static SelenideElement divBlock;

    public static SelenideElement searchParametrBlock(String name) {
        $$x("//span[@class='title__content']").forEach(element -> {
            if (name.equalsIgnoreCase(element.getText())) {
                divBlock = element;
            }
        });
        return divBlock;
    }

    private void clickOnCheckbox(String parametr) throws NoSuchElementException {
        try {
            $$x("//label[@class='checkbox__label']").forEach(element -> {
                if (parametr.contentEquals((element.getText()))) {
                    executeJavaScript("arguments[1].click()", element);
                }
            });
        } catch (NoSuchElementException exc) {
            $$x("//span[@class='n-filter-enum-sorted__value']").forEach(element -> {
                if (parametr.contentEquals(element.getText())) {
                    actions().moveToElement(element).click().build().perform();
                }
            });
        }
    }


}
