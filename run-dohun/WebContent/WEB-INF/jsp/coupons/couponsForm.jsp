<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- datepicker css, script-->
<script type="text/javascript" src="/resources/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-datepicker/css/bootstrap-datepicker.css" />
<link rel="stylesheet" href="/resources/bootstrap-datepicker/css/bootstrap-datepicker.standalone.css" />

<style>
/* coupon CSS */
.coupon {
    border: 5px dotted #bbb;
    width: 80%;
    border-radius: 15px;
    margin: 0 auto;
    max-width: 300px;
}
.coupon_container {
    padding: 2px 16px;
    background-color: #f1f1f1;
}
.coupon_promo {
    background: #ccc;
    padding: 3px;
}
.coupon_expire {
    color: red;
}


/* imaged preview */ 
.filebox input[type="file"] { 
	position: absolute; 
	width: 1px; 
	height: 1px; 
	padding: 0; 
	margin: -1px; 
	overflow: hidden; 
	clip:rect(0,0,0,0); 
	border: 0; 
} 
.filebox label { 
	display: inline-block; 
	padding: .5em .75em; 
	color: #999; 
	font-size: inherit; 
	line-height: normal; 
	vertical-align: middle; 
	background-color: #fdfdfd; 
	cursor: pointer; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; 
	margin-top: 5px;
} 
/* named upload */ 
.filebox .upload-name { 
	display: inline-block; 
	padding: .5em 3.54em; /* label의 패딩값과 일치 */ 
	font-size: inherit; 
	font-family: inherit; 
	line-height: normal; 
	vertical-align: middle; 
	background-color: #f5f5f5; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; 
	-webkit-appearance: none; /* 네이티브 외형 감추기 */ 
	-moz-appearance: none; 
	appearance: none;
}
</style>
<script type="text/javascript">
function fnCouponPreview(){
	var x = document.getElementsByClassName("coupon");
	if (x[0].style.display === "none") {
        x[0].style.display = "block";
    } else {
        x[0].style.display = "none";
    }
}

function fnCouponContent01(obj){
	var x = document.getElementById("couponContans01");
	if(obj.value.trim() == ''){
		x.innerHTML = '<b>inmanu 상품 구매시 20%할인</b>';
	}else{
		x.innerHTML = '<b>'+obj.value+'</b>';
	}
}
function fnCouponContent02(obj){
	var x = document.getElementById("couponContans02");
	if(obj.value.trim() == ''){
		x.innerHTML = '이용조건<br/>전국  inmanu 매장에서 사용이 가능합니다.<br/>유효기간 이후에는 사용이 불가능합니다.<br/>자체 행사상품에는 제외됩니다.<br/>(자세한 내용은 브랜드사에 문의 해주시기 바랍니다.)';
	}else{
		x.innerHTML = obj.value;
	}
}
function fnValidation(obj){
	if (uValidation.checkRegNumber(obj.value)){
		alert("숫자만 등록이 가능합니다.");
	}
}

