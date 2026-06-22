@price
Feature: FirstCry Filter By Price

  Scenario: Apply price low to high filter on Frocks and Dresses
    Given user opens FirstCry website
    When user closes the location popup
    And user goes to Girls Fashion section
    And user selects Frocks and Dresses
    And user applies price low to high filter
    Then user prints the filtered product results