Feature: DocString example

  Scenario: Passing multiline text

    Given user is on the OrangeHRM login page
    When user enters following message
      """
      Hello Admin,
      This is a multiline message.
      This message is coming from Cucumber DocString.
      """