package stepDefinition;

import cromaHooks.url;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.exception.XmlPathException;
import io.restassured.response.Response;
import org.apache.poi.sl.usermodel.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import static Constants.cromaConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stepDefinition.xmlPayload.getReturnOrderDetails;

public class cromaStoreApplication {
    HomePageObject homePage = new HomePageObject();
    private WebDriver driver;
    private WebDriverWait wait;
    private int initialTabCount;
    private String retrievedCity;

    // Constructor to receive WebDriver instance from Hooks
    public cromaStoreApplication(url pre) {
        this.driver = pre.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofMinutes(2));
    }


    @Given("I have a valid Order Number which is ready to pick")
    public void i_have_a_valid_order_number_which_is_ready_to_pick() {

    }
    @When("I will login to the store application for that ship node")
    public void i_will_login_to_the_store_application_for_that_ship_node() throws InterruptedException {
       /* try {
            WebElement Username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USERNAME)));
            Username.click();
            Username.sendKeys(SHIP_NODE);

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD)));
            passwordField.click();
            passwordField.sendKeys(SHIP_NODE);

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGIN)));
            loginButton.click();

            System.out.println("Logged In to Store Application");
            System.out.println(ANSI_YELLOW + "STEP1: Order Status: SALE" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "STEP2: Order Status: Allocated" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "STEP3: Order Status: Ready to Pick" + ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error logging into Store Application: " + e.getMessage());
        }*/


        homePage.login();

       /* WebElement storeHomePage = driver.findElement(By.xpath(STORE_HOME_PAGE_TEXT));
        assertTrue(storeHomePage.isDisplayed());
        WebElement EnterOrderNumberForRTP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ORDER_NUMBER_FOR_RTS)));
        EnterOrderNumberForRTP.click();
        EnterOrderNumberForRTP.sendKeys(ORDER_NUMBER);
        System.out.println("Order Number : "+ORDER_NUMBER);

        WebElement searchOrderNumberForRTS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_ORDER_NUMBER_FOR_RTS)));
        searchOrderNumberForRTS.click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"bodyTagId\"]/div[1]/div/div/footer/div/button[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(PRINT_AWB_BUTTON)).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Get the shadow root of <print-preview-app>
        WebElement shadowHost1 = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("print-preview-app")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowRoot1 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost1);

        // Debugging output
        System.out.println("Shadow root 1 found: " + (shadowRoot1 != null));

        // Get the shadow root of <print-preview-sidebar>
        WebElement shadowHost2 = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("print-preview-sidebar")));
        WebElement shadowRoot2 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost2);

        // Debugging output
        System.out.println("Shadow root 2 found: " + (shadowRoot2 != null));

        // Get the shadow root of <print-preview-button-strip>
        WebElement shadowHost3 = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("print-preview-button-strip")));
        WebElement shadowRoot3 = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost3);

        // Debugging output
        System.out.println("Shadow root 3 found: " + (shadowRoot3 != null));

        // Locate the cancel button
        WebElement cancelButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("cr-button.cancel-button")));
        cancelButton.click();

*/




    }
    @When("By entering order number I will select the shipment to fulfill")
    public void by_entering_order_number_i_will_select_the_shipment_to_fulfill() {
      homePage.RTS();
    }
    @Then("After validating the Item Id I will do Pick all shipments and finish picking shipment")
    public String after_validating_the_item_id_i_will_do_pick_all_shipments_and_finish_picking_shipment() throws InterruptedException {
        /*WebElement pickOrderHomePage = driver.findElement(By.xpath(PICK_ORDER_HOME_PAGE));
        assertTrue(pickOrderHomePage.isDisplayed());

        Thread.sleep(5000);
        WebElement itemID = driver.findElement(By.xpath("//span[contains(@class, 'comapps-label')][2]"));
        String extractedData = itemID.getText();
        System.out.println("ItemID : "+extractedData);

        // Use extracted data in the API call
        *//*RestAssured.baseURI = BaseURL;
        RestClient restClient = new RestClient();
        Response getOrderResponse = restClient.postRequest(GET_ORDER_DETAILS, getReturnOrderDetails(ORDER_NUMBER));

        String rawXmlResponse = getOrderResponse.asString();
        System.out.println("Raw XML Response:");
        System.out.println(rawXmlResponse);
        try {
            XmlPath getROResDetails = new XmlPath(rawXmlResponse);
            String itemIDResponse = getROResDetails.getString("Order.OrderLines.OrderLine.Item.@ItemID");
            System.out.println("Extracted ItemID: " + itemIDResponse);
        } catch (XmlPathException e) {
            System.err.println("Failed to extract ItemID due to XML parsing error: " + e.getMessage());
            // Handle the error as needed
        }*/
        //return extractedData;
        return "";
    }
    @Then("Order line should move to {string}")
    public void order_line_should_move_to(String string) {

    }
    @When("I do Continue Packing by updating carrier and volumetric")
    public void i_do_continue_packing_by_updating_carrier_and_volumetric() {

    }
    @When("Finish Pack packing")
    public void finish_pack_packing() {
        homePage.Packed();
    }
    @Then("After generating AWB I will do print invoice")
    public void after_generating_awb_i_will_do_print_invoice() throws InterruptedException, AWTException {
        homePage.CONNECT_TO_CARRIER();
    }
    @When("I click on View All Shipment Button and Filter by providing Shipment Details")
    public void i_click_on_view_all_shipment_button_and_filter_by_providing_shipment_details() {

    }
    @Then("Order should be displayed in Shipment summary page")
    public void order_should_be_displayed_in_shipment_summary_page() {
        /*WebElement storeHomePage = driver.findElement(By.xpath(STORE_HOME_PAGE_TEXT));
        assertTrue(storeHomePage.isDisplayed());*/

    }
    @When("I click on Handover to Shipment Button")
    public void i_click_on_handover_to_shipment_button() {
       homePage.HANDOVER_TO_SHIPMENT();
    }
    @When("i Select Delivery in Progress Button")
    public void i_select_delivery_in_progress_button() {
       homePage.DELIVERY_IN_PROGRESS();
    }
    @When("i Select Out for Delivery Button")
    public void i_select_out_for_delivery_button() {
       homePage.DELIVERY_IN_PROGRESS();
    }
    @When("i select Delivered Button")
    public void i_select_delivered_button() {
        homePage.DELIVERED();
    }



}
