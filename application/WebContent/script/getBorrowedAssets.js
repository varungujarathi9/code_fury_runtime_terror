var userId = null;
window.addEventListener('load', function(){
    // get userId from session
    if (typeof(Storage) !== "undefined") {
        var userId = sessionStorage.getItem("userId");
    }


});

function borrowedAssets(){
	//recieving user info from jsp
//	var jsName=document.getElementById("jspName").value;
//	var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
//	var jsRole=document.getElementById("jspRole").value;
//	var jsEmailId=document.getElementById("jspEmailId").value;
//	var jsUsername=document.getElementById("jspUsername").value;
//	var jsPassword=document.getElementById("jspPassword").value;
//	var jsLastLogin=document.getElementById("jspLastLogin").value;

    var borrowedAssets = document.getElementById("borrowedAssets");
    borrowedAssets.innerHTML = "Requesting server...";

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

    var ajax=new XMLHttpRequest();
    ajax.open("POST","/E-AssetManagement/BorrowerShowAssetsController",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("userId="+userId);
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
            borrowedAssets.innerHTML="Displaying...";
        }
        else if (this.readyState==4&&this.status==200) {
            //request ready

            if (this.responseText[0] == "[") {
                var assetsList = "<table  id='borrowedTable' class='table table-hover ' bgcolor='white'><thead class='table-dark'><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th><th>Date of Issue</th><th>Return By</th><th>Late Fees</th><th></th></tr><tr><th colspan='100%'><input type='text' id='myInput2' onkeyup='filterBorrowed()' style='width:100%!important;background-color:white;border: 1px solid black%important;border-width: 1px 1px 1px 1px !important;border-radius: 5px;' placeholder='Search for names..'></th></tr></thead><tbody>";
                var response = JSON.parse(this.responseText);
                for(i = 0; i < response.length; i++){
                    assetsList += "<tr>";
                    assetsList += "<td>"+response[i].assetName+"</td>";
                    assetsList += "<td>"+response[i].assetType+"</td>";
                    assetsList += "<td>"+response[i].assetDesc+"</td>";
                    assetsList += "<td>"+response[i].dateOfissue+"</td>";
                    assetsList += "<td>"+response[i].expectedReturnDate+"</td>";
                    assetsList += "<td>"+response[i].lateAmount+"</td>";
                    assetsList += "<td><input type='button' class='btn' id='r"+response[i].assetId+"' onclick='returnAsset("+response[i].assetId+")' value='Return Now'></td>";
                    assetsList += "</tr>";
                }
                assetsList += "</tbody></table>"
                borrowedAssets.innerHTML = assetsList;
            }
            else{
                borrowedAssets.innerHTML = this.responseText;
            }

        }
        else if(this.readyState==4){
            borrowedAssets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
}

function returnAsset(assetId){
    console.log("1");
    var returnButton = document.getElementById("r"+assetId);
    returnButton.value = "Returning.....";

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

    var ajax=new XMLHttpRequest();
    ajax.open("POST","/E-AssetManagement/ReturnAssetsController",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("userId="+userId+"&assetId="+assetId);
    ajax.onreadystatechange=function(){
        console.log(this.status+" "+this.readyState);
        if (this.readyState==4&&this.status==200) {
            //request ready
            returnButton.value = "Returned";
            borrowedAssets();
            // TODO: change color of button
        }
        else if(this.readyState==4){
            returnButton.value = "Not Returned";
            // TODO: change color of button
        }
    }

}
function filterBorrowed(){
	
	  // Declare variables
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput2");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("borrowedTable");
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