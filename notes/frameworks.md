# Library or Framework

When you pull code into your project and you utilize that code there are two major philosophies that that code can follow. The most familiar one is going to be that of a library.

**Library** is code that you call and once it has finished it's task returns control back to you. When utilizing a library, you start the application, you call the code, you get control back, and then you can call another library if you want. log4j is library

**Framework** is an application that you pull in and write code to complete. It calls you. When utilizing a framework, you start the framework, it calls your code, and you return control back to the framework.

Often a Framework is a partially completed application. You need to write the missing pieces.

Javalin: All the code to recieve an http request, parse it, and route that request to Java functions is written for you. You merely write a handler and tell Javalin about it. Javalin receives requests and routes those requests to the correct location... calling your code. When your code finishes executing, Javalin takes control again and actually sends the response back to the client.