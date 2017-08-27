/**
 * 
 */

function customAjaxFromData(url, formId, data,successCallback, errorCallback){
	var formData = $("#"+formId).serialize();
	$.ajax({
		url: url,
		type: "POST",
		data: formData+"&"+data,
		beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
        },
		success: successCallback,
		error:errorCallback
	});
};


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

/**
* javascript library Validation(연관배열 방식 사용)
*/
(function(window){
	
	var uValidation = {
			ualert : function(){
				alert("@@하이룽");	
			},
			checkLength : function(length, fieldId, fieldValue){
				if(fieldId == null || fieldId ==""){
					return fieldValue.length == length;
				}else{
					return document.getElementById(fieldId).value.length == length; 
				}
			},
			chckMaxLength : function(maxLength, fieldId, fieldValue){
				if(fieldId == null || fieldId ==""){
					return fieldValue.length <= maxLength;
				}else{
					return document.getElementById(fieldId).value.length <= maxLength; 
				}
			},
			checkMinLength : function(minLength, fieldId, fieldValue){
				if(fieldId == null || fieldId ==""){
					return fieldValue.length >= minLength;
				}else{
					return document.getElementById(fieldId).value.length >= minLength; 
				}
			},
			checkRegExp : function(regExp, fieldValue){
				return fieldValue.match(regExp); 
			},
			checkRegExpEmail : function(fieldValue){
				var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
				return fieldValue.match(regExp); 
			},
			checkRegExpPassword : function(fieldValue){
				var regExp = /^(?=.*\d)(?=.*[~`!@#$%\^&*()-])(?=.*[a-zA-Z]).{7,20}$/;
				return fieldValue.match(regExp); 
			}
	
	};
	window.uValidation= uValidation;
})(window);



