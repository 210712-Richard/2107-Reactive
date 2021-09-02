// default parameters
function fun(p1=5) {
    console.log(p1);
}

fun();
fun(3);

// Enhanced Object Literal

let o = {
    name: 'Richard'
}

let myName = 'Richard'

let eo = {
    myName
}

console.log(o);
console.log(eo);


let arr = ['Giraffe', 'Frog', 'Goat']
let arr2 = [60, 2, 5]

let animals = {
    [arr[0]]: arr2[0],
    [arr[1]]: arr2[1],
    [arr[2]]: arr2[2]
}
console.log(animals)

// timing events

setTimeout(fun, 1000) // takes a callback function and a time in ms
// At the end of that time, the callback function is placed in the event queue.
console.log('hi');

let x = setInterval(fun, 1000)

setTimeout(()=>{
    clearInterval(x);   
}, 10000)


// this
function playingThis(){
    console.log(this);
}
console.log(this);
playingThis();
// if we use the new keyword, this now refers to the object we are creating.
let object = new playingThis();

o = {
    play() {console.log(this)}
}
o.play()

let a = ()=>{
    console.log(this)
}

a();
// arrow functions can not be used as constructors
// new a();

function outer(){
    console.log(this)
    function inner(){
        console.log(this)
    }
    inner();
}

outer();
new outer();


function outer2(){
    console.log(this)
    let inner = ()=>{
        console.log(this)
    }
    inner();
}

outer2();
new outer2();

// further reading call(), bind(), apply()


// Symbol: A primitive data type that is essentially a uuid
let s = Symbol()
console.log(s);