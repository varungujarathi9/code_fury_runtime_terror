window.addEventListener('load', function(){
    console.log('opened');
    var showAssets = document.getElementById("showAssets");

    var ajax=new XMLHttpRequest();
    showAssets.innerHTML = "Requesting server...";

    ajax.open("POST","/E-AssetManagement/BorrowerShowAssetController",true);
    // ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
            showAssets.innerHTML="Displaying...";
        }
        else if (this.readyState==4&&this.status==200) {
            //request ready
            showAssets.innerHTML = this.responseText;
        }
    }
});