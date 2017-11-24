package ru.reg.project.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {
    private String rating;

    public String ratingShow() {
        SelenideElement rating = $x("//div[@class='rating__value'][1]");
        SelenideElement productName = $x("//h1[@class='title title_size_28 title_bold_yes']");
        String s = rating.getText();
        System.out.println("Rating value of " + "\"" + (productName.getText()) + "\"" + " is: " + (rating.getText()));
        return s;
    }
}
