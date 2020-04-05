<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><spring:message code="id"/>: ${member.id}</p>
	<p><spring:message code="email"/>: ${member.email}</p>
	<p><spring:message code="name"/>: ${member.name}</p>
	<p><spring:message code="register.day"/>: <tf:formatDateTime value="${member.registerDateTime }" pattern="yyyy-MM-dd HH:mm"/></p>
</body>
</html>