# Good Practices
## Watch Words
* Readability: Your code should be easy for another (and you) to understand
  * Conventions help other people read your code.
* Scalability
* Extensibility
* Reusability: When it is possible a reusable code base is going to save you time and hopeful increase the readabilty of your code.
* Maintainability
## Definitions
* *Explicit*: Stated visibly. Physically exists within the code.
* *Implicit*: Implied. Not stated. Exists in the code without being written.
* *Loose coupling*: The practice of separating specific code implementations from another such that you could replace those implementations without affecting any other code.
  * ex: Presentation layer: Javalin. Data Layer: Files.
    * With loose coupling (if you do it well), you can replace your entire data layer (the files) with database access code (Cassandra) and not touch the Javalin code at all.

## Naming Conventions
### Java
* Packages: reverse.domain.name
  * ex. com.revature.automobile
* Classes: PascalCase
  * ex. FourLiterEngine
* Methods and Variables: camelCase
  * ex. myConnection
* Constants: UPPER_CASE
  * ex. BREATHABLE_AIR