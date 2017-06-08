<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>
</head>

<style type="text/css">
/* 	ul {list-style-type: :none; } */
/* 	li {display: inline; } */
</style>
<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
<!-- <script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script> -->
<!-- <script type="text/javascript" src="/resources/js/common.js"></script> -->
<script>
	function buttonClick(){
		document.getElementById("myForm").action = "/baseballGameStart.do";
		document.getElementById("myForm").submit(); 
	}

	var startCheck = "true";
	var rNum = "";
	var userNum = document.getElementById("userNum");
	
	$("#baseball01").click(function(e){
 	    if(e.target && e.target.nodeName == "INPUT"){
    	console.log(e.target.value+" click" );
    	checkNum(e.target.value);
    	}
	});
	$("#baseball02").click(function(e){
 	    if(e.target && e.target.nodeName == "INPUT"){
    	console.log(e.target.value+" click" );
    	checkNum(e.target.value);
    	}
	});
		
		
	
// 	document.getElementById("parent-list01").addEventListener("click", function(e) {
// 	    console.log(e.target, e.target.nodeName);
// 	    if(e.target && e.target.nodeName == "INPUT"){
// 	    	console.log(e.target.value+" click" );
// 	    	checkNum(e.target.value);
// 	    }
// 	});
	
// 	document.getElementById("parent-list02").addEventListener("click", function(e) {
// 	    console.log(e.target, e.target.nodeName);
// 	    if(e.target && e.target.nodeName == "INPUT"){
// 	    	console.log(e.target.value+" click" );
// 	    	checkNum(e.target.value);
// 	    }
// 	});
	
	//중복값 및 최대길이 체크
	function checkNum(targetValue){
		var str = userNum.value;
		if(str.indexOf(targetValue) != -1){
    		console.log("중복");
    		alert("중복입니다.");
    	}else{
    		if(str.length == userNum.maxLength){
    			console.log("최대길이");
    		}else{
				userNum.value = userNum.value+targetValue;    	
    		}
    	}
	}
	
	//스크립트 submit
	/* var submitBtn = document.getElementById("submitBtn");
	submitBtn.onclick = function(){
	document.getElementById("myForm").action = "/index.do";
	document.getElementById("myForm").submit(); 
	} */
	
	
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			if(checkLength("4","userNum") == "true"){
				$.ajax({
					url: "/baseballGameStart.do",
					type: "POST",
					data: {
						"startCheck": startCheck,
						"userNum": $("#userNum").val(),
						"rNum" : rNum
					},
		        	success: function(result){
		        		console.log("성공");
		        		userNum.value = "";
		        		
		        		var newContent =  '';
		        		newContent += '<input type="text" value="'+result.userNum+'" readonly="readonly" size="6" class="btn btn-default" >';
		        		newContent += ' 스트라이크: '+result.strake;
		        		newContent += ' 볼: '+result.ball;
		        		newContent += '</br>';
		        		document.getElementById("resultContent").innerHTML += newContent;
		        		startCheck = result.startCheck;
		        		rNum = result.rNum;
		        		if(result.strake == 4){
		        			alert("정답입니다. 결과값: "+rNum );
		        		}
		            }, 
		            error: function(request,status,error){
		            	alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            }
		        });
			}else{
				return false;
			}
	    });
	});
</script>

<form id="myForm"  method="post" > 
    <div class="col-sm-8 text-left">
      <h1>Game</h1>
      <p>나는 게임한다.</p>
      <hr>
      
      <!--  -->
      <div class="container text-center">    
	  	<div class="col-sm-4">
	    	<h3>야구게임시작</h3>
	    	<p>4스트라이크면 종료...</p>
	    	<div id="baseball01">
	    		<input class="btn btn-default" type="button" value="1">
				<input class="btn btn-default" type="button" value="2">
				<input class="btn btn-default" type="button" value="3">
				<input class="btn btn-default" type="button" value="4">
				<input class="btn btn-default" type="button" value="5">
		    </div>
		    <div id="baseball02">			  
				<input class="btn btn-default" type="button" value="6">
				<input class="btn btn-default" type="button" value="7">
				<input class="btn btn-default" type="button" value="8">
				<input class="btn btn-default" type="button" value="9">
				<input class="btn btn-default" type="button" value="0">
		    </div>
		    <br/>
		    <input type="text" class="btn btn-default" id="userNum" name="userNum" maxlength="4" size="6">
		  	숫자4자리 입력 <input type="button" class="btn btn-primary" id="submitBtn" value="입력">
		    <div id="resultContent"></div>
	    </div>
	    <!--  -->  
	    <div class="col-sm-6">
	      	<img src="/resources/images/background/flower-399409_960_720.jpg" class="img-responsive thumbnail" style="width:100%" alt="Image">
	    </div>
	  </div>
	  <hr>
	</div>
      
      
      <!--  -->
      
      
</form>


<!-- <body>
	<input type="button" onclick="buttonClick();" value="이 동" />

	<form id="myForm"  method="post" >
	<div>
		<ul id="parent-list01">
		    <li id="post-1"><input type="button" value="1"></li>
		    <li id="post-2"><input type="button" value="2"></li>
		    <li id="post-3"><input type="button" value="3"></li>
		    <li id="post-4"><input type="button" value="4"></li>
		    <li id="post-5"><input type="button" value="5"></li>
	  	</ul>
	  	<ul id="parent-list02">
		    <li id="post-6"><input type="button" value="6"></li>
		    <li id="post-7"><input type="button" value="7"></li>
		    <li id="post-8"><input type="button" value="8"></li>
		    <li id="post-9"><input type="button" value="9"></li>
		    <li id="post-0"><input type="button" value="0"></li>
	  	</ul>
	</div>
	
	<div>
		<table>
			<tr>
				<td>숫자4자리 입력</td>	
			</tr>
			<tr>
				<td><input type="text" id="userNum" name="userNum" maxlength="4" size="6"> </td>			
			</tr>
			<tr>
				<td><input type="button" id="submitBtn" value="버튼"></td>			
			</tr>
		</table>
	</div>	
	<div id="resultContent">
	</div>
	
	</form>
</body> -->
</html>