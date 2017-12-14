package ru.reg.project.pages_new;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;

@Name("Yandex Market product page")
public class YandexMarketProductPage {

    @Name("Name")
    @FindBy(xpath = "//h1[@class='title title_size_28 title_bold_yes']")
    private SelenideElement nameOfProduct;

    @Name("Rating")
    @FindBy(xpath = "//div[contains(@class, 'rating__value')][1]")
    private SelenideElement rating;

    private String getName() {
        return $(nameOfProduct).getText();
    }

    private String getRating() {
        return $(rating).getText();
    }

    public void showTheProductInfo() {
        System.out.println("Name is: " + getName() + " " + "RATING IS: " + getRating());
    }


}
