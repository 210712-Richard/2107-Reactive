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

#### Scopes of a Spring Bean

* `singleton`: -default- ApplicationContext makes this bean on startup and never makes a new one.
* `prototype`: ApplicationContext makes the bean when it's asked for one and makes a new one every time.
* `request`: ApplicationContext makes the bean when the framework receives an http request and retains that bean until the response has been sent.
* `session`: ApplicationContext makes the bean when the framework creates an httpSession and retains the bean until that session is invalidated.

#### Bean Annotations

* `@Autowired`: Specifies that this field/constructor/setter is a candidate for dependency injection. Spring will search the ApplicationContext/BeanFactory to find an appropriate implementation of the dependency.
  * When placed over a field, Spring will perform "setter injection" by using Reflection to inject the dependency.
  * When placed over a constructor, Spring will perform constructor injection by satisfying the parameters of the constructor
  * When placed over a setter, Spring will perform setter injection by satisfying the parameters of the setter.
* `@Scope`: Species the lifetime of your Spring Bean
* `@Bean`: Registers the return value of the method as a Spring Bean

##### Stereotypes

In Spring, a stereotype is an annotation that specifies that a Bean should be managed by the BeanFactory/ApplicationContext. The type of stereotype that you use serves to indicate the purpose of the bean in your architecture. Some modules of Spring will look for specific stereotypes.

* `@Component`: General purpose Spring Bean
* `@Service`: A Spring Bean that is intended to be a "Service," or as operations that hold no state.
* `@Repository`: A Spring Bean that is intended to access data, either using the Repository design pattern or the DAO design pattern.
* `@Controller`: A Spring Bean that is intended to handle incoming http requests.
* `@Configuration`: A Spring Bean that registers other beans to the ApplicationContext/BeanFactory using the `@Bean` annotation.

#### BeanFactory and ApplicationContext
[Documentation](https://docs.spring.io/spring-framework/docs/2.5.x/reference/beans.html#context-introduction-ctx-vs-beanfactory)

#### Bean Wiring
In Spring, we can "wire" our Beans in several different ways.
1. `no` - No autowring, you explicitly reference a bean for wiring. (You do this in XML)
2. `byName`: will look for a bean matching the name you specify.
3. `byType`: Will look for a bean of the type that matches. If there is more than one, an exception is thrown.
4. `constructor`: Will look for a bean that matches the parameters of the constructor, if there is more than one, an exception is thrown.
5. `autodetect`: `constructor` and if that fails, go to `byType`

### Spring AOP

This module allows you to utilize the Aspect Oriented Programming techniques that Spring is already using to instantiate and process your beans to perform other tasks.

* **Aspects**: A modularization of a cross-cutting concern. In Spring AOP an Aspect is class that contains various `Advice` that address the concern. We built a LoggingProxy that was essentially an Aspect.
* **Cross-cutting Concern**: An issue that affects multiple objects in an application. If an issue only affected a single object we would just write a method to solve it, but if it affect multiple objects that might be a good candidate.
* **Joinpoints**: Any single instance in the application where `Advice` might be applied. In Spring AOP we can only apply `Advice` to instance method calls, so all instance method calls on Spring Beans are `Joinpoints`.
* **Pointcut**: An expression that targets a specific JoinPoint. Like regexp
* **Proxy**: An advised object. The proxy usually contains the object but when you invoke it's methods the advice will be applied.
* **Advice**: A modularization of a single part of the ccc. In Spring AOP this is a method that will be applied at a specific time (before, after, around the method).
* **Code Weaving**: Spring AOP performs code weaving at RUNTIME to apply advice. Meaning it is modifying your code at runtime using Reflection.

#### Types of Advice in Spring AOP
* **@Before**: Invoke your advice prior to the method call. Cannot affect the method.
* **@After**: Invoke your advice after the method call. Cannot affect the method.
* **@AfterReturning**: Invoke your advice after the method returns normally. May see the return value of the method.
* **@AfterThrowing**: Invoke your advice after the method throws a Throwable. May see the thrown object.
* **@Around**: Actually takes on the task of invoking the method entirely. May do literally anything. Can choose not to call the method at all. Can return anything. Can throw anything. Can catch an exception and choose not to throw it and then return something. Can call the method with ENTIRELY DIFFERENT ARGUMENTS. **POWER**