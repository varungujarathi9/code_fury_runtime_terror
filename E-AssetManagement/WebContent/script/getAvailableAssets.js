// window.addEventListener('load', showAssets());

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

    var ajax = new XMLHttpRequest();
    ajax.open("POST", "/E-AssetManagement/ShowAssetController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("userId=1");
    ajax.onreadystatechange = function () {
        if (this.readyState == 3) {
            showAssets.innerHTML = "Displaying...";
        } else if (this.readyState == 4 && this.status == 200) {
            //request ready

            if (this.responseText[0] == "[") {
                var assetsList = "<table class='table table-hover'><thead><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th><th> </th></tr></thead><tbody>";
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

    var ajax = new XMLHttpRequest();
    ajax.open("POST", "/E-AssetManagement/BorrowAssetController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("assetId=" + assetId + "&userId=1");
    ajax.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //request ready
            console.log(this.responseText);
            issueButton.value = "Issued";
            issueButton.disable = true;
            // TODO: change color of button
        } else if (this.readyState == 4) {
            issueButton.value = "Not Issued";
            // TODO: change color of button
        }
    }
    showAssets();
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