@color
Feature: FirstCry Filter By Colour Pink

  Scenario: Apply pink colour filter after price low to high filter
    Given user opens FirstCry website
    When user closes the location popup
    And user goes to Girls Fashion section
    And user selects Frocks and Dresses
    And user applies price low to high filter
    And user scrolls down to color filter
    And user selects pink color filter
