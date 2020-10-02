/**
 * @author Varun Gujarathi
 * @createdOn 02 Oct 2020
 */
function submitForm(){

    let dataValid = true;

    // retrieve values of form fields
    var regForm = document.forms["reg_form"];
    console.log(regForm);
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

    var submitBtn =  document.getElementById("submitBtn");

    // PASSWORD VALIDATION
    // TODO: Check if password is strong
    // check is password and confirm password fields are matching
    if(password != confPassword){
        dataValid = false;
        confPasswordAlert.innerHTML = "PASSWORD DOES NOT MATCH";
    }

    if(dataValid === true){
        console.log("Sending data to server");
        sendDataToServer();
    }

}

function sendDataToServer(){
    var ajax=new XMLHttpRequest();
    submitBtn.disabled =true;
    submitBtn.value="Processing...";
    // TODO: Uncomment the below line and
    // add the registration form submission endpoint

    //ajax.open("POST","",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send("name="+name.value+"&telephone="+telephone.value+"&email="+email.value+"&domain="+domain.value+"&shift="+shift.value+"&timestamp="+currentdate.getDate() + "/"
                + (currentdate.getMonth()+1)  + "/"
                + currentdate.getFullYear() + " @ "
                + currentdate.getHours() + ":"
                + currentdate.getMinutes() + ":"
                + currentdate.getSeconds());
  ajax.onreadystatechange=function(){
    if(this.readyState==3){
      submitButton.value="Submitting...";
    }
    else if (this.readyState==4&&this.status==200) {
      //request ready
      submitButton.value="Submitted!";
      document.forms["form"].reset();
      window.setTimeout(function(){submitButton.disabled =false;submitButton.value="Submit Form";},8000);
    }
}