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
<script type="text/javascript" src="/resources/js/pager.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	/* 등록버튼 */
	$("#inmanuBannerWriteForm").click(function(){
		$("#bannerForm").attr("action","inmanuBannerWriteForm.do").submit();
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
	   		<p>관리자 - Banner</p>
		    <hr>
			
			<table id="topCodeTable" class="table table-condensed" >
				<tbody align="left">
					<tr>
						<th>no</th>
						<th>배너명</th>
						<th>배너링크</th>
						<th>배너타입</th>
						<th>설명</th>
					</tr>
					<c:if test="${!empty bannerList}">
						<c:forEach items="${bannerList }" var="i">
							<tr>
								<td>${i.bannerIdx}</td>
								<td>${i.bannerName}</td>
								<td>${i.bannerUrl}</td>
								<td>${i.bannerType}</td>
								<td>${i.bannerDesc}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>

			<!-- Pager -->
			<!-- Pager -->
			<script type="text/javascript">
				pager("pager" ,${currentPage}, ${bannerListCnt}, 10, "bannerForm", "/inmanuBannerForm.do");
			</script>
			<div id="pager" class="container"></div>
			<!-- //Pager -->		
			
			<div class="modal-footer">
<!-- 				<a class="btn btn-default" href="/boardExcelDown.do">Excel</a> -->
				<button type="button" id="inmanuBannerWriteForm" name="inmanuBannerWriteForm" class="btn btn-default">등록</button>
				<button type="button" id="boardDelete" name="boardDelete" class="btn btn-default">삭제</button>
			</div>
		</div>
	</form>
	<div class="col-sm-2"></div>

	</div>
	<!-- //container-fluid -->
	
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>