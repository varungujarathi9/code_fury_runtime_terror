window.addEventListener('load', function () {
    var showAssets = document.getElementById("issueAssets");
    showAssets.innerHTML = "Requesting server...";

    var ajax = new XMLHttpRequest();
    ajax.open("GET", "/E-AssetManagement/BorrowerShowAssetController", true);
    // ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange = function () {
        if (this.readyState == 3) {
            showAssets.innerHTML = "Displaying...";
        } else if (this.readyState == 4 && this.status == 200) {
            //request ready

            var assetsList = "<table class='table table-hover'><thead><tr><th>Asset Name</th><th>Asset Type</th><th>Description</th><th> </th></tr></thead><tbody>";
            var response = JSON.parse(this.responseText);
            for (i = 0; i < response.length; i++) {
                assetsList += "<tr>";
                assetsList += "<td>" + response[i].name + "</td>";
                assetsList += "<td>" + response[i].assetType + "</td>";
                assetsList += "<td>" + response[i].description + "</td>";
                assetsList += "<td><button type='button' class='btn btn-outline-danger' id='" + response[i].assetId + "' onclick='issueAsset(" + response[i].assetId + ")'>Issue Now</button></td>";
                assetsList += "</tr>";
            }
            assetsList += "</tbody></table>"
            showAssets.innerHTML = assetsList;

        } else if (this.readyState == 4) {
            showAssets.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
});

function issueAsset(assetId) {
    var issueButton = document.getElementById(assetId);
    issueButton.innerHTML = "Issuing";

    var ajax = new XMLHttpRequest();
    ajax.open("GET", "/E-AssetManagement/BorrowIssueAssetController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("assetId=" + assetId);
    ajax.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            //request ready
            issueButton.innerHTML = "Issued";
            // TODO: change color of button
        } else if (this.readyState == 4) {
            issueButton.innerHTML = "Not Issued";
            // TODO: change color of button
        }
    }

}