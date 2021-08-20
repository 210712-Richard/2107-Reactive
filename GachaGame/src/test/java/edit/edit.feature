Feature: I can edit a Gacha as an Admin

Scenario: Login as admin, retrieve gachas, edit a gacha, retrieve gachas

Given url gachas
And def signin = call read('classpath:login/login.feature')
And request {}
And cookie SESSION = signin.adminSessionCookie
When method get
Then status 200
And def json = karate.extractAll(response, "(\\{.*?\\{.*?\\}.*?\\})", 1)
And karate.log(karate.map(json, JSON.parse))
And karate.log(json[0])