window.addEventListener("load", function() { 
  	//recieving user info from jsp
	var jsName=document.getElementById("jspName").value;
	var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
	var jsRole=document.getElementById("jspRole").value;
	var jsEmailId=document.getElementById("jspEmailId").value;
	var jsUsername=document.getElementById("jspUsername").value;
	var jsPassword=document.getElementById("jspPassword").value;
	var jsLastLogin=document.getElementById("jspLastLogin").value;
	
  document.getElementById("myfile").onchange = function(event) { 
    var reader = new FileReader(); 
    reader.readAsDataURL(event.srcElement.files[0]); 
    var me = this; 
    reader.onload = function () { 
      var fileContent = reader.result; 
	  console.log(fileContent); 
    } 
}});

function importUsers(){
    var form = document.getElementById('importUsers');
    var fileSelect = document.getElementById('myfile');
    var uploadButton = document.getElementById('submit');
    var status = document.getElementById('status');

    form.onsubmit = function(event) {
        event.preventDefault();
         //alert('submitted');
        status.innerHTML = 'Uploading . . . ';

        // Get the files from the input
        var files = fileSelect.files;
       // //alert(document.getElementById("myfile").value);
        console.log(document.getElementById("myfile").value);
        // Create a FormData object.
        var formData = new FormData();

        //Grab only one file since this script disallows multiple file uploads.
        var file = files[0]; 

        //Check the file type.
        if (!file.type.match('text.*')) {
            status.innerHTML = 'You cannot upload this file because it’s not an text file.';
            return;
        }

        if (file.size >= 2000000 ) {
            status.innerHTML = 'You cannot upload this file because it exceeds the maximum limit of 2 MB.';
            return;
        }

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
        //alert('formData');
    }
};