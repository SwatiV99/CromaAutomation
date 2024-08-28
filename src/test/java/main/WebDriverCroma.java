package main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverCroma {
    //global declaration for driver
    public static ChromeDriver driver;
    public static Properties properties = new Properties();

    // Private constructor to prevent instantiation
    protected WebDriverCroma() {
    }

    // Method to get the single instance of WebDriver
    public static WebDriver getDriver() {
        if (driver == null) {
            synchronized (WebDriverCroma.class) {
                if (driver == null) {
                    try {
                        initializeDriver();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to initialize driver", e);
                    }
                }
            }
        }
        return driver;
    }

    // Method to get the properties
    public static Properties getProperties() {
        if (properties.isEmpty()) {
            synchronized (WebDriverCroma.class) {
                if (properties.isEmpty()) {
                    try {
                        loadProperties("src/test/resources/configuarations/config.properties");
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Failed to load properties", e);
                    }
                }
            }
        }
        return properties;
    }

    // Method to initialize the WebDriver
    public static void initializeDriver() throws IOException {
        getProperties();//Ensure properties are getting loaded
        String browser = properties.getProperty("browserName");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            // You can add more browser cases here if needed
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }


    // Method to load properties from the configuration file
    public static Properties loadProperties(String path) throws IOException {
        try (InputStream inputStream = new FileInputStream(path)) {
            properties.load(inputStream);
        }
        return properties;
    }

    // Method to quit the WebDriver instance
    public static void quitDriver() {
       /* if (driver != null) {
            driver.quit();
            driver = null;
        }*/
    }
}
