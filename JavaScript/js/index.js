console.log("I can write my JS here, as well.")

// line comments

/*
block comments
*/

var a = "3";

for(var i = 0; i <= a; i++) {
    console.log(i);
    console.log(i==a); // "3" == 3 is true (this is called Type Coercion)
    console.log(i===a); // "3" === 3 is not true
}