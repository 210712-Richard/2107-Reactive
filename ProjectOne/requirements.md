# Project One
For Project One, you are required to build a REST API that satisifies the requirements set forth in the Tuition Reimbursement Management System document. Your TRMS must abide by following:

## Technology

* AWS S3 Integration for File Upload
* RESTful API built with Javalin
* Data persistence through AWS Keystore for Apache Cassandra
* Logging with Log4J2
* Unit testing with JUnit

## Other

* Complete approval process
* File upload to skip supervisor approval
* Discrete Data Access layer
* Full test coverage for the data and service layers

You will have 5 minutes to demo this to me and the rest of the batch.

Due Date: 4-30-21


#### Small example
```
POST /login -> logged in as Mohit
POST /reimbursements -> created reimbursement 4 (owned by Mohit)
DELETE /login -> logged out

Mohit's supervisor is Richard, and his department head is Sean

POST /login -> logged in as Sean
PUT /reimbursements/4 -> try to approve results in 403
DELETE /login -> logged out

POST /login -> logged in as Richard
PUT /reimbursements/4 -> try to approve results in 200
DELETE /login -> logged out

POST /login -> logged in as Sean
PUT /reimbursements/4 -> try to approve results in 200
DELETE /login -> logged out
```