<%@ tag body-content="empty" pageEncoding="UTF-8"%>
<%@ tag import="java.time.format.DateTimeFormatter"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ attribute name="value" required="true"
		type="java.time.temporal.TemporalAccessor"%>
<%@ attribute name="pattern" type="java.lang.String" %>
<%
	if(pattern == null) pattern = "yyyy-MM-dd";
%>
<%= DateTimeFormatter.ofPattern(pattern).format(value) %>
<!-- jstl은 LocalDateTime타입을 지원하지 않아서 위와 같이 태그파일로 직접 만들어 LocalDateTime을 지정한 형식으로 출력하기 함 -->