<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>


<script type="text/javascript">
function successReflush(){
	$("#content_div").html(result);	
}
function errorReflush(request,status,error){
	console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
}
$("#bus_reflush").click(function(){
	customAjax("/contactForm.do", "", successReflush, errorReflush);
});

</script>

</head>
<form id="myForm"  method="post" >
    <div class="col-sm-8 text-left">
    	<h1>Contact</h1>
    	<p>Contact</p>
    	<hr>
    	<h3>Contact</h3>
    	<p>Contact</p>
    	
    	<!-- TAP Area -->
    	<div>
   			<button type="button" class="btn btn-default pull-right" id="bus_reflush">새로고침</button>
    		<ul class="nav nav-tabs">
			    <li class="active"><a data-toggle="tab" href="#tap_home">버스</a></li>
			    <li><a data-toggle="tab" href="#tap_menu1">지하철</a></li>
			    <!-- <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
			    <li><a data-toggle="tab" href="#menu3">Menu 3</a></li> -->
		    </ul>
		    <div class="tab-content">
			    <div id="tap_home" class="tab-pane fade in active">
			      <h3>버스</h3>
			      <table class="table" style="font-size: 11px;">
			      	<tr>
			      		<th>정류소</th>
			      		<th>정거장</th>
			      		<th>시간</th>
			      	</tr>
			      	<c:forEach items="${resultParserList }" var="i">
				      	<tr>
				      		<td>${i.info01 }</td>
				      		<td>
				      			<c:if test="${i.info02 eq '도착정보가 없습니다.' }">
				      			도착정보 없음
				      			</c:if>
				      		</td>
				      		<td>
				      			<c:if test="${i.info03 eq '도착정보가 없습니다.' }">
				      			도착정보 없음
				      			</c:if>
				      		</td>
				      	</tr>
			      	</c:forEach>
			      </table>
			    </div>
			    <div id="tap_menu1" class="tab-pane fade">
			      <h3>지하철</h3>
			      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
			    </div>
			    <!-- <div id="menu2" class="tab-pane fade">
			      <h3>Menu 2</h3>
			      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
			    </div>
			    <div id="menu3" class="tab-pane fade">
			      <h3>Menu 3</h3>
			      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
			    </div> -->
			</div>
    	</div>
    	<!-- //TAP Area -->
    	
    	<!-- 다음 지도 API -->
    	<div id="map" style="width:100%;height:350px;"></div>
    	<p>
	    	<button type="button" onclick="getInfo()">지도 정보 보기</button> <br>
	    	<button type="button" onclick="moveMap('35.85296199535178', '128.58923896041034')">교대식당</button> <br>
	    	<button type="button" onclick="moveMap('35.85516349686351', '128.58835307803594')">경북여상</button> <br>
		</p>    
		<p id="infoDiv"></p>
		<p><em>지도 중심좌표가 변경되면 지도 정보가 표출됩니다</em></p>
		<p id="result"></p>

		<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
		    mapOption = { 
		        center: new daum.maps.LatLng(35.85710192190133, 128.59100419171855), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
		
		var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		 
		// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
		daum.maps.event.addListener(map, 'center_changed', function() {
		    // 지도의  레벨을 얻어옵니다
		    var level = map.getLevel();
		    // 지도의 중심좌표를 얻어옵니다 
		    var latlng = map.getCenter(); 
		    var message = '<p>지도 레벨은 ' + level + ' 이고</p>';
		    message += '<p>중심 좌표는 위도 ' + latlng.getLat() + ', 경도 ' + latlng.getLng() + '입니다</p>';

		    var resultDiv = document.getElementById('result');
		    resultDiv.innerHTML = message;
		});
		
		// 클릭한 위도, 경도 정보를 가져옵니다
		daum.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    var latlng = mouseEvent.latLng;
		    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다';
		    var resultDiv = document.getElementById('result'); 
		    resultDiv.innerHTML = message;
		});
		
		// 마커를 표시할 위치와 title 객체 배열입니다 
		var positions = [
		    {
		        title: '필마트', 
		        latlng: new daum.maps.LatLng(35.8565297371989, 128.58929911172467 )
		    },
		    {
		        title: '교대식당', 
		        latlng: new daum.maps.LatLng(35.85296199535178, 128.58923896041034)
		    },
		    {
		        title: '경북여상', 
		        latlng: new daum.maps.LatLng(35.85516349686351 , 128.58835307803594 )
		    },
		    {
		        title: '근린공원',
		        latlng: new daum.maps.LatLng(33.451393, 126.570738)
		    }
		];
		
		// 마커 이미지의 이미지 주소입니다
		var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		    
		for (var i = 0; i < positions.length; i ++) {
		    // 마커 이미지의 이미지 크기 입니다
// 		    var imageSize = new daum.maps.Size(24, 35); 
		    // 마커 이미지를 생성합니다    
// 		    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
		    // 마커를 생성합니다
		    var marker = new daum.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
// 		        image : markerImage // 마커 이미지 
		    });
		}
		
		function getInfo() {
		    // 지도의 현재 중심좌표를 얻어옵니다 
		    var center = map.getCenter(); 
		    // 지도의 현재 레벨을 얻어옵니다
		    var level = map.getLevel();
		    // 지도타입을 얻어옵니다
		    var mapTypeId = map.getMapTypeId(); 
		    // 지도의 현재 영역을 얻어옵니다 
		    var bounds = map.getBounds();
		    // 영역의 남서쪽 좌표를 얻어옵니다 
		    var swLatLng = bounds.getSouthWest(); 
		    // 영역의 북동쪽 좌표를 얻어옵니다 
		    var neLatLng = bounds.getNorthEast(); 
		    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
		    var boundsStr = bounds.toString();
		    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
		    message += '경도 ' + center.getLng() + ' 이고 <br>';
		    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
		    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
		    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
		    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
		    
		    var infoDiv = document.getElementById('infoDiv');
		    infoDiv.innerHTML = message;
		}
		
		function moveMap(lat, lng){
			 // 이동할 위도 경도 위치를 생성합니다 
		    var moveLatLon = new daum.maps.LatLng(lat, lng);
		    // 지도 중심을 부드럽게 이동시킵니다
		    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
		    map.panTo(moveLatLon);
		}
		
		</script>
		<!-- //다음 지도 API -->
	</div>
</form>
<br/>
</html>