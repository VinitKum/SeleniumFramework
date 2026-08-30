Feature: Orange HRM Login

  Background:
    Given user is on the OrangeHRM login page

  Scenario: Login with valid credentials

    When user enters following credentials
      | username | password |
      | Admin    | admin123 |

    And user clicks on login button

    Then user should be successfully logged in