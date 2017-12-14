package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import static com.codeborne.selenide.Selenide.$;

@Name("Yandex MainPage user's authorization block")
public class YandexMainPageAuthBlock extends ElementsContainer {

    @Name("User login field")
    @FindBy(xpath = ".//input[@name='login']")
    private SelenideElement loginField;

    @Name("User password field")
    @FindBy(xpath = ".//input[@name='passwd']")
    private SelenideElement pswField;

    @Name("Submit button")
    @FindBy(xpath = ".//button[contains(@class, 'button auth__button')]")
    private SelenideElement sbmBtn;

    public void inputLogin(String login) {
        $(loginField).val(login);
    }

    public void inputPassword(String psw) {
        $(pswField).val(psw);
    }

    public void submitBtnClick() {
        $(sbmBtn).click();
    }
}
