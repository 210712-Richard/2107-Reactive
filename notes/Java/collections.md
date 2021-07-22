# Collections
An API in Java for storing groups of objects.
Most of the collections in Java inherit from the Collection interface which itself extends Iterable.
The Map interface does not extend Collection but is considered part of the API. Note: A Map is not a Collection it is a collection.

```
        Iterable (I)
            |
        Collection (I)
            |
    ---------------------    
    |       |           |
List(I)     Set (I)     Queue (I)
                        |
                        Deque (I)
```

`ArrayList implements List`
`LinkedList implements List, Deque`