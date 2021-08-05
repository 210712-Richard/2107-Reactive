# Aspect Oriented Programming

Aspect Oriented Programming is a paradigm in which we solve problems by applying "Aspects" to "cross-cutting concerns".

* **cross-cutting concern**: A problem that affects multiple entities (several classes, or multiple layers) in your application.
    * Logging - it affects your entire application, all methods in all classes. you have to write `log.trace(method invoked)` every time you write a method.
    * Security - Only certain people should have access to certain information, why not apply that logic to affected entities rather than writing that logic in every method that needs it.

**MODULARITY**: Aspect Oriented Programming is the ultimate modularization technique. Rather than writing a method in a util class and importing that method and calling that method and dealing with the results which is more boilerplate code we can just write a method in an Aspect and have that Aspect automatically apply itself to our code.