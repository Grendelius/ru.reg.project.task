package ru.reg.project;


import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import ru.sbtqa.tag.cucumber.TagCucumber;

@RunWith(TagCucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = {"ru.reg.project.stepsdef"},
        features = {"src/test/resources/features/"},
        tags = {"@YmTest"}
)
public class YandexCucumberTest {
}
