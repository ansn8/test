<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>응답 내용</p>
	<ul>
		<c:forEach var="response" items="${ansData.responses}" varStatus="status">
			<li>${status.index + 1}번 문항 : ${response}</li>
		</c:forEach>
	</ul>
	<p>응답자 위치 : ${ansData.res.location}</p>
	<p>응답자 나이 : ${ansData.res.age}</p>
	<br>
	<p><a href="<c:url value="/main"/>">[첫 화면 이동]</a></p>
</body>
</html>