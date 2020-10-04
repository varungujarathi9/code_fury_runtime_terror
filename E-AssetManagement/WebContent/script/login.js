window.addEventListener('load',function()
{
document.querySelector("#userLogin").addEventListener('submit',
        function()
    {
        //console.log("login successfull !!");
        alert("login successfull !!");

    })


})

function login()
{
	//var x = document.getElementById("userLogin").elements.length;
	//console.log(x);
	 var regForms = document.getElementById["userLogin"];
	 var username = document.getElementById("userName").value;
// var username =  regForms["userName"];

	  // console.log(username);
	   // var password =  regForms["Password"];
		 var password= document.getElementById("Password").value;
	   //console.log(password);
	    var loginBtn =  document.getElementById("submit");
	    var ajax=new XMLHttpRequest();




        ajax.open("POST","http://localhost:8080/E-AssetManagement/UserLoginController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("&username="+username+"&password="+password);

        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            System.out.println("processing request");
            loginBtn.value="Validating your credentials...";
            }
            else if (this.readyState==4&&this.status==200) {
            //request ready
            	//alert('logged in');
          //  loginBtn.value="Logged In";
              //loginBtn.reset();
           // console.log("Form submitted");
            window.setTimeout(function(){loginBtn.disabled =false;loginBtn.value="Login";},3000);
            }


        }
}
