window.addEventListener('load', function(){
	//recieving user info from jsp
	var jsName=document.getElementById("jspName").value;
	var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
	var jsRole=document.getElementById("jspRole").value;
	var jsEmailId=document.getElementById("jspEmailId").value;
	var jsUsername=document.getElementById("jspUsername").value;
	var jsPassword=document.getElementById("jspPassword").value;
	var jsLastLogin=document.getElementById("jspLastLogin").value;
	
    var userName = document.getElementById("userName");
    showAssets.innerHTML = "Requesting server...";

    var ajax=new XMLHttpRequest();
    ajax.open("GET","/E-AssetManagement/UserInfoController",true);
    // ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
            showAssets.innerHTML="Displaying...";
        }
        else if (this.readyState==4&&this.status==200) {
            //request ready

            var assetsList = "<table border=1><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th></tr>";
            var response = JSON.parse(this.responseText);
            for(i = 0; i < response.length; i++){
                assetsList += "<tr>";
                assetsList += "<td>"+response[i].name+"</td>";
                assetsList += "<td>"+response[i].assetType+"</td>";
                assetsList += "<td>"+response[i].description+"</td>";
                assetsList += "<td><button id='"+response[i].assetId+"' onclick='issueAsset("+response[i].assetId+")'>Issue Now</button></td>";
                assetsList += "</tr>";
            }
            assetsList += "</table>"
            showAssets.innerHTML = assetsList;

        }
        else if(this.readyState==4){
            showAssets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
});