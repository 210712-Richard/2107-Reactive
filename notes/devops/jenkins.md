# Jenkins
An automation server designed for creating Continuous Integration pipelines. Developed in Java.

## History Lesson
Jenkins began it's life as a project called Hudson, which was an open-sourced project overseen by Sun Microsystems. In 2010, Oracle acquired Sun Microsystems. Oracle *hates* open-sourced projects. Oracle attempted to "close the source" of all of Sun's open-source projects and what actually happens when you do that is someone forks the repository and begins a different open-sourced project using the old code, *assuming anyone cared about the project in the first place*. People cared about Hudson, so all the developers who had previous been working on it and no longer could, began working on the fork of Hudson and renamed it Jenkins.

No one knows what Hudson is... Jenkins has become a staple in DevOps software.

Oops.

## Jobs

Basically a project. Jobs trigger based on some event, usually a git push, and perform a set of actions.

You can have jobs trigger other jobs.

When the job is triggered, Jenkins creates a "Build" or instance of that job and produces a weather report and job status.

### Weather Report

The weather report is a status derived fromt he overall success of the current build of the job and a certain number (default 4) of the previous builds. If all builds have succeeded, you see sunny skies, and if all builds have failed, you see thunderstorms.

### Status (the quest for blue balls)

Newer versions of Jenkins kind of ruin everything by moving to a check mark system.

A colored ball representing the health of the current build

* Grey ball/blank - no data 
* Red ball/red x - build failed
* Yellow ball/yellow something - build succeeded but tests of quality gate failed
* Blue Ball/green check - build success.

## Build

The result of running a job after an event triggered it. Jobs are highly customizable so the build could result in transpiled JS, or a `.jar` file, or a `.zip` file or even just a report based on running tests. Jenkins can even deploy artifacts to servers or cloud services.

## Plug-in Based
There is a large community of people creating plugins that allow us to accomplish a diverse set of goals.

Examples:
* GitHub
* Slack
* Discord
* SSH
* Install Maven
* Install Node
* Install Sonar Scanner