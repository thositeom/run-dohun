<%@page import="kr.dohun.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tlds" uri="http://run-dohun.kr/tlds" %>
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
	
	div .content{
		height:260px;
	}
	
	div .viewCount{
		height:30px;
		float: right;
		margin-right: 40px;
	}
	/* div span > span{
		font-size:20px;
	} */
</style>
<script type="text/javascript">
//버튼클릭 이벤트
$("button").click(function(){
	boardEvent($(this).attr("id"));
});
$(".viewCount > a").click(function(){
	boardRecommnded($(this).attr("id"));	
});
</script>
<!-- <script type="text/javascript" src="/resources/js/board.js"></script> -->
<form id="boardForm">
	<input type="hidden" id="boardContent" name="boardContent" value='${boardVo.boardContent}'>
	<input type="hidden" id="boardIdx" name="boardIdx" value='${boardVo.boardIdx}'>
	<input type="hidden" id="boardId" name="boardId" value='${boardVo.boardId}'>
	<input type="hidden" id="userIdx" name="userIdx" value='${boardVo.userIdx}'>
	<input type="hidden" id="boardTitle" name="boardTitle" value='${boardVo.boardTitle}'>
	
	<div class="col-sm-8 text-left"> 
		<h1>게시판-읽기</h1>
	    <p>나는 게시판. 나는 글을읽는다. 너도 글을읽는다.</p>
	    <hr>
	    <div class="row">
	  	    <div class="viewCount">
		    	<span><span class="glyphicon glyphicon-eye-open"></span>&nbsp;${boardVo.boardCount}</span>
		    	<a href="#" id="boardBest" data-toggle="tooltip" title="클릭시 일따봉!">
		    		<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<span id="bestResult">${boardVo.boardBest}</span>
		    	</a>
		    	<a href="#" id="boardWost" data-toggle="tooltip" title="클릭시 부~우">
		    		<span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;<span id="wostResult">${boardVo.boardWost}</span>
		    	</a>
		    </div>
	    </div>
	    
		<div class="panel panel-default">
			<div class="panel-heading"><b>${boardVo.boardTitle}</b></div>
		    <div class="panel-body content">${boardVo.boardContent}</div>
	    </div>
	  	<div class="panel panel-default">
			<div class="panel-body">
		    	<div class="form-group" id="fileList">
					<c:forEach items="${boardFileList }" var="i">
						<div class="fileDown" id="uploadFile' + id + '">
			              	<span>
			              	<a href="fileDownload.do?boardIdx=${boardVo.boardIdx}&fileIdx=${i.fileIdx}" class="btn-sm">
			              		<span class="glyphicon glyphicon-save-file"></span> 다운로드
			              	</a>
			              	<a href="fileDownload.do?boardIdx=${boardVo.boardIdx}&fileIdx=${i.fileIdx}">파일명 : ${i.fileOrigName}</a>
			              		<tlds:dataConversion value="${i.fileSize}" />
			              	</span>
			            </div>
					</c:forEach>
					<c:if test="${empty boardFileList}">
						<h2>등록된 파일이 없습니다.</h2>
					</c:if>
				</div>
			</div>
	    </div>   
        
		<div class="modal-footer">
			<button type="button" id="boardUpdateForm" name="boardUpdateForm" class="btn btn-default" >수정</button> 
			<button type="button" id="boardList" name="boardList" class="btn btn-default">목록</button>
		</div>
	</div>
</form>
	