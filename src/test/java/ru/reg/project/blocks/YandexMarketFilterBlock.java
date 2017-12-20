package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.actions;
import static java.time.Duration.ofSeconds;

@Name("Блок фильтрации")
public class YandexMarketFilterBlock extends ElementsContainer {

    @Name("Фильтр-ссылка")
    @FindBy(xpath = ".//a[contains(@class,'link link_theme_major')]")
    private ElementsCollection filterLinks;

    public void clickOnFilterLink(String filterName) {
        filterLinks.filter(exactText(filterName)).first().click();
        actions().pause(ofSeconds(2)).build().perform();
    }

}
