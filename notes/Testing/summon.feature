Feature: Summon an Historical Cat

    Scenario: Summon an Historical Cat using our currency
        Given the user is logged in
        And the user has enough currency
        When a POST request is sent to /users/{user}/inventory
        Then we recieve a new Historical Cat object
        And our currency is deducted
        And the Historical Cat is added to the user's inventory.

    Scenario: Fail to summon an Historical Cat when out of currency
        Given the user is logged in
        And the user does not have enough currency
        When a POST request is sent to /users/{user}/inventory
        Then we recieve a response with status code 402.
