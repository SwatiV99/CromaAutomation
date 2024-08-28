package hooks;

import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import main.WebDriverCroma;

import java.io.IOException;
import java.util.logging.Logger;

public class cromaHooks {

    @Before
    public void setup() throws IOException {

        //logger.info("Initializing WebDriver...");
        WebDriverCroma.initializeDriver();
        // logger.info("Driver initialized: " + (WebDriverCroma.driver != null));
    }

    @After
    public void teardown() {
        if (WebDriverCroma.getDriver() != null) {
           // WebDriverCroma.getDriver().quit();
           // logger.info("WebDriver closed.");
        }
    }
}
