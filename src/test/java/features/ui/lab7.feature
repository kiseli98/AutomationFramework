Feature: Lab 7. Creation and execution of automated tests on a live web application
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.

  Scenario Outline: As a User, I should be able to change search language
    Given I navigate to Google page
    When I set language to "<Language>"
    Then I check search button text was changed to "<Text>"
    Examples:
      | Language | Text           |
      | Română   | Căutare Google |
      | русский  | Поиск в Google |


  Scenario Outline: Search engine should be case insensitive
    Given I navigate to Google page
    When I search for "<Query>" in upper case
    And I memorize search results
    Given I navigate to Google page
    When I search for "<Query>" in lower case
    Then I check search results are equal
    Examples:
      | Query              |
      | american civil war |
      | us elections 2020  |


  Scenario: As a user, I should be able to see calculator upon search request
    Given I navigate to Google page
    When I search "calculator"
    Then I see calculator widget


  Scenario: As a user, I should be able to see money converter upon search request
    Given I navigate to Google page
    When I search "money converter"
    Then I see money converter widget
