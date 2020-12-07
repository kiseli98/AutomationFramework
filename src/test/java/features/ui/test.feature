Feature: UI - Test feature
  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  Curabitur sodales odio vitae nisi aliquet, nec sagittis augue vestibulum. Quisque at porta neque.

  Scenario Outline: Login with correct username and password
    Given I navigate to the login page
    When I login with the following credentials
      | Username   | Password   |
      | <Username> | <Password> |
    Then I should see the userform page
    Examples:
      | Username | Password      |
      | admin    | adminpassword |
      | admin1   | adminpassword1 |


  Scenario: I am on google page
    Given I navigate to "https://google.com/" URL
