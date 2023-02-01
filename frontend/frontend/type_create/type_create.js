import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newType = document.getElementById('newType')
    newType.addEventListener('submit', event => createType(event));
});


function createType(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/types', true);

    const request = {
        'name': document.getElementById("name").value,
        'size': parseInt(document.getElementById("size").value),
        'numberOfRooms': parseInt(document.getElementById("numberOfRooms").value),
        'internet_access': Boolean(document.getElementById("internet_access").value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}