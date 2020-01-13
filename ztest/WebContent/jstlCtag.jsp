
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 변수설정방식 2가지 -->
<c:set var="username1" value="홍길동"/> 
<c:set var="username2">임꺽정</c:set> 
${username1}<br>
${username2}<br>

<!-- 기본저장위치 확인 기본적으로 jspContext(page)에 저장됨 -->
${pageScope.username1}<br>
${requestScope.username2}<br>

<!-- 저장위치를 scope를 통해 직접지정 가능 -->
<c:set var="username3" scope="request">사냥꾼</c:set>
${pageScope.username3}<br>
${requestScope.username3}<br>

<!-- 변수삭제 -->
<c:remove var="username1"/>
${username1}<br>

<!-- if태그 -->
<!-- 조건이 참이면, value가 나타나고 ${result}의 결과는 true를 반환하고
	 조건이 거짓이면, 작동하지 않기에 value는 나타나지않고 ${result}는 false를 반환함
 -->
<c:if test="${10>20}" var="result">10은20보다 크다</c:if><br>
${result}<br>
<c:if test="${10<20}" var="result">20은10보다 크다</c:if><br>
${result}<br>

<!-- choose태그 자바의 switch~case문과 같음 -->
<c:set var="userid" value="user1"/>
<c:choose>
	<c:when test="${userid =='user1'}">
		user1입니다.
	</c:when>
	<c:when test="${userid =='user2'}">
		user2입니다.
	</c:when>
	<c:when test="${userid =='user3'}">
		user3입니다.
	</c:when>
	<c:otherwise>
		조건에 맞는 id가 없음(자바의 default값)
	</c:otherwise>
</c:choose>

<!-- forEach태그 자바의 for문 -->
<!--  기본형태
<c:forEach var="변수" items="반복문에 돌릴데이터" begin="0" end="1">
	반복할내용
</c:forEach>
 -->
<% 
pageContext.setAttribute("namelist", new String[]{"홍길동","임꺽정","일지매","이순신","장보고"}); 
%>
<ul><!-- items 전부 반복문으로 출력 -->
	<c:forEach var="name" items="${namelist}">
		<li>${name}</li>
	</c:forEach>
</ul>

<br>

<ul><!-- items를 특정 인덱스만 출력 -->
	<c:forEach var="name" items="${namelist}" begin="2" end="3">
		<li>${name}</li>
	</c:forEach>
</ul>

<br>

<%
ArrayList<String> list = new ArrayList<String>();
list.add("홍길동");
list.add("임꺽정");
list.add("일지매");
list.add("이순신");
list.add("장보고");
pageContext.setAttribute("nameList", list);
%>
<ul><!-- ArrayList의 값 출력 -->
	<c:forEach var="name" items="${nameList}">
		<li>${name}</li>
	</c:forEach>
</ul>
<%
pageContext.setAttribute("names", "홍길동,임꺽정,일지매,이순신,장보고");
%>
<ul><!-- ,콤마로 이루어진 문자열도 반복문으로 출력가능 -->
	<c:forEach var="name" items="${names}">
		<li>${name}</li>
	</c:forEach>
</ul>

<ul><!-- 배열이나 리스트없이 특정부분을 반복하고 싶을 경우 -->
	<c:forEach var="no" begin="1" end="6">
		<li>
		<a href="jstl0${no}.jsp">JSTL예제${no}</a>
		</li>
	</c:forEach>
</ul>

<!--  forTokens 문자열을 구분자를 통해 분리하는 반복문 -->
<% pageContext.setAttribute("tokens", "v1=20&v2=30&op=+"); %>
<ul>
	<c:forTokens var="item" items="${tokens}" delims="&">
		<li>${item}</li>
	</c:forTokens>
</ul>

<!-- url을 만드는 태그 이 태그를 사용해 매개변수를 포함한 url만들기 가능-->
	<c:url var="calcUrl" value="http://localhost:8181/calc">
		<c:param name="v1" value="20"/>
		<c:param name="v2" value="10"/>
		<c:param name="op" value="+"/>
	</c:url>
	<a href="${calcUrl}">계산하기 ${calcUrl}</a>

<!-- import태그 여러사이트의 내용을 가져와서 새로운서비스를 만드는 매쉬업에 유용함 아직 왜쓰는지 잘 모르겠다-->
	<textarea rows="10" cols="80">
	<c:import url="https://www.naver.com"/>
	</textarea>
	<br>
<!-- redirect태그 이 태그를 통해 sendRedirect기능 실행가능 왜 작동이 안되지;-->
	<!-- c:redirect url="http://daum.net"/>  -->
<fmt:parseDate var="date1" value="2013-11-16" pattern="yyyy-MM-dd"/>
<p>${date1 }</p>
<fmt:formatDate value="${date1}" pattern="MM/dd/yy"/>
</body>
</html>