function showAssets()
{
	//recieving user info from jsp
	var jsName=document.getElementById("jspName").value;
	var jsTelephoneNumber=document.getElementById("jspTelephoneNumber").value;
	var jsRole=document.getElementById("jspRole").value;
	var jsEmailId=document.getElementById("jspEmailId").value;
	var jsUsername=document.getElementById("jspUsername").value;
	var jsPassword=document.getElementById("jspPassword").value;
	var jsLastLogin=document.getElementById("jspLastLogin").value;
	
var userid = document.getElementById("userid").value;
   var btnid=document.getElementById("submit");
	
	//var x = document.getElementById("userLogin").elements.length;
	//console.log(x);
	// var regForms = document.getElementById["userLogin"];
	 //var username = document.getElementById("userName").value;
// var username =  regForms["userName"];

	  // console.log(username);
	   // var password =  regForms["Password"];
	//	 var password= document.getElementById("Password").value;
	   //console.log(password);
	  //  var loginBtn =  document.getElementById("submit");
	    var ajax=new XMLHttpRequest();




        ajax.open("POST","/E-AssetManagement/BorrowerShowAssetController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("&userid="+userid);

        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            System.out.println("processing request");
            btnid.value="Validating your credentials...";
            }
            else if (this.readyState==4&&this.status==200) {
            //request ready
            	////alert('logged in');
          //  loginBtn.value="Logged In";
              //loginBtn.reset();
           // console.log("Form submitted");
            	System.out.println("going to the controller");
            window.setTimeout(function(){loginBtn.disabled =false;btnid.value="SUBMIT";},3000);
            }


        }
}
