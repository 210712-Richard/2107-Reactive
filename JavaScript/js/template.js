// Template Literals
// ES6 added strings with expressions and returns

// returns in string
let oldString = 'Hello, my name is\nRichard, and I like\ncheese';
console.log(oldString);
let templateLiteral = `Hello, my name is
Richard, and I like
cheese`;
console.log(templateLiteral);

// in JavaScript we're often writing HTML templates
// that we wish to have put into our document.
let template = `<table id ="data">
    <tr>
        <th> Name </th>
    </tr>
</table>`;
oldString = "<table id=\"data\"><tr><th>Name</th></tr></table>"

// expressions = ${expression}
//Literally write JavaScript inside your string
let myName = "Richard";
console.log("Hi, my name is "+myName+" and I like cheese");
console.log(`Hi, my name is ${myName} and I like cheese`);

let price = 6.5;
let tax = .3;
console.log(`The price of your book is ${price*(1+tax)} dollars`);

// beware semi-colon injection
function add() {
    return
    {
        h: 5
    };
}
var h = add();
console.log(h);