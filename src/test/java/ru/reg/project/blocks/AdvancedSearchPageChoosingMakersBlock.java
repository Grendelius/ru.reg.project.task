package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Name("Choosing makers block")
public class AdvancedSearchPageChoosingMakersBlock extends ElementsContainer {

    @Name("Button")
    @FindBy(xpath = ".//button")
    private SelenideElement button;

    @Name("Search input field")
    @FindBy(xpath = ".//input[@class='input__control']")
    private SelenideElement searchInputField;

    @Name("Checboxes")
    @FindBy(xpath = ".//input[@class='checkbox__control']")
    private ElementsCollection checkboxes;

    public void setUpMakers(List<String> makersList) {
        $(button).click();
        $$(checkboxes).forEach(selenideElement -> makersList.forEach(s -> {
            $(searchInputField).val(s);
            if (selenideElement.isEnabled()) {
                zoom(0.5);
                actions().moveToElement(selenideElement).click().build().perform();
            }
        }));
    }

}
