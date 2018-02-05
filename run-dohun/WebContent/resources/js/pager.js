/**
 * Parameter
 * {pagerId:div id, currentPage:현재페이지, totalCount:총게시물수, rows:보여줄행수}
*/
function pager(pagerId, currentPage, totalCount, rows, formId, url){
	var totalPage = Math.ceil(totalCount/rows); 
	var prePage = currentPage -1;
	var nextPage = currentPage +1;
	
	formId = "&quot;"+formId+"&quot;";
	url = "&quot;"+url+"&quot;";
	
	//이전
	var pager = '<div class="container">';
	pager += '<ul class="pagination pagination-sm">';
	if(currentPage <= 1){
		pager += '<li><a href="javascript:pageClick(1,'+rows+', '+formId+','+url+');">이전</a></li>';
	}else{
		pager += '<li><a href="javascript:pageClick('+prePage+','+rows+','+formId+','+url+');">이전</a></li>';	
	}
	//페이지 
	for(var i=1; i<=totalPage; i++){
		if(currentPage == i){
			pager += '<li class="active"><a href="#">'+i+'</a></li>';
		}else{
			pager += '<li><a href="javascript:pageClick('+i+','+rows+','+formId+','+url+');">'+i+'</a></li>';
		}
	}
	//10페이지 이상일때 ‥ … 표시
	if(totalPage > 10){
		pager += '<li><a>…</a></li>';
		pager += '<li><a href="javascript:pageClick('+totalPage+','+rows+','+formId+','+url+');">'+totalPage+'</a></li>';
	}
	//다음
	if(totalPage <= nextPage){
		pager += '<li><a href="javascript:pageClick('+totalPage+','+rows+','+formId+','+url+');">다음</a></li>';
	}else{
		pager += '<li><a href="javascript:pageClick('+nextPage+','+rows+','+formId+','+url+');">다음</a></li>';
	}
	pager += '</ul>';
	pager += '</div>';
	
	$("#"+pagerId).html(pager);
	 
}
/**
 * Parameter
 * page: 페이지, rows, formId, url
*/
function pageClick(page, rows, formId, url){
	var data="";
	data = "currentPage="+page;
	data +="&rows="+rows;
	customAjaxFromData(url, formId, data, successBoard, errorBoard);
}
