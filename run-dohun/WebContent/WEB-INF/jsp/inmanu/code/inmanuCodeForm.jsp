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
<style type="text/css">
.selectRow {
	background-color: #D8D8D8
};

</style>
<script type="text/javascript">
$(document).ready(function() {
	
	/* subCode호출 */
	$("#topCodeTable > tbody > tr > td > span").click(function(){
/* 		$("#topCodeTable > tbody > tr").removeClass("selectRow");
		$("#"+$(this).attr("id")).closest("tr").addClass("selectRow");
		$("#codeForm").attr("action", "/inmanuSubCode.do");
		customAjax("/inmanuSubCode.do", {"topCode":$(this).attr("id")}, successSubCodeCallback, errorCodeCallback);
 */		
		/* jq input tag 생성 */
		$("<input></input>").attr({type:"hidden",
								   name:"user_id", 
								   value:""
								}).appendTo("#codeForm");
		$("#codeForm").attr("action","inmanuSubCode.do").submit();

	});
	
	/* topCode 추가 삭제 */
	$("#topCodeTable button").click(function() {
		if($(this).attr("id") === "topCodeAdd"){	//추가
			window.open("/inmanuTopCodeAddPop.do", "inmanu_topAddPop", "toolbar=no, scrollbars=no, resizable=no, width=400, height=400");
		}else if($(this).attr("id") === "topCodeDelete"){	//삭제
			var topCodeId = $(".selectRow > td:first-child > span").attr("id");
			if(typeof topCodeId === "undefined"){
				alert("삭제할 코드를 선택해 주세요.");
			}else{
				confirm("코드값: "+topCodeId + " 삭제하시겠습니까?");
				customAjax("/inmanuTopCodeDelete.do", {"topCode":topCodeId}, successTopCodeDelteCallback, errorCodeCallback);
			}
		}
	});
	
}); //$(document).ready()

/* subCode 추가*/
function subCodeAddPop(){
	alert();
};

function successTopCodeDelteCallback(result){
	alert("삭제되었습니다.");
	location.reload();
};

function successSubCodeCallback(result){
	var codeSubList = result.codeSubList;
	$("#subCodeTable > tbody > tr").remove();	
	
	var text;
	if(codeSubList == ""){//결과 값 없을 때
		text = '<tr><td colspan="5">등록된 하위코드가 없습니다.</td></tr>';
		$("#subCodeTable > tbody").append(text);
	}else{
		for(var i in codeSubList){
			text = "<tr>"
						+"<td>"+codeSubList[i].subCode+"</td>"
						+"<td>"+codeSubList[i].subCodeName+"</td>"
						+"<td>"+codeSubList[i].subCodeDesc+"</td>"
						+"<td>"+codeSubList[i].subCodeOrder+"</td>"
						+"<td><button class='btn-default btn-xs' id='subCodeUpdate'>수정</button><button class='btn-default btn-xs' id='subCodeDelte'>삭제</button></td>"
						+"</tr>";
			$("#subCodeTable > tbody").append(text);
		}
		$("#subCodeTable > tbody").append("<tr><td colspan='5'><button onclick='subCodeAddPop();' class='btn-default btn-xs'>추가</button></td></tr>");
	}
};

function errorCodeCallback(result, status, error){
	console.log("code:"+result.status+"\n"+"message:"+result.responseText+"\n"+"error:"+error);
};

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
	<form id="codeForm">
		<div class="row content">
			<div id="content_div">
				<h1>inma-Nu</h1>
	    		<p>관리자 - CODE</p>
	    	  	<hr>
				<!-- left -->
				<div class="col-sm-3 text-center">
					<table id="topCodeTable" class="table table-condensed" >
					<tbody>
						<tr>
							<th colspan="2">
								<span style="margin-right: 55%">대분류</span>
								<button id="topCodeAdd" class="btn-default btn-xs">추가</button>	
								<button id="topCodeDelete" class="btn-default btn-xs">삭제</button>
							</th>
						</tr>
						<c:if test="${!empty codeTopList}">
							<c:forEach items="${codeTopList}" var="i">
								<tr align="left">
									<td><span id="${i.topCode }">${i.topCode }</span></td>
									<td><span id="${i.topCode }">${i.topCodeName }</span></td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty codeTopList}">
							<tr>
								<td><span>데이터가 없습니다.</span></td>
							</tr>
						</c:if>
					</tbody>
					</table>
				</div>
				<!-- //left -->
				<!-- center -->
				<div class="col-sm-9 text-left">
					<table id="subCodeTable" class="table table-condensed" >
						<tr>
							<th>소분류</th>
							<th>코드명</th>
							<th>설명</th>
							<th>순서</th>
							<th></th>
						</tr>
						<tr>
							<%-- 
							<c:if test="${!empty codeSubList}">
								<c:forEach items="${codeSubList }" var="i">
										<td>${i.subCode }</td>
										<td>${i.subCodeName }</td>
										<td>${i.subCodeDesc }</td>
										<td>${i.subCodeOrder }</td>
								</c:forEach>
							</c:if> 
							--%>
							<td colspan="5">좌측 대분류에서 선택하시길 바랍니다.</td>
						</tr>
					</table>
				</div>
				<!-- //center -->
			</div>
			<!-- //content_div -->
		</div>
		<!-- //row content -->
	</form>
	</div>
	<!-- //container-fluid -->
	
	<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>