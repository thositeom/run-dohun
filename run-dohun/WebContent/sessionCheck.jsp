<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.fusionsoft.beans.wikiadm.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fst" uri="/fusionsoft"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>지식정보 세션체크</title>
</head>
<h1>지식정보 세션체크</h1>
<hr/>
<body>
<% if(request.getSession().getAttribute("MEMBER_INFO_SESSION") == null){ %>
	세션이 없습니다.
<% }else{ User user = (User)session.getAttribute("MEMBER_INFO_SESSION"); %>

<h3>세션값</h3> 
mac                : <%=user.getMac() %>             <br>
symm_enckey        : <%=user.getSymm_enckey() %>     <br>
enctype            : <%=user.getEnctype() %>         <br>
userid             : <%=user.getUserid() %>          <br>
pw                 : <%=user.getPw() %>              <br>
clientId           : <%=user.getClientId() %>        <br>
memberId           : <%=user.getMemberId() %>        <br>
memberPw           : <%=user.getMemberPw() %>        <br>
                                                    <br>
s_userid           : <%=user.getS_userid() %>        <br>
s_userps           : <%=user.getS_userps() %>        <br>
s_username         : <%=user.getS_username() %>      <br>
s_usernic          : <%=user.getS_usernic() %>       <br>
n_userperm         : <%=user.getN_userperm() %>      <br>
n_usergroup        : <%=user.getN_usergroup() %>     <br>
s_email            : <%=user.getS_email() %>         <br>
d_date             : <%=user.getD_date() %>          <br>
n_status           : <%=user.getN_status() %>        <br>
s_regnum           : <%=user.getS_regnum() %>        <br>
s_mobile           : <%=user.getS_mobile() %>        <br>
s_zip              : <%=user.getS_zip() %>           <br>
s_address1         : <%=user.getS_address1() %>      <br>
s_address2         : <%=user.getS_address2() %>      <br>
n_getmail          : <%=user.getN_getmail() %>       <br>
n_getsms           : <%=user.getN_getsms() %>        <br>
s_phone            : <%=user.getS_phone() %>         <br>
n_gender           : <%=user.getN_gender() %>        <br>
n_public           : <%=user.getN_public() %>        <br>
n_image            : <%=user.getN_image() %>         <br>
s_intro            : <%=user.getS_intro() %>         <br>
n_icon             : <%=user.getN_icon() %>          <br>
s_otherblog        : <%=user.getS_otherblog() %>     <br>
n_rssaggregation   : <%=user.getN_rssaggregation() %><br>
s_history          : <%=user.getS_history() %>       <br>
s_jobtype          : <%=user.getS_jobtype() %>       <br>
s_company          : <%=user.getS_company() %>       <br>
s_position         : <%=user.getS_position() %>      <br>
                                                    <br>
teacher_yn         : <%=user.getTeacher_yn() %>      <br>
                                                    <br>
tel_no             : <%=user.getTel_no() %>          <br>
member_div         : <%=user.getMember_div() %>      <br>
zip_code           : <%=user.getZip_code() %>        <br>
addr               : <%=user.getAddr() %>            <br>
detail_addr        : <%=user.getDetail_addr() %>     <br>
org_code           : <%=user.getOrg_code() %>        <br>
photo_file_name    : <%=user.getPhoto_file_name() %> <br>
homepage           : <%=user.getHomepage() %>        <br>
                                                    <br>
dup_info           : <%=user.getDup_info() %>        <br>
gpki_key           : <%=user.getGpki_key() %>        <br>
gpki_date          : <%=user.getGpki_date() %>       <br>
auth_yn            : <%=user.getAuth_Yn() %>         <br>
re_agree_date      : <%=user.getReagree_Date() %>    <br>
s_point            : <%=user.getS_point() %>         <br>
s_level            : <%=user.getS_level() %>         <br>


<%} %>
</body>
</html>