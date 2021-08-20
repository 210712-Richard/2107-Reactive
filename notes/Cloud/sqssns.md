# Messaging Queues

An entity that takes in messages from another entity that can be retrieved by a third entity.

Messaging queues are important in distributed architectures for ensuring delivery in an environment where we do not know that the recipient of the message will exist when the message is sent. We don't know if the recipient will exist long enough to reply.

Example Message Queues: Rabbit MQ, Kafka, AWS SQS

## Messages

A message is just a payload that needs to be delivered to a location. In an MSA environment a message is usually the payload of an http request to a service. We'd send the http request to the MQ and the service that is meant to recieve the message would poll the MQ to begin processing the request. (Results would also be sent through the queue).

## Benefits
* Easy to scale services as we have decoupled them from one another
  * If we have too many "producer" services for our "consumer" services to keep up, we can scale consumers to meet the demand.
* Create multiple queues for priority requests.
  * Consumers would check priority queues before checking the regular queue or even have dedicated priority queue consumers.
* Buffer our requests.
  * Consumers can't get overloaded with requests because they have to check the queue to get the request in the first place.
  * Requests are safely stored in the queue until they can be processed.
* Resiliency.
  * If a message does not get processed by the first consumer to retrieve it, the queue will offer the message up again to another consumer.

## Queue
FiFo (first in first out) structure that allows items to be added and they will be removed in order. (most of the time, depending on the MQ implementation)

## SQS - Simple Queue Service

An AWS Messaging Queue Service

### Standard Queue

Supports a nearly unlimited number of transactions (messages recieved and delivered) per second per API action

API action (actually calling the SQS API to put messages into the queue).

Message delivery is guaranteed *at least once* (sometimes duplicates might be delivered)

Occasionally messages might be delivered out of order.

### FIFO Queue

Supports 300 API actions per second, allowing up to 10 messages per API action. 300 - 3000 messages per second.

High Throughput mode that supports up to 30,000 messages with batching or 3000 without.

A message is delivered once and remains available until a consumer processes and deletes it. No duplicate processing occurs.

FIFO message order is strictly preserved.

### Features of SQS
* Payload size: 256 kb of text in any format
  * Each 64kb is considered a single request, so a single API call with 256 kb of text counts as 4 requests.
  * Java API allows us to store payloads in S3 to get around the payload size limit.
* Messages can exist in a queue for up to 14 days.
* When messages are received they become "locked" while processing. So if one consumer locks it, other consumers can't get it.
  * These locks have an expiry time at the end of which processing is assumed to have failed and it unlocks.
* Server-side encryption
* Long polling
* Dead letter queues: messages that fail to process can be sent to a DLQ which allows us to process those messages (usually by logging them) to analyze what went wrong.

### Short vs Long polling

**Polling**: Service sends a request to the queue to ask if there are any messages.

**Short Polling:** The service polls the queue and the queue immediately responds with either a message or it says there are no messages.

With short polling, if the queue receives a message after it has already responded, the service has to poll again to retrieve that messages. This can be really inefficient because if there are no messages for an extended period you are still experiencing heavy network traffic as you poll again and again. This can be really good if the service has other things to do between polls.

**Long Polling:** The service poll the queue and the queue responds with a message or waits (up to a specified time) for a message to arrive before responding. In AWS SQS the max polling time is 20 seconds.

### When would we use SQS in a project?

* A serice needs to communicate with another service but should not be tightly coupled to that service.
* We would like to buffer a service from requests from other services.
* We would like to have an SNS topic made into messages we can process (rather than notifications)

# SNS - Simple Notification Service

Fully managed messaging service for both application to application and application to person communication.

SNS allows you to create a *topic* and then allows you to create a *subscription* to that topic. Any time a message is *published* to the topic, all subscribers receive that message.

Subscription methods:
* AWS SQS
* AWS Lambda
* Webhooks(http/s)
* AWS Kinesis Data Firehose
* AWS Event Fork Pipelines
* SMS
* Email
* Mobile push notifications

## Publisher/Subsciber Pattern
When a message is published to the publisher it sends that message to all subscribers. The message is published as an "event" that can trigger processing on any given consumer

## Fan-out
In SQS, we guarantee that one (maybe more in Standard queue) consumer will process a message. If we need to consume data in multiple ways we either need to send it to multiple different queues or we can have multiple subscriptions on an SNS topic.
