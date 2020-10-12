window.addEventListener('load', function(){
	//recieving user info from jsp
	var jsUniqueId=document.getElementById("jspUniqueId").value;
//	var jsName=document.getElementById("jspName").value;
//	var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
//	var jsRole=document.getElementById("jspRole").value;
//	var jsEmailId=document.getElementById("jspEmailId").value;
//	var jsUsername=document.getElementById("jspUsername").value;
//	var jsPassword=document.getElementById("jspPassword").value;
//	var jsLastLogin=document.getElementById("jspLastLogin").value;

    var messages = document.getElementById("messages");
    messages.innerHTML = "Requesting server...";

    var ajax=new XMLHttpRequest();
    ajax.open("POST","/E-AssetManagement/OverdueMessagesController",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("userId="+jsUniqueId);
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
        	messages.innerHTML="Displaying...";
        }
        else if (this.readyState==4&&this.status==200) {
            //request ready

            var messagesList = "<table border=1><tr><th>User Id</th><th>Name</th><th>Asset Id</th><th>Asset Name</th><th>Issue Date</th><th>Expected Return Date</th></tr>";
            var response = JSON.parse(this.responseText);
            for(i = 0; i < response.length; i++){
            	messagesList += "<tr>";
            	messagesList += "<td>"+response[i].USER_ID+"</td>";
            	messagesList += "<td>"+response[i].NAME+"</td>";
            	messagesList += "<td>"+response[i].ASSET_ID+"</td>";
            	messagesList += "<td>"+response[i].ASSET_NAME+"</td>";
            	messagesList += "<td>"+response[i].ISSUE_DATE+"</td>";
            	messagesList += "<td>"+response[i].EXPECTED_RETURN_DATE+"</td>";
                messagesList += "</tr>";
            }
            messagesList += "</table>"
            	messages.innerHTML = messagesList;

        }
        else if(this.readyState==4){
        	messages.innerHTML = "AJAX RECEIVED ERROR RESPONSE <br/><b>Error code</b> : " + this.status;
        }
    }
});