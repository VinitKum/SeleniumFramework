Feature: Orange HRM Login using DataTable

  Scenario: Login using credentials from DataTable

    Given user is on the OrangeHRM login page
    When user enters following credentials from DataTable

     | username | password |
     | Admin    | admin123 |

    Then user should be successfully logged in