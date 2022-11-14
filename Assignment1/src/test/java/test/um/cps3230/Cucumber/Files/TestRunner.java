package test.um.cps3230.Cucumber.Files;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/test/um/cps3230/Cucumber/resources/Task2.feature"
)
public class TestRunner {


}

