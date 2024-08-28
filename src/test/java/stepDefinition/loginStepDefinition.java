package stepDefinition;

import io.cucumber.java.en.Given;
import pages.HomePageObject;

public class loginStepDefinition {
    HomePageObject homePage = new HomePageObject();

    @Given("User should login to Store Engagement Application")
    public void user_should_login_to_store_engagement_application() throws InterruptedException {
        homePage.login();


    }
}
