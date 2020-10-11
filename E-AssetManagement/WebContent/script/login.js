function login() {

    var loginForm = document.forms["login_form"];
    var username = loginForm["userName"];
    var password = loginForm["password"];
    var submitBtn = document.getElementById("submit");
    var formAlert = document.getElementById("form_alert");
    submitBtn.value = "Submitting...";
    
    if(username.value != "" && password.value != ""){
    
	    var ajax = new XMLHttpRequest();
	
	    ajax.open("POST", "/E-AssetManagement/UserLoginController", true);
	    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    ajax.send("&username=" + username.value + "&password=" + password.value);
	
	    ajax.onreadystatechange = function () {
	        if (this.readyState == 3) {
	            submitBtn.value = "Submitting...";
	        } else if (this.readyState == 4 && this.status == 200) {
	            //request ready
	            submitBtn.value = "Submitted!";
	            loginForm.reset();
	            var response = this.responseText;
	            if(response == "ADMIN"){
	                window.location.replace("/E-AssetManagement/adminHome.jsp");
	            }
	            else if(response == "BORROWER"){
	                window.location.replace("/E-AssetManagement/employeeHome.jsp");
	            }
	            else{
	                formAlert.innerHTML = this.responseText;
	            }
	            // window.setTimeout(function(){submitBtn.disabled =false;submitBtn.value="Register";},3000);
	        } else {
	            // formAlert.innerHTML = "Error submitting form";
	            window.setTimeout(function () {
	                formAlert.innerHTML = "";
	            }, 3000);
	            submitBtn.disabled = false;
	            submitBtn.value = "Register";
	        }
	    }
    }
    else{
    	formAlert.innerHTML = "Fields cannot be null";
    }
}