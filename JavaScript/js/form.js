window.onload = ()=>{
    console.log('hello');

    let id = document.getElementById('t_id');
    let applePie = document.getElementById('t_name');
    let major = document.getElementById('t_major');
    let button = document.getElementById('button');

    console.log(id);
    console.log(applePie);
    console.log(major);
    console.log(button);

    console.log(id.value);

    button.addEventListener('click',updateMajor);

    walkTheDom(document.getElementsByTagName('body')[0]);
}

function walkTheDom(element) {
    console.log(element);
    for(child of element.children) {
        walkTheDom(child);
    }
}

function updateMajor(){
    let me = document.getElementById('me');
    me.innerHTML = '';
    let id = document.getElementById('t_id').value;
    let applePie = document.getElementById('t_name').value;
    let major = document.getElementById('t_major').value;

    let ul = document.createElement('ul');
    me.append(ul);
    let li = document.createElement('li');
    ul.append(li);
    li.innerHTML=id;
    li = document.createElement('li');
    ul.append(li);
    li.innerHTML=applePie;
    li = document.createElement('li');
    ul.append(li);
    li.innerHTML=major;
}