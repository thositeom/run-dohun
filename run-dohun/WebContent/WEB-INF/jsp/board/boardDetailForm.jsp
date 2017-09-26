<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>

	#fileList {
		width: 100%;
		height:127px;
		list-style-type: none;
		color: gray;
		font-size: 14px;
		overflow: auto;
		margin-left: 20px;
	}
	
	#fileList .fileDown {
	  width: 100%;
	  margin-top: 10px;
	}
	
</style>

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
        
        <div class="panel panel-default">
        	<div class="form-group" id="fileList">
				<c:forEach items="${boardFileList }" var="i">
					<div class="fileDown" id="uploadFile' + id + '">
		              	<span>
		              	<a href="#" class="btn-sm" onclick="boardFileDownload(${boardVo.boardIdx},${i.fileIdx });">
		              		<span class="glyphicon glyphicon-save-file"></span> 다운로드
		              	</a>
		              	<a href="${i.filePath }${i.fileName }">파일명 : ${i.fileOrigName }</a>
		              	(${i.fileSize }) bytes
		              	</span>
		            </div>
					
					<%-- ${i.fileOrigName }
					${i.fileIdx }
					${i.fileName }
					${i.filePath }
					${i.fileSize }
					${i.fileExtention } --%>
					
				</c:forEach>
				<c:if test="${empty boardFileList}">
					<h2>등록된 파일이 없습니다.</h2>
				</c:if>
			</div>
	        <!-- <div class="panel panel-default col-sm-6">
        		
	        </div>
	         -->	
		</div>
		<div class="modal-footer">
			<button type="button" id="boardUpdate" name="boardUpdate" class="btn btn-default" >수정</button> 
			<button type="button" id="boardList" name="boardList" class="btn btn-default">목록</button>
		</div>
	</div>
</form>
