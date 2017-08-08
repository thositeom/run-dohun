<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="sampleForm" name="sampleForm"  method="post" > 
    <div class="col-sm-8 text-left">
	    <h1>Sample</h1>
	    <p>Sample</p>
	    <hr>
	    <h3>Sample</h3>
	    
	    <div class="col-sm-6">
	    <h4>정규식</h4>
	    <p>소문자, 숫자, 특수문자 7~20자리</p>
	    <p>/^(?=.*\d)(?=.*[~`!@#$%\^&*()-])(?=.*[a-zA-Z]).{7,20}$/</p>
	    <label for="password">입력:</label>
	    <input type="text" id="password" onchange="passwordCheck();">
	    <p id="passwordResult"></p>
	    <script type="text/javascript">
	    function passwordCheck(){
	    	var regex = /^(?=.*\d)(?=.*[~`!@#$%\^&*()-])(?=.*[a-zA-Z]).{7,20}$/;
	    	var password = $("#password").val().trim();
	    	if(password.match(regex)){
	    		$("#passwordResult").text("true");
	    	}else{
	    		$("#passwordResult").text("false");
	    	}
	    }
	    
	    </script>
	    </div>
	    
	    <div class="col-sm-6">
	    <h4>정규식</h4>
	    <p>이메일</p>
	    <p>/[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/</p>
	    <label for="email">입력:</label>
	    <input type="text" id="email" onchange="emailCheck();">
	    <p id="emailResult"></p>
	    <script type="text/javascript">
	    function emailCheck(){
	    	var regex = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	    	var email = $("#email").val().trim();
	    	if(email.match(regex)){
	    		$("#emailResult").text("true");
	    	}else{
	    		$("#emailResult").text("false");
	    	}
	    }
	    
	    </script>
	    </div>
	    
    </div>
</form>
