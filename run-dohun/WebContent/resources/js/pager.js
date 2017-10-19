/**
 * 인자값 (div id,현재페이지, 총게시물수, 보여줄행수) 
*/
function pager(pagerId, currentPage, boardListCnt, rows){
	/*var rows=10;*/
/*	var currentPage = ${currentPage};*/
	/*var boardListCnt = ${boardListCnt};*/
	var totalPage = Math.ceil(boardListCnt/rows); 
	var prePage = currentPage -1;
	var nextPage = currentPage +1;
		
	//이전
	var pager = '<div class="container">';
	pager += '<ul class="pagination pagination-sm">';
	if(currentPage <= 1){
		pager += '<li><a href="javascript:pageClick(1,'+rows+');">이전</a></li>';
	}else{
		pager += '<li><a href="javascript:pageClick('+prePage+','+rows+');">이전</a></li>';	
	}
	//페이지
	for(var i=1; i<=10; i++){
		if(currentPage == i){
			pager += '<li class="active"><a href="#">'+i+'</a></li>';
		}else{
			pager += '<li><a href="javascript:pageClick('+i+','+rows+');">'+i+'</a></li>';
		}
	}
	//10페이지 이상일때 ‥ … 표시
	if(totalPage > 10){
		pager += '<li><a>…</a></li>';
		pager += '<li><a href="javascript:pageClick('+totalPage+','+rows+');">'+totalPage+'</a></li>';
	}
	//다음
	if(totalPage <= nextPage){
		pager += '<li><a href="javascript:pageClick('+totalPage+','+rows+');">다음</a></li>';
	}else{
		pager += '<li><a href="javascript:pageClick('+nextPage+','+rows+');">다음</a></li>';
	}
	pager += '</ul>';
	pager += '</div>';
	$("#"+pagerId).html(pager);
	 
}

function pageClick(page, rows){
	var data = "currentPage="+page;
	data +="&rows="+rows;
	data +="&boardType=PN"
	customAjaxFromData("/boardForm.do", "boardForm", data, successBoard, errorBoard);
// 	customAjax("/boardForm.do", data, successCallback, errorCallback);
	
}
