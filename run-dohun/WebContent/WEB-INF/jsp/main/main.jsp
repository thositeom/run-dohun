<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
 
<head>
	<title>inma-Nu</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
	<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/board.js"></script>
	<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/resources/jquery-ui-1.12.1.custom/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="/resources/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" />
	<script src="/resources/jquery.jqGrid-4.4.3/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="/resources/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
	<script src="/resources/jquery.jqGrid-4.4.3/js/jquery.jqGrid.src.js"></script>
	<script src="/resources/jquery.jqGrid-4.4.3/src/i18n/grid.locale-kr.js"></script>
	<!-- 게시판 에디터 -->
	<script src="/resources/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.form.js"></script>
	<script type="text/javascript" src="/resources/js/pager.js"></script>
  	<!-- 네이버지도API -->
<!--   	<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=vZbX7ZW_zMbNccbH_O_1&submodules=geocoder"></script> -->
  	<!-- 다음지도API -->
  	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fd33ac52bad6f68a95100d08899b2e92"></script>
  	
  	<!-- 구글애드샌스 광고 무료수익 -->
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script>
// 	  (adsbygoogle = window.adsbygoogle || []).push({
// 	    google_ad_client: "ca-pub-8107095992534793",
// 	    enable_page_level_ads: true
// 	  });
	</script>
	<style type="text/css">
	
	
	/* Slideshow container */
	#myCarousel {
	  max-width: 100%;
	  position: relative;
	  margin: auto;	
	}
	
	/* banner */
	/* Remove margins and padding from the list */
	#todoListDiv > ul {
	  margin: 0;
	  padding: 0;
	}
	/* Style the list items */
	#todoListDiv > ul li {
	  cursor: pointer;
	  position: relative;
	  padding: 12px 8px 12px 40px;
	  list-style-type: none;
	  background: #eee;
	  font-size: 18px;
	  transition: 0.2s;
	  
	  /* make the list items unselectable */
	  -webkit-user-select: none;
	  -moz-user-select: none;
	  -ms-user-select: none;
	  user-select: none;
	}
	
	/* When clicked on, add a background color and strike out text */
	#todoListDiv > ul li.checked {
	  background: #888;
	  color: #fff;
	  text-decoration: line-through;
	}
	
	/* Add a "checked" mark when clicked on */
	#todoListDiv > ul li.checked::before {
	  content: '';
	  position: absolute;
	  border-color: #fff;
	  border-style: solid;
	  border-width: 0 2px 2px 0;
	  top: 10px;
	  left: 16px;
	  transform: rotate(45deg);
	  height: 15px;
	  width: 7px;
	}
		/* Set all odd list items to a different color (zebra-stripes) */
	#todoListDiv > ul li:nth-child(odd) {
	  background: #f9f9f9;
	}
	
	
	/* //banner */
	</style>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/common/layout/gnb.jsp"></jsp:include>

<div>
	<!-- img광고 -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
	    <!-- Indicators 배너이미지와 안맞아서 임시주석-->
	    <!-- <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active" style="margin-bottom: -30px"></li>
	      <li data-target="#myCarousel" data-slide-to="1" style="margin-bottom: -30px"></li>
	    </ol> -->
	    <!-- Wrapper for slides -->
	    <div class="carousel-inner" role="listbox">
	      <div class="item active">
	        <img src="/resources/images/background/inmanu_banner01.png" alt="Image" style="width:100%">
	        <div class="carousel-caption">
<!-- 		          <h3>inma-Nu $</h3> -->
<!-- 		          <p>running...</p> -->
	        </div>      
	      </div>
	      <div class="item">
	        <img src="/resources/images/background/inmanu_banner02.png" alt="Image" style="width:100%">
	        <div class="carousel-caption">
	        </div>
	      </div>
	    </div>
	    <!-- Left and right controls -->
	    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev" style="background-image: url('');">
	      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next" style="background-image: url('');">
	      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
	      <span class="sr-only">Next</span>
	    </a>
	</div>
	<!-- /img광고 -->
</div>

<div class="container-fluid text-center mainContent">    
  <div class="row content"> 
    <div class="col-sm-2 sidenav">
<!--       <p><a href="http://connect.garmin.com" target="_garmin">Garmin Connect</a></p> -->
<%--       <p><a href="https://w3school<!-- s.com" target="_w3schools">W3Schools</a></p> --%>
<!--       <p><a href="http://thositeom.tistory.com" target="_tistory">Tistory</a></p> -->
<!--       <p><a href="https://docs.oracle.com/javase/8/docs/api/" target="_javaApi">Java Standard Edition 8 API</a></p>  -->
    </div>

	<!-- 본문 -->   
    <div id="content_div">
	    <div class="col-sm-8 text-left">
	   
	    <!-- To Do List -->
	    <div id="todoListDiv" class="well">
		    <h3>To Do List</h3>
		    <div class="input-group" style="background-color: red; padding: 30px 30px">
			  <div class="input-group">
		      <input type="text" id="toDoListInput" class="form-control" size="200" placeholder="Title..." >
		      <div class="input-group-btn">
		        <span onclick="fnNewElement();" class="btn btn-warning">등록이요</span>
		      </div>
		    </div>
			  
			</div>
			<ul id="toDoListUL">
				<c:if test="${!empty todoListResult}">
					<c:forEach items="${todoListResult}" var="i">
						<li id="${i.todoListIdx}">
							<span >[${i.todoListIdx}] </span>${i.todoLisTitle}
						</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty todoListResult}">
					<h3 style="text-align: center; margin-top: 20px;">등록된 데이터가 없습니다.</h3>
				</c:if>
