
function showAllAssets() {


    var showAssets = document.getElementById("showAllAssets");
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

    var ajax = new XMLHttpRequest();
    ajax.open("POST", "/E-AssetManagement/ShowAllAssetsController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange = function () {
        if (this.readyState == 3) {
            showAssets.innerHTML = "Displaying...";
        } else if (this.readyState == 4 && this.status == 200) {
            //request ready

            if (this.responseText[0] == "[") {
                var assetsList = "<table id='allTable' class='table table-hover ' bgcolor='white'><thead class='table-dark'><tr><th>Asset ID</th><th>Asset Name</th><th>Asset Type</th><th>Description</th></tr><tr><th colspan='100%'><input type='text' id='allInput' onkeyup='filterTable()' style='width:100%!important;background-color:white;border: 1px solid black%important;border-width: 1px 1px 1px 1px !important;border-radius: 5px;' placeholder='Search for names..'></th></tr></thead><tbody>";
                var response = JSON.parse(this.responseText);
                for (i = 0; i < response.length; i++) {
                    assetsList += "<tr>";
                    assetsList += "<td>" + response[i].assetId + "</td>";
                    assetsList += "<td>" + response[i].name + "</td>";
                    assetsList += "<td>" + response[i].assetType + "</td>";
                    assetsList += "<td>" + response[i].description + "</td>";
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

function filterTable(){
	
		  // Declare variables
		  var input, filter, table, tr, td, i, txtValue;
		  input = document.getElementById("allInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("allTable");
		  tr = table.getElementsByTagName("tr");

		  // Loop through all table rows, and hide those who don't match the search query
		  for (i = 0; i < tr.length; i++) {
		    td = tr[i].getElementsByTagName("td")[1];
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