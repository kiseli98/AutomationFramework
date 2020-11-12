Feature: Lab 5. Introduction to test automation
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.


  Scenario: As a User, I should be able to search for a product on Ebay page
    Given I navigate to Ebay page
    When I search for "computers"
    Then I check tab title is "computers | eBay"
    And I check Ebay header is displayed

