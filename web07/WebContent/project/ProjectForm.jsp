<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"/>
	<h1>프로젝트 등록</h1>
	<form action="add.do" method="post">
	<ul>
		<li>제목<textarea style="resize: none;" rows="1" cols="50" name="title"></textarea></li>
		<li>내용<textarea style="resize: none;" rows="10" cols="50" name="content"></textarea></li>
		<li>시작일<textarea style="resize: none;" rows="1" cols="20" name="startDate" placeholder="예) 2020-01-29"></textarea></li>
		<li>종료일<textarea style="resize: none;" rows="1" cols="20" name="endDate"placeholder="예) 2020-01-29"></textarea></li>
		<li>태그<textarea style="resize: none;" rows="1" cols="20" name="tags" placeholder="예) 태그1,태그2,태그3"></textarea></li>
	</ul>
	<input type="submit" value="확인">
	<input type="reset" value="취소">
	</form>
	
	<jsp:include page="/Tail.jsp"/>
</body>
</html>