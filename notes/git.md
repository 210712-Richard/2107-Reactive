# Git

## What is Git?

* Collaboration tool
* Code repository software
* Code management tool

Git is an SCM tool. Software Control Managment Software. It is a tool for tracking changes to a software project's code and enabling the collaboration between multiple programmers modifying the code base.

* *Repository* - A location where we can store code
  * *Remote/Central* - The canonical or official copy of the code and all of the changes and versions of that codebase. Usually located in the cloud. [our repostitory](https://github.com/210712-Richard/2107-Reactive)
  * *Local* - The copy of the remote repository that exists on your machine that you can make changes to and then "push" them to the remote repository to be shared with others.
  
## Commands
* `git clone <repository url>` - Creates a local copy of a remote repository
* `git status` - Displays information about the *local* repository, including changes you have made to it since the last commit you made.
* `git add` - Adds a file to the list of tracked changes to our repository that will be added to the commit
* `git commit -m "message"` - Creates a commit that can be added to the repository.
* `git push origin <branch name>` - Push the commits you have created to the remote repository.

## Definitions
*branch* -
*commit* - A group or subset of changes to the repository that can be pushed or pulled from the remote and allows us to make modifications to the repository.
*merge conflict* -
*gitignore* - 


# Follow Along
* `git clone https://github.com/210712-Richard/2107-Reactive`
* `cd 2107-Reactive`
* 