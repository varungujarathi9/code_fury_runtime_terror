window.addEventListener('load', function(){
    var borrowedAssets = document.getElementById("borrowedAssets");
    borrowedAssets.innerHTML = "Requesting server...";

    var ajax=new XMLHttpRequest();
    ajax.open("GET","/E-AssetManagement/BorrowIssuedAssetController",true);
    // ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
            borrowedAssets.innerHTML="Displaying...";
        }
        else if (this.readyState==4&&this.status==200) {
            //request ready

            var assetsList = "<table border=1><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th><th>Date Issued</th><th>Due Date</th><th>Late Fees</th></tr>";
            var response = JSON.parse(this.responseText);
            for(i = 0; i < response.length; i++){
                assetsList += "<tr>";
                assetsList += "<td>"+response[i].name+"</td>";
                assetsList += "<td>"+response[i].assetType+"</td>";
                assetsList += "<td>"+response[i].description+"</td>";
                assetsList += "<td>"+response[i].dateOfIssue+"</td>";
                assetsList += "<td>"+response[i].dueDate+"</td>";
                assetsList += "<td>"+response[i].lateFees+"</td>";
                assetsList += "<td><button id='"+response[i].assetId+"' onclick='returnAsset("+response[i].assetId+")'>Return Now</button></td>";
                assetsList += "</tr>";
            }
            assetsList += "</table>"
            borrowedAssets.innerHTML = assetsList;

        }
        else if(this.readyState==4){
            borrowedAssets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
});

function returnAsset(assetId){
    var returnButton = document.getElementById(assetId);
    returnButton.innerHTML = "Issuing";

    var ajax=new XMLHttpRequest();
    ajax.open("GET","/E-AssetManagement/BorrowReturnAssetController",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("assetId="+assetId);
    ajax.onreadystatechange=function(){
        if (this.readyState==4&&this.status==200) {
            //request ready
            returnButton.innerHTML = "Returned";
            // TODO: change color of button
        }
        else if(this.readyState==4){
            returnButton.innerHTML = "Not Returned";
            // TODO: change color of button
        }
    }

}