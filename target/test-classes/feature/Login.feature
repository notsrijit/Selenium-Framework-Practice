Feature: Application Login

Scenario: Login with valid credentials

Given Open any Browser
And Navigate to the login page
When User enters username as "srijit@123.com" and password as "1234" into the fields
And User clicks on the Login Button
Then verify user is able to successfully login
