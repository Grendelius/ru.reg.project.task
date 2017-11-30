package ru.reg.project.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {

    public void ratingShow() {
        SelenideElement rating = $x("//div[contains(@class, 'rating__value')][1]");
        SelenideElement productName = $x("//h1[@class='title title_size_28 title_bold_yes']");
        rating.shouldBe(Condition.visible);
        System.out.println("Rating value of " + "\"" + (productName.getText()) + "\"" + " is: " + (rating.getText()));
    }
}
