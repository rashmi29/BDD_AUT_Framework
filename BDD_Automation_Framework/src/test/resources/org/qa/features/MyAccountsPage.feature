Feature: Account Page Feature

Background:
Given user has already logged in to application
|username|password|
|allen@gmail.com|Test@1234|

@Smoke @Regression
Scenario: Accounts page title
Given user is on Accounts page
When user gets the title of the page
Then page title should be "My Account"

@Smoke @Regression
Scenario: Accounts section count
Given user is on Accounts page
Then user gets accounts section
|My Account|
|My Orders|
|My Affiliate Account|
And accounts section count should be 3