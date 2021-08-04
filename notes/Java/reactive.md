# Reactive Programming

A declaritive programming paradigm concerned with data streams and propagation of change.

In Reactive programming we declare what is to happen when the data arrives and then it happens and we are not concerned with the when or the how. What we are concerned about is the transformation and/or processing of the data as it comes to us.

*imperative* - You are giving orders to the machine. You write out explicitly everything you wish to happen. Imperative programming is characterized by the use of control flow statements like `for`, `if`, `switch` and `while`

*declaritive* - You are telling the machine what you want to happen. You write out the goals and the actual control flow is handled elsewhere.

## Reactive Manisfesto [Reactive Manifesto](https://www.reactivemanifesto.org/)

* Responsive - The system responds in a timely mannner if at all possible.
* Resilient - The system STAYS responsive in the face of failure. (if one component fails, the rest of the system is able to continue)
* Elastic - The system STAYS responsive under varying workload. (if 1,000 requests it performs similarly to how it performs if 1,000,000 requests)
* Message Driven - Relies on asynchronous messages to ensure loose coupling.

## RxJava [RxJava Wiki](https://github.com/ReactiveX/RxJava/wiki)
A library for composing asynchronous and event-based programs by using observable sequences.

*Observable sequences* - a stream of data
*event-based program*  - an application that performs operations in response to an event occurring
*asynchronous* - able to execute out of order.

We can write applications that are able to perform tasks while they wait for data to stream in and finish processing.

### Observer/Subscriber Pattern
* **Observer**: an object that wishes to be notified when data is available/changes. The observer *subscribes* to an Observable.
* **Observable**: an object that receives data from a data source and allows an observer to subscribe to changes to it's state.

### Subjects
An entity that acts as both an Observer and an Observable.

It can subscribe to an Observable and Observers can subscribe to it.

Can be useful for processing data before the end-consumer receives it.

### Schedulers (Week 4)
Schedulers dictate concurrency behavior for subscribers.

* IO: Reuses any threads that are done with their tasks and creates a new one if none are available.
* NewThread: Creates a new thread for every task
* Single: same thread. (it will interrupt current tasks)
* Trampoline: same thread, but waits for previous tasks to complete
* from: Allows you to wrap an Executor as a scheduler