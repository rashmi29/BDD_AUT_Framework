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

@Regression
Scenario: Accounts section count
Given user is on Accounts page
Then uer gets accounts section
|MY ACCOUNT|
|MY ORDERS|
|MY AFFILIATE ACCOUNT|
|NEWSLETTER|
And accounts section count should be 4