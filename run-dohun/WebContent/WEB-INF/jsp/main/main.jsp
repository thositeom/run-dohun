<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
 
<head>
	<title>run-dohun</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
	<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<link rel="stylesheet" type="text/css" media="screen" href="/resources/jquery-ui-1.12.1.custom/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="/resources/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" />
	<script src="/resources/jquery.jqGrid-4.4.3/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="/resources/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
	<script src="/resources/jquery.jqGrid-4.4.3/js/jquery.jqGrid.src.js"></script>
	<script src="/resources/jquery.jqGrid-4.4.3/src/i18n/grid.locale-kr.js"></script>

  	<style>
    /* Remove the navbar's default margin-bottom and rounded borders */
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
	/* .row.content {height: 450px} */
    .row.content {height: 750px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  	</style>
  	
  	<!-- 구글애드샌스 광고 무료수익 -->
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<script>
	  (adsbygoogle = window.adsbygoogle || []).push({
	    google_ad_client: "ca-pub-8107095992534793",
	    enable_page_level_ads: true
	  });
	</script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/common/layout/gnb.jsp"></jsp:include>

<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="http://connect.garmin.com" target="_garmin">Garmin Connect</a></p>
      <p><a href="https://w3schools.com" target="_w3schools">W3Schools</a></p>
      <p><a href="http://thositeom.tistory.com" target="_tistory">Tistory</a></p>
      <p><a href="https://docs.oracle.com/javase/8/docs/api/" target="_javaApi">Java Standard Edition 8 API</a></p>
    </div>
    <div id="content_div">
	    <div class="col-sm-8 text-left"> 
	      <h1>run-dohun</h1>
	      <p>나는 달린다. 왜냐 살뺄거니까</p>
	      <hr>
	    <!-- 모달 -->
	      <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>
	    	<!-- Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Modal Header</h4>
		        </div>
		        <div class="modal-body">
		          <p>Some text in the modal.</p>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		        </div>
		      </div>
		    </div>
		</div>
		<!-- /모달 -->
		
		<!-- img광고 -->
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1"></li>
		    </ol>
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner" role="listbox">
		      <div class="item active">
		        <img src="/resources/images/background/buildings-2297210_960_720.jpg" alt="Image">
		        <div class="carousel-caption">
		          <h3>run-dohun $</h3>
		          <p>running...</p>
		        </div>      
		      </div>
		      <div class="item">
		        <img src="/resources/images/background/landscape-2211587_960_720.jpg" alt="Image">
		        <div class="carousel-caption">
		          <h3>fun-run $</h3>
		          <p>ing...</p>
		        </div>
		      </div>
		    </div>
		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		</div>
		<!-- /img광고 -->
		<!--  -->
		<div class="sidenav">
	      <div class="well">
	       <p>Some text..</p>
	      </div>
	      <div class="well">
	       <p>Some text..</p>
	      </div>
	    </div>
		<!-- /  -->
	    </div>
	</div>
    <div class="col-sm-2 sidenav">
      <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/jsp/common/layout/footer.jsp"></jsp:include>
</body>
</html>