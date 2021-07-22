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

##### Access Modifiers
1. public - Anything can see this.
2. protected - All children and things in the same package can see this.
3. (no modifier) - things in the same package can see this
4. private - only visible within the class.

##### Inheritance vs Composition
Inheritance is the idea that something *is* something else. A dog IS an animal.
Composition is the concept of one entity containing another entity. A Dog HAS a name.
A Bicycle having two Wheel objects is an example of COMPOSITION.
A Bicycle inheriting the MovableObject Interface is an example of INHERITANCE.

### Functional Programming

A programming paradigm in which we write functions (without internal state) to manipulate data to solve a problem.

* *Pure functions*: A function that produces the same output for the same arguments no matter how many time you call it, and has no side-effects. A pure function should not modify any of the arguments it has been given, instead creating copies and manipulating those copies.

#### Functions vs Methods

A function exists as a first-class object. It is a piece of execution that we can use as parameters to other functions, returned from a functoin, and stored in data structures.

A method on the other hand is part of an object that can be called when you have access to that object.

#### Functions in Java

Java 8 introduced the `java.util.function` package which contains a number of Functional Interfaces

A *Functional Interface* is an interface containing only a single abstract method that can be instantiated as a Function.

To implement a Functional Interface as a function in Java, we create a `Lambda`.

##### Lambdas

`(parameters) -> returnValue`

```Java
(parameters) -> {
  // implementation
}
```
