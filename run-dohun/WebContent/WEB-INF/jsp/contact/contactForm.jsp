<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form id="myForm"  method="post" >
    <div class="col-sm-8 text-left">
    	<h1>Contact</h1>
    	<p>Contact</p>
    	<hr>
    	<h3>Contact</h3>
    	<p>Contact</p>
    	
    	<div>
    		<c:forEach items="${resultParserList }" var="i">
    			${i.info01 }
    			${i.info02 }
    			${i.info03 }
    			<br/>
    		</c:forEach>
    	</div>
    	
    </div>
</form>
</html>