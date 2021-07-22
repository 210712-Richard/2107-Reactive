# Stream API
A functional programming approach to working with a "theoretically infinite" group of objects. We wish to consume these objects and filter or mutate them in some way and we wish to do it in such a way as we only process that stuff when we need it.

*Lazy* API: No processing occurs until a "terminal" function is called upon the stream.

## Intermediate Functions
* `distinct()`: Returns a stream containing only distinct/unique values from the Stream.
* `filter()`: Returns a stream of all elements of the stream that passed the given `Predicate`
* `flatMap()/map()`: Returns a stream of all elements resulting from applying the given `Function` to each element. Used to transform each element of the stream in some way.
* `mapToDouble()/flatMapToDouble()`: Returns a DoubleStream resulting from applying the given `Function` to each element.
* `mapToInt()/flatMapToInt()`: Returns a IntStream resulting from applying the given `Function` to each element.
* `mapToLong()/flatMapToLong()`: Returns a LongStream resulting from applying the given `Function` to each element.
* `limit()`: Returns a stream consisting of the elements of the stream but no longer than the given size.
* `peek()`: Returns a stream consisting of the elements of the stream while performing the action specified by the `Consumer`. `peek()` is usually used to print the elements of the Stream to check previous processing you've done.
* `skip()`: Returns a stream consisting fo the elements of the stream without the first x numbers.
* `sorted()`: Returns a stream consisting of the elements in the Stream but sorted either according to the `Comparable` or a given `Comparator`.

## Terminal Functions
* `allMatch()`: Returns true if every member of the stream matches a given `Predicate`
* `anyMatch()`:Returns true if any member of the stream matches a given `Predicate`
* `collect()`: Returns a `Collection` of values in the stream using the given `Collector`
* `count()`: Returns the number of elements in the stream.
* `findAny()`: Returns an `Optional` describing an element of the stream, or an empty optional if the stream is empty.
* `findFirst()`: Returns an `Optional` describing the first element of the stream, or an empty optional if the stream is empty.
* `forEach()`: Applies a consumer to each element and returns nothing.
* `forEachOrdered()`: Applies a consumer to each element and returns nothing.
* `max()`: Returns the "largest" element of the stream according to the given `Comparator`
* `min()`: Returns the "smallest" element of the stream according to the given `Comparator`
* `noneMatch()`: Returns true if no member of the stream matches a given `Predicate`
* `reduce()`: Returns an optional that is the reuslt of performing a reduction on the elements of the stream using the provided `BinaryOperator`, taking all elements and returning a single element;
* `toArray()`: Returns an aray of all elements in the stream.

## Optional
An object representation of something that *might* exist. Really good for avoiding null-pointer exceptions.

* `isPresent()`: Returns true if there is a value in the Optional.
* `ifPresent()`: Invokes a `Consumer` function on the value if one exists.
* `get()`: Returns the value if present or throws `NoSuchElementException`
* `orElse()`: Returns the value if present or a supplied value otherwise.