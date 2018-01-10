<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>inma-Nu Top 코드등록 </title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("button").click(function(){
		
		if($(this).attr("id") === "inmanuTopCodeAdd"){
			var check = confirm	("코드값을 등록 하시겠습니까?");
			if(check === true){
	 			customAjaxFrom("/inmanuTopCodeAdd.do", "codeForm", successTopCodeAddCallback, errorTopCodeAddCallback);				
			}
		}else if($(this).attr("id") === "inmanuTopCodePopClose"){
			self.close();
		}
	});
});
function successTopCodeAddCallback(result){
	alert("저장되었습니다.");
	opener.location.reload();
	window.close();
}
function errorTopCodeAddCallback(result, status, error){
	console.log("code:"+result.status+"\n"+"message:"+result.responseText+"\n"+"error:"+error);
}

</script>
</head>	
<body>
	<div class="container-fluid text-center">
	<form id="codeForm">
		<div class="row content">
			<div id="content_div">
				<h3>inma-Nu</h3>
	    		<p>팝업창창</p>
	    	  	<hr>
				<!-- center -->
				<div class="col-sm-12 text-left">
					<div class="input-group">
						<span class="input-group-addon">코드값</span> 
						<input id="topCode" name="topCode" type="text" maxlength="30" class="form-control" placeholder="대문자만 입력 가능합니다." maxlength="40" style="ime-mode:disabled; text-transform:uppercase;">
					</div>
					<br/>
					<div class="input-group">
						<span class="input-group-addon">코드명</span>
						<input id="loginPwd" name="topCodeName" type="text" maxlength="30" class="form-control" placeholder="코드명을 입력하세요." maxlength="20">
					</div>
					<br/>
					<div class="input-group">
						<span class="input-group-addon">코드설명</span>
						<input id="loginPwd" name="topCodeDesc" type="text" maxlength="30" class="form-control" placeholder="코드설명을 입력하세요." maxlength="20">
					</div>
					<div class="modal-footer">
						<button class="btn-default btn-xs" id="inmanuTopCodeAdd">추가</button>
						<button class="btn-default btn-xs" id="inmanuTopCodePopClose">닫기</button>
					</div>
				</div>
				<!-- //center -->
			</div>
			<!-- //content_div -->
		</div>
		<!-- //row content -->
	</form>
	</div>
	<!-- //container-fluid -->
</body>
</html>