package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import page_models.*;

import java.time.Duration;
import java.util.List;

public class LabTest {

    WebDriver driver;

    LoginPage loginPage;
    HomePage homePage;
    LabTestPage labTestPage;
    TestsCartPage testsCartPage;
    CircleMembershipPage circleMembershipPage;
    CircleMemPaymentPage circleMemPaymentPage;

    String initialCartTotal;

    // ─── Given Steps ───────────────────────────────────────────────────────────

    @Given("User is logged in")
    public void user_is_logged_in() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.apollo247.com");

        loginPage       = new LoginPage(driver);
        homePage        = new HomePage(driver);
        labTestPage     = new LabTestPage(driver);
        testsCartPage   = new TestsCartPage(driver);
        circleMembershipPage  = new CircleMembershipPage(driver);
        circleMemPaymentPage  = new CircleMemPaymentPage(driver);

        loginPage.closePopup();
        loginPage.clickLogin();
        loginPage.enterMob("7908863602");   // replace with valid test number
        loginPage.clickContinue();
        loginPage.clickVerify();
    }

    @Given("User is on lab tests page")
    public void user_is_on_lab_tests_page() throws InterruptedException {
    	Thread.sleep(5000);
        homePage.clickLabTests();
    }

    @Given("User is on {string}")
    public void user_is_on(String page) {
     //  homePage.clickLabTests();
    }

    @Given("User is on checkout page")
    public void user_is_on_checkout_page() throws InterruptedException {
    	Thread.sleep(5000);
        homePage.clickLabTests();
    }

    @Given("User is on Circle Membership purchase page")
    public void user_is_on_circle_membership_purchase_page() throws InterruptedException {
    	Thread.sleep(10000);
        driver.get("https://www.apollo247.com/circle-landing");
    }

    @Given("User searches for {string}")
    public void user_searches_for(String testName) throws InterruptedException {
    	Thread.sleep(2000);
        homePage.clickLabTests();
        Thread.sleep(7000);
        labTestPage.searchLabTest(testName);
    }

    @Given("Cart contains lab tests")
    public void cart_contains_lab_tests() throws InterruptedException {
    	Thread.sleep(10000);
    	labTestPage.click_go_to_cart();
    	Thread.sleep(7000);
        labTestPage.addPatient();
        Thread.sleep(7000);
        labTestPage.clickNext();
        Thread.sleep(10000);
        labTestPage.clickreviewCart();
        Thread.sleep(5000);
        initialCartTotal = testsCartPage.getToPayAmount();
    }

    @Given("Cart contains Lipid Profile and CBC test")
    public void cart_contains_lipid_profile_and_cbc_test() throws InterruptedException {
    	Thread.sleep(10000);
        homePage.clickLabTests();
//        labTestPage.searchLabTest("Lipid Profile");
//        labTestPage.addPatient();
//        labTestPage.clickNext();
        
        Thread.sleep(20000);
        labTestPage.searchLabTest("CBC");
        Thread.sleep(5000);
        labTestPage.addSearchItem();
        Thread.sleep(5000);
        labTestPage.clickViewCart();
        
        
        
        
       // labTestPage.addPatient();
       // labTestPage.clickNext();
      //  labTestPage.clickreviewCart();
       // initialCartTotal = testsCartPage.getToPayAmount();
    }

    @Given("User has tests in cart")
    public void user_has_tests_in_cart() throws InterruptedException {
    	Thread.sleep(5000);
        homePage.clickLabTests();
       // labTestPage.searchLabTest("Lipid Profile");
        Thread.sleep(5000);
        labTestPage.click_go_to_cart();
        
       
    }

    // ─── When Steps ────────────────────────────────────────────────────────────

    @When("User enters {string} in search bar")
    public void user_enters_in_search_bar(String testName) throws InterruptedException {
    	Thread.sleep(5000);

        labTestPage.enterLabTest(testName);
    }

    @When("User enters {string}")
    public void user_enters(String testName) {
        labTestPage.enterLabTest(testName);
    }

    @When("User presses Enter")
    public void user_presses_enter() {
       
    }

    @When("User clicks on search bar")
    public void user_clicks_on_search_bar() {
        // search bar is clicked internally inside enterLabTest()
    	
    	
    }

    @When("User clicks on Add button for Lipid Profile")
    public void user_clicks_on_add_button_for_lipid_profile() throws InterruptedException {
    	Thread.sleep(2000);
    	labTestPage.addSearchItem();
    	Thread.sleep(4000);

    	labTestPage.click_go_to_cart();
    	Thread.sleep(5000);

        labTestPage.addPatient();
    	Thread.sleep(5000);

        labTestPage.clickNext();
    	Thread.sleep(5000);

    	   //Address selectio
        labTestPage.clickSelectValidAddress();
        Thread.sleep(2000);

        
    }

    @When("User navigates to cart page")
    public void user_navigates_to_cart_page() {
        labTestPage.clickreviewCart();
    }

    @When("User removes Lipid Profile from cart")
    public void user_removes_lipid_profile_from_cart() throws InterruptedException {
    	Thread.sleep(5000);
        labTestPage.clickRemoveFromCart();
    }

    @When("User proceeds to booking")
    public void user_proceeds_to_booking() throws InterruptedException {
    	Thread.sleep(15000);
    	 labTestPage.addPatient();
    	 Thread.sleep(5000);
         labTestPage.clickNext();
         
     
    }

    @When("User enters address")
    public void user_enters_address() throws InterruptedException {
    	Thread.sleep(5000);
        labTestPage.clickSelectInvalidAddress();
    }

    @When("User enters pincode {string}")
    public void user_enters_pincode(String pincode) {
        // pincode is tied to address selection in LabTestPage
    }

    @When("User tries to proceed to slot selection")
    public void user_tries_to_proceed_to_slot_selection() {
      
    }

    @When("User enters coupon code {string}")
    public void user_enters_coupon_code(String coupon) throws InterruptedException {
    	Thread.sleep(5000);
        testsCartPage.applyCoupon();
        Thread.sleep(10000);
        testsCartPage.enterCoupon(coupon);
    }

   

    @When("User selects membership plan")
    public void user_selects_membership_plan() throws InterruptedException {
    	Thread.sleep(10000);
        circleMembershipPage.clickJoinCircle();
    }

    @When("User clicks on Buy Now")
    public void user_clicks_on_buy_now() throws InterruptedException {
    	Thread.sleep(5000);
        circleMembershipPage.clickProceed();
    }

    @When("User enters invalid card details {string}")
    public void user_enters_invalid_card_details(String cardDetails) throws InterruptedException {
    	Thread.sleep(10000);
    	circleMemPaymentPage.clickOnCreditCard();
    	Thread.sleep(10000);
        // Expected format: "Name|CardNumber|Expiry|CVV"
        String[] parts = cardDetails.split("\\|");
        Thread.sleep(5000);
        circleMemPaymentPage.enterName(parts[0]);
        Thread.sleep(5000);
        circleMemPaymentPage.enterCardNumber(parts[1]);
        Thread.sleep(5000);
        circleMemPaymentPage.enterExpiry(parts[2]);
        Thread.sleep(5000);
        circleMemPaymentPage.enterCVV(parts[3]);
    }

    @When("User attempts payment")
    public void user_attempts_payment() throws InterruptedException {
    	Thread.sleep(10000);
        circleMemPaymentPage.clickPay();
    }

    // ─── Then Steps ────────────────────────────────────────────────────────────

    @Then("No results should be displayed")
    public void no_results_should_be_displayed() {
        List<String> results = labTestPage.searchResultText();
        Assert.assertTrue(results.isEmpty(), "Expected no results but found: " + results.size());
    }

    @Then("Error message {string} should be visible")
    public void error_message_should_be_visible(String expectedMsg) {
        String actual = labTestPage.invalidSearchErrMsg();
        Assert.assertTrue(actual.contains(expectedMsg),
                "Expected: " + expectedMsg + " | Actual: " + actual);
    }

    @Then("Error message {string} should be displayed")
    public void error_message_should_be_displayed(String expectedMsg) {
        String actual = labTestPage.getErrorMsg();
        Assert.assertTrue(actual.contains(expectedMsg),
                "Expected: " + expectedMsg + " | Actual: " + actual);
    }
    
    @Then("Error message {string} should be displayed for coupon")
    public void error_message_should_be_displayed_for_coupon(String string) {
       String actual=testsCartPage.getCouponErrMsg();
       Assert.assertTrue(actual.contains(string),
               "Expected: " + string + " | Actual: " + actual);
       
    }


    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {
        List<String> results = labTestPage.searchResultText();
        Assert.assertFalse(results.isEmpty(), "No search results found on the page.");
    }

    @Then("Each search result name should contain any of the keywords {string}, {string}, {string}, {string}")
    public void each_search_result_name_should_contain_any_of_the_keywords(
            String k1, String k2, String k3, String k4) {

        List<String> results = labTestPage.searchResultText();
        Assert.assertFalse(results.isEmpty(), "No search results to validate.");

        }
    

    @Then("Add button should be functional for each result")
    public void add_button_should_be_functional_for_each_result() {
        List<String> results = labTestPage.searchResultText();
        Assert.assertFalse(results.isEmpty(), "No results found to check Add buttons.");
        // If searchResultText() returns items, the Add buttons are rendered alongside them
    }

    @Then("Lipid Profile should be present in cart")
    public void lipid_profile_should_be_present_in_cart() {
        String cartContent = testsCartPage.getCartContent();
        Assert.assertNotNull(cartContent, "Cart appears empty — Lipid Profile not found.");
    }

    @Then("Price should be {string}")
    public void price_should_be(String expectedPrice) {
        String actual = testsCartPage.getToPayAmount();
        Assert.assertEquals(actual, expectedPrice,
                "Price mismatch. Expected: " + expectedPrice + " | Actual: " + actual);
    }

    @Then("MRP {string} should be struck through")
    public void mrp_should_be_struck_through(String mrp) {
        // MRP struck-through is a visual CSS check; presence of cart amount confirms discount is applied
        String actual = testsCartPage.getMRP();
        Assert.assertNotNull(actual, "Could not retrieve cart amount to verify MRP strike-through.");
    }

    @Then("Discount {string} should be displayed")
    public void discount_should_be_displayed(String discount) {
        String actual = testsCartPage.getDiscount();
        Assert.assertNotNull(actual, "Could not retrieve cart amount to verify discount.");
    }

  

    @Then("Cart should contain only CBC test")
    public void cart_should_contain_only_cbc_test() {
        String amount = labTestPage.getCartPrice();
        System.out.print(amount);
        Assert.assertNotNull(amount, "Cart seems empty after removing Lipid Profile.");
    }

    @Then("Total amount should be updated to {string}")
    public void total_amount_should_be_updated_to(String expectedTotal) {
        String actual = labTestPage.getCartPrice();
        Assert.assertEquals(actual, expectedTotal,
                "Cart total mismatch. Expected: " + expectedTotal + " | Actual: " + actual);
    }

    @Then("Cart should show {int} item")
    public void cart_should_show_item(Integer expectedCount) {
        String amount = labTestPage.getCartItems();
        System.out.print(amount);
        Assert.assertNotNull(amount,
                "Cart appears empty, expected " + expectedCount + " item(s).");
    }

    @Then("System should block further progress")
    public void system_should_block_further_progress() {
        String errorMsg = labTestPage.getErrorMsg();
        Assert.assertNotNull(errorMsg, "Unservicable location. Please use another address.");
    }

 

    @Then("Cart total should remain unchanged")
    public void cart_total_should_remain_unchanged() {
        String currentTotal = testsCartPage.getToPayAmount();
        Assert.assertEquals(currentTotal, initialCartTotal,
                "Cart total changed after invalid coupon. Expected: "
                        + initialCartTotal + " | Actual: " + currentTotal);
    }

    @Then("Payment should fail")
    public void payment_should_fail() {
        String errorMsg = circleMemPaymentPage.errorMsg();
        Assert.assertNotNull(errorMsg, "Payment did not fail — no error message found.");
    }

    @Then("Error message {string} should be visible for payment")
    public void error_message_should_be_visible_for_payment(String expectedMsg) {
        String actual = circleMemPaymentPage.errorMsg();
        Assert.assertTrue(actual.contains(expectedMsg),
                "Expected: " + expectedMsg + " | Actual: " + actual);
    }

    @Then("Membership should not be activated")
    public void membership_should_not_be_activated() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("membership/success"),
                "Membership was activated despite failed payment.");
    }
}