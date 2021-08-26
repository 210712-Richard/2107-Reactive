/*
JavaScript Types:

    Primitives:
        number (Number)
        string (String)
        symbol (Symbol)
        boolean (Boolean)
        null
        undefined
    
    Objects (the above wrappers, Arrays, Functions)
*/

a = "ten"; // string
b = 10; // number
c = true; // boolean
d = {}; // object
e = null; // null
f = ''; // string
g = (0/0); // NaN - Not A Number - number
h = []; // array
i = function() {}; // function
//j; // undefined

var list = [a, b, c, d, e, f, g, h, i];

// standard for 
// for(let k = 0; k< list.length; k++) {
//     console.log(list[k]);
//     console.log(typeof(list[k]));
// }

// enhanced for loop (for-in loop)
for(let element in list) {
    // for-in loops iterate over all the keys in an object
    console.log(element);
}

// enhanced for loop (for-of loop)
for(let element of list) {
    // for-of loops iterate over all the values in an object
    console.log(element);
}

// the Array prototype has several functions that we can make use of and forEach is one of those.
list.forEach(
    // takes in a callback function that it calls for each element
    (value, index)=>{
        console.log(index+' '+value);
    }
);

// Given the following code, what prints?
let index = 0;
while(index<3) {
    console.log(index++);
}

// switch statements do not perform type coercion
switch(index) {
    case 1: console.log("one"); break;
    case '3': console.log("three"); break;
    default: console.log("default"); console.log(index); break;
}

let string = 'single quotes I need to escape the \' character';
string = "double quotes I need to escape the \" character";
string =  `backticks I need to escape 
the \` character`; // this is also called a template literal and more on that later.