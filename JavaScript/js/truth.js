// Thruthy vs Falsey values

// Truthy = a value that is considered to be boolean true by JS
// Falsey = a value that is considered to be boolean false by JS

// truthy values in JS = non-zero numbers that are not NaN, non-empty strings, non-null objects, and true
// falsey values in JS = zero, NaN, empty string, null, false

if(10) {
    console.log('10 is true');
} else {
    console.log('10 is false');
}

if(-10) {
    console.log('-10 is true');
} else {
    console.log('-10 is false');
}

if(0) {
    console.log('0 is true');
} else {
    console.log('0 is false');
}

if((0/0)) {
    console.log(`${0/0} is true`);
} else {
    console.log(`${0/0} is false`);
}

if("wat"-1) {
    console.log(`${"wat"-1} is true`);
} else {
    console.log(`${"wat"-1} is false`);
}

if(true) {
    console.log('true is true');
} else {
    console.log('true is false');
}

if('false') {
    console.log('"false" is true');
} else {
    console.log('"false" is false');
}

if(false=='false') {
    console.log('nope');
}

if('false'==false) {
    console.log('nope');
}

if(0==false) {
    console.log("is zero == to false?")
}
if(''==false) {
    console.log("is empty string == to false?")
}

if(''){
    console.log("'' is true");
} else {
    console.log("'' is false");
}
if(""){
    console.log('"" is true');
} else {
    console.log('"" is false');
}


if({}){
    console.log('{} is true');
} else {
    console.log('{} is false');
}

if([]){
    console.log('[] is true');
} else {
    console.log('[] is false');
}

// real fun

var a = 5;
var b = 6;

for(var c= 0; c<b; c++) {
    a = 1+a*b;
}

if(a=b) {
    console.log(a);
    console.log('success');
} else {
    console.log(a);
    console.log('failure');
}