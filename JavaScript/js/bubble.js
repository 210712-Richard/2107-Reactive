// global variables
// a-e are the divs we are observing
// cap is a flag for whether capturing should be enabled
// button is the dom element that will flip the cap flag

let cap = false;
let a = document.getElementById('A');
let b = document.getElementById('B');
let c = document.getElementById('C');
let d = document.getElementById('D');
let e = document.getElementById('E');
let button = document.getElementById('button');

window.addEventListener("load", ()=>{
    button.innerHTML='Capture?';
    addEventListenersToDivs();
    button.onclick = switchCapture;
});

function addEventListenersToDivs() {
    a.addEventListener('mouseover', select, cap);
    b.addEventListener('mouseover', select, cap);
    c.addEventListener('mouseover', select, cap);
    d.addEventListener('mouseover', select, cap);
    e.addEventListener('mouseover', select, cap);

    a.addEventListener('mouseout', unselect, cap);
    b.addEventListener('mouseout', unselect, cap);
    c.addEventListener('mouseout', unselect, cap);
    d.addEventListener('mouseout', unselect, cap);
    e.addEventListener('mouseout', unselect, cap);
}

function removeEventListenersFromDivs() {
    a.removeEventListener('mouseover', select, cap);
    b.removeEventListener('mouseover', select, cap);
    c.removeEventListener('mouseover', select, cap);
    d.removeEventListener('mouseover', select, cap);
    e.removeEventListener('mouseover', select, cap);

    a.removeEventListener('mouseout', unselect, cap);
    b.removeEventListener('mouseout', unselect, cap);
    c.removeEventListener('mouseout', unselect, cap);
    d.removeEventListener('mouseout', unselect, cap);
    e.removeEventListener('mouseout', unselect, cap);
}

function select(event) {
    //console.log(event);
    //event.stopPropagation();
    event.currentTarget.style.backgroundColor='Purple';
    console.log(event.currentTarget.id);
}
function unselect(event) {
    //console.log(event);
    event.currentTarget.style.backgroundColor='Orange';
}

function switchCapture() {
    removeEventListenersFromDivs();
    if(!cap) {
        button.innerHTML = 'Bubble?';
        console.log('set to capture')
    } else {
        button.innerHTML = 'Capture?';
        console.log('set to bubble');
    }
    cap = !cap;
    addEventListenersToDivs();
}