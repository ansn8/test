
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div style="background-color:#00008b;color:#ffffff;height:20px;padding: 5px;">
SPMS(Simple Project Management System)
<!-- loginss는 로그인세션 -->
<c:if test="${loginss.email !=null}">
	<span style="float:right;">
	${loginss.name}님 안녕하세요
	<a style="color:white;"
	href="../auth/logout.do">로그아웃</a>
	</span>
</c:if>

<c:if test="${loginss.email == null}">
	<a style="color:white;" href="../auth/logIn.do">로그인</a>
</c:if>
<a style="color:white;" href="../member/list.do">목록</a>
<a style="color:white;" href="../project/list.do">프로젝트</a>
</div>
<p>${loginss.email}</p>
<p>${loginss.name}</p>