package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Selenide.actions;

@Name("Блок выбора производителей")
public class AdvancedSearchPageChoosingMakersBlock extends ElementsContainer {

    @Name("Развернуть")
    @FindBy(xpath = ".//button")
    private SelenideElement button;

    @Name("Поле поиска производителя")
    @FindBy(xpath = ".//input[@class='input__control']")
    private SelenideElement searchInputField;

    @Name("Чекбоксы")
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
