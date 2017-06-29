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
    
<form id="boardForm">
	<div class="col-sm-8 text-left"> 
		<h1>게시판</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
	    <div class="table-responsive">
		    <table class="table">
		    	<thead>
		    		<tr>
		    			<th>No</th>
		    			<th>카테고리</th>
		    			<th>제목</th>
		    			<th>아이디</th>
		    			<th>등록일</th>
		    		</tr>
			    </thead>
				<tbody>
					<c:if test="${!empty boardList}">
						<c:forEach items="${boardList}" var="boardList" >
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
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
		<div class="modal-footer">
			<button type="button" id="boardWriteForm" name="boardWriteForm" class="btn btn-default">글쓰기</button>
			<button type="button" id="boardUpdateForm" name="boardUpdateForm" class="btn btn-default" >수정</button>
			<button type="button" id="boardDelete" name="boardDelete" class="btn btn-default" >삭제</button>
		</div>
	</div>
</form>
</html>