# Maven

A project management tool for Java. Maven uses a Project Object Model to define the lifecycle, dependencies, and documentation of a Java Project.

## Project Object Model

An XML file that defines the project. The XML representation of your project tells us what version of Java you are using, what kind of artifact you are producing, how to produce that artifact, and what dependencies your project needs in order to run.

Anyone who pulls your project from the repository has your `pom.xml` and therefore can have Maven manage the lifecycle and dependencies of your project automatically without having to do a bunch of setup.

### Project Coordinates
```xml
    <groupId>com.revature</groupId> <!-- organization's package structure -->
	<artifactId>GachaGame</artifactId> <!-- Actual name of the project -->
	<version>0.0.1-SNAPSHOT</version> <!-- What version of the project this is -->
	<packaging>jar</packaging> <!-- What kind of artifact gets built? --> 
```

---
**Aside about Packaging**
In Maven we have 3 ways to "package a project" (Create a compiled artifact that we can give to others)
* `jar`: *J*ava *Ar*chive - A grouping of Java classes either as a library or as an executable.
  * To use a jar as an executable it must have a manifest that specifies a main method.
* `war`: *W*eb *Ar*chive - A grouping of Java classes that is intended to run on a web server.
  * People often think of wars as being a grouping of jars, but this isn't 100% accurate
* `ear`: *E*nterprise *Ar*chive - You don't see this much anymore as it was an EJB standard. A grouping of multiple projects that can be deployed simultaneously.
* `pom`: Save your Project Object Model as something we can inherit in other projects to obtain those default values.
---

## Project Lifecycle
The phases of building a Maven project.
[Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
* validate - make sure the project is set up correctly (dependencies and etc.)
* compile - run the Java compiler
* test - run our unit tests
* package - create our build artifact
* verify - ensure the package is correct by checking integration tests
* install - intall the artifact to our local repository
* deploy - intall the artifact in a remote repository

## Maven Repository
*Not a git repository*

The public maven repository in the cloud from which Maven is downloading artifacts.

You can configure your project to upload to the public repository or to your own private repository so that others can utilize your projects as dependencies for their own projects.

We also have a local repository on our own computers that store these artifacts so that Maven doesn't have to download them every time you run the application. The repository is stuck in a folder called `.m2` usually in your user directory.


### Create a Maven Project in Eclipse
* File -> New -> Maven Project
* Check Simple Project
* Next
* Group Id: Package name
* Artifact Id: Project name
* Finish