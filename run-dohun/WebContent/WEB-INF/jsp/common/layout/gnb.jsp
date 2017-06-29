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
		  case "board":
			  menuUrl ="/boardForm.do";
			  break;
		  case "contact":
			  menuUrl ="/contactForm.do";
			  break;
		 /*  case "projects":
			  menuUrl ="/projectsForm.do";
			  break; */
		  default:
// 			  menuUrl ="/memberLoginForm.do";
			  menuUrl ="/projectsForm.do";
		  }
		  
		  if($(this).attr("id") != "login"){
			  $.ajax({
				  url: menuUrl,
				  type: "POST",
					  success: function(result){
						  console.log("success");
						  $("#content_div").html(result);
					  }, 
					  error: function(request,status,error){
					    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						alert("권한이없습니다.");
						$(location).attr('href',"/memberLoginForm.do");
					  }
				});
		  }
	  });
	});
  </script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="/index.html">Logo</a>
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
  
