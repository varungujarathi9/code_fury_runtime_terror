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
				console.log(this.responseText);
	            if(this.responseText[0] == "{"){
					var response = JSON.parse(this.responseText);
					if (typeof(Storage) !== "undefined") {
						// javascript session variable
						sessionStorage.setItem("userId", response.uniqueId);
						sessionStorage.setItem("name", response.name);
						sessionStorage.setItem("role", response.role);
						sessionStorage.setItem("mobile", response.telphoneNumber);
						sessionStorage.setItem("email", response.emailId);
						sessionStorage.setItem("userName", response.username);
					}
					else{
						formAlert.innerHTML = "Session not created!!!";
					}
					if(sessionStorage.getItem("role") == "ADMIN"){
						window.location.replace("/E-AssetManagement/adminHome.html");
					}
					else if (sessionStorage.getItem("role") == "BORROWER"){
						window.location.replace("/E-AssetManagement/employeeHome.html");
					}
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
	            submitBtn.value = "Submit";
	        }
	    }
    }
    else{
    	formAlert.innerHTML = "Fields cannot be null";
    }
}