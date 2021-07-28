# SOA

Service Oriented Architecture: an application designed to be accessed by another application as a service. Primarily refers to business to business (B2B) use cases.

ex. Grubhub exposes an API for China Express to consume. Equifax exposes an API so car dealerships can access your credit score. JPMC exposes an API for JPMC to consume. JPMC exposes an API for other banks to consume.

## Properties of a SOA
1. Represents a business outcome.
   1. Trying to access a functionality of the business.
      1. Grubhub allows China Express to receive orders made to Grubhub
      2. Equifax exposes credit scores.
      3. JPMC exposes the functionality to send an email to another service.
      4. JPMC exposes the ability to apply for a loan.
2. Self-contained.
   1. The client sends a message to one thing, and that thing sends a response back and it done.
      1. The service is allowed to send additional requests to other services, but the client only sent one.
3. Black-box to consumers/clients.
   1. Input comes in.
      1. China Express' authentication information
      2. Your social security number
      3. Email Address, and message
      4. loan information
   2. Output comes out.
      1. Pending orders
      2. Your credit score
      3. Nothing
      4. A loan
   3. What happened between? You don't know.
      1. You don't see payment or personal information
      2. How is the score calculated?
      3. How is the email sent?
      4. How is risk calculated?
4. May consist of underlying services.
   1. The credit score service might need to call a bank service to get information.
   2. The email service might need to call another service to actually send an email.

## Web Services

Applications designed to be accessed by another application as a service over the internet.

A SOA over the internet.

### SOAP

Simple Object Access Protocol

This is a protocol (a set of steps) for sending and receiving resources from a service.

Rigidly defined in XML and communicates in XML. (WSDL and Envelope) Messages are sent over any transport protocol that can handle XML messages and it has fallen out of use as people migrate to REST

### REST

*Re*presentational *S*tate *T*ransfer

This is an *architechture* that utilizes the http(s) protocol to access the "representational state" of entities on the service.

#### Representation State

A copy of the state of an entity on the service.

Not necessarily and usually not the entity itslef, as that entity only exists on the server or database. It is a copy of that enitity that we can use to mutate/replace/create entities on the service.

REST is all about the practice of utitizing representational states of an entity to mutate the actual state of that entity.

Often, the representational state is an XML or JSON file.

ex. A string representation of an object. If we change that string and send it back, the service should read the new string and make those changes.

#### Interface

HTTP/S: We access the application by sending GET, PUT, POST, DELETE, PATCH, OPTIONS, CONNECT, HEAD and/or TRACE requests to it.

Data can be transmitted in any language agnostic format, usually XML or JSON.

#### REST Constraints
1. Client-Server Architecture
   1. Seperate the user interface from the data.
   2. Separate the end-user from the data.
2. Statelessness
   1. The service should not maintain state.
   2. All state should exist elsewhere (usually a database of some kind)
   3. How do we get around this for session data?
      1. Store it as cookies.
      2. Store tokens (as cookies) that correspond with data in the database.
3. Cacheability
   1. Data can be cached by a browser/client
   2. Each response would categorize it's data as cacheable or non-cacheable.
4. Layered System
   1. Underlying services
5. Code on Demand (optional)
   1. The service can serve executable code. (send a js file to be run on the client instead of doing the processing ourselves)
6. Uniform Interface
   1. Resource Indentification in Requests
      1. We identify our resources through the use of a URN.
      2. The URN of the resource can be found in the request itself.
      3. ex. GET request to `/cats` | GET request to `/restaurants/McDonalds`
   2. Resource Manipulation through Representations
      1. When we try to update a resource we send a representation of that resource with the changes we want to the server. The server then updates the corresponding entity with that data.
      2. ex. PUT to `/restaurants/McDonalds` but *our version* of that has an extra menu item.
   3. Self-descriptive messages
      1. GET request shoul retrieve something
      2. If you can't find something, the response should have a 404
      3. DELETE requests should remove something.
      4. if the `application/json` header is present, the body should contain JSON data.
      5. If the request was successful, the 200 status code should be there.
      6. The request was not successful, the 200 status code should not be present.
   4. Hypermedia as the Engine of Application State (HATEOAS)
      1. We can represent other entities on the service that exist inside of an entity as URIs within that entity.


#### RESTful API Design

##### Collections
`https://mycats.com/cats` represents a collection of cat entities. Any operation on this URI affects the entire collection of cats. Collections should be plural.

GET - return a representation of the collection (often as a list).
POST - create a new member of the collection
PUT - replace the entire collection
DELETE - delete the entire collection
PATCH - update the collection with the parts of the collection included in the PATCH

##### Resources
`https://mycats.com/cats/Sprankles` or `https://mycats.com/cats/1` represents a specific cat. The collection followed by the id of the cat we want. Any operation done on a resource should only affec that resource.

GET - return a representation of the resource.
POST - create an entity inside that resource (maybe a kitten)
PUT - replace the resource or creates it if it doesn't exist (this might not be allowed in some apis)
DELETE - delete the specific resource
PATCH - update the resource with the parts of the resource included in the PATCH

##### Guidelines
* pluralize collections
  * /cats
  * /books/
  * /restaurants/1/menuitems
  * /octopi
* DON'T USE VERBS
  * Your HTTP methods are your verbs. You don't put them in the url
  * /cats/1/add-cat is VERY BAD
  * POST to /cats/1/kittens (add a kitten to cat number 1)
  * POST to /cats (add a cat to the collection)
  * /cats/1/update-name is VERY BAD
  * PUT to /cats/1 (update cat 1)
  * PUT to /users/Richard/checking (send an object representation of the checking account with the new balance in it)
  * /users/Richard/checking/deposit is bad.
  * /users/CreateNewUser is bad
* Make your endpoints descriptive
  * /z/3 is bad
  * /cats/3 is good

## Richardson Maturity Model [Fowler](https://martinfowler.com/articles/richardsonMaturityModel.html)

How RESTful is your service? Forget the constraints, that tells us IF your service is a REST service. The maturity model measures how good your service is at being a REST service. How did you desing your endpoints and interact with them?

* Level 0: Not restful at all, we use HTTP as a transport protocol and nothing else is defined.
  * We have an endpoint we send requests to that endpoint, all requests go to the same endpoint regardless of what they're trying to do.
* Level 1: A little RESTful - Introduces Resources
  * Each resource now has it's own endpoint that we can send requests to.
* Level 2: Sorta RESTful - Introduce HTTP Verbs
  * We determine WHAT we are doing by examining the http verb of the request.
* Level 3: Probably RESTful - HATEOAS
  * Once we are representing other resources within our resource in a way that allows our client to obtain that resource we are self-documenting.