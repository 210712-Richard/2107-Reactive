# Software Devolopment Methodologies

## Software Development Lifecycle (SDLC)

**Disclaimer**: I personally get kind of salty about sdlc because that's how I am, but you should <_never_ epress any disinterest, disdain, or displeasure with any methodology in an interview because you do not know how they do things. Many interviewers get very defensive of their particular style and some of them think they're doing a lot better than they actually are at following a specific style.

SDLC is the process through which we develop software. The idea that software moves thorugh particular phases:
1. Analysis
   1. System Investigation - investigating the needs for the software and feasibility
   2. Analysis - how do we solve the problem with software?
2. Design - Actually planning out how the software will be devoloped
   1. *NOTE*: Some people claim that Agile skips this step, but those people are either misunderstanding Agile, misrepresenting Agile on purpose, or doing Agile wrong.
3. Development - Actually writing the code.
   1. Environments should be set up during this phase.
   2. TDD: Unit testing should be performed here, ideally.
4. Integration and Testing - After the code is written, perform tests, including integration tests.
   1. Integration Test - a test that test large portions of code working together. My test an entire user story for example.
5. Acceptance - The code is deployed to a UAT (User Acceptance Testing) environment and stakeholder can approve it. One approved, the code can be deployed to production.
6. Maintenance - bug fixes and upgrades.

## Waterfall
(older, more traditional approach)

You start with analysis and then write a huge design document that you meticulously test and build each individual piece of the application from the ground up and at the very end you put the last piece in, hit run, and everything works perfectly.

Building a car: You can't build half of an engine and expect it to work. You have to build all of the pieces, test them, QA them, and then assemble them, and then you have an engine, and that engine doesn't do anything without the rest of the car around it.

### Pros
* The product works.
* The product is well tested.
* The product should be relatively bug free.
* It satisfies our (original) requirements exactly.

### Cons
* Nothing to show our stakeholders until it is done (months, years later)
* If requirements change, there's no built-in way to really deal with that.

## Agile

### Wrong ways to do Agile:
* "New requirements came in, thow everything away and start over!"
* "Don't waste time designing anything, this is Agile."
* "We think we need to write software to accomplish this task, but research isn't Agile, so don't analyze the problem, just start writing software."
* "We don't need a UAT environment, this is Agile."
* "Don't bother testing it, this is Agile."

### What *is* Agile?

Agile is a software development methodology that prizes the creation of an MVP (Minimum Viable Product) at each stage of development.

* *MVP* - A working piece of the product, usually consisting of the most important or vital user stories.
  * TRMS MVP
    * Submit a reimbursement
    * Approval Process
    * Awarding a Reimbursement
  * Bank App
    * Registration
    * Create an account
    * Deposit/withdraw to account
  * Gacha Game
    * Registration
    * Currency management
    * summoning

In Agile, development is broken into *Sprints*
* *Sprint* - Length of time (usually between 2 weeks and a month) at the end of which an MVP will have been developed.
* *User Stories* - A product feature, written out as a set of requirements to consider the product complete.
  * User story points - each user story is assigned a point value to represent the difficulty (sometimes complexity) of completing the user story.
    * Linear Scale - 1, 2, 3, 4, 5, ...
    * Fibonacci - 1, 2, 3, 5, 8, 13, ...
    * T-Shirts - xs, s, m, l, xl, xxl, ...
  * All Associate View
  ```txt
  As a trainer, I should have a report view that allows me to see all the usual reports that include all associates with abnormal statuses.

  Given I am a trainer with access to the reports page for a batch,
  When I navigate to the reports page,
  Then I can select "Trainees (Include Dropped)"
  And I will see the reports page with all dropped trainees' scores and QC audits
  and dropped associates will be indicated as such.
  ```
  * User stories generally follow the following template: `As the [role], I [do the task]`.
  * `System` is a valid role.

### Agile Ceremonies

*Ceremony* - An event that occurs before, during, or after a Sprint.

* *Sprint Planning* - Usually a meeting, often lasting one or two days, where the team plans out what user stories need to be accomplished in the sprint.
  * *Sprint Zero* - A sprint of just design and framework development (setting up the project)
* *Sprint Retrospective* - A meeting at the end of a sprint where you discuss what went wrong, what went right, and what you should change and or keep doing for the next sprint.
* *Standup* - A (15 minute) (daily) meeting in which each member of the team provides a brief report of their progress since last standup and what they are currently working on. They should also report any blockers.
  * *Blocker* - Any circumstance _out of your control_ that is preventing you from making progress on your user story.

### Scrumboard
(Kanban, Scrumban, whatever)

A graphical representation of the product in terms of user stories and their completion.
* Product Backlog - The bank of all user stories for the project.
* Sprint Backlog - The bank of all user stories selected for development this sprint.
* Development - The user stories being actively developed by a developer
* Testing - User stories undergoing manual, automated, unit, and/or acceptance testing. Failure results in sending the story back to Development
* Peer Review - User stories that another member of the team is reviewing in order to ensure completion. (often finding minor typos or mistakes that the original developer just became blind to, or finding additional requirements that the original developer forgot). If you fail peer review you go back to Development.
* Done - User stories that are complete.
  