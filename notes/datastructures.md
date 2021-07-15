# Some Datastructures
A way to store data.

# Lists
A list is a way to store a group of data such that we can reference them singly or as a group. This is like an Array, but without a fixed size.

## LinkedList
We place data into something called a "node" and each node knows where the next node is.

Like a spine. Each vertebrae is a piece of data and is attached to the next one. 
* *singly-linked list*: A LinkedList in which the "head" node is linked to the next node and so on.
* *doubly-linked list*: A LinkedList in which the "head" node is linked to the next node and the second node is linked back to the head node, and so on.

### Pros
* Very fast at insertion and removal
### Cons
* Very slow at retrieval

## ArrayList
A list that just uses an array as it's data structure. When you try to insert something that doesn't fit, it creates a new array automatically and then moves all the values into the new array and then inserts. If you remove something, it will move all values into the empty spots, etc.

### Pros
* Very fast at retrieval
### Cons
* Very slow at insertion and removal