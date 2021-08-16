# Behavior Driven Development

BDD is designed to solve the problem of over-designing software.

We design our software and we decide "we need a function to do this" and then as we continue devoloping the application we determine that that function we wrote is not required.

We've wasted time and time is money

Unused code should not make it to production, so we've actually created additional work for ourselves where we either need remove the code entirely or implement a process to exclude that code from the production environment.

Solution: Behavior Driven Development - An end-to-end test/usage of your application.

BDD assumes you are the client and you have to describe how you are going to use the application.

Ideally, you would write your BDD documentation before writing a single line of code.

GOAL: Write only the code that will fulfill the features you've written in BDD.

1. Create user stories
2. Create acceptance criteria
   1. Details that actually fulfill your user stories
3. (optional) Test the application by writing step implementations to these acceptance criteria.

## Gherkin
A language designed to write acceptance criteria

* **Feature**: The high level feature/user story, although this can be as big as an Epic
* **Scenario**: More specific user story.
* **Given**: Current state of the user or application
* **When**: an action that is performed
* **Then**: some confirmation that something has happened.