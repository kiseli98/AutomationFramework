Feature: UI - Creation and execution of automated tests on a live web application
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.


  @Smoke
  @priority:1
  Scenario Outline: As a User, I should be able to change search language
    Given I navigate to Google page
    When I set language to "<Language>"
    Then I check search button text was changed to "<Text>"
    Examples:
      | Language | Text           |
      | Română   | Căutare Google |
      | русский  | Поиск в Google |

  @priority:1
  @testId:1
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

  @priority:1
  @issueId:3
  Scenario: As a user, I should be able to see calculator upon search request
    Given I navigate to Google page
    When I search "calculator"
    Then I see calculator widget

  @priority:1
  Scenario: As a user, I should be able to see money converter upon search request
    Given I navigate to Google page
    When I search "money converter"
    Then I see money converter widget


  Scenario: As a user, I want to type smth in the search box
    Given I navigate to Google page
    When I type values
      | Value       |
      | 11111111111 |
      | 222222222   |
      | 3333333333  |
