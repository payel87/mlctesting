import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(strict = true,
features = {"C:/mlctesting/src/test/java/Features/Auspost.feature"},
plugin = {"json:target/cucumber-parallel/1.json"},
monochrome = true,
tags = {"@api"},
glue = { "steps" })
public class Parallel01IT extends AbstractTestNGCucumberTests {
}
