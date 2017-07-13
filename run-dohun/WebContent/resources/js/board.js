/**
 * 게시판 공통 script
 */

function successBoard(result){
	$("#content_div").html(result);
}
function errorBoard(result,status,error){
	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
}

$("button").click(function(){
	
	var url;
	var formId="boardForm";
	
	switch ($(this).attr("id")) {
		case "boardWriteForm" :
			url="/boardWriteForm.do";
			break;
		case "boardWrite" :
			$("#boardContent").val(CKEDITOR.instances.editor1.getData());
			url="/boardWrite.do";
			break;
		case "boardUpdateForm" :
			url="/boardUpdateForm.do";
			break;
		case "boardUpdate" :
			url="/boardUpdate.do";
			break;
		case "boardDelete" :
			url="/boardDelete.do";
			// 체크 되어 있는 값 추출
			/*$("input[name=boardCheck]:checked").each(function() {
				var test = $(this).val();
				console.log(test);
			});*/
			break;
		case "boardList" :
			url="/boardList.do";
			break;
		default:
			break;
	}
	
	customAjaxFrom(url,formId,successBoard,errorBoard);	
});


