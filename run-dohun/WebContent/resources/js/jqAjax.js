/**
 * 
 */
//jqAjax 정리중 20170817
$.jqAjax = {};
$.extend($.jqAjax, {
	version : "0.0.1",
	
	customAjax : function(url, data, successCallback, errorCallback){
		$.ajax({
			url: url,
			data: data,
			beforeSend : function(xmlHttpRequest){
				xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을 header에 기록
			},
			type: "POST",
			success: successCallback,
			error:errorCallback
		});
	},
	customAjaxFrom : function(url, formId, successCallback, errorCallback){
			var formData = $("#"+formId).serialize();
			$.ajax({
			url: url,
			type: "POST",
			data: formData,
			beforeSend : function(xmlHttpRequest){
				xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을 header에 기록
			},
			success: successCallback,
			error:errorCallback
			});
	},
	serializeObjectAjax : function (url, formId, successCallback, errorCallback){
		var formdata = $("#"+formId).serializeObject();
			$.ajax({
			url: url,
			data: formdata,
			type: "POST",
			beforeSend : function(xmlHttpRequest){
				xmlHttpRequest.setRequestHeader("AJAX", "true"); // ajax 호출을 header에 기록
			},
			success: successCallback,
			error:errorCallback
		});
	}	
});
