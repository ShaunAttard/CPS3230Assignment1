Feature: Web test Automation

  In order to test the screen scraper and api calls.
  As a user of this program
  I want to be able to add listings to the given website

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again

  Scenario: Alert Layout
    Given I am an administrator of the website and I upload 3 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website

  Scenario: Alert Limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 5 alerts

  Scenario Outline: Icon check
    Given I am an administrator of the website and I upload an alert of type "<alert-type>"
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be "<icon-file-name>"
    Examples:
    |alert-type | icon-file-name|
    |1          | icon-car.png  |
    |2          | icon-boat.png |
    |3          | icon-property-rent.png|
    |4          | icon-property-sale.png|
    |5          | icon-toys.png         |
    |6          | icon-electronics.png  |


