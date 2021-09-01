let listGachaTemplate = `<table class="table">
<thead>
    <tr>
        <th>Name</th>
        <th>Rarity</th>
        <th>Type</th>
        <th>Stats</th>
        <th>Ability</th>
    </tr>
</thead>
<tbody id="gacha-table-body"></tbody>
</table>`;

window.onload= () => {
    document.getElementById('list-gacha').innerHTML = listGachaTemplate;
    fetch('http://localhost:8080/gachas')
        .then(response => {
            console.log(response);
            return response.json()})
        .then(list => {
            console.log(list);
            populateGachaTable(list);
        });
}

function populateGachaTable(list) {
    let body = document.getElementById('gacha-table-body');

    list.forEach(gacha => {
        let tr = document.createElement('tr');

        addTdToTable(gacha.name, tr);
        addTdToTable(gacha.rarity, tr);
        addTdToTable(gacha.type, tr);

        addStatsToTable(gacha.stats, tr);

        addTdToTable(gacha.ability, tr);

        body.appendChild(tr);
    });
}

function addTdToTable(value, parent){
    let td = document.createElement('td');
    td.innerHTML = value;
    parent.appendChild(td);
}

function addStatsToTable(values, parent) {
    let td = document.createElement('td');
    let ul = document.createElement('ul');

    let li = document.createElement('li');
    li.innerHTML = 'Attack: '+values.attack;
    ul.appendChild(li);

    li = document.createElement('li');
    li.innerHTML = 'Defense: '+values.defense;
    ul.appendChild(li);

    li = document.createElement('li');
    li.innerHTML = 'Health: '+values.health;
    ul.appendChild(li);

    td.appendChild(ul);
    parent.appendChild(td);
}