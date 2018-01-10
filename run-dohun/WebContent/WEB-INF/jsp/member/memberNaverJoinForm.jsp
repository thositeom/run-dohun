<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>inma-Nu SNS 계정 회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- 네이버 로그인 API -->
<script type="text/javascript" src="/resources/js/naverApi/naverLogin_implicit-1.0.3.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  
	//Validation Event
	$(".form-group > input").keyup(function(){
		switch($(this).attr("id")) {
		case "userEmail":
			searchEmail();
			break;
		case "userPassword":
			searchPwd();
			break;
		default:
		}
	});
	  
	//사용자 가입
	$("#creatUser").click(function(){
		if($("#userEmail").val().trim() == ""){
			alert("이메일을 입력하세요");
			return false;
		}
		else if($("#userPassword").val().trim() == ""){
			alert("비밀번호 입력하세요");
			return false;
		};
		customAjaxFrom("/memberNaverJoin.do", "createUserForm", successMemberJoin, errorMemberJoin);
	});
}); // $(document).ready END

function successMemberJoin(result){
	console.log("success");
	$("#closeModal").click();
};
function errorMemberJoin(request,status,error){
	 console.log("code:"+request.status+" error:"+error);
};


//Email vaildation조회
function searchEmail(){
	var userEmail = $("#userEmail").val().trim();

	if(userEmail.length < 1){
		joinVaildation("userEmail", "default");
	}else{
		if (!uValidation.checkRegExpEmail(userEmail)){
			joinVaildation("userEmail", "error");
   	}else{
   		joinVaildation("userEmail", "ok");
   	}
	}
};
  
//Pwd vaildation조회
function searchPwd(){
	var userPwd =  $("#userPassword").val().trim();
	if(userPwd.length < 1){
		//빈칸
		joinVaildation("userPassword", "default");
		return false;
	}
	if(userPwd.length < 7){
		//7자리 이하
		joinVaildation("userPassword", "error");
		return false;
	}
	if(!uValidation.checkRegExpPassword(userPwd)){
		//정규식  false
		joinVaildation("userPassword", "error");
	}else{
		//정규식 true
		joinVaildation("userPassword", "ok");
	}
};

//css제어
function joinVaildation(inputId, status){
	var id_div  = '#'+inputId+'_div';
	var id_span = '#'+inputId+'_span';
	
	switch(status){
	case "error":
		//정규식  false
		$(id_div).removeClass("has-success has-feedback");
		$(id_div).addClass("has-error has-feedback");
		$(id_span).removeClass("glyphicon-ok");
		$(id_span).addClass("glyphicon-warning-sign");
		$(id_span).show();
		break;
	case "ok":
		//정규식 true
   		$(id_div).removeClass("has-error has-feedback");
   		$(id_div).addClass("has-success has-feedback");
   		$(id_span).removeClass("glyphicon-warning-sign");
		$(id_span).addClass("glyphicon-ok");
		$(id_span).show();
		break;
	default :
		$(id_div).removeClass("has-success has-feedback");
   		$(id_div).removeClass("has-error has-feedback");
   		$(id_span).removeClass("glyphicon-ok");
   		$(id_span).removeClass("glyphicon-warning-sign");
   		$(id_span).hide();
	}
};
</script>

</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/index.html">Logo</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
			</ul>
			<!-- <ul class="nav navbar-nav navbar-right">
				<li id="login" class="active">
					<a href="/memberLoginForm.do">
					<span class="glyphicon glyphicon-log-in"></span> Login</a>
				</li>
			</ul> -->
		</div>
	</div>
	</nav>
	<div class="container-fluid text-center">
		<div class="row content">
			<div id="content_div">
				<!-- left -->
				<div class="col-sm-4 text-center"></div>
				<!-- //left -->
				<!-- 로그인 -->
				<div class="col-sm-4 text-center">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">SNS 계정 회원가입</h4>
						</div>
						<div class="modal-body text-left">
							<form id="createUserForm">
								<input type="hidden" id="snsId" name="snsId" value="${snsId}">
							
								<div id="userId_div" class="form-group">
									  <label for="usr">UserId <b style="color: red;">*</b></label> 
									  <input type="text" class="form-control" id="userId" name="userId" readonly="readonly" value="${email }">
									  <span id="userId_span" class="glyphicon form-control-feedback"></span>
								</div>
								<div id="userName_div" class="form-group">
									  <label for="usr">Username <b style="color: red;">*</b></label> 
									  <input type="text" class="form-control" id="userName" name="userName" readonly="readonly" value="${name }">
									  <span id="userName_span" class="glyphicon form-control-feedback"></span>
								</div>
								<div id="userEmail_div" class="form-group">
									  <label for="usr">Email Address <b style="color: red;">*</b></label> 
									  <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="비밀번호 분실시 사용됩니다.">
									  <span id="userEmail_span" class="glyphicon form-control-feedback"></span>
								</div>
								<div id="userPassword_div" class="form-group">
									  <label for="Password">Password <b style="color: red;">*</b></label>
									  <input type="password" class="form-control" id="userPassword" name="userPassword">
									  <span id="userPassword_span" class="glyphicon form-control-feedback"></span>
								</div>
								<div id="userPhone_div" class="form-group">
									  <label for="usr">Phone</label>
									  <input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="차후에 본인인증 사용">
									  <span id="userPhone_span" class="glyphicon form-control-feedback"></span>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" id="creatUser" class="btn btn btn-success">Create an account</button>
							<button type="button" id="closeModal" class="btn btn-default" >Close</button>
						</div>
					</div>
				<!-- /모달 -->
				<br/><br/>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>