Feature: Login page feature

@Smoke @Regression
Scenario: Login page title
Given user is on login page
When user gets the title of the page
Then page title should be "Account Login"

@Smoke @Regression
Scenario: Forgot password link
Given user is on login page
Then forgot your password link should be displayed

@Smoke @Regression
Scenario: Login with valid credentials
Given user is on login page
When user enters username "allen@gmail.com"
And user enters password "Test@1234"
And user clicks on login button
Then user gets the title of the page
And page title should be "My Account"