/**
 * @author Madhura Avachat
 * @createdOn 03 Oct 2020
 */
window.addEventListener('load',function()
{
   //Show Add Category Button on selecting 'Other' Category
    document.getElementById('category').addEventListener('change', function () {
        var categoryValue = this.value;
        if(categoryValue == "Other"){
            document.getElementById('addCategory').style.display = 'block'; 
            document.getElementById('submitBtn').disabled = true; 
        }
        else{
            document.getElementById('addCategory').style.display = 'none'; 
            document.getElementById('submitBtn').disabled = false; 
        }
     
    });

})


function addAsset(){

    var dataValid = true;
    
    // retrieve values of form fields
    var addAsset = document.forms["addAssetForm"];
    var name = addAsset["name"];
    var description =  addAsset["description"];
    var category =  addAsset["category"];


    // get p tag elements
    var nameAlert = document.getElementById("name_alert");
    var descriptionAlert =  document.getElementById("description_alert");
    var categoryAlert =  document.getElementById("category_alert");
    var formAlert =  document.getElementById("form_alert");

    var submitBtn =  document.getElementById("submitBtn");

    //Verifying required input
    if(name.value!="" && description.value!=""){
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


        ajax.open("POST","http://localhost:8080/E-AssetManagement/AddAssetController",true);
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
