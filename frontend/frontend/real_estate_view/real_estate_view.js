import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayReal_estate();
});

function fetchAndDisplayReal_estate() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayReal_estate(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type')
        + '/real_estates/' + getParameterByName('real_estate'), true);
    xhttp.send();
}

function displayReal_estate(real_estate) {
    setTextNode('id',real_estate.id)
    setTextNode('name', real_estate.name);
    setTextNode('city', real_estate.city);
    setTextNode('age', real_estate.age);
    setTextNode('type', real_estate.type);
}
