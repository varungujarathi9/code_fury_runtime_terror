/**
 * @author Madhura Avachat
 * @createdOn 03 Oct 2020
 */


function addCategory(){

    var dataValid = true;
    
    // retrieve values of form fields
    var addCategoryForm = document.forms["addCategoryForm"];
    var name = addCategoryForm["name"];
    var lendingPeriod =  addCategoryForm["lendingPeriod"];
    var banPeriod =  addCategoryForm["banPeriod"];
    var lateFees =  addCategoryForm["lateFees"];


    // get p tag elements
    var nameAlert = document.getElementById("name_alert");
    var lendingPeriodAlert =  document.getElementById("lendingPeriod_alert");
    var banPeriodAlert =  document.getElementById("banPeriod_alert");
    var lateFeesAlert =  document.getElementById("lateFees_alert");
    var formAlert =  document.getElementById("form_alert");

    var submitBtn =  document.getElementById("submitBtn");

;
    if(name.value!="" && lendingPeriod.value!="" && lateFees.value!="" && banPeriod.value!=""){
        dataValid=true;
        formAlert.innerHTML = "";
    }
    else{
        dataValid=false;
        formAlert.innerHTML = "Please fill required values";
    }
    var integerPattern = /^\d+$/;
    if(!lendingPeriod.value.match(integerPattern)){
    	
        dataValid=false;
        lendingPeriodAlert.innerHTML = "Please fill in Integer values";
    }
    
    if(!banPeriod.value.match(integerPattern)){
    	
        dataValid=false;
        banPeriodAlert.innerHTML = "Please fill in Integer values";
    }

    //Sending Data to the Controller
    if(dataValid == true){
        console.log("Sending data to server");
        var ajax=new XMLHttpRequest();
        submitBtn.disabled = true;
        submitBtn.value="Processing...";

        ajax.open("POST","/E-AssetManagement/AddCategoryController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("name="+name.value.toUpperCase()+"&lendingPeriod="+lendingPeriod.value+"&banPeriod="+banPeriod.value+"&lateFees="+lateFees.value);
        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            	submitBtn.value="Submitting...";
            }
            else if (this.readyState==4&&this.status==200) {
            	//request ready
	            submitBtn.value="Add Category";
	            addCategoryForm.reset();
	            formAlert.innerHTML=this.responseText;
	            window.setTimeout(function(){submitBtn.disabled =false;},3000);
            }
            else{
                window.setTimeout(function(){formAlert.innerHTML = "";},3000);
                submitBtn.disabled =false;
                submitBtn.value="Add Category"
            }
        }
    }
}
