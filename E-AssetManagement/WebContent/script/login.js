window.addEventListener('load', function () {
    document.querySelector("#userLogin").addEventListener('submit',
    function () {
        //console.log("login successfull !!");
        //alert("login successfull !!");

    })


})

function login() {

    var loginForm = document.forms["login_form"];
    var username = loginForm["userName"];
    var password = loginForm["password"];
    var submitBtn = document.getElementById("submit");

    var ajax = new XMLHttpRequest();

    ajax.open("POST", "/E-AssetManagement/UserLoginController", true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("&username=" + username + "&password=" + password);

    ajax.onreadystatechange = function () {
        if (this.readyState == 3) {
            submitBtn.value = "Submitting...";
        } else if (this.readyState == 4 && this.status == 200) {
            //request ready
            submitBtn.value = "Submitted!";
            loginForm.reset();
            console.log("Form submitted");
            if(this.responseText == 'ADMIN'){
                window.location.replace("/E-AssetManagement/adminHome.html");
            }
            else if(this.reponseText == 'BORROWER'){
                window.location.replace("/E-AssetManagement/employeeHome.html");
            }
            else{
                
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