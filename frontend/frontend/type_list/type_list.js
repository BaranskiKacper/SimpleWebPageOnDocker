import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTypes();
});

function fetchAndDisplayTypes() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTypes(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types', true);
    xhttp.send();
}

function displayTypes(types) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    types.types.forEach(type => {
        tableBody.appendChild(createTableRow(type.name));
    })
}


function createTableRow(type) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(type));
    tr.appendChild(createLinkCell('view', '../type_view/type_view.html?type=' + type));
    tr.appendChild(createLinkCell('edit', '../type_edit/type_edit.html?type=' + type));
    tr.appendChild(createButtonCell('delete', () => deleteType(type)));
    return tr;
}


function deleteType(type) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayTypes();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/types/' + type, true);
    xhttp.send();
}
