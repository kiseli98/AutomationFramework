Feature: UI - Creation and execution of automated tests on a live web application (DEMO)
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.

  @priority:2
  Scenario: As a user, I want to see my order history - new approach
    Given I am on the store page
    When I login with the following credentials
      | Username        | Password |
      | admin@admin.com | admin    |
    And I open order history
    Then I see order history table is displayed


  @priority:3
  Scenario: As a user, I want to see my order history - old approach
    Given I am on the store page - old
    When I login with the following credentials - old
      | Username        | Password |
      | admin@admin.com | admin    |
    And I open order history - old
    Then I see order history table is displayed -old
