/**
 * 
 */
//id값으로 길이 체크
function checkLength(length, id){
	var val = document.getElementById(id);
	if(length == val.value.length){
		return "true";
	}else{
		return "false";
	}
} 

//vaildation
function vaildation(form){
}

//Ajax 폼 데이터 있을때 
function customAjaxFrom(url, formId, successCallback, errorCallback){
	var formData = $("#"+formId).serialize();
	$.ajax({
		url: url,
		type: "POST",
		data: formData,
		success: successCallback,
		error:errorCallback
	});
};

//Ajax 폼 데이터 없을때 
function customAjax(url, successCallback, errorCallback){
	$.ajax({
		url: url,
		type: "POST",
		success: successCallback,
		error:errorCallback
	});
};

