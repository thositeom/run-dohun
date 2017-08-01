<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/resources/js/board.js"></script>
<script type="text/javascript">

var rows=10;
var currentPage = ${currentPage};
var totalCnt = ${boardListCnt};
var totalPage = Math.ceil(totalCnt/rows); 

var pager = '<div class="container">';
pager += '<ul class="pagination pagination-sm">';
pager += '<li><a href="#">이전</a></li>';

for(var i=1; i<=totalPage; i++){
	if(currentPage == i){
		pager += '<li class="active"><a href="#">'+i+'</a></li>';
	}else{
		pager += '<li><a href="javascript:pageClick('+i+');">'+i+'</a></li>';
	}
}
pager += '<li><a href="#">다음</a></li>';
pager += '</ul>';
pager += '</div>';
$("#pager").html(pager);

function pageClick(page){
	var data = {"currentPage":page};
	customAjaxFromData("/boardForm.do", "boardForm", data, successBoard, errorBoard);
// 	customAjax("/boardForm.do", data, successCallback, errorCallback);
	
}

//체크박스
$("#boardCheckAll").click(function(){
	if($("#boardCheckAll").prop("checked")) {
		$("input[name=boardCheck]").each(function() {
			$(this).prop("checked", true); 
		});
	}else{
		$("input[name=boardCheck]").each(function() {
			$(this).prop("checked", false); 
		});
	}
});

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
		    			<th>
		    				<input type="checkbox" id="boardCheckAll" />
		    			</th>
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
