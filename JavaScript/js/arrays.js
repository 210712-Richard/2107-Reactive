// objects in JS consists of key-value pairs

var obj = {0: 1, 1: 2, "2": 3, "length": 3};
console.log(obj);
var arr = [1, 2, 3];
console.log(arr);

// Every key in JS is a string.
// JavaScript has to perform string comparison when looking up array values.
// JavaScript cannot perform at the speed of math.

// normally to access an object's values we use dot notation to access the fields
// obj.key
console.log(obj.length);
console.log(arr.length);

// if the keys are numbers (even if those numbers are stored as strings) we can't use dot notation
//console.log(obj.1);
//console.log(obj."1");

// Array notation allows us to put values in to match to a key
console.log(obj[0]);
console.log(arr[0]);
console.log(obj['length']);
console.log(arr['length']);

let h = 'length';
console.log(arr[h]);

let sample = {
    'metal': 'titanium'
}

let key = 'metal';
console.log(sample[key])

// ARRAYS ARE OBJECTS
// OBJECTS ARE KEY/VALUE PAIRS

// OBJECT KEYS ARE STRINGS
// JS ARRAYS are NOT contiguous memory addresses
// JavaScript cannot do that.
// We cannot and do not perform arithmetic to find the next index
// we instead perform STRING COMPARISON

// JS has no real access to system memory

// This does mean that JS arrays do not have a fixed length


console.log(arr.length);
console.log(arr);
arr.length = 5;
console.log(arr.length);
console.log(arr);

arr.length = 1;
console.log(arr.length);
console.log(arr);

arr.length = 7;
console.log(arr.length);
console.log(arr);

arr[4] = 6;
console.log(arr);

arr[12] = 5;
console.log(arr);

// delete removes a key from an object
delete arr[0];
console.log(arr);
// do not use delete to remove items from an array.

let arr2 = [1, 2, 3, 4, 5];
console.log(arr2);
arr2.splice(3,2); // remove 2 elements starting at element 3 (inclusive)
console.log(arr2);

arr2.push(30);
console.log(arr2);

// when you pass a function to another function
// and expect the second function to call the first
// the first function is called a Callback function
console.log(
    arr2.findIndex(
        bob => {
            return bob === 3;
        }
    )
)

console.log(
    arr2.find(
        bob => {
            return bob === 3;
        }
    )
)

let arr3 = [1,2,3,4,5,6,7,8,9,10];
let m = arr3.map(element=>(element%2)?1:0);
console.log(m);
console.log(arr3.reduce((prev, curr)=> {
    let num = (curr%2)?1:0;
    num+=prev;
    return num;
}));

arr3 = [];

for(let i = 0; i<10; i++) {
    arr3.push({
        h: (i+1),
        h2: (i+1)*2
    });
}
console.log(arr3);

console.log(arr3.find(element => element.h2===16));

console.log(arr3.filter(element => element.h%2));