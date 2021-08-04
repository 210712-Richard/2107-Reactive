# Threads

Thread - A sequence of instructions carried out by a computer

Threading - The process of creating multiple threads to achieve parallel processing

Parallel processing - Running multiple computations simultaneously.

Blocking Architecture - When the machine cannot run anymore threads and must wait for one to finish before starting another.

Many processors allow for multiple threads of execution at the same time.

A **Thread** has it's own stack but shares heap memory with other threads.

**Stack**: Instructions that are being carried out. Reference variables are stored in stack frames which usually correspond to method calls. Stacks are last in first out.

**ex.**: I call the `main` method. This puts `main` on the stack with all of it's reference variables. If `main` calls `calculate` then `calculate` gets put onto the stack and must finish execution before we go back to `main`.

## Java
Thread class - an object that represents a thread.

A thread object has two important methods:
    * `start()`: The method that actually creates a new thread of execution and then calls `run()`
    * `run()`: The method that contains the implementation/calculations to be performed by the new thread.

To create a thread that does something, you have to extend `Thread` and override `run()`. If you override `start()` you can ruin the `Thread`.

### Thread States
* *NEW*: A new thread upon which execution has not begun.
* *RUNNABLE*: A thread that is currently executing.
* *WAITING*: A thread is waiting for another thread to complete execution.
* *TIMED_WAITING*: A thread that is waiting for a particular length of time.
* *BLOCKED*: A thread that is trying to access a resource that is not available. (usually a synchronized method)
* *TERMINATED*: A thread that has completed execution.

### Problems
* **Deadlock**: T1 needs A and B, it gets A but can't get B so it has to wait. T2 needs A and B, it gets B but can't get A so it has to wait. Because each has one of the resources the other needs, neither can continue and we have *deadlock*.
* **Livelock**: T1 needs A and B. It gets A but can't get B so it lets A go. T2 needs A and B. It gets B but can't get A so it lets B go. T1 needs A and B. It gets A but...
  * Canadian Standoff: T1 says "after you". T2 says "after you". T1 says "After you." etc...
* **Starvation**: T1 and T2 are sharing resource equally. When T1 lets A go, T2 gets it. When T2 lets it go, T1 gets it. Everything is fine, except that T3 also needs A but can't get it because the other two threads always have it. T3 is *starving*.
* **Race Conditions**: When two threads try to access a resource at the same time and unwanted behavior results based on "who got there first".

#### Producer/Consumer

**Producer**: An entity that makes something that needs to be processed available.

**Consumer**: An entity that processes something that needs to be processed.

Producer Consumer Problem is when either the Producers or the Consumers end up starving out the other, and you either end up with Consumers with no data to consume or Producers that produce data faster than Consumers can consume it. This can be fixed with Thread-safe datastructures.

### Monitor Design Pattern
See Example

### Executor Service and Callables
See Example