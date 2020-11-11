Feature: UI - Test feature
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  Curabitur sodales odio vitae nisi aliquet, nec sagittis augue vestibulum. Quisque at porta neque.

#  Scenario Outline: Login with correct username and password
#    Given I navigate to the login page
#    When I login with the following credentials
#      | Username   | Password   |
#      | <Username> | <Password> |
#    Then I should see the userform page
#    Examples:
#      | Username | Password      |
#      | admin    | adminpassword |
##      | admin1   | adminpassword1 |
#
#
#  Scenario: I am on google page
#    Given I navigate to "https://google.com/" URL



#  LAB 5
  Scenario: As a User, I should be able to search for a product on Ebay page
    Given I navigate to Ebay page
    When I search for "computers"
    Then I check tab title is "computers | eBay"
    And I check Ebay header is displayed


##  LAB 6
#  Scenario: As a User, I should be able to search for a product on EliteShoppy
#    Given I navigate to EliteShoppy page
#    When I search for "Big wing sneakers" product
#    Then I see the searching results are displayed correctly
#
#  Scenario: As a User, I should be able to take a poll
#    Given I navigate to EliteShoppy page
#    When I open Men's wear clothing
#    And I take poll by choosing "Lower Price" answer
#    Then I should see the polling results
#
#  Scenario: As a User, I should be able to sign in
#    Given I navigate to EliteShoppy page
#    When I login with the following credentials
#      | Username | Email            |
#      | test123  | test123@test.com |
#    Then I assure I am logged in
#
#  Scenario Outline: As a User, I should be able to sign up
#    Given I navigate to EliteShoppy page
#    When I register a new account with the following credentials
#      | Username   | Email   | Password   |
#      | <Username> | <Email> | <Password> |
#    Given I navigate to EliteShoppy page
#    When I login with the following credentials
#      | Username   | Email   |
#      | <Username> | <Email> |
#    Then I assure I am logged in
#    Examples:
#      | Username | Email          | Password |
#      | test1    | test1@test.com | test1    |
#
#
#  Scenario: As a User, I should be able use contact form
#    Given I navigate to EliteShoppy page
#    When I open contact section
#    And I fill in contact form and submit message
#      | Name     | Email             | Subject | Message                                                  |
#      | John Doe | john.doe@test.com | Test    | Lorem ipsum dolor sit amet, consectetur adipiscing elit. |
#    Then I assure that message was successfully sent
#
#
#  Scenario Outline: As a User, I should be redirected to the corresponding page upon clicking on the carousel
#    Given I navigate to EliteShoppy page
#    When I click on the carousel banner nr <Number>
#    Then I assure that I was redirected to "<Page>" page
#    Examples:
#      | Number | Page         |
#      | 1      | WOMEN'S WEAR |
#      | 2      | MEN'S WEAR   |
#      | 3      | WOMEN'S WEAR |
#      | 4      | WOMEN'S WEAR |
#      | 5      | MEN'S WEAR   |
#
#
#
