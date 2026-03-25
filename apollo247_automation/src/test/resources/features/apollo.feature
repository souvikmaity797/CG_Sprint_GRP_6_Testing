Feature: Apollo247 Lab Tests End-to-End Validation

  Background:
    Given User is logged in

  # -------------------- SEARCH FUNCTIONALITY --------------------

  Scenario: TC_AP_01_01 Verify lab test search with valid keyword
    Given User is on "https://www.apollo247.com/lab-tests-city/kolkata"
    When User clicks on search bar
    And User enters "Thyroid"
    And User presses Enter
    Then Search results should display Thyroid related tests
    And Results should include "TSH Test ₹359"
    And Results should include "Thyroid Profile ₹659"
    And Discount label should be visible
    And Add button should be functional

  Scenario: TC_AP_02_02 Verify search with invalid keyword
    Given User is on lab tests page
    When User enters "cbcxyz!!!" in search bar
    And User presses Enter
    Then No results should be displayed
    And Error message "No results found. Please try a different search term." should be visible

  # -------------------- ADD TO CART & DISCOUNT --------------------

  Scenario: TC_AP_03_01 Verify Lipid Profile added to cart with discount
    Given User searches for "Lipid Profile"
    When User clicks on Add button for Lipid Profile
    And User navigates to cart page
    Then Lipid Profile should be present in cart
    And Price should be "₹1,009"
    And MRP "₹2,522" should be struck through
    And Discount "60%" should be displayed
    And Savings "₹1,513" should be shown

  Scenario: TC_AP_03_03 Verify removing item from cart updates total
    Given Cart contains Lipid Profile and CBC test
    When User removes Lipid Profile from cart
    Then Cart should contain only CBC test
    And Total amount should be updated to "₹409"
    And Cart should show 1 item

  # -------------------- ADDRESS VALIDATION --------------------

  Scenario: TC_AP_04_02 Verify non-serviceable pincode blocks booking
    Given User has tests in cart
    When User proceeds to booking
    And User enters address "Connaught Place, New Delhi"
    And User enters pincode "110001"
    And User tries to proceed to slot selection
    Then System should block further progress
    And Error message "Home collection is not available at this location. Please enter a valid Kolkata address." should be displayed

  # -------------------- COUPON VALIDATION --------------------

  Scenario: TC_AP_07_02 Verify invalid coupon code shows error
    Given User is on checkout page
    And Cart contains lab tests
    When User enters coupon code "INVALCODE999"
    And User clicks on Apply button
    Then Coupon should not be applied
    And Error message "Invalid coupon code. Please check and try again." should be displayed
    And Cart total should remain unchanged

  # -------------------- MEMBERSHIP PAYMENT --------------------

  Scenario: TC_AP_09_02 Verify failed payment does not activate membership
    Given User is on Circle Membership purchase page
    When User selects membership plan
    And User clicks on Buy Now
    And User enters invalid card details "4111111111111119"
    And User attempts payment
    Then Payment should fail
    And Error message "Payment failed. Please try again or use a different payment method." should be displayed
    And Membership should not be activated