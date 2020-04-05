<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${empty authInfo}">
		<p>메인페이지</p>
		<p><a href="<c:url value="/register/step1"/>">회원가입하기</a></p>
		<p><a href="<c:url value="/login"/>">[로그인]</a></p>
	</c:if>
	<c:if test="${!empty authInfo}">
		<p>${authInfo.name}님, 환영합니다.</p>
		<p><a href="<c:url value="/edit/changePassword"/>">[비밀번호 변경]</a></p>
		<p><a href="<c:url value="/logout"/>">[로그아웃]</a></p>
		<p><a href="<c:url value="/survey"/>">설문조사하기</a></p>
		<p><a href="<c:url value="/members"/>">회원조회하기</a></p>
	</c:if>
</body>
</html>