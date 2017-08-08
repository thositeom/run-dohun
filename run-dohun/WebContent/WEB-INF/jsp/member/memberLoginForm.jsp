<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>Bootstrap Example</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

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
			  menuUrl ="/baseballGameFrom.do";
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
	
	//아이디 입력시 vaildation조회
	var checkIdStatus = 0;
	function searchName() {
		var userId = $("#userId").val().trim();
		if(userId.length < 1){
			joinVaildation("userId", "default");
		}else{
			$.ajax({
				url: "/memberNameCheck.do",
				type: "POST",
				data: {"userId":userId},
					success: function(result){
						if(result.status == "true"){//사용불가
							joinVaildation("userId", "error");
							checkIdStatus = 0;
						}else{//사용가능
							joinVaildation("userId", "ok");
							checkIdStatus = 1;
						}
					}, 
					error: function(request,status,error){
					  console.log("code:"+request.status+" error:"+error);
					}
			});
		}
    };
	 
    //Email vaildation조회
    function searchEmail(){
    	var userEmail = $("#userEmail").val().trim();
    	var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;

    	if(userEmail.length < 1){
    		joinVaildation("userEmail", "default");
    	}else{
    		if (!userEmail.match(regExp)){
    			joinVaildation("userEmail", "error");
	    	}else{
	    		joinVaildation("userEmail", "ok");
	    	}
    	}
    }
    
  	//Pwd vaildation조회
    function searchPwd(){
    	/* var regex = /^.*(?=.{7,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/; */
    	var regex = /^(?=.*\d)(?=.*[~`!@#$%\^&*()-])(?=.*[a-zA-Z]).{7,20}$/;
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
		if(!userPwd.match(regex)){
			//정규식  false
			joinVaildation("userPassword", "error");
		}else{
			//정규식 true
			joinVaildation("userPassword", "ok");
		}
		
  	}
    
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
	}
	
		  
	$("#creatUser").click(function(){
		var formData = $("#createUserForm").serialize();

		var userId = $("#userId").val().trim();
		var userEmail = $("#userEmail").val().trim();
		var userPwd = $("#userPassword").val().trim();

		if(userId==""){
			alert("이름을 입력하세요");
			return false;
		}else if(userEmail==""){
			alert("이메일을 입력하세요");
			return false;
		}
		else if(userPwd==""){
			alert("비밀번호 입력하세요");
			return false;
		};

		$.ajax({
			  url: "/memberJoin.do", 
			  type: "POST",
			  data: formData,
			  success: function(result){
				  console.log("success");
				  //form 초기화
				  $("form").each(function() {
					  if(this.id == "createUserForm") this.reset();
				  });
				  $("#closeModal").click();
			  }, 
			  error: function(request,status,error){
				  console.log("code:"+request.status+" error:"+error);
			  }
		});
	});
  
  
  });
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
			<a class="navbar-brand" href="http://localhost/">Logo</a>
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
					</div>
				</form>
				</div>
				<!-- //로그인 -->
				<!-- Modal -->
				<div class="modal fade" id="joinModal" role="dialog">
					<div class="modal-dialog">
					
						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Sign up</h4>
							</div>
							<div class="modal-body text-left">
								<form id="createUserForm">
									<div id="userId_div" class="form-group">
										  <label for="usr">UserId (*)</label> 
										  <input type="text" class="form-control" id="userId" name="userId">
										  <span id="userId_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userName_div" class="form-group">
										  <label for="usr">Username (*)</label> 
										  <input type="text" class="form-control" id="userName" name="userName">
										  <span id="userName_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userEmail_div" class="form-group">
										  <label for="usr">Email Address (*)</label> 
										  <input type="text" class="form-control" id="userEmail" name="userEmail">
										  <span id="userEmail_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userPassword_div" class="form-group">
										  <label for="Password">Password (*)</label>
										  <input type="password" class="form-control" id="userPassword" name="userPassword">
										  <span id="userPassword_span" class="glyphicon form-control-feedback"></span>
									</div>
									<div id="userPhone_div" class="form-group">
										  <label for="usr">Phone (*)</label>
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