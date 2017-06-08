<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.page</title>
</head>

<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
 -->
<!-- Firebase -->
<script src="https://www.gstatic.com/firebasejs/3.6.3/firebase.js"></script>
<script>

	// Initialize Firebase
 	var config = {
		
	};
	firebase.initializeApp(config);
	function writeUserData() {
	    var userId = document.getElementById("userId").value;
	    var name = document.getElementById("name").value;
		var email = "Email";
		var num = firebase.database().ref("users/").push().key;
		firebase.database().ref("users/" + userId).set({
			username : name,
			email : email,
			num : num
		});
	}

</script>

<body>
	<form action="/index.do" method="post">
		id :	<input type="text" id="userId" name="userId"> 
		pwd :	<input type="text" id="name" name="name"> 
		<input type="button" value="button" onclick="writeUserData();">
	
	한글
	</form>
	<div id="resultView"></div>

</body>
</html>
