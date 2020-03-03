let hideEmployeeSavedAlertTimer = undefined;

document.addEventListener("DOMContentLoaded", () => {
	getSaveActionElement().addEventListener("click", saveActionClick);
});

// Save
function saveActionClick(event) {
	const saveActionElement = event.target;
	saveActionElement.disabled = true;
	var checkFirst = document.getElementById("fname").value;
	var checkLast = document.getElementById("lname").value;
	var checkPass = document.getElementById("password").value;
	var checkPass2 = document.getElementById("verifypw").value;
	var empType = document.getElementById("emptype").value;
	if (checkFirst == ""){
						document.getElementById("fnameErr").innerHTML = "First name is empty. Please enter your first name."; 
						document.getElementById("lnameErr").innerHTML = "";
						document.getElementById("passwordErr").innerHTML = "";
						return false;
					} 
	if (checkLast == ""){
						document.getElementById("fnameErr").innerHTML = "";
						document.getElementById("lnameErr").innerHTML= "Last name is empty. Please enter your last name.";
						document.getElementById("passwordErr").innerHTML = "";
						return false;
					} 
	if (checkPass != checkPass2){
						document.getElementById("fnameErr").innerHTML = "";
						document.getElementById("lnameErr").innerHTML = "";
						document.getElementById("passwordErr").innerHTML = "Passwords do not match. Please ensure your password and its verfication match.";
						return false;
					} 
	const employeeId = getEmployeeId(); 
	const employeeIdIsDefined = ((employeeID != null) && (employeeID.trim() !== ""));
	const saveActionUrl = ("/employeeDetail/"
		+ (employeeIDIsDefined ? employeeID : ""));
	const saveEmployeeRequest = {
		fname: checkFirst,
		lname: checkLast,
		password: checkPass,
		emptype: empType
	};

	if (employeeIdIsDefined) {
		ajaxPut(saveActionUrl, saveEmployeeRequest, (callbackResponse) => {
			saveActionElement.disabled = false;
			if (isSuccessResponse(callbackResponse)) {
				displayEmployeeSavedAlertModal();
			}
		});
	} else {
		ajaxPost(saveActionUrl, saveProductRequest, (callbackResponse) => {
			saveActionElement.disabled = false;
			if (isSuccessResponse(callbackResponse)) {
				displayEmployeeSavedAlertModal();
			}
		});
	}			
	
	displayEmployeeSavedAlertModal();
}

function displayEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	const savedAlertModalElement = getSavedAlertModalElement();
	savedAlertModalElement.style.display = "none";
	savedAlertModalElement.style.display = "block";

	hideEmployeeSavedAlertTimer = setTimeout(hideEmployeeSavedAlertModal, 1200);
}

function hideEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	getSavedAlertModalElement().style.display = "none";
}
// End save

//Getters and setters
function getSavedAlertModalElement() {
	return document.getElementById("employeeSavedAlertModal");
}

function getSaveActionElement() {
	return document.getElementById("saveButton");
}
//End getters and setters
