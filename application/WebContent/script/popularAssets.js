window.addEventListener('load', function(){

    var assets = document.getElementById("assets");
    try{
        var ajax = new XMLHttpRequest();
    } catch (e){
        try{
            ajaxRequest = new ActiveXObject("Msxml2.XMLHTTP3.0"); }
        catch (e){//alert("Your browser broke!");
        return false;
        }
    }
    
   
    ajax.onreadystatechange=function(){
        
        if (this.readyState==4 && this.status==200) {
            //request ready

            var assetsList = "<table><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th></tr>";
            var response = JSON.parse(this.responseText);
            for(i = 0; i < response.length; i++){
                assetsList += "<tr>";
                assetsList += "<td>"+response[i].name+"</td>";
                assetsList += "<td>"+response[i].assetType+"</td>";
                assetsList += "<td>"+response[i].description+"</td>";
                assetsList += "</tr>";
            }
            assetsList += "</table>"
            assets.innerHTML = assetsList;
           // assets.innerHTML ="working";

        }
        else if(this.readyState==4){
            assets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
    ajax.open("GET","/E-AssetManagement/BorrowerShowAssetController",true);
    ajax.send();
})  