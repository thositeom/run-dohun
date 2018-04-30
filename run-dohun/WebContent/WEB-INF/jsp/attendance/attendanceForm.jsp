<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- datepicker css, script-->
<script type="text/javascript" src="/resources/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="/resources/bootstrap-datepicker/css/bootstrap-datepicker.css" />
<link rel="stylesheet" href="/resources/bootstrap-datepicker/css/bootstrap-datepicker.standalone.css" />

<script type="text/javascript">

fnPrintClock(); 

function fnPrintClock() {
    
    var clock = document.getElementById("clock");            // 출력할 장소 선택
    var currentDate = new Date();                                     // 현재시간
    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
    var amPm = 'AM'; // 초기값 AM
    var currentHours = fnAddZeros(currentDate.getHours(),2); 
    var currentMinute = fnAddZeros(currentDate.getMinutes(),2);
    var currentSeconds = fnAddZeros(currentDate.getSeconds(),2);
    
    if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
    	amPm = 'PM';
    	currentHours = fnAddZeros(currentHours - 12,2);
    }

    if(currentSeconds >= 50){// 50초 이상일 때 색을 변환해 준다.
       currentSeconds = '<span style="color:#de1951;">'+currentSeconds+'</span>'
    }
    clock.innerHTML = currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:30px;'>"+ amPm+"</span>"; //날짜를 출력해 줌
    
    setTimeout("fnPrintClock()",1000);         // 1초마다 printClock() 함수 호출
}

function fnAddZeros(num, digit) { // 자릿수 맞춰주기
	  var zero = '';
	  num = num.toString();
	  if (num.length < digit) {
	    for (i = 0; i < digit - num.length; i++) {
	      zero += '0';
	    }
	  }
	  return zero + num;
}

function fnBtnAttend(obj){
	var text = obj.firstChild;
	
	if(text.data == "출근하기"){
		customAjax("/attendanceInsert.do", {"attentStatus":"S"}, successCallback, errorCallback)
	}else{
		customAjax("/attendanceInsert.do", {"attentStatus":"E"}, successCallback, errorCallback)
	}
	text.data = text.data == "출근하기" ? "퇴근하기" : "출근하기";
}
function successCallback(result){
	
	if(result.success == "Y"){
		alert("성공");
	}else{
		alert("실패");
	}
} 
function errorCallback(){
	
}

</script>

<form id="boardForm" >
	<div class="col-sm-8">
		<div class="row text-left">
		 	<h1>급여관리</h1>
		    <p>나는 급여. 나는 돈을받는다. 너는 돈을준다.</p>
		    <hr>	
		</div>
		
		<div class="col-sm-4 text-left">
			<div style="width:100%; color:#666; font-size:40px;" id="clock"></div>
			<div id="datepicker"></div>
			<script>
			$(function(){
				$("#datepicker").datepicker({
					format: 'yyyy-mm-dd', 
					language: 'ko',
					maxViewMode: 2
				}).on("changeDate", function(e) {
			    });
			});
			</script>
		</div>
		<div class="col-sm-8 text-left">
			<div class="form-group">
				<button class="btn btn-default" onclick="fnBtnAttend(this);">출근하기</button>
			</div>
		
			<div class="input-group input-daterange">
			    <input type="text" class="form-control" id="startAttend" value="2012-04-05" readonly="readonly" style="background: #ffffff">
			    <div class="input-group-addon">to</div>
			    <input type="text" class="form-control" id="endAttend" value="2012-04-19" readonly="readonly" style="background: #ffffff">
			</div>
			
			<br/>
			<br/>
			<table id="topCodeTable" class="table table-condensed">
			<colgroup>
				<col width="7%">
				<col width="20%">
				<col width="20%">
				<col width="15%">
				<col width="15%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>No</th>
					<th>출근시간</th>
					<th>퇴근시간</th>
					<th>아이디</th>
					<th>이름</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>2017.03.02:03:02</td>
					<td>2017.03.02:03:02</td>
					<td>tho***</td>
					<td>호옹이</td>
					<td>출석</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2017.03.02:03:02</td>
					<td>2017.03.02:03:02</td>
					<td>tho***</td>
					<td>호옹이</td>
					<td>출석(<b style="color: red;">지각</b>)</td>
				</tr>
				<c:if test="${!empty codeTopList}">
					<c:forEach items="${codeTopList}" var="i">
						<tr>
							<td><span id="${i.topCode }">${i.topCode }</span></td>
							<td><span id="${i.topCode }">${i.topCodeName }</span></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty codeTopList}">
					<tr class="text-center">
						<td colspan="6"><span>데이터가 없습니다.</span></td>
					</tr>
				</c:if>
			</tbody>
			</table>
		</div>
		
		<br/>
	</div>
</form>