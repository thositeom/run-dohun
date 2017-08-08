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

//Ajax form submit; 
function customAjaxFrom(url, formId, successCallback, errorCallback){
	var formData = $("#"+formId).serialize();
	$.ajax({
		url: url,
		type: "POST",
		data: formData,
		beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
        },
		success: successCallback,
		error:errorCallback
	});
};

//Ajax data submit;
function customAjax(url, data, successCallback, errorCallback){
	$.ajax({
		url: url,
		data: data,
		beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
        },
		type: "POST",
		success: successCallback,
		error:errorCallback
	});
};

//serialize to json;
jQuery.fn.serializeObject = function () {
	  //json 형태로 변환
	  var formData = {};
	  var formArray = this.serializeArray();

	  for(var i = 0, n = formArray.length; i < n; ++i)
	    formData[formArray[i].name] = formArray[i].value;

	  return formData;
		
	  //아래쳐럼 가져와 사용
	  /*var formdata = $('form').serializeObject();
		console.log(formdata);
		console.log(JSON.stringify(formdata));*/
};

//Ajax json submit;
function serializeObjectAjax(url, formId, successCallback, errorCallback){
	var formdata = $("#"+formId).serializeObject();
	console.log(formdata);
	console.log(JSON.stringify(formdata));
	$.ajax({
		url: url,
		data: formdata,
		type: "POST",
		success: successCallback,
		error:errorCallback
	});
	
};



