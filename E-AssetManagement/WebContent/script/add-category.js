/**
 * @author Madhura Avachat
 * @createdOn 03 Oct 2020
 */
window.addEventListener('load',function()
{

})


function addCategory(){

    var dataValid = true;
    
    // retrieve values of form fields
    var addCategoryForm = document.forms["addCategoryForm"];
    var name = addCategory["name"];
    var lendingPeriod =  addCategory["lendingPeriod"];
    var lateFees =  addCategory["lateFees"];


    // get p tag elements
    var nameAlert = document.getElementById("name_alert");
    var lendingPeriodAlert =  document.getElementById("lendingPeriod_alert");
    var lateFeesAlert =  document.getElementById("lateFees_alert");
    var formAlert =  document.getElementById("form_alert");

    var submitBtn =  document.getElementById("submitBtn");

    //Verifying required input
    if(name.value!="" && lendingPeriod.value!="" ){
        dataValid=true;
        formAlert.innerHTML = "";
    }
    else{
        dataValid=false;
        formAlert.innerHTML = "Please fill required values";
    }

    //Sending Data to the Controller
    if(dataValid == true){
        console.log("Sending data to server");
        var ajax=new XMLHttpRequest();
        submitBtn.disabled =true;
        submitBtn.value="Processing...";


        ajax.open("POST","http://localhost:8080/E-AssetManagement/AddCategoryController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("name="+name.value+"description"+description.value+"category="+category.value);
        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            submitBtn.value="Submitting...";
            }
            else if (this.readyState==4&&this.status==200) {
            //request ready
            submitBtn.value="Submitted!";
            document.forms["addAssetForm"].reset();
            window.setTimeout(function(){submitBtn.disabled =false;},8000);
            }
            else{
                formAlert.innerHTML = "Error submitting form";
                window.setTimeout(function(){formAlert.innerHTML = "";},5000);
                submitBtn.disabled =false;
                submitBtn.value="Add Asset"
            }
        }
    }
}
