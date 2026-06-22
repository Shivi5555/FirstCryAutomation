package stepDefinitions;

import base.Base;
import io.cucumber.java.en.*;
import pageObjects.FirstCryPage;

public class FirstCrySteps extends Base {

    FirstCryPage firstCryPage;
    @Given("user opens FirstCry website")
    public void user_opens_first_cry_website() throws Exception {
        Base.getDriver().get(getUrl());
        Thread.sleep(1500);

        firstCryPage = new FirstCryPage(Base.getDriver());

        System.out.println("FirstCry website opened.");
    }
    @When("user closes the location popup")
    public void user_closes_the_location_popup() throws Exception{
        firstCryPage.closeLocationPopup();
        Thread.sleep(1500);

    }

    @When("user goes to Girls Fashion section")
    public void user_goes_to_girls_fashion_section()throws Exception {
        firstCryPage.goToGirlsFashion();
        Thread.sleep(1500);

        
    }

    @When("user selects Frocks and Dresses")
    public void user_selects_frocks_and_dresses() throws Exception{
        firstCryPage.selectFrocksAndDresses();
        Thread.sleep(1500);

    }

    @Then("user should be on Frocks and Dresses page")
    public void user_should_be_on_frocks_and_dresses_page() throws Exception{
        firstCryPage.validateFrocksAndDressesPage();
        Thread.sleep(1500);

    }

    @When("user applies price low to high filter")
    public void user_applies_price_low_to_high_filter() throws Exception{
        firstCryPage.sortPriceLowToHigh();
        Thread.sleep(1500);
    }

    @When("user scrolls down to color filter")
    public void user_scrolls_down_to_color_filter() throws Exception {
        firstCryPage.scrollToColorFilter();
        Thread.sleep(1500);
    }

    @When("user selects pink color filter")
    public void user_selects_pink_color_filter() throws Exception {
        firstCryPage.selectPinkColor();
        Thread.sleep(1500);
    }

    @Then("user prints the filtered product results")
    public void user_prints_the_filtered_product_results() throws Exception {
        firstCryPage.printFilteredResults();
        Thread.sleep(1500);
    }
}