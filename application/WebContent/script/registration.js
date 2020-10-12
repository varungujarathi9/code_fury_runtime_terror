/**
 * @author Varun Gujarathi
 * @createdOn 02 Oct 2020
 */

var dataValid = true;

// retrieve values of form fields
var regForm = document.forms["reg_form"];
var name = regForm["name"];
var telephone = regForm["mobile"];
var email = regForm["email"];
var username = regForm["username"];
var password = regForm["password"];
var confPassword = regForm["conf_password"];

// get p tag elements
var nameAlert = document.getElementById("name_alert");
var mobileAlert = document.getElementById("mobile_alert");
var emailAlert = document.getElementById("email_alert");
var usernameAlert = document.getElementById("username_alert");
var passwordAlert = document.getElementById("password_alert");
var confPasswordAlert = document.getElementById("conf_password_alert");
var formAlert = document.getElementById("form_alert");

var submitBtn = document.getElementById("submitBtn");

function usernameSelector() {
    var emailInput = regForm["email"];
    var userNameInput = regForm["username"];
    var checkBox = regForm["useEmail"];
    if (checkBox.checked) {
        checkBox.value = "true";
        if (emailInput.value == "") {
            //alert("Please enter email");
            checkBox.checked = false;
            emailInput.focus();
        } else {
            userNameInput.value = emailInput.value;
        }
    } else {
        checkBox.value = "false";
    }
}


function validateName() {
    var name = regForm["name"];
    namePattern = /^[A-Za-z ]+$/;
    if (name.value.length === 0) {
        nameAlert.innerHTML = "Name cannot be empty"
        return false;
    }
    else if (name.value.match(namePattern) == null) {
        dataValid = false;
        nameAlert.innerHTML = "Name must be in alphabets only";
        return false;
    }
    else {
        nameAlert.innerHTML = "";
        return true;
    }
}

function validateMobile() {
    var mobile = regForm["mobile"];
    var mobilePattern =  /^[0-9]+$/;
    if (mobile.value.match(mobilePattern) == null) {
        mobileAlert.innerHTML = "Mobile number must numeric only";
        return false;
    }
    else if(mobile.value.length < 10){
        mobileAlert.innerHTML = "Mobile number must be 10 digits";
        return false;
    }
    else {
        mobileAlert.innerHTML = "";
        return true;
    }
}

function validateEmail() {
    // change username if email is changed and checkbox is ticked
    usernameSelector()

    var email = regForm["email"];
    var emailPattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (email.value.length === 0) {
        emailAlert.innerHTML = "Email cannot be empty"
        return false;
    } else if (email.value.match(emailPattern) == null) {
        dataValid = false;
        emailAlert.innerHTML = "Email Invalid";
        return false;
    } else {
        emailAlert.innerHTML = "";
        return true;
    }

}

function validatePassword() {
    var password = regForm["password"]
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{7,15}$/;
    if (password.value.length === 0) {
        passwordAlert.innerHTML = "Password cannot be empty"
        return false;
    } else if (password.value.match(passwordPattern) == null) {
        dataValid = false;
        passwordAlert.innerHTML = "Password must be of length (7-15) have one lower case, one upper case, one number and one special character";
        return false;
    } else {
        passwordAlert.innerHTML = "";
        return true;
    }
}

function matchPasswords() {
    // retrieve values of form fields
    var password = regForm["password"];
    var confPassword = regForm["conf_password"];
    if (password.value != confPassword.value) {
        dataValid = false;
        confPasswordAlert.innerHTML = "Passwords do not match";
        return false;
    } else {
        confPasswordAlert.innerHTML = "";
        return true;
    }
}

function submitForm() {
    // retrieve values of form fields
    var regForm = document.forms["reg_form"];
    var name = regForm["name"];
    var telephone = regForm["telephone"];
    var email = regForm["email"];
    var username = regForm["username"];
    var password = regForm["password"];
    var confPassword = regForm["conf_password"];

    // NAME VALIDATION
    dataValid = validateName();
    // EMAIL VALIDATION
    dataValid &= validateMobile();
    // EMAIL VALIDATION
    dataValid &= validateEmail();
    // PASSWORD VALIDATION
    dataValid &= validatePassword();
    dataValid &= matchPasswords();



    //TODO: EMAIL VALIDATION

    if (dataValid == true) {
        formAlert.innerHTML = "";
        console.log("Sending data to server");
        var ajax = new XMLHttpRequest();
        submitBtn.disabled = true;
        submitBtn.value = "Processing...";
        var currentDate = new Date();
        ajax.open("POST", "/E-AssetManagement/UserRegistrationController", true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("name=" + titleCase(name.value) + "&telephone=" + mobile.value + "&email=" + email.value + "&username=" + username.value + "&password=" + password.value + "&conf_password=" + confPassword.value + "&timestamp=" + currentDate.getDate() + "/" +
            (currentDate.getMonth() + 1) + "/" +
            currentDate.getFullYear() + " @ " +
            currentDate.getHours() + ":" +
            currentDate.getMinutes() + ":" +
            currentDate.getSeconds());
        ajax.onreadystatechange = function () {
            if (this.readyState == 3) {
                submitBtn.value = "Submitting...";
            } else if (this.readyState == 4 && this.status == 200) {
                //request ready
                submitBtn.value = "Submitted!";
                document.forms["reg_form"].reset();
                console.log("Form submitted");
                if(this.responseText == '1')
                    window.location.replace("/E-AssetManagement/login.html");
                else
                    formAlert.innerHTML = this.responseText;
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
    } else {
        formAlert.innerHTML = "Form is invalid, please check above!!!";
    }
}

function titleCase(str){
	str = str.toLowerCase();
	str = str.split(' ');
	for (var i = 0; i < str.length; i++) {
	  str[i] = str[i].charAt(0).toUpperCase() + str[i].slice(1); 
	}
	return str.join(' '); 
}