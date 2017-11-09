package ru.reg.project.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {
    private MarketPage marketPageSteps;

    public void ratingShow() {
        SelenideElement raiting = $x("//div[@class='rating__value'][1]");
        SelenideElement productName = $x("//h1[@class='title title_size_28 title_bold_yes']");
        System.out.println("Rating value of " + (productName.getText()) + " is: " + (raiting.getText()));
    }
}
