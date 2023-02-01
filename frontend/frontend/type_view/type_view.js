import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayType();
    fetchAndDisplayReal_estates();
    displayCreateReal_estate();
});


function fetchAndDisplayReal_estates() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayReal_estates(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/real_estates', true);
    xhttp.send();
}


function displayReal_estates(real_estates) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    real_estates.realEstates.forEach(real_estate => {
        tableBody.appendChild(createTableRow(real_estate));
    })
}
function displayCreateReal_estate() {
    let newReal_estateBtn = document.getElementById('newReal_estate');
    newReal_estateBtn.appendChild(createLinkCell('Create a new real_estate', '../real_estate_create/real_estate_create.html?type='
        + getParameterByName('type')))
}


function createTableRow(real_estate) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(real_estate.id));
    tr.appendChild(createTextCell(real_estate.name));
    tr.appendChild(createLinkCell('view', '../real_estate_view/real_estate_view.html?type='
        + getParameterByName('type') + '&real_estate=' + real_estate.id));
    tr.appendChild(createLinkCell('edit', '../real_estate_edit/real_estate_edit.html?type='
        + getParameterByName('type') + '&real_estate=' + real_estate.id));
    tr.appendChild(createButtonCell('delete', () => deleteReal_estate(real_estate.id)));
    return tr;
}


function deleteReal_estate(real_estate) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayReal_estates();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/types/' + getParameterByName('type')
        + '/real_estates/' + real_estate, true);
    xhttp.send();
}



function fetchAndDisplayType() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayType(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type'), true);
    xhttp.send();
}


function displayType(type) {
    setTextNode('type', type.name);
    setTextNode('size', type.size);
    setTextNode('numberOfRooms', type.numberOfRooms);
    setTextNode('internet_access', type.internet_access);
}
