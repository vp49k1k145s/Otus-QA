package homeWorkSeven.scenarios;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/homeWorkSeven/features",
        glue = "homeWorkSeven.stepdefs",
        tags = "@all"
)

public class CucumberRun {
}
