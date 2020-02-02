<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<!-- 각 링크 URL끝에 .do를 붙여 이 링크들은 기존의 서블릿에서 프런트컨트롤러로 요청을 하게함 -->
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>회원목록</h1>
	<p><a href='add.do'>신규 회원</a></p>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>메일</th>
				<th>가입일</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="member" items="${members}">
			<tr>
				<td>${member.no}</td>
				<td><a href='update.do?no=${member.no}'>${member.name}</a></td>
				<td>${member.email}</td>
				<td>${member.createdDate}</td>
				<td><a href='delete.do?no=${member.no}'>[삭제]</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<jsp:include page="/Tail.jsp"/>
</body>
</html>