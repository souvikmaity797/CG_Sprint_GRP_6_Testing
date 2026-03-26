Feature: Apollo247 Lab Tests End-to-End Validation

  Background:
    Given User is logged in

  # -------------------- SEARCH FUNCTIONALITY --------------------

Scenario: TC_AP_09_02 Verify failed payment does not activate membership
    Given User is on Circle Membership purchase page
    When User selects membership plan
    And User clicks on Buy Now
    And User enters invalid card details "John Doe|4111111111111111|12/28|123" 
    And User attempts payment
    Then Payment should fail
    And Error message "Payment failed. Please try again or use a different payment method." should be displayed
    And Membership should not be activated