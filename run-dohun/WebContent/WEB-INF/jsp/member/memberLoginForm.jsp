<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>Bootstrap Example</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<!-- 카카오 로그인 API -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<!-- 네이버 로그인 API -->
<script type="text/javascript" src="/resources/js/naverApi/naverLogin_implicit-1.0.3.js"></script>

<style>
/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 450px
}
</style>
<script type="text/javascript">
  $(document).ready(function(){
	  $("#userId_span").hide();
	  
	  $(".nav.navbar-nav > li").click(function(){
		  $(".nav.navbar-nav > li").removeAttr("class");
		  $("#"+$(this).attr("id")).addClass("active");
		  
		  var menuName = $(this).attr("id");
		  var menuUrl="";
		  
		  switch(menuName) {
		  case "home":
			  menuUrl ="/mainForm.do";
			  break;
		  case "honey":
			  menuUrl ="/honeyForm.do";
			  break;
		  case "game":
			  menuUrl ="/baseballGameForm.do";
			  break;
		  case "about":
			  menuUrl ="/aboutForm.do";
			  break;
		  case "contact":
			  menuUrl ="/contactForm.do";
			  break;
		  case "projects":
			  menuUrl ="/projectsForm.do";
			  break;
		  default:
			  menuUrl ="/login.do";
		  }
		  
		  if($(this).attr("id") == menuName){
			  $.ajax({
				  url: menuUrl,
				  type: "POST",
				  data: {
					  "startCheck": "aaa",
					  "userNum": "bbb",
					  "rNum" : "ccc"
					  },
					  success: function(result){
						  console.log("success");
						  $("#content_div").html(result);
					  }, 
					  error: function(request,status,error){
// 					    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					    console.log("code:"+request.status+" error:"+error);
					  }
				});
		  }
	  });
	
	 
    
    
	$(".form-group > input").keyup(function(){
		switch($(this).attr("id")) {
		case "userId":
			searchName();
			break;
		case "userEmail":
			searchEmail();
			break;
		case "userPassword":
			searchPwd();
			break;
		default:
			
		}
	});
	
	//사용자 가입취소
	$("#closeModal, #closeModal02").click(function(){
		$("form").each(function() {
			if(this.id == "createUserForm"){
				this.reset();
			}
			$("#userId_div, #userPassword_div, #userEmail_div").removeClass("has-success has-feedback");
    		$("#userId_div, #userPassword_div, #userEmail_div").removeClass("has-error has-feedback");
    		$("#userId_span, #userPassword_span, #userEmail_span").removeClass("glyphicon-ok");
    		$("#userId_span, #userPassword_span, #userEmail_span").removeClass("glyphicon-warning-sign");
    		$("#userId_span, #userPassword_span, #userEmail_span").hide();
		});
	});
	
	function successMemberJoin(result){
		console.log("success");
		$("#closeModal").click();
	};
	function errorMemberJoin(request,status,error){
		 console.log("code:"+request.status+" error:"+error);
	};
	//사용자 가입
	$("#creatUser").click(function(){
		if($("#userId").val().trim() == ""){
			alert("이름을 입력하세요");
			return false;
		}else if($("#userEmail").val().trim() == ""){
			alert("이메일을 입력하세요");
			return false;
		}
		else if($("#userPassword").val().trim() == ""){
			alert("비밀번호 입력하세요");
			return false;
		};
		
		customAjaxFrom("/memberJoin.do", "createUserForm", successMemberJoin, errorMemberJoin);
		
	});
  });

  
  
  
  
  
  
  
  
  
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
	


