# JSON
JavaScript Object Notation
A notation that was developed to create JavaScript objects from string literals.

```JavaScript
let obj =  {person: "Hello"} // <== This is an object literal, which is essentially JSON
```

JSON is very convenient for JavaScript applications to send and receive. Since all* applications on the internet use JavaScript, any server that a JS application might contact should be able to handle JSON.

*Not true obviously, but for our purposes might as well be.

JSON has become such a widely used standard that applications that *don't* intend to be consumed by JS applications use it.

B2B - How can I send data that Business X is going to be able to consume?
* XML - which everyone hates
* JSON - which everyone tolerates/likes.
* your own proprietary format that you created and no one understands.

## What is it?

```json
{
    "person": {
        "name": "Richard",
        "age": 508
    },
    "food": "Carrot",
    "tools: [
        "Screwdriver",
        "Wrench",
        {
            "name": "Jeffery Bezos"
        }
    ]
}
```
Syntax:
* `{}` - Object
* `[]` - Array
* `"key": "value"` - entry inside an object
* `,` - fields are comma-separated