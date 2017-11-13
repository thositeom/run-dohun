<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title>네아로API호출</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<head>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-3.2.0.min.js"></script>
	<!-- 네아로API호출 -->
	<script type="text/javascript" src="/resources/js/naverApi/naverLogin_implicit-1.0.3.js"></script>
	<script type="text/javascript">
		var naver_id_login = new naver_id_login("vZbX7ZW_zMbNccbH_O_1", "http://127.0.0.1/naverLogin.do");
		// 접근 토큰 값 출력
// 		alert(naver_id_login.oauthParams.access_token);
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		  function naverSignInCallback() {
			$('#accessTokn').val(naver_id_login.oauthParams.access_token);
		    $('#email').val(naver_id_login.getProfileData('email'));
		    $('#nickname').val(naver_id_login.getProfileData('nickname'));
		    $('#age').val(naver_id_login.getProfileData('age'));
		    $('#birthday').val(naver_id_login.getProfileData('birthday'));
		    $('#nickenc_idame').val(naver_id_login.getProfileData('enc_id'));
		    $('#gender').val(naver_id_login.getProfileData('gender'));
		    $('#profile_image').val(naver_id_login.getProfileData('profile_image'));
		    $('#name').val(naver_id_login.getProfileData('name'));
		    $('#id').val(naver_id_login.getProfileData('id'));
			
		    $("form").submit();

		  }
	</script>
	<!-- //네아로API호출 -->
	
</head>
<body>
<form action="/memberNaverLogin.do">
	<input type="hidden" id="accessTokn" name="accessTokn" />
	<input type="hidden" id="email" name="email" />
	<input type="hidden" id="nickname" name="nickname" />
	<input type="hidden" id="age" name="age" />
	<input type="hidden" id="birthday" name="birthday" />
	<input type="hidden" id="enc_id" name="enc_id" />
	<input type="hidden" id="gender" name="gender" />
	<input type="hidden" id="profile_image" name="profile_image" />
	<input type="hidden" id="name" name="name" />
	<input type="hidden" id="id" name="id" />
</form>
</body>
</html>