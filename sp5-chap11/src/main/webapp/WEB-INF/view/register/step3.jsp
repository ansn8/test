<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<!-- 컨트롤러에서 등록한 커맨드객체 RegisterRequest를 통해 값을 가져올 수 있음 -->
	<h3>회원가입 완료</h3>
	<spring:message code="register.done" arguments="${registerRequest.name }"/>
	<p><spring:message code="name"/> : ${registerRequest.name}</p>
	<p><spring:message code="email"/> : ${registerRequest.email}</p>
	<p><a href="<c:url value="/main"/>">[<spring:message code="go.main"/>]</a></p>
</body>
</html>