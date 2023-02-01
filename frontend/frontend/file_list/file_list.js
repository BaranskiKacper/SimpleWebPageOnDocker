import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    getParameterByName
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFiles();
});

function fetchAndDisplayFiles() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFiles(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/files' , true);
    xhttp.send();
}

function displayFiles(files) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    files.files.forEach(file => {
        tableBody.appendChild(createTableRow(file));
    })
}


function createTableRow(file) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(file.id));
    tr.appendChild(createTextCell(file.title));
    tr.appendChild(createTextCell(file.author));
    return tr;
}





