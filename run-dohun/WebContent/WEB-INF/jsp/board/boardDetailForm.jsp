<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="/resources/js/board.js"></script>
<form id="boardForm">
	<input type="hidden" id="boardContent" name="boardContent" value='${boardVo.boardContent}'>
	<input type="hidden" id="boardIdx" name="boardIdx" value='${boardVo.boardIdx}'>
	
	<div class="col-sm-8 text-left"> 
		<h1>게시판-읽기</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
		<div class="form-group">
		    <label for="제목">제목:</label>
		    <input type="text" class="form-control" id="boardTitle" name="boardTitle" value='${boardVo.boardTitle}'>
		</div>
		<!-- CKEditor -->
		<div class="form-group">
			<textarea name="editor1" id="editor1" >
	        </textarea>
	        <script>
	            CKEDITOR.replace("editor1");
	            CKEDITOR.instances.editor1.setData($("#boardContent").val());
	        </script>
        </div>
        <div class="form-group">
        	<label for="업로드">업로드:</label>
        	<input type="text" class="form-control" id="fileUpload">
		</div>
		<div class="modal-footer">
			<button type="button" id="boardUpdate" name="boardUpdate" class="btn btn-default" >수정</button> 
			<button type="button" id="boardList" name="boardList" class="btn btn-default">목록</button>
		</div>
	</div>
</form>
