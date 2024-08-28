package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/resources/features/cromaFeature.feature",
            glue = {"stepDefinition", "hooks"}
            /* dryRun = false,
             plugin = {"pretty", "html:target/cucumber-reports"},
             monochrome = true*/


    )
    public class cromaTestRunner {
}
