import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newReal_estate = document.getElementById('newReal_estate')
    newReal_estate.addEventListener('submit', event => createReal_estate(event));
});


function createReal_estate(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/types/' + getParameterByName('type')
        + '/real_estates', true);

    const request = {
        'name': document.getElementById("name").value,
        'city': document.getElementById("city").value,
        'age': document.getElementById("age").value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}