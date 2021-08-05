# Spring

A lightweight, IoC container and AOP framework for Java.

**lightweight**: The framework itself doesn't take very much to run (mostly compared to other frameworks that have died because Spring exists)

Framework of Frameworks, Spring is comprised of many Projects and Modules. It's a Modular framework, meaning base Spring has very few features and you bring in additional modules to add more functionality. The amount of Spring you have to use in your project is highly customizable.

## Spring Modules

Spring at it's core is something called:

### Spring Core

The *core Spring framework* is *just an* IoC Container. As Spring Core is a dependency for every other module, this means that you are taking advantage of IoC and dependency injection if you are using Spring at all.

**IoC**: Inversion of Control -  literally being a framework. The Framework takes control and calls you instead of the other way around.

**Container**: An entity that creates and manages Beans

**IoC Container**: An entity that creates and manages Beans automatically, and performs dependency injection.

**Dependency Injection**: The act of automatically resolving client dependencies to services. Talking about our *Beans* and the *Beans* within those *Beans*. If Object `A` needs an instance of Interface `B`, the IoC Container, when creating an `A` object will check itself for any instances of `B`, and if it finds one, will automatically place that instance in our new `A` object.

```Java
class A {
    B b;
}
```
The IoC container in Spring Core is in charge of creating objects and resolving object dependencies. Anytime you use the `new` keyword, you are *not* Spring. The IoC Container manages Spring Beans and those Spring Beans go through a lifecycle that includes instantiation and dependency resolution. Therefore, if you don't instantiate with Spring, you aren't using it.

[Martin Fowler on DI](https://martinfowler.com/articles/injection.html)

**Spring Bean**: Any object managed by the IoC Container.

