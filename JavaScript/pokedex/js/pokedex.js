window.onload  = () => {
    document.getElementById('pokemonSubmit').onclick=pokemonFetch;
}

// async functions return promises of their results
async function asyncPokemonFetch(){
    let id = document.getElementById('pokemonId').value;
    // fetch returns a promise of type Response
    // await has the program wait for the result of our promise, in this case the Response.
    //let pokeResponse = await fetch('https://pokeapi.co/api/v2/pokemon'+id);
    // if we await something that returns a promise, we'll get the result of that promise.
    //let pokemon = await pokeResponse.json();
    let pokemon = await fetch('https://pokeapi.co/api/v2/pokemon/'+id).then(response => response.json());
    console.log(pokemon);

    //console.log(JSON.parse("{\"name\":\"hello\", \"age\":23}"));
    //console.log(JSON.stringify(pokemon));
    return pokemon;
}

function pokemonFetch(){
    let id = document.getElementById('pokemonId').value;
    fetch('https://pokeapi.co/api/v2/pokemon/'+id)
        .then(response => response.json())
        .then(pokemon => populateDex(pokemon))
        .catch(error => {
            console.log(error);
        });
}

function populateDex(pokemon) {
    console.log(pokemon);
    let image = document.getElementById('pokemonImg');
    image.src = pokemon.sprites.front_default;
    image.alt = pokemon.asyncPokemonFetch;

    document.getElementById('pokemonName').innerHTML = pokemon.name;

    let stats = document.getElementById('stats');
    createStatsTable(stats, pokemon.stats);
    let types = document.getElementById('types');
    addTypes(types, pokemon.types);
    let games = document.getElementById('games');
    addGames(games, pokemon.game_indices);
}


function addTypes(parentNode, typeArray) {
    parentNode.innerHTML = '';
    let primaryType = typeArray.find((type) => type.slot === 1);
    let h2 = document.createElement('h2');
    h2.innerHTML = primaryType.type.name;
    parentNode.appendChild(h2);

    if (typeArray.length > 1) {
        let secondaryType = typeArray.find((type) => type.slot === 2);
        let h3 = document.createElement('h3');
        h3.innerHTML = secondaryType.type.name;
        parentNode.appendChild(h3);
    }
}

function addGames(parentNode, gameIndices) {
    parentNode.innerHTML = '';
    let ul = document.createElement('ul');
    parentNode.appendChild(ul);
    for (let i = 0; i < gameIndices.length; i++) {
        let li = document.createElement('li');
        li.innerHTML = gameIndices[i].version.name;
        ul.appendChild(li);
    }
}

function createStatsTable(parentNode, statArray) {
    parentNode.innerHTML = '';
    let table = document.createElement('table');
    table.className = 'table';
    let tableHeaders = document.createElement('tr');
    let tableData = document.createElement('tr');

    parentNode.appendChild(table);
    table.appendChild(tableHeaders);
    table.appendChild(tableData);

    for (let i = statArray.length - 1; i >= 0; i--) {
        let th = document.createElement('th');
        let td = document.createElement('td');
        tableHeaders.appendChild(th);
        tableData.appendChild(td);

        th.innerHTML = statArray[i].stat.name;
        td.innerHTML = statArray[i].base_stat;
    }
}