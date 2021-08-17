# Code Quality

Static Code Analysis: Looking at the code and seeing that it conforms to convention, doesn't have obvious bugs, doesn't have obvious security vulnerabilities. This is all done without actually running the code.

Performing static code analysis helps you produce code that is easier to maintain, easier to read, and less buggy.

## Sonar

SonarCloud is an SaaS implementation of SonarQube

One of the things that SonarCloud is doing for us is watching our repositories and performing static code analysis whenever a push is performed on a branch it is watching. This means we don't have to configure our pipeline to run a scanner and report to a SonarQube instance.

SonarQube is an application that performs static code analysis or a code base to check for common errors, possible security problems, difficult to read code, and potential bugs.

### Code Smells

Something that makes your code difficult to read, refactor, or maintain but that is not actually a bug.

Examples:
* Commented-out code
* Declared but unused variables
* unhandled exceptions
* cognitive complexity

### Cognitive Complexity

How difficult it is to trace the code. Takes the number of logical branches in a particular method into account as well as the number of nexted logical branches.

### Security problems

Anything that SonarQube thinks might be exploitable in the code base.

Example:
* hard coded password fields
* Not using `PreparedStatement` to protect against SQL injection

