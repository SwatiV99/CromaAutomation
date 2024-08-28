package pages;

import main.WebDriverCroma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import static Constants.cromaConstants.*;
import static Constants.cromaConstants.LOGIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageObject extends WebDriverCroma {
    private WebDriverWait wait;
    private int initialTabCount;
    private String retrievedCity;

    public HomePageObject(){
        super();
        PageFactory.initElements(driver,this);
        this.wait = new WebDriverWait(driver, Duration.ofMinutes(2));
    }

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(xpath = "//div[@class='form-group']//input[@id='password']")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"loginBtn\"]")
    WebElement loginButton;

    public void login() throws InterruptedException {
        System.out.println(driver);
        driver.get(properties.getProperty("BASE_URL"));
        driver.manage().window().maximize();
        Thread.sleep(4000);
        usernameField.click();
        usernameField.sendKeys(SHIP_NODE);
        passwordField.click();
        passwordField.sendKeys(SHIP_NODE);
        loginButton.click();
    }
    public void RTS(){
        WebElement storeHomePage = driver.findElement(By.xpath(STORE_HOME_PAGE_TEXT));
        assertTrue(storeHomePage.isDisplayed());
        WebElement EnterOrderNumberForRTP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ORDER_NUMBER_FOR_RTS)));
        EnterOrderNumberForRTP.click();
        EnterOrderNumberForRTP.sendKeys(ORDER_NUMBER);
        System.out.println("Order Number : "+ORDER_NUMBER);

        WebElement searchOrderNumberForRTS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_ORDER_NUMBER_FOR_RTS)));
        searchOrderNumberForRTS.click();
    }
    public void Packed(){
        WebElement pickAllButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLICK_ON_PICK_ORDER_BUTTON)));
        pickAllButton.click();
        System.out.println("Order is Picked");

        WebElement PickAllOKButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PICK_OK_BUTTON)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        PickAllOKButton.click();

        WebElement finishPickButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FINISH_PICK_BUTTON)));
        finishPickButton.click();
        System.out.println(ANSI_YELLOW + "STEP4: Order Status: Ready to Ship" + ANSI_RESET);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        WebElement shipmentSummeryPage = driver.findElement(By.xpath(SHIPMENT_SUMMERY_PAGE));
        assertTrue(shipmentSummeryPage.isDisplayed());

        WebElement continuePackingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CONTINUE_PACKING)));
        continuePackingButton.click();

        WebElement updateCarrierAndVolumetricButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UPDATE_CARRIER_AND_VOLUMETRICS_BUTTON)));
        updateCarrierAndVolumetricButton.click();

        WebElement weight = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WEIGHT)));
        WebElement height = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HEIGHT)));
        WebElement length = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LENGTH)));
        WebElement width = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WIDTH)));

        checkAndSendKeys(weight);
        checkAndSendKeys(height);
        checkAndSendKeys(length);
        checkAndSendKeys(width);

        WebElement carrierDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CARRIER)));
        carrierDropdown.click();

        WebElement carrierDropdownValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CARRIER_VALUE_FOR_UCV)));
        carrierDropdownValue.click();

        WebElement updateCarrierAndVolumetricOKButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(UPDATE_CARRIER_AND_VOLUMETRICS_OK_BUTTON)));
        updateCarrierAndVolumetricOKButton.click();

        WebElement finishPackingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FINISH_PACKING_BUTTON)));
        finishPackingButton.click();
        System.out.println(ANSI_YELLOW + "STEP5: Order Status: Packed" + ANSI_RESET);
    }
    public void CONNECT_TO_CARRIER() throws InterruptedException, AWTException {
        driver.findElement(By.xpath(PRINT_AWB_BUTTON)).click();
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        System.out.println("Printed AWB");

        String originalTab = driver.getWindowHandle();
        WebElement printInvoiceButton = driver.findElement(By.xpath(PRINT_INVOICE_BUTTON));
        printInvoiceButton.click();
        Thread.sleep(5000);
        System.out.println("Printed Invoice");
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        String printInvoiceTitle = driver.getTitle();
        System.out.println("Title of the new tab: " + printInvoiceTitle);
        driver.switchTo().window(originalTab);
        String originalTitle = driver.getTitle();
        System.out.println("Title of the original tab: " + originalTitle);

        // Get the current tab count
        allTabs = driver.getWindowHandles();
        int currentTabCount = allTabs.size();

        // Assert that a new tab has been opened using if condition
        if (currentTabCount > initialTabCount) {
            System.out.println("A new tab has been opened successfully.");
        } else {
            System.out.println("No new tab has been opened.");
        }

        // Alternatively, you can use an assertion
        assertEquals("A new tab should be opened", initialTabCount + 2, currentTabCount);

        WebElement connectToCarrierButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CONNECT_TO_CARRIER_BUTTON)));
        connectToCarrierButton.click();

        WebElement connectToCarrierOKButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CONNECT_TO_CARRIER_OK_BUTTON)));
        connectToCarrierOKButton.click();

        WebElement doneButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLICK_ON_DONE_BUTTON)));
        doneButton.click();

        System.out.println(ANSI_YELLOW + "STEP6: Order Status: Connect to Carrier" + ANSI_RESET);
    }
    public void HANDOVER_TO_SHIPMENT(){
        WebElement viewAllShipmentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(VIEW_ALL_SHIPMENT_BUTTON)));
        viewAllShipmentButton.click();

        WebElement shipmentSearchResultsPage = driver.findElement(By.xpath(SHIPMENT_SEARCH_RESULTS_PAGE));
        assertTrue(shipmentSearchResultsPage.isDisplayed());

        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILTER_BUTTON)));
        filterButton.click();

        WebElement orderNumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLICK_ON_ORDER_NUMBER_IN_FILTER)));
        orderNumber.click();
        WebElement orderNumberTextField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TYPE_ORDER_NUMBER)));
        orderNumberTextField.sendKeys(ORDER_NUMBER);

        WebElement shipmentNumber = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SHIPMENT_NUMBER_EXPAND)));
        shipmentNumber.click();
        WebElement shipmentNumberTextField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TYPE_SHIPMENT_NUMBER)));
        shipmentNumberTextField.sendKeys(SHIPMENT_NO);

        WebElement statusExpandButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(STATUS_EXPAND)));
        statusExpandButton.click();
        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(STATUS_DROPDOWN)));
        statusDropdown.click();
        WebElement statusValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(STATUS_VALUE)));
        statusValue.click();

        WebElement carrierExpandButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CARRIER_EXPAND)));
        carrierExpandButton.click();
        WebElement carrierDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CARRIER_DROPDOWN)));
        carrierDropdown.click();
        WebElement carrierValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CARRIER_VALUE)));
        carrierValue.click();

        WebElement applyButton9 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(APPLY_BUTTON)));
        applyButton9.click();

        WebElement retrievedCityText = driver.findElement(By.xpath("//*[@id='list-item_0']/div/div[3]/div/span[3]"));
        retrievedCity = retrievedCityText.getText();
        System.out.println("Retrived City is "+retrievedCity);

        WebElement sourceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='list-item_0']/div/div[2]/div[2]/div[2]/label")));
        String retrievedText = sourceElement.getText();
        System.out.println("Retrieved text: " + retrievedText);
        WebElement filterButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILTER_BUTTON)));
        filterButton1.click();
        WebElement lpModeExpand = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LP_MODE_EXPAND)));
        lpModeExpand.click();
        WebElement lpModeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LP_MODE_DROPDOWN)));
        lpModeDropdown.click();
        System.out.println(retrievedText);
        String lpModeValueXpath = String.format("//*[contains(text(), '%s')]", retrievedText);
        WebElement lpModeValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lpModeValueXpath)));
        lpModeValue.click();
        WebElement cityExpandButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CITY_EXPAND)));
        cityExpandButton.click();
        WebElement typeCityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TYPE_CITY)));
        typeCityField.sendKeys(retrievedCity);
        System.out.println("Retrieved text: " + retrievedCity);

        WebElement applyButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(APPLY_BUTTON)));
        applyButton1.click();

        WebElement selectCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SELECT_CHECK_BOX)));
        selectCheckBox.click();

        WebElement handoverShipmentsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HANDOVER_SHIPMENTS_BUTTON)));
        handoverShipmentsButton.click();

        WebElement handoverShipmentsOkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(HANDOVER_SHIPMENTS_OK_BUTTON)));
        handoverShipmentsOkButton.click();

        System.out.println(ANSI_YELLOW + "STEP7: Order Status: HOTC" + ANSI_RESET);
    }
    public void DELIVERY_IN_PROGRESS(){
        WebElement filterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FILTER_BUTTON)));
        filterButton.click();

        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(STATUS_DROPDOWN)));
        statusDropdown.click();

        WebElement clickOnHOTC = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLICK_ON_HOTC)));
        clickOnHOTC.click();

        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(APPLY_BUTTON)));
        applyButton.click();

        WebElement selectCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SELECT_CHECK_BOX)));
        selectCheckBox.click();

        WebElement deliveryInProgressButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DELIVERY_IN_PROGRESS_BUTTON)));
        deliveryInProgressButton.click();

        WebElement deliveryInProgressOkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DELIVERY_IN_PROGRESS_OK_BUTTON)));
        deliveryInProgressOkButton.click();

        System.out.println(ANSI_YELLOW + "STEP8: Order Status: DIP" + ANSI_RESET);
    }
    public void OUT_FOR_DELIVERY(){
        driver.findElement(By.xpath(FILTER_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(STATUS_DROPDOWN)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(CLICK_ON_DIP)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(APPLY_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(SELECT_CHECK_BOX)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(OUT_FOR_DELIVER_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(OUT_FOR_DELIVERY_OK_BUTTON)).click();
        System.out.println(ANSI_YELLOW + "STEP9: Order Status: Out for Delivery" + ANSI_RESET);
    }
    public void DELIVERED(){
        driver.findElement(By.xpath(FILTER_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(STATUS_DROPDOWN)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(CLICK_ON_OUT_FOR_DELIVERY)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(APPLY_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(SELECT_CHECK_BOX)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(DELIVERED_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(DELIVERED_OK_BUTTON)).click();
        System.out.println(ANSI_YELLOW + "STEP10: Order Status: Delivered" + ANSI_RESET);
    }

    public static void checkAndSendKeys(WebElement element) {
        String value = element.getAttribute("value");
        if (value.isEmpty()) {
            element.sendKeys("2");
        } else if (value.equals("0")) {
            element.clear();
            element.sendKeys("1");
        }
    }
}
