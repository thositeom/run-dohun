/**
 * 
 */

/**
 * AjaxForm submit; fileupload
*/
function customAjaxForm(url, formId, successCallback, errorCallback){
	var formData = $("#"+formId).serialize();
	$("#"+formId).ajaxSubmit({ 
	    url: url, 
	    type: "POST",
	    processData: false,
	    enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지정해주어야 하는 부분이며 multipart로 지정해주지 않으면 controller로 파일을 보낼 수 없음
//	    data: formData,
	    beforeSend : function(xmlHttpRequest){
			xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
		},
		success: successCallback,
		error:errorCallback
	});
}
/*function customAjaxForm(url, formId, successCallback, errorCallback){
	$("#"+formId).ajaxForm({
		url: url,
		type: "POST",
		enctype: "multipart/form-data", // 여기에 url과 enctype은 꼭 지정해주어야 하는 부분이며 multipart로 지정해주지 않으면 controller로 파일을 보낼 수 없음
		beforeSubmit: function (data,form,option) {
			//validation체크
			//막기위해서는 return false를 잡아주면됨
			return true;
		},
		beforeSend : function(xmlHttpRequest){
			xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을  header에 기록
		},
		success: successCallback,
		error:errorCallback
	}).ajaxSubmit();
}
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



