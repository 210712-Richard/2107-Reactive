# DevOps

DevOps is a portmanteau of Development and Operations.

It is the concept of blending your dev and operations (and testing) teams into one team by automating the process of testing and deploying the application. One team is now responsible for the entire lifecycle of the app.

Traditional: Dev team pushes -> QA team tests -> Ops team deploys

DevOps: Dev team pushes and then an automated process runs all the tests that they wrote and ensures code quality meets some defined standards and before (optionally) deploying the application all while the dev team is free to continue development, and receives near instant feedback on the status of the code they pushed.

Deployment should be AUTOMATED and REPEATABLE

## Continuous Integration (CI)

The practice by which all code is continually (multiple times a day) pushed to a central repository and merged with the overall code base, triggering an automated process which will run unit tests and check code quality for your application.

Ideally: Every single push to every single branch of your repository would trigger a CI pipeline that would track which branch was being built and send you a report informing you of the success and quality of your code. (compile, test, static code analysis).

For us: Probably just prod. (main branch, maybe a dev branch)

## Continuous Delivery (CD)

The practice by which CI occurs and then the pipeline creates a *deliverable artifact* which can be utilized to deploy the application. CD *can* automate the deployment process for you, but manual approval is required before it will.

Examples:
1. CD for a Java project would usually produce a `.jar` or `.war` file, and we could potentially have a button that would `approve` that file for release and automatically deploy that file to a server.
2. CD for a front-end project might produce a `zip` file of a static JS/Html/css bundle that we could upload to a server.

## Continuous Deployment (CD)

The practice by which CI occurs and then the pipeline creates a *deliverable artifact* which is automatically deployed to a server (this could be a QA environment or a Prod environment) if the artifact passes all quality gates.

## GitHub Actions
A GitHub service that allows you to automate workflows for CI/CD Pipelines. Tightly coupled to the GitHub service.

