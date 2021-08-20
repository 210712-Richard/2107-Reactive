Feature: I can edit a Gacha as an Admin

Scenario: Login as admin, retrieve all gachas, edit the gacha retrieved, upload those changes, retrieve that gacha and verify changes, change it again, verify again

Background: 
	* def signin = call read('classpath:login/login.feature')

Given url gachas
# Retrieve the signin credentials for our admin

# Empty request for GET
And request {}
# Use our signin credentials
And cookie SESSION = signin.adminSessionCookie
# Get request
When method get
# Ensure that it is a 200
Then status 200
# Save the first object in the ndjson response as an object
And def gacha = JSON.parse(karate.extractAll(response, "(\\{.*?\\{.*?\\}.*?\\})", 1)[0])
# Log some stuff for visibility (comment out later)
And karate.log(gacha)
And karate.log(gacha.name)
# Create a URL for the gacha we retrieved so we can edit and retrieve it later.
And def myGachaUrl = gachas+'/'+gacha.rarity+'/'+gacha.name

# New request, change attack to 1
Given url myGachaUrl
And set gacha.stats.attack = 1
# The gacha object as a json string
And json req = gacha
# Give that json string as the request body
And request req
# Using our admin credentials
And cookie SESSION = signin.adminSessionCookie
# Put request
When method put
# Make sure it's a 200 status
Then status 200

# Verify that a get to /rarity/name has an attack of 1
And karate.log(gacha.rarity)
And call read('classpath:retrieveGacha.feature') { rarity: '#(gacha.rarity)', name: '#(gacha.name)' }
And match retrieved contains {stats: {attack: 1, defense: '#notnull', health: '#notnull'}}

# new request, change attack to 9
Given url myGachaUrl
And set gacha.stats.attack = 9
# The gacha object as a json string
And json req = gacha
# Give that json string as the request body
And request req
# Using our admin credentials
And cookie SESSION = signin.adminSessionCookie
# Put request
When method put
# Make sure it's a 200 status
Then status 200

# Verify that a get to /rarity/name has an attack of 9
And karate.log(gacha.rarity)
And call read('classpath:retrieveGacha.feature') { rarity: '#(gacha.rarity)', name: '#(gacha.name)' }
And match retrieved contains {stats: {attack: 9, defense: '#notnull', health: '#notnull'}}