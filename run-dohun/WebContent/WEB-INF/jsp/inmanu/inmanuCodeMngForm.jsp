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
<script type="text/javascript">
$(document).ready(function() {
	$("#topCodeTable > tbody > tr > td > span").click(function(){
		alert($(this).attr("id"));
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
		<div class="row content">
			<div id="content_div">
				<!-- left -->
				<div class="col-sm-3 text-center">
					<table id="topCodeTable" class="table table-condensed" >
					<tbody>
						<tr>
							<th colspan="2">대분류</th>
						</tr>
						<tr>
							<c:if test="${!empty codeTopList}">
								<c:forEach items="${codeTopList}" var="i">
									<td><span id="${i.topCode }">${i.topCode }</span></td>
									<td><span id="${i.topCode }">${i.topCodeName }</span></td>
								</c:forEach>
							</c:if>
							<c:if test="${empty codeTopList}">
								<td><span>데이터가 없습니다.</span></td>
							</c:if>
						</tr>
					</tbody>
					</table>
				</div>
				<!-- //left -->
				<!-- center -->
				<div class="col-sm-9 text-left">
					<table class="table table-condensed" >
						<tr>
							<th>소분류</th>
							<th>코드명</th>
							<th>설명</th>
							<th>순서</th>
						</tr>
						<tr>
							<c:if test="${!empty codeSubList}">
								<c:forEach items="${codeSubList }" var="i">
										<td>${i.subCode }</td>
										<td>${i.subCodeName }</td>
										<td>${i.subCodeDesc }</td>
										<td>${i.subCodeOrder }</td>
								</c:forEach>
							</c:if>
							<c:if test="${empty codeSubList}">
								<td>데이터가 없습니다.</td>
							</c:if>
						</tr>
					</table>
				</div>
				<!-- //center -->
			</div>
			<!-- //content_div -->
		</div>
		<!-- //row content -->
	</div>
	<!-- //container-fluid -->
	
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>