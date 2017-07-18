<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판</title>
</head>
<script type="text/javascript" src="/resources/js/board.js"></script>

<style type="text/css">
	ul {list-style-type: :none; }
	li {display: inline; }
</style>
    
<script type="text/javascript">

var rows=10;
var currentPage = ${currentPage};
var totalPage = ${boardListCnt};
// var startRow = ${startRow};
// var endRow = ${endRow};

var totalPage = Math.ceil(totalPage/rows); 

var pager = '<div class="container">';
pager += '<ul class="pagination pagination-sm">';
pager += '<li><a href="#">이전</a></li>';
for(var i=1; i<totalPage; i++){
	if(currentPage == i){
		pager += '<li class="active"><a href="#">'+i+'</a></li>';
	}else{
		pager += '<li><a href="#">'+i+'</a></li>';
	}
}
pager += '<li><a href="#">다음</a></li>';
pager += '</ul>';
pager += '</div>';
$("#pager").html(pager);

</script>    
    
<form id="boardForm">
	<div class="col-sm-8 text-left"> 
		<h1>게시판</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
	    <div class="table-responsive">
		    <table class="table">
		    	<thead>
		    		<tr>
		    			<th>선택</th>
		    			<th>boardIdx</th>
		    			<th>제목</th>
		    			<th>답변수</th>
		    			<th>추천수</th>
		    			<th>조회수</th>
		    			<th>ID</th>
		    		</tr>
			    </thead>
				<tbody>
					<c:if test="${!empty boardList}">
						<c:forEach items="${boardList}" var="board" >
						<tr>
							<td><input type="checkbox" name="boardCheck" value="${board.boardIdx}"/></td>
							<td>#${board.boardIdx}</td>
							<td><span onclick="boardDetailForm(${board.boardIdx});" style="cursor:pointer">${board.boardTitle}</span></td>
							<td>${board.boardAnswerCnt}</td>
							<td>${board.boardBest}</td>
							<td>${board.boardCount}</td>
							<td>${board.boardCreateUser}</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty boardList}">		 			
						<tr>	
				    		<td colspan="6">등록된 게시물이 없습니다.</td>
				    	</tr>				
					</c:if>
				</tbody>
		    </table>
		</div>
		<div id="pager" class="container">
		</div>
		<div class="modal-footer">
			<button type="button" id="boardWriteForm" name="boardWriteForm" class="btn btn-default">글쓰기</button>
<!-- 			<button type="button" id="boardUpdateForm" name="boardUpdateForm" class="btn btn-default" >수정</button> -->
			<button type="button" id="boardDelete" name="boardDelete" class="btn btn-default" >삭제</button>
		</div>
	</div>
</form>
</html>