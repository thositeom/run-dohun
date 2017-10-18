<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>

<script type="text/javascript">
function successReflush(){
	$("#content_div").html(result);	
}
function errorReflush(request,status,error){
	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
}
$("#bus_reflush").click(function(){
	customAjax("/contactForm.do", "", successReflush, errorReflush);
});

</script>

</head>
<form id="myForm"  method="post" >
    <div class="col-sm-8 text-left">
    	<h1>Contact</h1>
    	<p>Contact</p>
    	<hr>
    	<h3>Contact</h3>
    	<p>Contact</p>
    	
    	<!-- TAP Area -->
    	<div>
   			<button class="btn btn-default pull-right" id="bus_reflush">새로고침</button>
    		<ul class="nav nav-tabs">
			    <li class="active"><a data-toggle="tab" href="#tap_home">버스</a></li>
			    <li><a data-toggle="tab" href="#tap_menu1">지하철</a></li>
			    <!-- <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
			    <li><a data-toggle="tab" href="#menu3">Menu 3</a></li> -->
		    </ul>
		    <div class="tab-content">
			    <div id="tap_home" class="tab-pane fade in active">
			      <h3>버스</h3>
			      <table>
			      	<c:forEach items="${resultParserList }" var="i">
				      	<tr>
				      		<td>${i.info01 }</td>
				      		<td>${i.info02 }</td>
				      		<td>${i.info03 }</td>
				      	</tr>
			      	</c:forEach>
			      </table>
			    </div>
			    <div id="tap_menu1" class="tab-pane fade">
			      <h3>지하철</h3>
			      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
			    </div>
			    <!-- <div id="menu2" class="tab-pane fade">
			      <h3>Menu 2</h3>
			      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
			    </div>
			    <div id="menu3" class="tab-pane fade">
			      <h3>Menu 3</h3>
			      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
			    </div> -->
			</div>
    	</div>
    	<!-- //TAP Area -->
    	
    </div>
</form>
<br/>
</html>