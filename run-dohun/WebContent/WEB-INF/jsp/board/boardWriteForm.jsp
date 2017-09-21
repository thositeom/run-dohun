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
	<input type="hidden" id="boardContent" name="boardContent"/>
	
	<div class="col-sm-8 text-left"> 
		<h1>게시판-글쓰기</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
		<div class="form-group">
		    <label for="제목">제목:</label>
		    <input type="text" class="form-control" id="boardTitle" name="boardTitle">
		</div>
		<!-- CKEditor -->
		<div class="form-group">
			<textarea name="editor1" id="editor1" >
	        </textarea>
	        <script>
	            CKEDITOR.replace('editor1');
	        </script>
        </div>
        <jsp:include page="/WEB-INF/jsp/board/fileUpload.jsp"></jsp:include>
        
        <!-- <div class="form-group">
		    <label for="파일업로드">파일업로드:</label>
		    <input type="file" class="form-control" id="fileUpload1" name="fileUpload1" />
			<input type="file" class="form-control" id="fileUpload2" name="fileUpload2" />
			
		</div> -->
		<div class="modal-footer">
			<button type="button" id="boardWrite" name="boardWrite" class="btn btn-default">글쓰기</button>
			<button type="button" id="boardList" name="boardList" class="btn btn-default">목록</button>
		</div>
		
	</div>
</form>
</html>