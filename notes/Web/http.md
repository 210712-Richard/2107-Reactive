# HTTP
Hyper-text Transfer Protocol is a means of communicating over the internet. The protocol consists of requests and responses.

## Request

A request has two pieces: A header and a Body.

The Header has metadata about the request itself including:
    * The destination of the request (a url usually)
    * The type of request (an http verb)
    * encryption information
    * multi-part message headers
    * etc...

### Verbs of HTTP
The types of request you can make:

* *GET* - Returns a resource. No information in body of request
* *POST* - Creates an entity on the server. We do not know the final destination of the created resource.
* *PUT* - Places an entity at a specific location on the server.
* *DELETE* - Removes an entity on the server
---
* *PATCH* - Updates an entity on the server
* *HEAD* - Retrieves the headers for an endpoint. No information in body of response.
* *CONNECT* - try to establish a connection to the server
* *TRACE* - I don't actually care
* *OPTIONS* - Returns a list of verbs that are available at an endpoint

## Response

An Http response is also made up of a Header and a Body.

The header has information like:
    * The origin of the data (a url usually)
    * The status code of the response (a number)
    * encryption information
    * multi-part message headers
    * etc...

### Status Codes - THE SERVER SPEAKS

#### 100 - Informational - I'm doing something.
The server is currently operating on your request, and is telling you to hold on.
* 100 - Continue - I've received part of your request, please send me the next part.
#### 200 - Success - I've done it.
The server has finished processing your request successfully and is sending you the results.
* 200 - Ok - Nothing went wrong.
* 201 - Created - You asked me to make something and I did.
* 204 - No Content - You asked me to do something but I have nothing to send you as a result.
#### 300 - Redirects - What you asked for is actually elsewhere
* 301 - Moved Permanently - I'm sending you *there*, please do not come *here* in the future.
#### 400 - Client Errors - YOU did something wrong
* 400 - Bad Request - You sent me a request, something is wrong with, I don't understand.
* 401 - Unauthorized - You might have access, but you aren't authenticated so you don't.
* 403 - Forbidden - You do not have acess, even if you are authenticated.
* 404 - Page Not Found - There is nothing at that URI.
* 405 - Method Not Allowed - You sent a PUT to a resource that I do not allow you to PUT to.
* 409 - Conflict - This request would invalidate the state of the server. (Usually this means you tried to create something with the same id as something that already exists. Maybe you created a user with a username that was already taken.)
* 418 - I am a Teapot - You asked me to make coffee, but I am a teapot and cannot make coffee.
* 451* - Unavailable for Legal Reasons - Fahrenheit 451 (about government censorship). The title is meant to be temperature at which books ignite. Content was taken down because the government requested it to be removed. Usually used for copyright claims.

#### 500 - Server Errors - *I* did something wrong
* 500 - Internal Server Error - The code entered an error state. In Java, this is usually an exception was thrown and not handled.
* 503 - Service Unavailable - The server went down temporarily.


# URI, URL, URN

URI: Uniform Resource Identifier - A string of characters that is used to identify a name or resource on the internet by location, name, or both.

URL: Uniform Resource Location - A URI that specifies *WHERE* a resource is available and *HOW* to retrieve that resource. A URL has to specify a protocol and a domain. ex: `http://mywebsite.com` and `http://mywebsite.com/cats/1`

URN: Uniform Resource Name - A URI that specifies a unique name for a resource. ex. `/cats/1` and `http://mywebsite.com/cats/1`