<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>관리자 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	/* 목록버튼*/
	$("#inmanuBannerForm").click(function(){
		$("#bannerForm").attr("action","inmanuBannerForm.do").submit();
	});
	
	/* 등록버튼 */
	$("#inmanuBannerWrite").click(function(){
		$("#bannerForm").attr("action","inmanuBannerWrite.do").submit();
	});
	
}); //$(document).ready()

</script>
</head>	
<body>
	<!-- nav -->
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
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
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li id="login" class="active">
				</li>
			</ul>
		</div>
	</div>
	</nav>
	<!-- //nav -->
	
	<div class="container-fluid text-center">
	
	<div class="col-sm-2"></div>
	<form id="bannerForm">
		<div class="col-sm-8"> 
			<h1>inma-Nu</h1>
	   		<p>관리자 - Banner 등록화면</p>
		    <hr>
		    	<div class="form-group text-left">
			    	<label for="sel1">배너구분:</label>
				    <select class="form-control" name="bannerType">
				        <option value="0">메인화면 상단배너</option>
				        <option value="1">메인화면 우측배너</option>
				        <option value="2">메인화면 하단배너</option>
				        <option value="3">메인화면 왼쪽배너</option>
				    </select>
				</div>    
				<div class="form-group text-left">
					<label for="bannerName">배너명(필수):</label>
					<input type="text" name="bannerName" class="form-control" >
				</div>
				<div class="form-group text-left">					
					<label for="bannerUrl">배너링크:</label>
					<input type="text" name="bannerUrl" class="form-control" >
				</div>
				<div class="form-group text-left">					
					<label for="bannerDesc">설명:</label>
 					<textarea class="form-control" rows="5" name="bannerDesc"></textarea>
				</div>

			<div class="modal-footer">
				<button type="button" id="inmanuBannerWrite" name="inmanuBannerWrite" class="btn btn-default">등록</button>
				<button type="button" id="inmanuBannerForm" name="inmanuBannerForm" class="btn btn-default">목록</button>
			</div>
		</div>
	</form>
	<div class="col-sm-2"></div>

	</div>
	<!-- //container-fluid -->
	
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>