# Java

Java was an answer to a problem: C

1. Object-Oriented Programming
   1. C is a procedural language
2. Portability
   1. write once, run anywhere: Java can run anywhere that has a JVM
   2. In C, you write a program, and you have to compile it for each target architechture you wish to run it on. That architecture in turn, must have a compiler that was written for it. In C, if you wish to distribute a program, you have to manage multiple executables.
3. Memory Management
   1. In C, you have direct control over memory management. You manage allocation and free memory. This is amazing for applications which need to have excellent performance (like operating systems and very small computers), but can lead to serious security vulnerabilities and errors.
   2. In Java, memory is managed for you and you don't have any control over it.

## Features of Java

### Write once, run everywhere.

Java runs on a virtual machine called the Java Virtual Machine (JVM). You download a JVM for your system and you can run (nearly) any Java program (that isn't a higher version than your JVM). The JVM acts as an intermediary between the program and the actual operating system.

You get a copy of the JVM when you download a JRE

The JRE (Java Runtime Environment) contains the JVM and certain libraries that the JVM requires to run applications.

The JDK (Java Development Kit) contains the JRE, a linker, and a compiler.

### Automatic Memory Management

Java automatically allocates and deallocates (frees) memory as needed. When you create an object, Java allocates memory for that object. When you stop using that memory, the Garbage Collector can and might deallocate that memory automatically. When the program ends, all the memory it was using gets freed.

### Object-Oriented Programming

A programming paradigm.
* *paradigm*: A way to think about a problem. (a way to view the world)
* *programming paradigm*: A way to approach solving a problem.

*Procedural Programming*: A progamming paradigm in which you write code that executes in sequence.
*Object-Oriented Programming*: A programming paradigm in which we model the problem as objects and manipulate those objects to solve it.

#### Four Pillars of OOP
* Inheritance
  * The idea that an entity can take on the properties and behaviors of another "parent" entity and optionally modify them.
* Polymorphism
  * The idea that an entity can behave differently in certain situations.
* Abstraction
  * The idea that we can reveal behavior without revealing implementation.
* Encapsulation
  * The idea that an entity can restrict access to its properties from other entities.

### Functional Programming