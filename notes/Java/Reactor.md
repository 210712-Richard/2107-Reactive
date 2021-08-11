# Project Reactor

**Publishers**: An entity that implements the `Publisher` Interface and can be subscribed to. In other words, a Publisher is an Observable.
    * **Flux**: A publisher that represents ZERO or MORE entities
    * **Mono**: A publisher that represents a single entity.
      * The first element that is published will also send an `onComplete` message and close the subscription.