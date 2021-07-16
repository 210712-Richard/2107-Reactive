# Throwables
In Java we have a type of Object called a Throwable and that is an object that can be "thrown". It is an object that can circumvent the normal flow of execution and eject out of a method call without returning.

There are two types of Throwable that we care about

                Throwable (C)
                |           |
        Exception (C)       Error (C)
        |
        RuntimeException (C)

## Error

A circumstance in which a reasonable application should not try to recover. Often a problem with the virtual machine.

Examples: OutOfMemoryError, StackOverflowError, VirtualMachineError

## Exception

Represents a circumstance that indicates that something has not gone according to plan. Generally speaking, this means that something was not in the state we expected it to be. (most RuntimeExceptions occur because an object was expected to exist or have a certain state and did not), or that something outside of the application did something that wasn't expected. (SQL database connection failed, or a file didn't exist in the filesystem)

### RuntimeException (Unchecked Exception)

Represents a flaw in your code.

* You mistakenly referenced an index that didn't exist in your array. `ArrayIndexOutOfBoundsException`
* You divided an integer by zero. `ArithmeticException`
* You tried to call a method on an object that didn't exist. `NullPointerException`

### Exceptions that are not RuntimeExceptions (Checked Exceptions)

Represents a risk.

Something you are doing could result in something out of your control going wrong and the compiler is asking you what it should do in the event that something does go wrong.

* You wish to access a file, but you can't guarantee anything exists on the filesystem 100% of the time, because your code can't *know* the file exists. `FileNotFoundException`