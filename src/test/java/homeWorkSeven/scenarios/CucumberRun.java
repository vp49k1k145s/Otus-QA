package homeWorkSeven.scenarios;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
       glue = {"homeWorkSeven/features"},
        features = "classpath:features"
)

public class CucumberRun {
}
