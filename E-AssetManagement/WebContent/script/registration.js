/**
 * @author Varun Gujarathi
 * @createdOn 02 Oct 2020
 */
window.addEventListener('load',function()
{
    let content=document.querySelector("#content");

    userLogin.addEventListener('click',function()
    {

        document.querySelector("#content div").style.display="block";
    })



})
function submitForm(){

    var dataValid = true;

    // retrieve values of form fields
    var regForm = document.forms["reg_form"];
    var name = regForm["name"];
    var telephone =  regForm["telephone"];
    var email =  regForm["email"];
    var username =  regForm["username"];
    var password =  regForm["password"];
    var confPassword =  regForm["conf_password"];

    // get p tag elements
    var nameAlert = document.getElementById("name_alert");
    var telephoneAlert =  document.getElementById("telephone_alert");
    var emailAlert =  document.getElementById("email_alert");
    var usernameAlert =  document.getElementById("username_alert");
    var passwordAlert =  document.getElementById("password_alert");
    var confPasswordAlert =  document.getElementById("conf_password_alert");
    var formAlert =  document.getElementById("form_alert");

    var submitBtn =  document.getElementById("submitBtn");

    // PASSWORD VALIDATION
    // TODO: Check if password is strong
    // check is password and confirm password fields are matching
    if(password.value != confPassword.value){
        dataValid = false;
        console.log(password.value);
        console.log(confPassword.value);
        confPasswordAlert.innerHTML = "PASSWORD DOES NOT MATCH";
    }
    else{
        confPasswordAlert.innerHTML = "";
    }

    if(dataValid == true){
        console.log("Sending data to server");
        var ajax=new XMLHttpRequest();
        submitBtn.disabled =true;
        submitBtn.value="Processing...";
        var currentDate = new Date();
        // TODO: Uncomment the below line and
        // add the registration form submission endpoint

        ajax.open("POST","/UserRegistrationController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("name="+name.value+"&telephone="+telephone.value+"&email="+email.value+"&username="+username.value+"&password="+password.value+"&conf_password="+confPassword.value+"&timestamp="+currentDate.getDate() + "/"
                    + (currentDate.getMonth()+1)  + "/"
                    + currentDate.getFullYear() + " @ "
                    + currentDate.getHours() + ":"
                    + currentDate.getMinutes() + ":"
                    + currentDate.getSeconds());
        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            submitBtn.value="Submitting...";
            }
            else if (this.readyState==4&&this.status==200) {
            //request ready
            submitBtn.value="Submitted!";
            document.forms["reg_form"].reset();
            console.log("Form submitted");
            window.setTimeout(function(){submitBtn.disabled =false;submitBtn.value="Register";},3000);
            }
            else{
                // formAlert.innerHTML = "Error submitting form";
                window.setTimeout(function(){formAlert.innerHTML = "";},3000);
                submitBtn.disabled =false;submitBtn.value="Register";
            }
        }
    }
}
