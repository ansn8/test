<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<c:url value="/resources/boot/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
	<title>test01</title>
	

</head>


<body>
	  <nav class="navbar navbar-default navbar-static-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/main">Home</a>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">스터디게시판 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="/study?category=java">Java</a></li>
					    <li><a href="/study?category=c">C</a></li>
					    <li><a href="/study?category=cpp">C++</a></li>
					    <li><a href="/study?category=cs">C#</a></li>
					    <li><a href="/study?category=html">HTML/CSS</a></li>
					    <li><a href="/study?category=js">JavaScript</a></li>
					    <li><a href="/study?category=php">PHP</a></li>
					    <li><a href="/study?category=infomaster">기본정보기술자</a></li>
            </ul>
          </li>
          <li><a class="blog-nav-item" href="/kihontest">기본정보기술자</a></li>
	      <li><a class="blog-nav-item" href="#/freeboard" onclick="alert('준비중입니다.');">자유게시판</a></li>
	      <li><a class="blog-nav-item" href="#/daily" onclick="alert('준비중입니다.');">데일리</a></li>
	      <li><a class="blog-nav-item" href="/visitor">방명록</a></li>
        <hr>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">로그인 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
				<!-- <li><a class="blog-nav-item" href="/register/step1">회원가입</a></li> -->
				<li><a class="blog-nav-item" href="/login">로그인하기</a></li>
            </ul>
          </li>
				
				
        </ul>
      </div><!--/.nav-collapse -->
    </div>
  </nav>
  
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
 </body>


</html>