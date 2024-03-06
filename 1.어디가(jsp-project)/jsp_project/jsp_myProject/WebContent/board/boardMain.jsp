<%@page import="board.BoardMd"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
 	BoardDB manager = BoardDB.getInstance();
 	ArrayList<BoardMd> list = manager.getBoards();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board Main</title>
		<link rel="stylesheet" href="../main/css/main_style.css">
	</head>
	<body>
		<nav class="navbar">
			<div class="navbar_logo">
				<img alt="" src="../main/images/logo.png">
			</div>
			<ul class="navbar_menu">
				<li><a href="../main/mainView.jsp" class="nav_atag">Home</a></li>
				<li><a href="boardMain.jsp" class="nav_atag">Board</a></li>
			</ul>
		</nav>
		<center>
			<div>
				<table width="800" border="1" style="border-collapse:collapse">
					<%
					if (id != null) {
						%>
						<tr>
							<td colspan="5">
								<a href="boardWrite.jsp">글쓰기</a>
							</td>
						</tr>
						<%
					}
					%>
					<tr>
						<th width="50">번호</th>
						<th width="500">제목</th>
						<th width="100">글쓴이</th>
						<th width="100">날짜</th>
						<th width="50">조회수</th>
					</tr>
					<%
					for (int i = 0; i < list.size(); i++) {
						%>
						<tr>
							<td><%= list.get(i).getB_num() %></td>
							<td><a href="boardView.jsp?b_num=<%= list.get(i).getB_num() %>"><%= list.get(i).getB_title() %></a></td>
							<td><%= list.get(i).getB_author() %></td>
							<td><%= list.get(i).getB_time() %></td>
							<td><%= list.get(i).getB_viewNum() %></td>
						</tr>
						<%
					}
					%>
				</table>
			</div>
		</center>
	</body>
</html>