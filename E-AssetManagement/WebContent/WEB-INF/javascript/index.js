window.addEventListener('load',function(){
    let submitBtn = document.getElementById("submit");
    submitBtn.addEventListener('click', function(){
        let dataValid = true;
        let regForm = document.querySelector("#reg_form");
        let confPasswordAlert = document.getElementById("conf_password_alert");
        let password = document.getElementById("password");
        let confPassword = document.getElementById("conf_password");

        // PASSWORD VALIDATION
        // TODO: Check if password is strong
        // check is password and confirm password fields are matching
        if(password != confPassword){
            // TODO: Send data to backend
            dataValid = false;
            confPasswordAlert.innerHTML = "PASSWORD DOES NOT MATCH";
        }

        if(dataValid === true){
            console.log("Sending data to server");
            sendDataToServer();
        }

    })
});

function sendDataToServer(){
    
}