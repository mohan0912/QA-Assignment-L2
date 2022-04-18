#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@AutomateTwitter
Feature: Automate Twitter

  @LoginFlow
  Scenario: Automate Login Flow
    Given I am on the login screen of twitter
    And I validate required field validation for Email field
    When I enter valid email address and select Next
    And I navigated to enter Password page having prefilled disabled email field
    And I verify Login button disabled for empty Password field
    And I enter valid password and select Login
    Then I should be navigated to Home Page

  @ProfileUpdate
  Scenario Outline: Automate Update Profile Flow
    Given I am on the login screen of twitter
    And I enter valid email address and select Next
    And I navigated to enter Password page having prefilled disabled email field
    And I enter valid password and select Login
    And I should be navigated to Home Page
    And I select Profile link
    And I select Update Profile on Profile Page
    When I update the Header Photo and Profile photo
    And I update the Location as <location>, Website as <url>, Bio as <bio> and Select Save
    Then I should see the updated value for Location as <location>, Website as <url>, Bio as <bio> on Profile Page

    Examples: 
      | location    | url                | bio                      |
      | Bhubaneswar | http://twitter.com | Selenium Automation user |

  @getTweets
  Scenario Outline: Automate Update Profile Flow
    Given I am on the login screen of twitter
    And I enter valid email address and select Next
    And I navigated to enter Password page having prefilled disabled email field
    And I enter valid password and select Login
    And I should be navigated to Home Page
    And I select Explore link
    And I search for The Times Of India page
    And I extract and display all the tweet received in last 2 hours 

 