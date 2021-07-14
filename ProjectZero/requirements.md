# Project Zero
For Project 0, you will be building a REST API that will allow users to have certain functionality. The state of the application will be saved to text files that the app will read and write to.

You will be required to conduct a 5 minute presentation/demo. This presentation will be a significant part of the assessment.

Require TDD and testing using JUnit.

Presentation Date: July 28th, 2021

## Features

1. Two types of users. ex. bank teller and customer or shop owner and customer
2. Must have basic login functionality (NB. DO NOT PERFORM ANY ENCRYPTION OR SALTING)
3. Account creation
4. At least 3 things user type 1 can do (user type 2 can do these as well, if that makes sense). These functionalities must in some way change the state of the application.
5. At least 1 thing user type 2 can do that user type 1 can not do.

### Example: Bank
Customer:
1. Create an account (checking or savings)
2. Deposit/withdraw into/from an account
3. Apply for a loan

Teller:
1. Approve a loan


### Project Zero Phase 1 (Checking in Wednesday 7th, 2021)

Create a console interface that allows you to call methods that perform the various functionalities listed.

### Project Zero Phase 2

Create the REST API that allows us to perform the various functionalities.

## Tech Stack

* Java
* log4j
* JUnit
* Javalin

## Submission

I require that your code be pushed to your repository which should be named [first].[last].p0