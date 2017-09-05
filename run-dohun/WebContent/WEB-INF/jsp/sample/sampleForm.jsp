<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="sampleForm" name="sampleForm"  method="post" > 
    <div class="col-sm-8 text-left">
	    <h1>Sample</h1>
	    <p>Sample</p>
	    <hr>
	    <h3>Sample</h3>
	    
	    * 영문, 숫자, 특수문자(&lt;, &gt;, (, ), {, }, [, ], ', &quot;, /, \, | 제외)를 각 1자 이상 포함하여 9자 이상 50자 이내로 입력하시기 바랍니다.
	    
	    <div class="col-sm-6">
	    <h4>정규식</h4>
	    <p>소문자, 숫자, 특수문자 9~50자리</p>
	    <p>/^(?=.*\d)(?=.*[~`!@#$%\^&*()+\-/=_?:;,.])(?=.*[a-zA-Z]).{9,50}$/</p>
	    <label for="password">입력:</label>
	    <input type="text" id="password" onchange="passwordCheck();">
	    <p id="passwordResult"></p>
	    <script type="text/javascript">
	    function passwordCheck(){
	    	var regex = /^(?=.*\d)(?=.*[~`!@#$%\^&*()+\-/=_?:;,.])(?=.*[a-zA-Z]).{9,50}$/;
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
	    
	    <div class="col-sm-6">
	    	<h4>XSS체크</h4>
		    <label for="xss">입력:</label>
		    <input type="text" id="xss" onchange="xssStart();">
		    <p id="xssResult"></p>
		    <p id="xssResult2"></p>
		    <script type="text/javascript">
		    function xssStart(){
		    	var str = $("#xss").val().trim();
		    	$("#xssResult").text(testEvent.xssCheck(str));
		    }
		    var testEvent = {
		    		xssCheck : function(str){
		    			str = testEvent.replaceAll(str, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
				     	str = testEvent.replaceAll(str, "<script", "&lt;script");
				    	str = testEvent.replaceAll(str, "script>", "script&gt;");
				    	str = testEvent.replaceAll(str, "<iframe", "&lt;iframe");
				    	str = testEvent.replaceAll(str, "<object", "&lt;object");
				    	str = testEvent.replaceAll(str, "<embed", "&lt;embed"); 
				    	str = testEvent.replaceAll(str, "onload", "no_onload");
				    	str = testEvent.replaceAll(str, "expression", "no_expression");
				    	str = testEvent.replaceAll(str, "onmouseover", "no_onmouseover");
				    	str = testEvent.replaceAll(str, "onclick", "no_onclick");
				    	str = testEvent.replaceAll(str, "onmouseout", "no_onmouseout");
				    	str = testEvent.replaceAll(str, "<img src=1", "&lt;img src=1");
				    	str = testEvent.replaceAll(str, "onerror=confirm", "");
				    	return str;
		    		},
		    		replaceAll : function(str, originalStr, changeStr){
		    			return str.split(originalStr).join(changeStr);
		    		}
		    }
		    
		    
		    /* function xssCheck(){
		    	var str = $("#xss").val().trim();
		    	str = replaceAll(str, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
		     	str = replaceAll(str, "<script", "&lt;script");
		    	str = replaceAll(str, "script>", "script&gt;");
		    	str = replaceAll(str, "<iframe", "&lt;iframe");
		    	str = replaceAll(str, "<object", "&lt;object");
		    	str = replaceAll(str, "<embed", "&lt;embed"); 
		    	str = replaceAll(str, "onload", "no_onload");
		    	str = replaceAll(str, "expression", "no_expression");
		    	str = replaceAll(str, "onmouseover", "no_onmouseover");
		    	str = replaceAll(str, "onclick", "no_onclick");
		    	str = replaceAll(str, "onmouseout", "no_onmouseout");
		    	str = replaceAll(str, "<img src=1", "&lt;img src=1");
		    	str = replaceAll(str, "onerror=confirm", "");
		    	
		    	$("#xssResult").text(str);
		    }
		    
		    function replaceAll(str, originalStr, changeStr){
		    	return str.split(originalStr).join(changeStr);
		    } */
		    </script>
	    </div>
	    
	    
	    <div class="col-sm-6">
	    <h4>lucy-xss체크</h4>
	    <label for="lucyXss">입력:</label>
	    <input type="text" id="lucyXss" onchange="lucyXssCheck();">
	    <p id="lucyXssResult"></p>
	    <script type="text/javascript">
	    function lucyXssCheck(){
	    	var lucyXss = $("#lucyXss").val().trim();
// 	    	$("#lucyXssResult").text("true");
	    	
	    	var data = "lucyXss="+lucyXss;
	    	customAjax("/lucyXssTest.do", data, successLucyXssCallback, errorLucyXssCallback);
	    	
	    }
	    function successLucyXssCallback(result){
	    	$("#lucyXssResult").text(result.lucyXss);
	    }
	    function errorLucyXssCallback(){
	    }
	    </script>
	    </div>
	    
	    
    </div>
</form>
