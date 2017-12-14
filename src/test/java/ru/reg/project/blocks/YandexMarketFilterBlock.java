package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

@Name("Block of filters parameters on the Yandex Market result page")
public class YandexMarketFilterBlock extends ElementsContainer {

    @Name("Filter link")
    @FindBy(xpath = ".//a[contains(@class,'link link_theme_major')]")
    private ElementsCollection filterLinks;

    public void clickOnFilterLink(String filterName) {
        $$(filterLinks).filter(exactText(filterName)).first().click();
    }

}