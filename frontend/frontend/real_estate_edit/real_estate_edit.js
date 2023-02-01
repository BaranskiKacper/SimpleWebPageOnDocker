import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayReal_estate();
});

/**
 * Fetches currently logged type's real_estates and updates edit form.
 */
function fetchAndDisplayReal_estate() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/real_estates/'
        + getParameterByName('real_estate'), true);
    xhttp.send();
}

/**
 * Action event handled for updating real_estate info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayReal_estate();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/types/' + getParameterByName('type') + '/real_estates/'
        + getParameterByName('real_estate'), true);

    const request = {
        'name': document.getElementById('name').value,
        'city': document.getElementById('city').value,
        'age':  document.getElementById('age').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}



