# Logging
Why do we perform logging? Debug situations that resulted in failures, analyze usage of the application, and maintain records for government or business regulations.



## Logging Levels
Logging levels allow us to write logs that have different purposes and to turn off logs whose purposes no longer suit our application.
For example, if I had an application that I had used print statements to perform debugging and I wanted to release that application to production, I either have to release the version that has all those print statements in it or I have to go in and delete every print statement (or comment them out). With Logging, we just log those statements to a specific level and turn that level off when we go to production.

In Log4j, we have the following levels
* ALL - you can't log to the ALL level, but if your level is set to ALL, then all logs from all levels will be logged.
* TRACE - Determining what methods are being called in what order and what they return. TRACE level logs everything in TRACE and all levels below TRACE.
* DEBUG - Printing the state of the application to make debugging an application easier. DEBUG level logs everything for DEBUG and below.
* INFO - Milestones. What, in broad strokes, is happening in the application. INFO and below.
* WARN - Warnings that something bad might happen.
* ERROR - Something bad happened (You usually log exceptions here).
* FATAL - The application is shutting down because something bad happened. (You usually log Errors here).
* NONE - Log nothing.