//아이디 입력시 vaildation조회
var checkIdStatus = 0;
function searchName() {
	var userId = $("#userId").val().trim();
	if(userId.length < 5){
		joinVaildation("userId", "default");
	}else{
		customAjax("/memberNameCheck.do", {"userId":userId}, successIdChceck, errorIdChceck);
	}
};
function successIdChceck(result){
	if(result.status == "true"){//사용불가
		joinVaildation("userId", "error");
		checkIdStatus = 0;
	}else{//사용가능
		joinVaildation("userId", "ok");
		checkIdStatus = 1;
	}
};
function errorIdChceck(request,status,error){
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
				<!-- <li id="home" class="active"><a href="#">Home</a></li>
        <li id="honey"><a href="#">Honey</a></li>
        <li id="game"><a href="#">Game</a></li>
        <li id="about"><a href="#">About</a></li>
        <li id="projects"><a href="#">Projects</a></li>
        <li id="contact"><a href="#">Contact</a></li> -->
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="login" class="active">
					<a href="/memberLoginForm.do">
					<span class="glyphicon glyphicon-log-in"></span> Login</a>
				</li>
			</ul>
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
				<form action="/memberLogin.do" method="post">
					<h1>Login</h1>
					<p>사용자로그인</p>
					<hr>
					<div>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<input id="loginId" name="loginId" type="text" class="form-control" placeholder="Email" maxlength="40">
						</div>
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input id="loginPwd" name="loginPwd" type="password" class="form-control" placeholder="Password" maxlength="20">
						</div>
						<br>
						<button type="submit" class="btn btn-default">Sing in</button>
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#joinModal">Sing up</button>
						<!-- <button type="button" class="btn btn-default" onclick="createKaKaoButton();">카카오 로그인</button>
						<button type="button" class="btn btn-default" onclick="logOutKaKao();">카카오 로그아웃</button> -->
						<span id="naver_id_login"></span>
					</div>
				</form>
				<!-- 네아로 API-->
				<script type="text/javascript">
					var naver_id_login = new naver_id_login("vZbX7ZW_zMbNccbH_O_1", "http://127.0.0.1/naverLogin.do");
					var state = naver_id_login.getUniqState();
					naver_id_login.setButton("green", 2,32);
					naver_id_login.setDomain("http://127.0.0.1");
					naver_id_login.setState(state);
					naver_id_login.init_naver_id_login();
				</script>
				<!-- //네아로 초기화 API-->
				
				<div>
					<a id="kakao-login-btn"></a>
					<a href="http://developers.kakao.com/logout"></a>
					<script type='text/javascript'>

				    // 사용할 앱의 JavaScript 키를 설정해 주세요.
				    Kakao.init('fd33ac52bad6f68a95100d08899b2e92');
					function createKaKaoButton(){
						//<![CDATA[
						
					    // 카카오 로그인 버튼을 생성합니다.
					      Kakao.Auth.login({
					      success: function(authObj) {
// 					    	  console.log(JSON.stringify(authObj));
						      console.log("access_token: " + authObj.access_token);
						      console.log("token_type: " + authObj.token_type);
						      console.log("refresh_token: " + authObj.refresh_token);
						      console.log("expires_in: " + authObj.expires_in);
						      console.log("scope: " + authObj.scope);
						      
						      Kakao.API.request({
									url: '/v1/user/me',
									success: function(res) {
										console.log(res.properties.nickname);
										console.log(res.properties.profile_image);
										
									},
									fail: function(error) {
										console.log(error);
									}
								});
					      },
					      fail: function(err) {
					         alert(JSON.stringify(err));
					      }
					    });
					  //]]>
					}
					
					function logOutKaKao(){
						//현재 로그인되어 있는 사용자를 로그아웃시키고, Access Token과 Refresh 토큰을 삭제합니다.
						Kakao.Auth.logout();	
					}
					  
				  </script>
				</div>
				<!-- //카카오 로그인 -->
				</div>
				
				<!-- //로그인 -->
				<!-- Modal -->
				<div class="modal fade" id="joinModal" role="dialog">
					<div class="modal-dialog">
					
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" id="closeModal02" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Sign up</h4>
							</div>
							<div class="modal-body text-left">
								<form id="createUserForm">
									<div id="userId_div" class="form-group">
										  <label for="usr">UserId <b style="color: red;">*</b></label> 
										  <input type="text" class="form-control" id="userId" name="userId">
										  <span id="userId_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userName_div" class="form-group">
										  <label for="usr">Username <b style="color: red;">*</b></label> 
										  <input type="text" class="form-control" id="userName" name="userName">
										  <span id="userName_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userEmail_div" class="form-group">
										  <label for="usr">Email Address <b style="color: red;">*</b></label> 
										  <input type="text" class="form-control" id="userEmail" name="userEmail">
										  <span id="userEmail_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userPassword_div" class="form-group">
										  <label for="Password">Password <b style="color: red;">*</b></label>
										  <input type="password" class="form-control" id="userPassword" name="userPassword">
										  <span id="userPassword_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userPhone_div" class="form-group">
										  <label for="usr">Phone</label>
										  <input type="text" class="form-control" id="userPhone" name="userPhone">
										  <span id="userPhone_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userAddress_div" class="form-group">
										  <label for="usr">Local Address</label>
										  <input type="text" class="form-control" id="userAddress" name="userAddress">
										  <span id="userAddress_span" class="glyphicon form-control-feedback"></span>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" id="creatUser" class="btn btn btn-success">Create an account</button>
								<button type="button" id="closeModal" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /모달 -->
			</div>
			<!-- right -->
			<div class="col-sm-4 text-center"></div>
			<!-- //right -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>