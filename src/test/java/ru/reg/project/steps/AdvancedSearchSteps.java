package ru.reg.project.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class AdvancedSearchSteps extends AbstractSteps {
    private ElementsCollection pBlocks = $$x("//h4[@class='title']");
    private SelenideElement checkBoxes =
            $x(".//*[@class='radiobox__radio i-bem n-filter-block__item n-filter-block__item_js_inited']");

    public AdvancedSearchSteps chooseSearchParametr(String pName, boolean decision) {

        pBlocks.stream().forEach(element -> {
            if (element.getText().equalsIgnoreCase(pName)) {
                actions().moveToElement(element).click().build().perform();
                if (decision) {
                    checkBoxes.isSelected();
                }
//                actions().moveToElement(element).click().build().perform();
            }
        });


        return this;
    }

}
