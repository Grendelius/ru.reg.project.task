package ru.reg.project.blocks;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;

@Name("Yandex MainPage user's authorization block")
public class YandexMainPageAuthBlock extends ElementsContainer {

    @ElementTitle("User login field")
    @FindBy(xpath = ".//input[@name='login']")
    private SelenideElement loginField;

    @ElementTitle("User password field")
    @FindBy(xpath = ".//input[@name='passwd']")
    private SelenideElement pswField;

    @ElementTitle("Submit button")
    @FindBy(xpath = ".//button[contains(@class, 'button auth__button')]")
    private SelenideElement sbmBtn;

    public void inputLogin(String login) {
        loginField.val(login);
    }

    public void inputPassword(String psw) {
        pswField.val(psw);
    }

    public void submitBtnClick() {
        sbmBtn.click();
    }
}
