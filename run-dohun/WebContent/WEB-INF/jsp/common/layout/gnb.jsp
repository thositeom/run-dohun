<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tlds" uri="http://run-dohun.kr/tlds" %>

<script type="text/javascript">
  $(document).ready(function(){
	  $(".nav.navbar-nav > li").click(function(){
		  $(".nav.navbar-nav > li").removeAttr("class");
		  $("#"+$(this).attr("id")).addClass("active");
		  
		  var menuName = $(this).attr("id");
		  var menuUrl="";
		  var data;
		  
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
		  case "board":
			  menuUrl ="/boardForm.do";
			  data = "boardType\=PN";
			  break;
		  case "contact":
			  menuUrl ="/contactForm.do";
			  break;
		  case "sample":
			  menuUrl ="/sampleForm.do";
			  break;
		 /*  case "projects":
			  menuUrl ="/projectsForm.do";
			  break; */
		  default:
// 			  menuUrl ="/memberLoginForm.do";
			  menuUrl ="/projectsForm.do";
		  }
		  
		  if($(this).attr("id") != "login"){
			  customAjax(menuUrl, data, gnbSuccessCallback, gnbErrorCallback);
		  }
	  });
	});
  
  function gnbSuccessCallback(result){
	  console.log("success");
	  
	  $(".navbar-toggle").addClass("collapsed");
	  $("button").attr("aria-expanded","false");

	  $("#myNavbar").removeClass("in");
	  $("#myNavbar").attr("aria-expanded","false");
	  
	  $("#content_div").html(result);
  
  }
  function gnbErrorCallback(request,status,error){
	  console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	  alert("권한이없습니다.");
	  $(location).attr('href',"/memberLoginForm.do");
  }
  </script>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/index.html"><img alt="logo" src="/resources/images/background/backpacker-running.png" width="35px" height="25px"></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li id="home" class="active"><a href="#">Home</a></li>
        <tlds:isLogin>
	        <li id="honey"><a href="#">Honey</a></li>
        </tlds:isLogin>
        <li id="game"><a href="#">Game</a></li>
        <li id="about"><a href="#">About</a></li>
        <li id="board"><a href="#">Board</a></li>
        <li id="projects"><a href="#">Projects</a></li>
        <li id="contact"><a href="#">Contact</a></li>
        <li id="sample"><a href="#">Sample</a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
       	<tlds:isLogin>
        	<li id="logOut"><a href="/memberLogOut.do"><span class="glyphicon"></span>LogOut</a></li>
       	</tlds:isLogin>
       	<tlds:isNotLogin>
        	<li id="login"><a href="/memberLoginForm.do"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
   		</tlds:isNotLogin>
      </ul>
    </div>
  </div>
</nav>
  
