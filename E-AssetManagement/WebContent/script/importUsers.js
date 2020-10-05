

function importUsers(){
    var form = document.getElementById('importUsers');
    var fileSelect = document.getElementById('myfile');
    var uploadButton = document.getElementById('submit');
    var status = document.getElementById('status');

    form.onsubmit = function(event) {
        event.preventDefault();

        status.innerHTML = 'Uploading . . . ';

        // Get the files from the input
        var files = fileSelect.files;

        // Create a FormData object.
        var formData = new FormData();

        //Grab only one file since this script disallows multiple file uploads.
        var file = files[0]; 

        //Check the file type.
        if (!file.type.match('text.*')) {
            status.innerHTML = 'You cannot upload this file because it’s not an text file.';
            return;
        }

        /*if (file.size >= 2000000 ) {
            status.innerHTML = 'You cannot upload this file because it exceeds the maximum limit of 2 MB.';
            return;
        }*/

         // Add the file to the AJAX request.
        formData.append('myfile', file, file.name);

        // Set up the request.
        var ajax = new XMLHttpRequest();

        // Open the connection.
        ajax.open('POST', '/E-AssetManagement/importUsersController', true);


        // Set up a handler for when the task for the request is complete.
        ajax.onload = function () {
          if (ajax.status === 200) {
            status.innerHTML = 'Your upload is successful..';
          } else {
            status.innerHTML = 'An error occurred during the upload. Try again.';
          }
        };

        // Send the data.
        ajax.send(formData);
    }
};