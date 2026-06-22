@navigation

Feature: FirstCry Girls Fashion Navigation

  Scenario: Navigate to Girls Fashion and select Frocks and Dresses
    Given user opens FirstCry website
    When user closes the location popup
    And user goes to Girls Fashion section
    And user selects Frocks and Dresses
    Then user should be on Frocks and Dresses page