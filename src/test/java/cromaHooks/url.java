package cromaHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static Constants.cromaConstants.*;
import static org.junit.Assert.assertEquals;

public class url {
    private WebDriver driver;

    @Before
    public void setup() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get(PORT);
            driver.manage().window().maximize();

        }
    }

    @Test
    public void testAPIWithSelenium() {
        // Extract data from the Selenium-driven page
        WebElement element = driver.findElement(By.cssSelector("span[ng-bind='shipmentLine.OrderLine.ItemDetails.ItemID']"));
        String extractedData = element.getText();
        RestAssured.baseURI = BaseURL;
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + extractedData)  // Example of passing extracted data
                .get("/endpoint");

        // Assert the response
        assertEquals(200, response.getStatusCode());
        System.out.println("Response: " + response.getBody().asString());
    }

    @After
    public void tearDown() {
     /* if (driver != null) {
            driver.quit();
        }*/
    }

    public WebDriver getDriver() {
        return driver;
    }
}
