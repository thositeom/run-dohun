<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>run-dohun 500ERROR</title>
<!-- 	<link rel="stylesheet" href="/resources/bootstrap-3.3.7-dist/css/bootstrap.css"> -->
	<link rel="stylesheet" type="text/css" media="screen" href="/resources/jquery-ui-1.12.1.custom/jquery-ui.css" />
	<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="/resources/jquery.jqGrid-4.4.3/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	
	<style> 
		.imgMove {
		    position: relative;
		    animation: errorMove 5s infinite alternate;
		}
		/* Standard syntax */
		@keyframes errorMove {
		    0%   {left:0px; bottom:0px;}
		    25%  {left:200px;  bottom:500px;}
		    50%  {left:600px; bottom:200px;}
		    75%  {left:921px; bottom:200px;}
		    100% {left:0px; bottom:0px;}
		}
	</style>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("button").click(function(){
			$(location).attr('href', window.location.protocol+"//"+window.location.hostname+"/index.do");
		});
	}); 
	</script>
	<form>
    <div class="col-sm-8 text-left"> 
      <h1>오백에러</h1>
      <div class="thumbnail">
	      <img class="img-circle40" src="resources/images/background/500error.jpg" width="600" height="400" alt="Image">
	      <div class="caption"><button class="btn btn btn-success imgMove" id="btn" name="btn" >메인화면</button>
	      </div>
      </div>
    </div>
    </form>
