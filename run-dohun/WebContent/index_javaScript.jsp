<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>
</head>

<style type="text/css">
	ul {list-style-type: :none; }
	li {display: inline; }
</style>


<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<body>
	<form id="myForm" action="/index.do" method="post" >
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
				<td><input type="text" id="userNum" name="userNum" readonly="readonly" maxlength="4" size="6"> </td>			
			</tr>
			<tr>
				<td><input type="button" id="submitBtn" value="버튼"></td>			
			</tr>
		</table>
	</div>	
	
	</form>
</body>
<script>
	var userNum = document.getElementById("userNum");
	
	document.getElementById("parent-list01").addEventListener("click", function(e) {
	    console.log(e.target, e.target.nodeName);
	    if(e.target && e.target.nodeName == "INPUT"){
	    	console.log(e.target.value+" click" );
	    	checkNum(e.target.value);
	    }
	});
	
	document.getElementById("parent-list02").addEventListener("click", function(e) {
	    console.log(e.target, e.target.nodeName);
	    if(e.target && e.target.nodeName == "INPUT"){
	    	console.log(e.target.value+" click" );
	    	checkNum(e.target.value);
	    }
	});

	var submitBtn = document.getElementById("submitBtn");
		submitBtn.onclick = function(){
		document.getElementById("myForm").submit();
	};
	
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
	
</script>


</html>