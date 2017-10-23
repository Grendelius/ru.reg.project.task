package ru.reg.project;

import org.junit.Before;
import org.junit.Test;
import ru.reg.project.steps.MainYandexPageSteps;


public class YandexTest {
    private MainYandexPageSteps mainYandexPageSteps;


    @Before
    public void init() throws Exception {
        mainYandexPageSteps = new MainYandexPageSteps();
    }

    @Test
    public void cityTest() {
        mainYandexPageSteps.openYandexSearchPage("chrome");
        mainYandexPageSteps.chooseCity("Баку");
    }

}
