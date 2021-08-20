Feature: Retrieve a specific gacha give the rarity and name

Background:
  * def args = { rarity: '#(rarity)', name: '#(name)' }
  * def gachaUrl = gachas+'/'+args.rarity+'/'+args.name

Scenario:
  Given url gachaUrl
  And request { }
  When method get
  Then status 200
  And match response contains { name: '#(name)', rarity: '#(rarity)' }
  And def retrieved = response