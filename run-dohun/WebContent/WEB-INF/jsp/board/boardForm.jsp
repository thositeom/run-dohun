<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <script type="text/javascript" src="/resources/js/board.js"></script> -->
<style>
/* .asdasdasd { */
/* text-overflow:ellipsis; */
/* white-space:nowrap; */
/* word-wrap:normal; */
/* width:1009px; */
/* overflow:hidden; */

/* } */

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
</script> 
<form id="boardForm">
	<div class="col-sm-8 text-left"> 
		<h1>게시판</h1>
	    <p>나는 게시판. 나는 글을쓴다. 너도 글을쓴다.</p>
	    <hr>
	    <div class="table-responsive">
		    <table class="table table-condensed">
		    	<tr>
		    		<td>
				    	<div class="container col-sm-12 col-md-12 col-lg-12">
				    		<div class="pull-left" style="width: 140px;"><b>#302</b></div>
  							<div class="pull-right">
						    	<a href="#" id="boardBest" data-toggle="tooltip" title="클릭시 일따봉!">
						    		<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<span id="bestResult">1</span>
						    	</a>
						    	<a href="#" id="boardWost" data-toggle="tooltip" title="클릭시 부~우">
						    		<span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;<span id="wostResult">5</span>
						    	</a>
					    	</div>
  							<div class="pull-left ">제목내용이 나오고있다아아제목내용이 나오고있다아아제목내용이 나오고있다아아제목내용이 나오고있다아아제목내용이 나오고있다아아제목내용이 나오고있다아아</div>
						</div>
		    		</td>
		    	</tr>
		    	
		    	<!-- 
		    	<div class="pull-left">제목입니다.</div>
	    		<div class="pull-right">
			    	<a href="#" id="boardBest" data-toggle="tooltip" title="클릭시 일따봉!">
			    		<span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;<span id="bestResult">1</span>
			    	</a>
			    	<a href="#" id="boardWost" data-toggle="tooltip" title="클릭시 부~우">
			    		<span class="glyphicon glyphicon-thumbs-down"></span>&nbsp;<span id="wostResult">5</span>
			    	</a>
		    	</div>
				
				 -->
		    	
		    	
		    	
		    	<%-- <thead>
		    		<tr>
		    			<th style="width: 23px">
		    				<input type="checkbox" id="boardCheckAll" />
		    			</th>
		    			<th style="width: 70px"></th>
		    			<th style="width: 157px">제목</th>
		    			<th style="width: 23px">답변수</th>
		    			<th style="width: 23px">추천수</th>
		    			<th style="width: 23px">조회수</th>
		    			<th style="width: 23px">ID</th>
		    		</tr>
			    </thead>
				<tbody>
					<c:if test="${!empty boardList}">
						<c:forEach items="${boardList}" var="board">
						<tr>
							<td style="width: 23px"><input type="checkbox" name="boardCheck" value="${board.boardIdx}"/></td>
							<td style="width: 70px">#${board.boardIdx}</td>
							<td style="width: 157px"><span onclick="boardDetailForm(${board.boardIdx});" style="cursor:pointer">${board.boardTitle}</span></td>
							<td style="width: 23px">${board.boardAnswerCnt}</td>
							<td style="width: 23px">${board.boardBest}</td>
							<td style="width: 23px">${board.boardCount}</td>
							<td style="width: 23px">${board.boardCreateUser}</td>
						</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty boardList}">		 			
						<tr>	
				    		<td colspan="6">등록된 게시물이 없습니다.</td>
				    	</tr>				
					</c:if>
				</tbody> --%>
		    </table>
		</div>
		<!-- Pager -->
		<script type="text/javascript">
			pager("pager" ,${currentPage}, ${boardListCnt}, 10);
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
	