/*
There are 3 scopes in JS

Global
Function
Block (ES6+)
*/
console.log(a);
//console.log(h);

var a = 'ten'; // var binds the variable to the scope of the function.
// When outside of a function, the global scope is used.

h = 'fifteen'; // this variables has been defined globally

console.log(h);
console.log(a);

for(let i = 0; i< 5; i++) { // i is block scoped
    console.log('inside for block');
    const block = 'hello'; // block scoped
    //block = 'me'; // const can not be reassigned
    var alby = 'hooray'; // function scoped
    globalFor = 'hooray'; // global scope
    console.log(i);
    console.log(block);
    console.log(alby);
    console.log(globalFor);
}

console.log('outside of the for block')
//console.log(i);
//console.log(block);
console.log(alby);
console.log(globalFor);


let g = 3; // block scoped variable in the global block

function fun() {
    console.log(b);
    global = 'hello';
    var b = 6; // var declarations are 'hoisted'
                // to the top of the function
                // they are scoped to.
    console.log('global: '+global);
    console.log('a: '+a);
    console.log('g: '+g);
    console.log('b: '+b);
    // A function has access to all variables declared within it
    // and all variables declared in the scopes it is defined in.

    // A function has access to variables scoped to it's enclosing function.
}

console.log('calling fun.')
fun();
console.log('finished with fun');
console.log('global: '+global);
console.log('a: '+ a);
console.log('g: '+g);
//console.log('b: '+b);

console.log('---------------');

var h = 3;
function bar(){
    console.log('bar h = '+h);
    var h = 5;
    console.log('bar h = '+h);
}
bar();
console.log('h = '+h);

console.log('---------------');

// CLOSURE
/*
An inner function has access to the scope of an outer function.
**EVEN IF THE OUTER FUNCTION HAS RETURNED**
*/
function foo(){
    this.hidden = 6;
}

var h = new foo();
h.hidden = 3;
console.log(h);

function outerFunction(){
    var hidden = 6;
    return {
        getHidden: function() {
            return hidden;
        },
        setHidden: function(num) {
            hidden = num;
        }
    }
}

h = outerFunction();
console.log(h);


// IIFEs
/*
What if we didn't want to leave a function to be called a second time?
We write a function that will immediately execute (usually as a singleton constructor)
and then the function is no longer in scope and can't be called a second time.

Immediately Invoked Function Expression (IIFE)
*/
let mySingleton = ( () => {
    let hidden = 6;
    return {
        getHidden: function() {
            return hidden;
        },
        setHidden: function(num) {
            hidden = num;
        }
    }
})();// immediately invoke (call) the function
console.log(mySingleton);

// Prototypes
/*
In JS, an object has a reference to another object to which it can delegate tasks (key lookups)
called a prototype. The prototype is essentially the parent object to our object.
Objects inherit all the keys (and their values) of the prototype (and the prototypes' prototype)

Whenever an object is accessed, if the key being requested is not found in the object,
it will look at the prototype (and repeat this process until the key is found or there are no more
prototypes)
*/

let bean = function(){
    this.name = "Bean";
    this.type = "Green";
    this.grow = function() {
        return "I grew 3 inches";
    }
}

console.log(bean);
let greenBean = new bean();
console.log(greenBean);
console.log(greenBean.grow());
console.log(greenBean.name);

let gmoBean = function() {
    this.name = "GMOBean";
    this.grow = function() {
        return "I grew 5 inches";
    }
}

gmoBean.prototype = new bean();
gmoBean.prototype.constructor = gmoBean;

var b = new gmoBean();
console.log(b);
console.log(b.grow());
console.log(b.type);
console.log(b.__proto__.name);
console.log(b.__proto__.grow());

console.log(b instanceof bean);
console.log(b instanceof gmoBean);
console.log(b instanceof {}.constructor);
console.log(b instanceof [].constructor);

// As of ES6, we have class **SYNTAX** in JS
// This still results in prototypes being created
// but you don't have to work with them directly.

class Bean {
    name = "Bean";
    type = "Green";
    grow() {
        return "I grew 3 inches";
    }
}

let classBean = new Bean();
console.log(classBean);
console.log(Bean);

class GmoBean extends Bean {
    name = "GMOBean";
    grow() {
        return "I grew 5 inches";
    }
}

let classGmoBean = new GmoBean();
console.log(classGmoBean);
console.log(classGmoBean.grow());

// no true overriding in JS, just shadowing.
// no true encapsulation in JS, simulate with closure
// what about overloading? no, absolutely not.

// functions are hoisted. So the add variable holds the last function declared for that variable.

function add(num1, num2) {
    return num1+num2;
}

console.log(add(1,2));

function add(num1, num2, num3) {
    return num1+num2+num3;
}

console.log(add(1,2,3));