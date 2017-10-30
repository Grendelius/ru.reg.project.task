package ru.reg.project.steps;

import com.codeborne.selenide.SelenideElement;

public interface AbstractSteps {

    SelenideElement searchParameterBlock(String name);

    void clickOnCheckbox(String parametr);


}