//이미지 업로드 script
var imgTarget = $('.preview-image .upload-hidden'); 

 imgTarget.on('change', function(){ 
 	var div = document.getElementById("couponImgDiv");
 	
 	if(window.FileReader){ 
 		//image 파일만 
 		if(!$(this)[0].files[0].type.match(/image\//)){
 			alert("이미지 파일만 업로드가 가능합니다.");
 			return;
 		} 
 		
 		var filename = $(this)[0].files[0].name;
 		$(this).siblings('.upload-name').val(filename); /* 추출한 파일명 삽입 */

 		var reader = new FileReader(); 
 		reader.onload = function(e){ 
 			var src = e.target.result;
 			div.children[0].remove();
 			
 			var img = document.createElement("IMG");/*노드생성 */
 			img.setAttribute("alt", "이미지");
 			img.setAttribute("width", "100%");
 			img.setAttribute("height", "180px");
 			img.setAttribute("src", src);
 			div.appendChild(img); /*노드추가 */
 		} 
 		reader.readAsDataURL($(this)[0].files[0]); 
 	}else{ 
 		$(this)[0].select(); 
 		$(this)[0].blur(); 
 		var imgSrc = document.selection.createRange().text; 
 		
 		var img = document.createElement("IMG"); /*노드생성 */
		img.setAttribute("alt", "이미지");
		img.setAttribute("width", "100%");
		img.setAttribute("height", "180px");
		div.appendChild(img); /*노드추가 */
			
 		var img = $(this).siblings('.upload-display').find('img'); 
 		
 		img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")"; 
	} 
});

 
</script> 
<form id="boardForm">
	<div class="col-sm-8 text-left">
	<div class="row">
	 	<h1>쿠폰작성</h1>
	    <p>나는 쿠폰. 나는 쿠폰을만든다. 너는 쿠폰을쓴다.</p>
	    <hr>	
		
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
				    <label for="제목">제목</label>
				    <input type="text" class="form-control" id="couponTitle" name="couponTitle" placeholder="쿠폰 제목을 입력해주세요.">
				</div>
				<hr/>	
				<!-- 파일업로드 -->		
				<div class="form-group">
					<div class="filebox preview-image"> 
						<input class="upload-name" value="파일선택" disabled="disabled"> 
						<label for="input-file">업로드</label> 
						<input type="file" class="upload-hidden" id="input-file" name="file[]" multiple="multiple" >
						<p style="color: red;">※ 권장사이즈 가로:280px, 세로:200px</p> 
					</div>
				</div>				
				<hr/>
				<!-- 쿠폰내용 -->
				<div class="form-group">
				    <label for="쿠폰내용">쿠폰내용</label>
				    <input type="text" class="form-control" id="couponContent01" name="couponContent01" onfocusout="fnCouponContent01(this);" placeholder="쿠폰과 관련된 내용을 입력해주세요.">
				</div>
				<!-- 쿠폰상세내용 -->
				<div class="form-group">
				    <label for="쿠폰상세내용">쿠폰상세내용</label>
				    <textarea class="form-control" style="resize: none;" id="couponContent02" name="couponContent02" rows="5" onfocusout="fnCouponContent02(this);" placeholder="쿠폰과 관련된 상세한 내용을 입력해주세요."></textarea>
				</div>
				<hr/>
				<!-- 쿠폰 사용 만료일 -->
				<div class="form-group">
				    <label for="쿠폰 사용 만료일">쿠폰 사용 만료일</label>
					<input type="text" class="form-control" id="datepicker" name="couponDate" placeholder="날짜를 선택해주세요.">
				</div>
				<script>
				$(function(){
					$("#datepicker").datepicker({
						format: 'yyyy-mm-dd', 
						language: 'ko'
					}).on("changeDate", function(e) {
						var htmlString = '<p class="coupon_expire">유효기간: '+e.format('yyyy년 mm월 dd일')+'</p>';
						$(".coupon_expire").html(htmlString);
				    });
				});
				</script>
				
				<hr/>
				<!-- 쿠폰발급수량 -->
				<div class="form-group">
					<label for="쿠폰발급수량">쿠폰발급수량</label>
				    <input type="text" class="form-control" id="couponQty" name="couponQty" onfocusout="fnValidation(this);" placeholder="발급할 쿠폰 수량">
				</div>
				<hr/>
				<div class="form-group">
					<button type="button" id="boardWriteForm" name="boardWriteForm" class="btn btn-default btn-primary">목록</button>
					<button type="button" id="boardUpdateForm" name="boardUpdateForm" class="btn btn-default pull-right btn-success" >저장</button>
				</div>
			</div>
		
			<div class="col-sm-6">
				<div class="coupon">
					<div class="coupon_container" style="height: 30px;"></div>
					<div id="couponImgDiv">
						<img src="/resources/images/background/flower-399409_960_720.jpg" alt="Avatar" width="100%" height="180px">
					</div>
					<div class="coupon_container" style="background-color:white">
						<h2 id="couponContans01"><b>inmanu 상품 구매시 20%할인</b></h2> 
						<p id="couponContans02">
							이용조건<br/>
							전국  inmanu 매장에서 사용이 가능합니다.<br/>
							유효기간 이후에는 사용이 불가능합니다.<br/>
							자체 행사상품에는 제외됩니다.<br/>
							(자세한 내용은 브랜드사에 문의 해주시기 바랍니다.)
						</p>
					</div>
					<div class="coupon_container">
						<p>Use Promo Code: <span class="coupon_promo">BOH232</span></p>
						<p class="coupon_expire">유효기간: 2018년 04월 24일</p>
					</div>
				</div>
			</div>
		</div>
	
	</div>
	</div>
	
	
</form>
