Feature: Login as Michael
Scenario: send a request and login successfully

Given url loginUrl
And request { username: 'Michael' }
When method post
Then status 200
And match response contains { username: 'Michael', type: "PLAYER", currency: '#notnull'}
And match responseCookies contains { SESSION: '#notnull' }
And def sessionCookie = responseCookies.SESSION

Scenario: send a request and login as admin

Given url loginUrl
And request { username: 'Richard' }
When method post
Then status 200
And match response contains { username: 'Richard', type: "GAME_MASTER" }
And match responseCookies contains { SESSION: '#notnull' }
And def adminSessionCookie = responseCookies.SESSION