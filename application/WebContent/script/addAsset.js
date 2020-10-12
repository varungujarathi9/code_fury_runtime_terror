/**
 * @author Madhura Avachat
 * @createdOn 03 Oct 2020
 */
window.addEventListener('load',function()
{
	// retrieve values of form fields
    var addAsset = document.forms["addAssetForm"];
    var category =  addAsset["category"];
	var submitBtn =  document.getElementById("submitBtn");
	var formAlert =  document.getElementById("form_alert");

	var ajax=new XMLHttpRequest();
    submitBtn.disabled = true;
    //category.disabled = true;
    submitBtn.value="Loading data...";

    ajax.open("POST","/E-AssetManagement/ShowCategoriesController",true);
    ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    ajax.send();
    ajax.onreadystatechange=function(){
        if(this.readyState==3){
        submitBtn.value="Loading...";
        }
        else if (this.readyState==4&&this.status==200) {
	        //request ready
	        submitBtn.value="Add Asset!";
	        submitBtn.disabled = false;
	        //category.disabled = false;

	        if (this.responseText[0] == "[") {
	            var options = "<option value=''>-Select Category-</option>";
	            var response = JSON.parse(this.responseText);
	            for (i = 0; i < response.length; i++) {
	            	options += "<option value="+response[i]+">"+response[i]+"</option>";
	            }
	            category.innerHTML = options;
	        }
	        else{

	        }
	        addAsset.reset();

        }
        else{
            submitBtn.disabled =false;
            submitBtn.value="Add Asset"
        }
    }

})


function addAsset(){

    var dataValid = true;

    // retrieve values of form fields
    var addAsset = document.forms["addAssetForm"];
    var name = addAsset["name"];
    var description =  document.getElementById("description");
    var category =  addAsset["category"];


    // get p tag elements
    var nameAlert = document.getElementById("name_alert");
    var descriptionAlert =  document.getElementById("description_alert");
    var categoryAlert =  document.getElementById("category_alert");
    var formAlert =  document.getElementById("form_alert");

    var submitBtn =  document.getElementById("submitBtn");

    //Verifying required input
    if(name.value!="" && description.value!="" && category.value!=""){
        dataValid=true;
        formAlert.innerHTML = "";
    }
    else{
        dataValid=false;
        formAlert.innerHTML = "Please fill required values";
    }

    //Sending Data to the Controller
    if(dataValid == true){
        console.log(description.value);
        var ajax=new XMLHttpRequest();
        submitBtn.disabled =true;
        submitBtn.value="Processing...";

//        if (typeof(Storage) !== "undefined") {
//
//            if(sessionStorage.getItem("userId")==null){
//                //alert('Session expired');
//                window.location.replace('/E-AssetManagement/login.html');
//            }
//            else{
//                //console.log(sessionStorage.getItem("role"))
//                userId=sessionStorage.getItem("userId");
//            }
//        }
//        else{
//            //alert('Session expired');
//            window.location.replace('/E-AssetManagement/login.html');
//        }


        ajax.open("POST","/E-AssetManagement/AddAssetController",true);
        ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.send("name="+titleCase(name.value)+"&category="+category.value+"&description="+description.value);
        ajax.onreadystatechange=function(){
            if(this.readyState==3){
            submitBtn.value="Submitting...";
            }
            else if (this.readyState==4&&this.status==200) {
            //request ready
            submitBtn.value="Add Asset";
            addAsset.reset();
            formAlert.innerHTML = this.responseText;
            window.setTimeout(function(){submitBtn.disabled =false;},3000);
            }
            else{
                window.setTimeout(function(){formAlert.innerHTML = "";},3000);
                submitBtn.disabled =false;
                submitBtn.value="Add Asset";
            }
        }
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
