
export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

export function createButtonCell(text, action) {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode(text));
    button.classList.add('ui-control', 'ui-button');
    td.appendChild(button);
    button.addEventListener('click', action);
    return td;
}

export function createLinkCell(text, url) {
    const td = document.createElement('td');
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    td.appendChild(a);
    return td;
}

export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}

export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}
export function getParameterById(id) {
    return new URLSearchParams(window.location.search).get(id);
}
export function setTextNode(id, text) {
    let element = document.getElementById(id);
    clearElementChildren(element);
    element.appendChild(document.createTextNode(text));
}
