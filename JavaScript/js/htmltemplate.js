window.onload = createTemplate;
let cheeseRangers = [
    {
        'name': 'Richard',
        'color': 'Purple',
        'cheese': 'Bleu'
    },
    {
        'name': 'Kyle',
        'color': 'Green',
        'cheese': 'Pepperjack'
    },
    {
        'name': 'Jasmine',
        'color': 'Pink',
        'cheese': 'Cheddar'
    },
    {
		'name': 'Ellis',
		'color': 'Blue',
		'cheese': 'PepperJack'
	},
    {
        'name': 'Ivan',
        'color': 'black',
        'cheese': 'mozzarella'
    },
    {
		'name': 'Brian',
		'color': 'Orange',
		'cheese': 'Swiss'
	},
    {
	
        'name' : 'Sidd',
        'color' : 'Alabaster',
        'cheese' : 'provolone'
    },
    {
        'name': 'Josh',
        'color': 'Blue',
        'cheese': 'Munster'
    },
    {
        'name' : 'Khine',
        'color' : 'Green',
        'cheese' : 'Cottage cheese'
    },
    {
        'name' : 'Michael',
        'color' : 'Green',
        'cheese' : 'Nacho Cheese'
    },
    {
        'name': 'Elizabeth',
        'color': 'Blue',
        'cheese': 'Cheddar'
    },
    {
        "name:": "Stephen",
        "color":"blue",
        "cheese":"Mozarella"
    },
    {
        'name': 'Quentin',
        'color': 'blue',
        'cheese': 'pepperjack'
    }
]

function populateTable(){
    console.log('Hi! Everything is done loading, now.')
    // In JS we have access to an object called the
    // document. The document object is a representation of the DOM
    // DOM = Document Object Model
    // The browser interprets HTML as a tree structure
    // The DOM is that tree.
    let tbody = document.getElementById('data');
    console.log(tbody);
    cheeseRangers.forEach((ranger) => {
        // create a table row
        let alpha = document.createElement('tr');
        // append the table row to the tbody element
        tbody.append(alpha);

        let td = document.createElement('td');
        alpha.appendChild(td);
        td.innerHTML = ranger.name;

        td = document.createElement('td');
        alpha.appendChild(td);
        td.innerHTML = ranger.color;

        td = document.createElement('td');
        alpha.appendChild(td);
        td.innerHTML = ranger.cheese;

        alpha.style = `background-color:${ranger.color}`
    });
}

let template = `
<h1>CheeseRangers</h1>
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Fav Color</th>
            <th>Fav Cheese</th>
        </tr>
    </thead>
    <tbody id="data">
    </tbody>
</table>
`
function createTemplate() {
    document.getElementById('template').innerHTML=template
    populateTable();
}