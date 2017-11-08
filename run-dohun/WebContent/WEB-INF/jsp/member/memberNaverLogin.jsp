<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">

<head>
<title>네아로</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<head>
<!-- 네이버아디디로로그인 Callback페이지 처리 Script -->
<!-- <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script> -->
<script type="text/javascript">
	/* 
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		// naver_id_login.getProfileData('프로필항목명');
		// 프로필 항목은 개발가이드를 참고하시기 바랍니다.
		console.log(naver_id_login.getProfileData('age'));	//연령대
		console.log(naver_id_login.getProfileData('birthday'));	//생일
		console.log(naver_id_login.getProfileData('email'));	//이메일
		console.log(naver_id_login.getProfileData('enc_id'));	//몰라
		console.log(naver_id_login.getProfileData('gender'));	//성별
		console.log(naver_id_login.getProfileData('id'));		//??
		console.log(naver_id_login.getProfileData('nickname'));	//닉네임
		console.log(naver_id_login.getProfileData('profile_image'));	//이미지
		console.log(naver_id_login.getProfileData('name'));	//이름
		
		window.open("about:blank","_self").close();
	}

	// 네이버 사용자 프로필 조회
	var naver_id_login = new naver_id_login("vZbX7ZW_zMbNccbH_O_1", "http://127.0.0.1/memberNaverLogin.do");
	naver_id_login.get_naver_userprofile("naverSignInCallback()"); */

	// 	location.href='${apiURL}';
	/* 	var win = window.open("", "_self");
    win.close(); */
    
    window.opener.naverLoginResult("","");
    
    
</script>
<!-- //네이버아디디로로그인 Callback페이지 처리 Script -->
</head>
<body>

<% String session01 = (String) session.getAttribute("_state_");  %> 
<br> 세션 : <%= session01 %>

</body>
</html>