# Garbage Collection

The process of freeing memory, that had previously been allocated to objects that are no longer in use, automatically. In C, you allocate memory yourself and must actively free it when you are done or you run out memory eventually, leading to bugs, security issues, and just slow performance. In Java, we have an entity called the Garbage Collector. Memory is allocated automatically for us, and when we stop using that memory it is the GC's job to pick up behind us.

## How does it know?
How does the GC know to pick up our trash?
References!
If we have, in scope, a reference to the piece of memory, GC won't touch it. If any variable we've defined, (or a library we're using has defined) is pointing to a memory address, GC won't collect it.
GC counts the number of references to an object and only collects that object once that number hits zero. It should be noted, that once we have no references to an object we never again can *have* a reference to that object.

## When does this happen?
Once GC has seen that an object has no references it *marks* it for collection. This does *not* mean GC has freed that memory. It is entirely possible for GC to *never* free that memory. GC runs when it determines it is necessary and only frees the memory it determines it needs to. The Garbage Collector runs concurrently with your code and the less it runs the better your code performs.

**NOTE** YOU CANNOT FORCE GARBAGE COLLECTION TO COLLECT ANYTHING

### Mark
The Mark phase is when the GC looks through memory and marks objects for deletion. It doesn't free memory when it is doing this.
### Sweep
The Sweep phase is when GC frees marked objects. It doesn't necessarily free *all* marked objects.

## Types of GC
In Java currently there are 5 Garbage Collectors.

* *Serial* - Works on a single thread, stopping your code until it is finished.
* *Parallel* - Utilizes multiple threads so it doesn't need to stop your code to run, but isn't very efficient.
* *CMS - Concurrent Mark Sweep* - runs while your code runs and is more efficient than Parallel
* *G1 - Garbage First* (JDK7 onwards) - partitions the heap into several regions and frees memory to keep those regions as empty as possible.
* *C4 - Continously concurrent compacting collector* - will actively relocated marked memory to compact the used memory leave as large a contiguous free space as possible (essentially defragging the machine).

# Memory
In programming we generally talk about two types of memory. We have memory locations that exist only for code that is actively being executed (Stack memory) and we have locations for everything else (Heap memory).

## Stack

Refers to the memory allocated to variables that are in scope. All declared variables are stored in the Stack and as such all primitive values exist in the Stack. *NOTE*: I'm oversimplifying, as Class and Instance scoped variables generally exist in Heap space.

Reference variables themselves exist in the stack and if they store a memory address, the address usually points to the Heap.

## Heap

Heap memory is your "actual system memory" in that you actually store data and the stack references that data. When the stack reference disappears, objects stored in the heap do not.

When you create objects they are created in Heap memory (this includes the String Pool), and the GC reigns over the Heap.

## Reference Variables
So far, we've seen two types of variables. A primitive which stores a value, and a reference when stores a memory location.

However, there are actually two types of reference variable and we've only ever used one.

1. Strong reference: The default reference type. So long as this reference exists, an object is not eligible for GC. ex. `Hello h = new Hello()`
2. Weak reference: This is a reference to an object that can still be eligible for GC if there are not any strong references to it. Mostly used by frameworks and libraries that create connections to other entities so that those connections can be kept track of without rendering them ineligble for GC when *we no longer need them*.

You have a weak reference to a resource in the static context with a strong reference in an object scope. If you GC the object that contains the Strong reference we don't need the resource anymore either, and it should be GC'd.

```Java
Connection c = new Connection(); // strong reference
WeakReference<Connection> weakref = new WeakReference<Connection>(c); // weak reference
c = null;
// Connection is eligible for GC despite our weak reference still existing.
```

There are 2 types of weak references
1. Soft reference: Memory is eligible for GC but will only be collected if necessary
2. Phantom reference: objects are eligible for GC but before removing them from memory, they get put into a queue.