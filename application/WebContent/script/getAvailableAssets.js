
function showAssets() {
    //recieving user info from jsp
    // var jsName=document.getElementById("jspName").value;
    // var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
    // var jsRole=document.getElementById("jspRole").value;
    // var jsEmailId=document.getElementById("jspEmailId").value;
    // var jsUsername=document.getElementById("jspUsername").value;
    // var jsPassword=document.getElementById("jspPassword").value;
    // var jsLastLogin=document.getElementById("jspLastLogin").value;

    var showAssets = document.getElementById("issueAssets");
    showAssets.innerHTML = "Requesting server...";

    if (typeof(Storage) !== "undefined") {

        if(sessionStorage.getItem("userId")==null){
            //alert('Session expired');
            window.location.replace('/E-AssetManagement/login.html');
        }
        else{
            //console.log(sessionStorage.getItem("role"))
            var userId=sessionStorage.getItem("userId");
        }
    }
    else{
    	//alert('Session expired');
        window.location.replace('/E-AssetManagement/login.html');
    }
//    var userId=sessionStorage.getItem("userId");
    var ajax = new XMLHttpRequest();
    ajax.open("POST", "/E-AssetManagement/ShowAssetController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//    console.log(userId);
    ajax.send("userId="+userId);
    ajax.onreadystatechange = function () {
        if (this.readyState == 3) {
            showAssets.innerHTML = "Displaying...";
        } else if (this.readyState == 4 && this.status == 200) {
            //request ready

            if (this.responseText[0] == "[") {
                var assetsList = "<table id='availableTable' class='table table-hover ' bgcolor='white'><thead class='table-dark'><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th><th> </th></tr><tr><th colspan='100%'><input type='text' id='myInput' onkeyup='filterAvailable()' style='width:100%!important;background-color:white;border: 1px solid black%important;border-width: 1px 1px 1px 1px !important;border-radius: 5px;' placeholder='Search for names..'></th></tr></thead><tbody>";
                var response = JSON.parse(this.responseText);
                for (i = 0; i < response.length; i++) {
                    assetsList += "<tr>";
                    assetsList += "<td>" + response[i].name + "</td>";
                    assetsList += "<td>" + response[i].assetType + "</td>";
                    assetsList += "<td>" + response[i].description + "</td>";
                    assetsList += "<td><input type='button' class='btn' id='" + response[i].assetId + "' onclick='issueAsset(" + response[i].assetId + ")' value='Issue Now'></td>";
                    assetsList += "</tr>";
                }
                assetsList += "</tbody></table>"
                showAssets.innerHTML = assetsList;
            }
            else{
                showAssets.innerHTML = this.responseText;
            }
        } else if (this.readyState == 4) {
            showAssets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
}

function issueAsset(assetId) {
    var issueButton = document.getElementById(assetId);
    issueButton.innerHTML = "Issuing";

    // get userId from session
    if (typeof(Storage) !== "undefined") {
        var userId = sessionStorage.getItem("userId");
    }
    else{
        //alert('Invalid Session!! Please login again');
        location.reload();
        return false;
    }

    if (userId == null){
        //alert('Invalid Session!! Please login again');
        location.reload();
        return false;
    }

    if (typeof(Storage) !== "undefined") {

        if(sessionStorage.getItem("userId")==null){
            //alert('Session expired');
            window.location.replace('/E-AssetManagement/login.html');
        }
        else{
            //console.log(sessionStorage.getItem("role"))
            userId=sessionStorage.getItem("userId");
        }
    }

    var ajax = new XMLHttpRequest();
    ajax.open("POST", "/E-AssetManagement/BorrowAssetController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("assetId=" + assetId + "&userId="+userId);
    ajax.send("assetId=" + assetId + "&userId="+userId);
    ajax.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //request ready
            console.log(this.responseText);
            issueButton.value = "Issued";
            issueButton.disable = true;
            showAssets();
            // TODO: change color of button
        } else if (this.readyState == 4) {
            issueButton.value = "Not Issued";
            // TODO: change color of button
        }
    }
}
function filterAvailable(){
	
		  // Declare variables
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("availableTable");
		  tr = table.getElementsByTagName("tr");

		  // Loop through all table rows, and hide those who don't match the search query
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[0];
		    if (td) {
		      txtValue = td.textContent || td.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		    }
		  }
}
// function varType(object) {
//     var stringConstructor = "test".constructor;
//     var arrayConstructor = [].constructor;
//     var objectConstructor = ({}).constructor;
//     if (object === null) {
//         return "null";
//     }
//     if (object === undefined) {
//         return "undefined";
//     }
//     if (object.constructor === stringConstructor) {
//         return "String";
//     }
//     if (object.constructor === arrayConstructor) {
//         return "Array";
//     }
//     if (object.constructor === objectConstructor) {
//         return "Object";
//     } {
//         return "don't know";
//     }
// }