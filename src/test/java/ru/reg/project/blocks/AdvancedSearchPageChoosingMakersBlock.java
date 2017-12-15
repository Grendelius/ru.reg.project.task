package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.actions;

@Name("Choosing makers block")
public class AdvancedSearchPageChoosingMakersBlock extends ElementsContainer {

    @ElementTitle("Button")
    @FindBy(xpath = ".//button")
    private SelenideElement button;

    @ElementTitle("Search input field")
    @FindBy(xpath = ".//input[@class='input__control']")
    private SelenideElement searchInputField;

    @ElementTitle("Checkboxes")
    @FindBy(xpath = ".//input[@class='checkbox__control']")
    private ElementsCollection checkboxes;

    public void setUpMakers(List<String> makersList) {
        button.click();
        checkboxes.forEach(element -> makersList.forEach(s -> {
            searchInputField.val(s);
            actions().pause(Duration.ofMillis(500)).build().perform();
            if (element.isEnabled())
                actions().moveToElement(element).click().build().perform();
        }));

    }
}
