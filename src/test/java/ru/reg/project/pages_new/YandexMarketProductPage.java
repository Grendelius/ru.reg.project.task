package ru.reg.project.pages_new;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;

@Name("Yandex Market product page")
public class YandexMarketProductPage {

    @ElementTitle("Name")
    @FindBy(xpath = "//h1[@class='title title_size_28 title_bold_yes']")
    private SelenideElement nameOfProduct;

    @ElementTitle("Rating")
    @FindBy(xpath = "//div[contains(@class, 'rating__value')][1]")
    private SelenideElement rating;

    private String getName() {
        return $(nameOfProduct).getText();
    }

    private String getRating() {
        return $(rating).getText();
    }

    @ActionTitle(value = "Показать рейтинг и наименование товара")
    public void showTheProductInfo() {
        System.out.println("Name is: " + getName() + " " + "RATING IS: " + getRating());
    }


}
