import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newFile = document.getElementById('newFile')
    newFile.addEventListener('submit', event => createFile(event));

});



function createFile(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/files', true);

    let request = new FormData();
    request.append("title",document.getElementById("title").value);
    request.append("author",document.getElementById("author").value);
    request.append("content",document.getElementById("content").files[0]);

    xhttp.send(request);
}