<!-- 			  <li>Hit the gym</li> -->
<!-- 			  <li class="checked">Pay bills</li> -->
<!-- 			  <li>Meet George</li> -->
			</ul>
		</div>
		<script>
		// Create a "close" button and append it to each list item
		var mynodeId = document.getElementById("todoListDiv"); 
		var myNodelist = mynodeId.getElementsByTagName("LI");
		var i;
		for (i = 0; i < myNodelist.length; i++) {
		  var span = document.createElement("SPAN");
		  var txt = document.createTextNode("\u00D7");
		  span.className = "close";
		  span.appendChild(txt);
		  myNodelist[i].appendChild(span);
		}
		
		// Click on a close button to hide the current list item
		// To Do List 삭제
		var close = document.getElementsByClassName("close");
		var i;
		for (i = 0; i < close.length; i++) {
			close[i].onclick = function() {
				var div = this.parentElement;
			    div.style.display = "none";
				customAjax("/todoListDelete.do", {"todoListIdx":div.id}, successTodoListDeleteCallback, errorToDoListCallback);
		  }
		}
		
		// Add a "checked" symbol when clicking on a list item
		var list = document.querySelector('#todoListDiv > ul');
		list.addEventListener('click', function(ev) {
		  if (ev.target.tagName === 'LI') {
		    ev.target.classList.toggle('checked');
		  }
		}, false);
		
		// ToDoList 추가
		function fnNewElement() {
		  var li = document.createElement("li");
		  var inputValue = document.getElementById("toDoListInput").value;
		  customAjax("/todoListWrite.do", {"todoLisTitle":inputValue}, successToDoListCallback, errorToDoListCallback);
		}
		
		function successToDoListCallback(){
			var li = document.createElement("li");
			var inputValue = document.getElementById("toDoListInput").value;
			var t = document.createTextNode(inputValue);
			li.appendChild(t);
			if (inputValue === '') {
			  alert("You must write something!");
			} else {
			  document.getElementById("toDoListUL").appendChild(li);
			}
			document.getElementById("toDoListInput").value = "";
			
			var span = document.createElement("SPAN");
			var txt = document.createTextNode("\u00D7");
			span.className = "close";
			span.appendChild(txt);
			li.appendChild(span);
			
			for (i = 0; i < close.length; i++) {
			  close[i].onclick = function() {
			    var div = this.parentElement;
			    div.style.display = "none";
			  }
			}
		}
		function successTodoListDeleteCallback(){
// 			location.reload();
		}
		function errorToDoListCallback(){
			alert("실패 ");
		}
		
		</script>
		<!-- //To Do List -->
	     
		<!-- 북마크 -->
	      <div class="well">
	       <h3>북마크 바로가기</h3>
	       <input type="text" id="bookmarkInput" onkeyup="bookmarkFunction();" placeholder="이름검색" title="즐겨찾기 검색" class="form-control">
				<table class="table" id="bookmarTable">
				  <tr class="header">
				    <th style="width:60%;">검색 키워드</th>
				    <th style="width:40%;">바로가기</th>
				  </tr>
				  <tr>
				    <td>W3Schools</td>
				    <td><a href="https://www.w3schools.com/bootstrap/default.asp" target="_w3schools">https://www.w3schools.com/bootstrap/default.asp</a></td>
				  </tr>
				  <tr>
				    <td>Garmin Connect</td>
				    <td><a href="http://connect.garmin.com" target="_garmin">http://connect.garmin.com</a></td>
				  </tr>
				  <tr>
				    <td>Java Standard Edition 8 API</td>
				    <td><a href="https://docs.oracle.com/javase/8/docs/api/" target="_javaApi">https://docs.oracle.com/javase/8/docs/api/</a></td>
				  </tr>
				  <tr>
				    <td>Tistory</td>
				    <td><a href="http://thositeom.tistory.com" target="_tistory">http://thositeom.tistory.com</a></td>
				  </tr>
				  <tr>
				    <td>Naver 네이버</td>
				    <td><a href="https://developers.naver.com/main/" target="_naver">https://developers.naver.com/main/</a></td>
				  </tr>
				  <tr>
				    <td>pixlr 웹포토샵</td>
				    <td><a href="https://pixlr.com/editor/" target="_pixlr">https://pixlr.com/editor/</a></td>
				  </tr>
				  <tr>
				    <td>dafont 폰트</td>
				    <td><a href="https://www.dafont.com/" target="_dafont">https://www.dafont.com/</a></td>
				  </tr>
				  <tr>
				    <td>Paris specialites</td>
				    <td>France</td>
				  </tr>
				</table>
				<script>
				function bookmarkFunction() {
				  var input, filter, table, tr, td, i;
				  input = document.getElementById("bookmarkInput");
				  filter = input.value.toUpperCase();
				  table = document.getElementById("bookmarTable");
				  tr = table.getElementsByTagName("tr");
				  for (i = 0; i < tr.length; i++) {
				    td = tr[i].getElementsByTagName("td")[0];
				    if (td) {
				      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
				        tr[i].style.display = "";
				      } else {
				        tr[i].style.display = "none";
				      }
				    }       
				  }
				}
				</script>
	      </div>
	      </div>
		<!-- //북마크  -->
	    </div>
	</div>
	<!-- //본문 -->
  </div>
  
<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>