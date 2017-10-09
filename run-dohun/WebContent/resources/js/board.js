/**
 * 게시판 공통 script
 */

function successBoard(result){
	$("#content_div").html(result);
}
function errorBoard(result,status,error){
	console.log("code:"+result.status+"\n"+"message:"+result.responseText+"\n"+"error:"+error);
}

$("button").click(function(){
	var url;
	var formId="boardForm";
	var data;
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
			$("#boardContent").val(CKEDITOR.instances.editor1.getData());
			url="/boardUpdate.do";
			break;
		case "boardDelete" :
			url="/boardDelete.do";
			// 체크 되어 있는 값 추출
			var count = 0;
			$("input[name=boardCheck]:checked").each(function() {
				count++;
			});
			if(count == 0 ){
				alert("삭제할 게시물을 선택해 주세요.");
				return null;
			}
			break;
		case "boardList" :
			url="/boardList.do";
			break;
		default:
			break;
	}
	
	/*customAjaxFrom(url,formId,successBoard,errorBoard);*/
	customAjaxForm(url,formId,data,successBoard,errorBoard);
});


function successBoardRecommended(result){
	if(result.status == "error"){
		alert("추천 비추천은 한번만!");	
	}else if(result.status == "success"){
		if(result.boardRecommendedType == "B"){
			$("#bestResult").html(result.boardVo.boardBest);
		}else if(result.boardRecommendedType == "W"){
			$("#wostResult").html(result.boardVo.boardWost);
		}
	}
}
$(".viewCount > a").click(function(){
	var data;
	switch ($(this).attr("id")) {
	case "boardBest" :
		url="/boardRecommended.do";
		data={"BoardRecommendedType": "B"};
		break;
	case "boardWost" :
		url="/boardRecommended.do";
		data={"BoardRecommendedType": "W"}
		break;
	default:
		break;
	}
	customAjaxForm(url,"boardForm",data,successBoardRecommended,errorBoard);
});



function boardDetailForm(boardIdx){
	var data = {"boardIdx":boardIdx};
	customAjax("/boardDetailForm.do",data,successBoard,errorBoard);	
}
