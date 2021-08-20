Feature: login as a user and summon a new cat
Scenario: As a logged in user, send a post request to the summon url and receive a new cat

Given url michaelInventory
And def signin = call read('classpath:login/login.feature')
And request {}
And cookie SESSION = signin.sessionCookie
When method post
Then status 200
And match response contains {id: '#notnull', name: '#notnull'}
