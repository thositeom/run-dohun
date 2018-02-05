<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <script type="text/javascript" src="/resources/js/board.js"></script> -->
<style>
/* 스위치 테두리 사이즈 */
.switch {
  position: relative;
  display: inline-block;
  width: 27px;
  height: 15px;
}

.switch input {display:none;}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

/* 스위치 내용 사이즈*/
.slider:before {
  position: absolute;
  content: "";
  height: 13px;
  width: 13px;
  left: 2px;
  bottom: 1px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

/* 스위치 이동범위 */
input:checked + .slider:before {
  -webkit-transform: translateX(10px);
  -ms-transform: translateX(10px);
  transform: translateX(10px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>
<script type="text/javascript">
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
//버튼클릭 이벤트
$("button").click(function(){
boardEvent($(this).attr("id"));
});	
$(".list-group-item-text > a").click(function(){
	boardDetailForm($(this).attr("id"));	
});

</script> 
<form id="boardForm">
	<div class="col-sm-8 text-left"> 
		<h1>게시판</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
		<c:if test="${!empty boardList}">
    		<div class="list-group">
<!--     			<input type="checkbox" id="boardCheckAll" /> -->
    			<div>
	    			<label class="switch">
					  <input type="checkbox" id="boardCheckAll">
					  <span class="slider round"></span>
					</label>
				</div>
				<c:forEach items="${boardList}" var="board">
				    <!-- 디자인01 -->
				    <%-- <div class="list-group-item">
					    <div class="list-group-item-heading ">
					    	<b>#${board.boardIdx}</b>
					    	<a href="#" id="${board.boardIdx}">${board.boardTitle}</a>
					    </div>
					    <div class="list-group-item-text" style="margin-bottom: 20px;">
				    	  	  <span class="glyphicon glyphicon-user">${board.boardCreateUser}</span>
					    	  <span class="pull-right">
					    	  	  <span><span class="glyphicon glyphicon-eye-open"></span>&nbsp;${board.boardCount}</span>
					    	  	  <span><span class="glyphicon glyphicon-comment"></span>&nbsp;${board.boardAnswerCnt}</span>
						    	  <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<span id="bestResult">${board.boardBest}</span>
						    	  <span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;<span id="wostResult">${board.boardWost}</span>
					    	  </span>
						</div>
	  				</div> --%>
	  				<!-- 디자인02 -->
				    <div class="list-group-item">
					    <div class="list-group-item-heading">
					    	  <label class="switch" style="margin: 0px 0px -2px 0px;">
					    	  	  <input type="checkbox" name="boardCheck" value="${board.boardIdx}"/>
						    	  <span class="slider round"></span>
					    	  </label>
					    	  <b>#${board.boardIdx}</b>
					    	  <span class="pull-right">
								   <span><span class="glyphicon glyphicon-eye-open"></span>&nbsp;${board.boardCount}</span>
								   <span><span class="glyphicon glyphicon-comment"></span>&nbsp;${board.boardAnswerCnt}</span>
							       <span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<span id="bestResult">${board.boardBest}</span>
								   <span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;<span id="wostResult">${board.boardWost}</span>
					    	  </span>
						</div>
					    <div class="list-group-item-text" style="margin-bottom: 10px;">
					    	<a href="#" id="${board.boardIdx}">${board.boardTitle}</a>
					    	<span class="pull-right" style="margin-left: 5px;">${board.boardCreateUser}</span>
					    	<span class="glyphicon glyphicon-user pull-right" style="margin-top: 2px;"></span>
					    </div>
	  				</div>
	  			</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty boardList}">
			<div class="text-center">등록된 게시물이 없습니다.</div>		
		</c:if>
   	
	    <%-- 
	    ${board.boardIdx}
	    ${board.boardTitle}
	    ${board.boardAnswerCnt}     
		${board.boardBest}          
		${board.boardCount}         
		${board.boardCreateUser} 
		--%>   
	    
		<!-- Pager -->
		<script type="text/javascript">
			pager("pager" ,${currentPage}, ${boardListCnt}, 10, "boardForm", "/boardForm.do");
		</script>
		<div id="pager" class="container"></div>
		
		<div class="modal-footer">
			<a class="btn btn-default" href="/boardExcelDown.do">Excel</a>
			<button type="button" id="boardWriteForm" name="boardWriteForm" class="btn btn-default">글쓰기</button>
<!-- 			<button type="button" id="boardUpdateForm" name="boardUpdateForm" class="btn btn-default" >수정</button> -->
			<button type="button" id="boardDelete" name="boardDelete" class="btn btn-default" >삭제</button>
		</div>
	</div>
</form>
	