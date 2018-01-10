<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 
    <div class="col-sm-8 text-left">
    
	    <h1>Sample</h1>
	    <p>Sample</p>
	    <hr>
	    <h3>Sample</h3>
		<form id="sampleForm" name="sampleForm"  method="post" >    
	    * 영문, 숫자, 특수문자(&lt;, &gt;, (, ), {, }, [, ], ', &quot;, /, \, | 제외)를 각 1자 이상 포함하여 9자 이상 50자 이내로 입력하시기 바랍니다.
	    
	    <div class="col-sm-6">
	    <h4>정규식</h4>
	    <p>소문자, 숫자, 특수문자 9~50자리</p>
	    <p>/^(?=.*\d)(?=.*[~`!@#$%\^&*()+\-/=_?:;,.])(?=.*[a-zA-Z]).{9,50}$/</p>
	    <label for="password">입력:</label>
	    <input type="text" id="password" onchange="passwordCheck();">
	    <p id="passwordResult"></p>
	    <script type="text/javascript">
	    function passwordCheck(){
	    	var regex = /^(?=.*\d)(?=.*[~`!@#$%\^&*()+\-/=_?:;,.])(?=.*[a-zA-Z]).{9,50}$/;
	    	var password = $("#password").val().trim();
	    	if(password.match(regex)){
	    		$("#passwordResult").text("true");
	    	}else{
	    		$("#passwordResult").text("false");
	    	}
	    }
	    
	    </script>
	    </div>
	    
	    <div class="col-sm-6">
	    <h4>정규식</h4>
	    <p>이메일</p>
	    <p>/[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/</p>
	    <label for="email">입력:</label>
	    <input type="text" id="email" onchange="emailCheck();">
	    <p id="emailResult"></p>
	    <script type="text/javascript">
	    function emailCheck(){
	    	var regex = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	    	var email = $("#email").val().trim();
	    	if(email.match(regex)){
	    		$("#emailResult").text("true");
	    	}else{
	    		$("#emailResult").text("false");
	    	}
	    }
	    </script>
	    </div>
	    
	    <div class="col-sm-6">
	    	<h4>XSS체크</h4>
		    <label for="xss">입력:</label>
		    <input type="text" id="xss" onchange="xssStart();">
		    <p id="xssResult"></p>
		    <p id="xssResult2"></p>
		    <script type="text/javascript">
		    function xssStart(){
		    	var str = $("#xss").val().trim();
		    	$("#xssResult").text(testEvent.xssCheck(str));
		    }
		    var testEvent = {
		    		xssCheck : function(str){
		    			str = testEvent.replaceAll(str, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
				     	str = testEvent.replaceAll(str, "<script", "&lt;script");
				    	str = testEvent.replaceAll(str, "script>", "script&gt;");
				    	str = testEvent.replaceAll(str, "<iframe", "&lt;iframe");
				    	str = testEvent.replaceAll(str, "<object", "&lt;object");
				    	str = testEvent.replaceAll(str, "<embed", "&lt;embed"); 
				    	str = testEvent.replaceAll(str, "onload", "no_onload");
				    	str = testEvent.replaceAll(str, "expression", "no_expression");
				    	str = testEvent.replaceAll(str, "onmouseover", "no_onmouseover");
				    	str = testEvent.replaceAll(str, "onclick", "no_onclick");
				    	str = testEvent.replaceAll(str, "onmouseout", "no_onmouseout");
				    	str = testEvent.replaceAll(str, "<img src=1", "&lt;img src=1");
				    	str = testEvent.replaceAll(str, "onerror=confirm", "");
				    	return str;
		    		},
		    		replaceAll : function(str, originalStr, changeStr){
		    			return str.split(originalStr).join(changeStr);
		    		}
		    }
		    
		    
		    /* function xssCheck(){
		    	var str = $("#xss").val().trim();
		    	str = replaceAll(str, "document.cookie", "&#100;&#111;&#99;&#117;&#109;&#101;&#110;&#116;&#46;&#99;&#111;&#111;&#107;&#105;&#101;");
		     	str = replaceAll(str, "<script", "&lt;script");
		    	str = replaceAll(str, "script>", "script&gt;");
		    	str = replaceAll(str, "<iframe", "&lt;iframe");
		    	str = replaceAll(str, "<object", "&lt;object");
		    	str = replaceAll(str, "<embed", "&lt;embed"); 
		    	str = replaceAll(str, "onload", "no_onload");
		    	str = replaceAll(str, "expression", "no_expression");
		    	str = replaceAll(str, "onmouseover", "no_onmouseover");
		    	str = replaceAll(str, "onclick", "no_onclick");
		    	str = replaceAll(str, "onmouseout", "no_onmouseout");
		    	str = replaceAll(str, "<img src=1", "&lt;img src=1");
		    	str = replaceAll(str, "onerror=confirm", "");
		    	
		    	$("#xssResult").text(str);
		    }
		    
		    function replaceAll(str, originalStr, changeStr){
		    	return str.split(originalStr).join(changeStr);
		    } */
		    </script>
	    </div>
	    
	    
	    <div class="col-sm-6">
	    <h4>lucy-xss체크</h4>
	    <label for="lucyXss">입력:</label>
	    <input type="text" id="lucyXss" onchange="lucyXssCheck();">
	    <p id="lucyXssResult"></p>
	    <script type="text/javascript">
	    function lucyXssCheck(){
	    	var lucyXss = $("#lucyXss").val().trim();
// 	    	$("#lucyXssResult").text("true");
	    	
	    	var data = "lucyXss="+lucyXss;
	    	customAjax("/lucyXssTest.do", data, successLucyXssCallback, errorLucyXssCallback);
	    	
	    }
	    function successLucyXssCallback(result){
	    	$("#lucyXssResult").text(result.lucyXss);
	    }
	    function errorLucyXssCallback(){
	    }
	    </script>
	    </div>
	    
	    <div class="col-sm-6">
	    <h4>데이터변환 bytes -> KB,MB,GB,TB,EB</h4>
	    <label for="dataConversion">입력:</label>
	    <input type="text" id="bytes" onchange="dataConversion();">
	    </br>
	    <label for="dataConversion2">true/false:</label>
	    <input type="text" id="si" onchange="dataConversion();">
	    <p id="dataConversion"></p>
	    <script type="text/javascript">
	    function dataConversion(){
	    	var bytes = $("#bytes").val().trim();
	    	var si = $("#si").val().trim();
	    	var data = {"bytes":bytes,"si":si};
	    	customAjax("/dataConversion.do", data, successLucyXssCallback, errorLucyXssCallback);
	    }
	    function successLucyXssCallback(result){
	    	$("#dataConversion").text(result.dataConversion);
	    }
	    function errorLucyXssCallback(){
	    }
	    </script>
	    </div>
	    
	    <div class="col-sm-6">
	    <h4>excelView Download</h4>
	    <h3><a href="/excelViewDown.do">Download Excel Document</a></h3>
	    <p id="emailResult"></p>
	    </div>
	    
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
	    
	 </form>
	 
	 
	 <!-- spring form sample -->
	 <div class="col-sm-6">
	    <h4>spring form sample</h4>
	 <form:form commandName="sampleVo" action="/springFormTest.do">
		<form:label path="">id</form:label>
		<form:input path="id"/>
		<form:label path="">이름</form:label>
		<form:input path="name"/>
		<form:password path="password"/>

		<form:checkbox path="checkBox" value="Y" label="예" onchange="oneCheckBox(this);" /> &nbsp;
		<form:checkbox path="checkBox" value="N" label="아니오" onchange="oneCheckBox(this);"/>
		<%-- <form:checkbox path="checkBox" value="Y"/><label for="checkNews1">예</label>&nbsp; 
		<form:checkbox path="checkBox" value="N"/><label for="checkNews2">아니요</label> --%>
		<input type="submit" value="확인" />
	</form:form>
	<script type="text/javascript">
	function oneCheckBox(a){
		var obj = document.getElementsByName("checkBox");
        for(var i=0; i<obj.length; i++){
            if(obj[i] != a){
                obj[i].checked = false;
            }
        }
	}
	</script>
	
	
	<div>
	<!-- $("#topCodeTable > tbody > tr > td > span").click(function(){
		/* jq input tag 생성 */
		$("<input></input>").attr({type:"hidden",
								   name:"user_id", 
								   value:""
								}).appendTo($form);

		$("#frm").attr("action","inmanuSubCode.do").submit();
	});
 -->	
	</div>
	
		 
	   
    </div>

