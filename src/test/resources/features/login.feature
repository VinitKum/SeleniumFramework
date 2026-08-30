Feature: Orange HRM Login

  Background:
    Given user is on the OrangeHRM login page


  Scenario Outline: Login with valid credentials

    When user enters "<username>" and "<password>"
    And user clicks on login button
    Then user should be successfully logged in

    Examples:
      | username | password |
      | Admin    | admin123 |


  Scenario Outline: Login with invalid credentials

    When user enters "<username>" and "<password>"
    And user clicks on login button
    Then login error message should be displayed

    Examples:
      | username | password |
      | Admin    | wrong123 |