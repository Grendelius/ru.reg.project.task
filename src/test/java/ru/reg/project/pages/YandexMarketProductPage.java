package ru.reg.project.pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

@PageEntry(title = "Страница продукта Яндекс")
public class YandexMarketProductPage extends Page {

    @ElementTitle("Наименование продукта")
    @FindBy(xpath = "//h1[@class='title title_size_28 title_bold_yes']")
    private SelenideElement nameOfProduct;

    @ElementTitle("Рейтинг продукта")
    @FindBy(xpath = "//div[contains(@class, 'rating__value')][1]")
    private SelenideElement rating;

    private String getName() {
        return $(nameOfProduct).getText();
    }

    private String getRating() {
        return $(rating).getText();
    }

    public YandexMarketProductPage() {
        page(this);
    }

    @ActionTitle(value = "выводит рейтинг и наименование товара")
    public void showTheProductInfo() {
        System.out.println("Name is: " + getName() + " " + "RATING IS: " + getRating());
    }


}
