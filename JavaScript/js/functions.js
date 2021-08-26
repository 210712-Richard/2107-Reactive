function add(first, second){
    console.log(first);
    console.log(second);
    return first+second;
}

console.log(add(5,6));
console.log('---')
console.log(add(5));
console.log('---')
console.log(add(5,5,5,5,5,5));

function add(first, second) {
    if(second === undefined) {
        return first;
    }
    return first+second;
}

function add(first, second) {
    console.log(arguments); // implicit varargs
    let sum = 0;
    for(elements of arguments) {
        sum+=elements;
    }
    return sum;
}

// Curried function: A function that returns a 
// function as it's result
// we can chain calls to this result.
console.log(add(5)(5)(4)(3)(3)(1)());

function add(first, second) {
    if(arguments.length === 2) {
        return first+second;
    }
    if(arguments.length === 1) {
        // curried function
        let total = first; // give the curried function
        // closure to the sum.
        function innerAdd(num) {
            if(num !== undefined) {
                total+=num;
                return innerAdd;
            } else {
                return total;
            }
        }
        return innerAdd;
    }
    let sum = 0;
    for (elements of arguments) {
        sum+=elements;
    }
    return sum;
}