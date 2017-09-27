<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--     <link rel="stylesheet" href="/resources/uploader-master/demos/css/simple-demo.css" rel="stylesheet" /> -->
    <style type="text/css">
   	/*
	 * Widget
	 */
	.uploader
	{
		/* border: 2px dotted #A5A5C7; */
		width: 100%;
		color: #92AAB0;
		text-align: center;
		vertical-align: middle;
		padding: 15px 0px;
		margin-bottom: 10px;
	  	font-size: 24px;
		cursor: default;
		-webkit-touch-callout: none;
		-webkit-user-select: none;
		-khtml-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
	}
	.uploader-active {
		border-color: #0B85A1;
	}
	.uploader div.or {
		font-size:14px;
		font-weight: bold;
		color: #C0C0C0;
		padding: 10px;
	}
	.uploader div.browser label {
		background-color: #5a7bc2;
		padding: 5px 15px;
		color: white;
		padding: 6px 0px;
	  	font-size: 14px;
		font-weight: bold;
		cursor: pointer;
		border-radius: 2px;
		position: relative;
		overflow: hidden;
		display: block;
		width: 300px;
		margin: 10px auto 0px auto;
		box-shadow: 2px 2px 2px #888888;
	}
	.uploader div.browser span {
		cursor: pointer;
	}
	.uploader div.browser input {
		position: absolute;
		top: 0;
		right: 0;
		margin: 0;
		border: solid transparent;
		border-width: 0 0 100px 200px;
		opacity: .0;
		filter: alpha(opacity= 0);
		-o-transform: translate(250px,-50px) scale(1);
		-moz-transform: translate(-300px,0) scale(4);
		direction: ltr;
		cursor: pointer;
	}
	.uploader div.browser label:hover {
		background-color: #427fed;
	}
	
	 /*
	 * File list
	 */ 
	#fileList {
	/*  float:right; */
		width: 100%;
		height:127px;
		list-style-type: none;
		color: gray;
		font-size: 10px;
		overflow: auto;
	}
	#fileList .file {
	  width: 100%;
	  margin-bottom: 5px;
	}
	#fileList .info {
	  width: 100%;
	  height: 45px;
	  display: block;
	  overflow: hidden;
	  line-height: 13px;
	}
	#fileList .filename {
	  font-weight: bold;
	}
	/* #fileList .bar {
	  border: solid 1px #C0C0C0;
	  height: 12px;
	  margin-top: 5px;
	  padding: 1px;
	  width: 100%;
	}
	#fileList .progress {
	  height: 12px;
	  background-color: #00CCFF;
	} */
	#fileList span.success {
	  color: #009900;
	}
	#fileList span.error {
	  color: #990000;
	}
    </style>
    
    <script type="text/javascript" src="/resources/uploader-master/src/dmuploader.js"></script>
    <script type="text/javascript">
      //-- Some functions to work with our UI
      function add_log(message)
      {
        var template = '<li>[' + new Date().getTime() + '] - ' + message + '</li>';
        $('#debug').find('ul').prepend(template);
      }
      
      function add_file(id, file)
      {
        var template = '' +
        '<div class="file" id="uploadFile' + id + '">' +
            '<div class="info">' +
              '파일명 - <span class="filename" title="Size: ' + file.size + 'bytes - Mimetype: ' + file.type + '">' + file.name + '</span><br /><small>Status: <span class="status">Waiting</span></small>' +
              '<span style="margin-left: 10px;"><button type="button" onclick="btnFileDelete('+ id +');" class="btn btn-success btn-xs">삭제</button></span>'+
              '</div>' +
          '</div>';
          $('#fileList').prepend(template);
      }
      
      function update_file_status(id, status, message)
      {
        $('#uploadFile' + id).find('span.status').html(message).addClass(status);
      }
      
      function update_file_progress(id, percent)
      {
        $('#uploadFile' + id).find('div.progress').width(percent);
      }
      
      // Upload Plugin itself
      $('#drag-and-drop-zone').dmUploader({
        url: '/fileUpload.do',
        dataType: 'json',
        /* allowedTypes: 'image/*', */
        allowedTypes: '*',
        /*extFilter: 'jpg;png;gif',*/
        onInit: function(){
          add_log('Penguin initialized :)');
        },
        onBeforeUpload: function(id){
          add_log('Starting the upload of #' + id);
          
          update_file_status(id, 'uploading', 'Uploading...');
        },
        onNewFile: function(id, file){
          add_log('New file added to queue #' + id);
          
          add_file(id, file);
        },
        onComplete: function(){
          add_log('All pending tranfers finished');
        },
        onUploadProgress: function(id, percent){
          var percentStr = percent + '%';

          update_file_progress(id, percentStr);
        },
        //업로드 성공시
        onUploadSuccess: function(id, data){
          add_log('Upload of file #' + id + ' completed');
          add_log('Server Response for file #' + id + ': ' + JSON.stringify(data));
          update_file_status(id, 'success', 'Upload Complete');
          update_file_progress(id, '80%');
          
          //saveFileName, origName, fileSize, extention 한줄로 만들기
          for(var i=0; i<data.fileList.length; i++){
		 	var fileString = data.fileList[i].saveFileName +"//"+ data.fileList[i].origName +"//"+
		 					 data.fileList[i].fileSize +"//"+ data.fileList[i].extention;
		 	
          	$('#hiddenForm').append('<input type="hidden" id="fileUpload'+id+'" name="fileUploadList" value="'+fileString+'"/>');
          };
          
        },
        onUploadError: function(id, message){
          add_log('Failed to Upload file #' + id + ': ' + message);
          
          update_file_status(id, 'error', message);
        },
        onFileTypeError: function(file){
          add_log('File \'' + file.name + '\' cannot be added: must be an image');
          
        },
        onFileSizeError: function(file){
          add_log('File \'' + file.name + '\' cannot be added: size excess limit');
        },
        /*onFileExtError: function(file){
          $.danidemo.addLog('#demo-debug', 'error', 'File \'' + file.name + '\' has a Not Allowed Extension');
        },*/
        onFallbackMode: function(message){
          alert('Browser not supported(do something else here!): ' + message);
        }
      });
      
      
      function fileDeleteSuccess(){
    	  
      }
      
	  function fileDeleteError(){
    	  
      }
      //임시업로드삭제
      function btnFileDelete(id){
    	  var data = {"fileString":$("#fileUpload"+id).val()};
    	  
    	  customAjax("/fileDelete.do", data, fileDeleteSuccess, fileDeleteError);
    	  
    	  $("#fileUpload"+id).remove();	//전송할 데이터 삭제
    	  $("#uploadFile"+id).remove(); //UI삭제
      }
    </script>
    
	</head>
  
  <!-- Drag & Drop Images Here -->
	<div class="form-group">
		<div class="panel panel-default col-sm-6">
			<div id="drag-and-drop-zone" class="panel-body demo-panel-debug uploader">
				<div>Drag &amp; Drop Images Here</div>
				<div class="or">-or-</div>
				<div class="browser">
	            <label>
	              <span>Click to open the file Browser</span>
	              <input type="file" name="files[]" multiple="multiple" title='Click to add Files'>
	            </label>
	          </div>
			</div>
	   	</div>
	    <!-- Files will be places here -->
	    <div class="panel panel-default col-sm-6">
		    <div class="panel-body">
			    <!-- 저장시 file저장을 위한 hidden영역 -->
			    <div id="hiddenForm"></div>
		      	<!-- 임시파일 list -->
		      	<div id="fileList">
		      	</div>
		    </div>
	    </div>
	</div>
    
    <div class="form-group">
      <!-- Debug box -->
      <div class="panel panel-default" style="overflow: auto; font-size: 10px">
        <div class="panel-heading">
          <h3 class="panel-title">Debug</h3>
        </div>
        <div id="debug" class="panel-body demo-panel-debug">
        	<ul style="padding: 0px 0px 0px 20px;"></ul>
        </div>
      </div> 
    </div>

