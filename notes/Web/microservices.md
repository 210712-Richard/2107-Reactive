# Microservices Architecture (MSA)

## Monolith

Every coding practice, convention, methodology, you can apply to a monolith can be applied to a microservice.

The Monolith is how we have been building applications. Every feature of your application lives in a single project and when you run your project you run the code for every feature. When someone submits a reimbursement, that goes to the same place as a request to login as a benco which goes to the same place as a request to dispense reimbursement to it's recipient.

GachaGame
* User Management
  * Managing User's Gacha's
* Gacha Management

TRMS
* Authentication
* Reimbursement (Submission and approval and any kind of editing)
* Payment Service (for dispensing and receiving reimbursement)
* Message Service (for communicating about reimbursements)

In an enterprise environment, a single application in a singe project becomes:
* very large
* hard to maintain
* hard to read
* hard to extend
* difficult to scale (you had to scale vertically just to run it, and now it's expensive to scale horizontally)
* single point of failure (what happens if your payment service crashes? the entire app goes with it!)

If I can't send messages, why can't I submit a reimbursement?

### Pros
* Simple deployment architecture. Just deploy your project x times to x servers. Set up a load balancer to distribute traffic, point the client at your load balancer. Done.
* Easy to deploy. Just put your app on the server.

### Cons
* Change one part of the service, the rest of the app may become unstable.
  * If I make a change to the payment service it could potentially affect another part of the app.
* Need to understand the entire code-base in order to make that change in the first place.
  * Need to know where the parts you want to change even are, and you should try to find out if those parts would affect other parts of the application.
* Entire code-base MUST be written in a single language.
  * If the monolith is written in Java, we can't then write a single part of it in Python even if Python would be better.
* Requires larger machines as the project ages (and thus grows). Making infrastructure expensive.
* If a bug in one feature causes loss of service, the entire app breaks.

## MSA

A fine-grained service that relies on other services to form a single cohesive application. (The layered model in REST)

The code base is inherently maintainable, extensible, easy to scale, and fault-tolerant.

## Pros
* Changes to one service (project/repository) do not endanger other services (usually) *MAINTAINABLE*
  * If you're changing a service, this might require you to modify another service.
  * If every single time you make a change to a service, you have to modify a specific other service as well, that's a sign that they should never have been split.
* Each code-base is digestible as it only has a single domain/feature-set *READABLE*
* Each individual service can use the langauge best-suited for that use-case and your team's skill set.
* Can be run on smaller machines, allowing for cost-efficient horizontal scaling. *SCALABLE*
* Adding new features usually just means standing up a new service rather than modifiying the entire-code base. *EXTENSIBLE*
* If a bug occurs in one service that causes loss of service, that service goes down and the rest of the architure continues to run. *FAULT-TOLERANT*
  * We expect MSAs to fail fast, fail gracefully, and recover quickly.
  * *Circuit Breaking*: If something fails (I sent a request to this server but it was down) we give a default answer back to the client (hey, I know you wanted that, but this should be good enough until that is back up)

## Cons
* Deployment architecture becomes complex. (Very complex very quickly, each service increasing the complexity)
  * We have X services, each service must be replicated (horizontal scaling).
    * Each service can be replicated a different number of times.
  * We have Y additional services to *manage* the services that actually make up our application
    * Gateway Service
    * Discovery Service
    * Configuration Service
    * etc.
  * Understanding your ecosystem requires more cognitive load.
* Difficult to deploy
  * We are no longer deploying a single app. We now have several and we need to ensure that all of them get deployed successfully. Some have different deployment requirements (and environments) than others.
    * Orchestration services like Kubernetes can help.
* Sometimes services need to talk to each other.
  * We solve this with Message Queues.
  * The more a service needs to talk to a particular service, the more likely you've made your services too granular.

## Gateway Service

A service that acts to route requests to the appropriate endpoint without exposing the underlying services to the client.

* Netflix Zuul (now defunct)
* Spring Cloud Gateway (Spring Cloud project)
* AWS API Gateway (Serverlest AWS Service)

## Discovery Service

A service that acts as a registry for all the other services so that we know their locations (ip addresses) and health statuses. Often operates on a heartbeat system where the ms will upon startup send a hearbeat at a specified interval to tell the discovery service that it is alive.

* Netflix Eureka (Spring Cloud Eureka)
* Consul
* Zookeeper

### Eureka

Eureka works on heartbeats and comes packaged with a service called Ribbon. Ribbon is a load balancer and it will help balance requests between multiple instances of a service.

## Circuit Breaking

A library  or service that provides default answers to queries when the actual answers are not available. Essentiall the concept of "Basically Available" taken to it's logical extreme. If it isn't available we still need to give them *something*.

In the Netflix OSS we have Hystrix.

Spring Cloud wants us to implement the Circuit Breaker Design Pattern ourselves.

## Configuration Service

A service that acts as the store for the configuration files for each of our servics. When a service comes up and registeres with Discovery, it asks Discovery for the location of Config, and queries Config for the information it needs to do it's job.

This is good for decoupling specific configurations for dev/test/prod environments form the repository itself. Also good for storing sensitive information in a private repository that only the config service has access to, reducing the risk of leaking that information.

* Spring Cloud Config
* Zookeeper

# Recommended Reading
[Fowler: Decomposing Services](https://martinfowler.com/articles/break-monolith-into-microservices.html)

# CHAOS ENGINEERING