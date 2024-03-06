<%@page import="java.sql.SQLException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String id = (String)session.getAttribute("user_id");
	String nName = (String)session.getAttribute("user_nName");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<link rel="stylesheet" href="css/main_style.css">
	</head>
	<body>
		<nav class="navbar">
			<div class="navbar_logo">
				<img alt="" src="images/logo.png">
			</div>
			<ul class="navbar_menu">
				<li><a href="mainView.jsp" class="nav_atag">Home</a></li>
				<li><a href="../board/boardMain.jsp" class="nav_atag">Board</a></li>
			</ul>
		</nav>
		<center>
			<%
			if (id == null || nName == null) {
				%>
				<div class="login_Box">
					<p><a href="../member/login.jsp">로그인</a> 해주세요.</p>
					<p>계정이 없으시면 <a href="../member/register.jsp">회원가입</a>을 해주세요.</p>
				</div>
				<%	
			} else {
				%>
				<div class="login_Box">
					<p><%= nName %>님 반갑습니다.</p>
					<p><a href="../member/viewInfo.jsp">내 정보 보기 / 수정</a></p>
					<p><a href="../member/logout.jsp">로그아웃</a></p>
				</div>
				<%
			}
			%>
		</center>
	</body>
</html>