<%@page import="board.BoardDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String b_num = request.getParameter("b_num");
	String b_title = request.getParameter("b_title");
	String b_content = request.getParameter("b_content");
	BoardDB manager = BoardDB.getInstance();
	int re = manager.updateBoard(Integer.parseInt(b_num), b_title, b_content);
	
	if (re == -1) {
		%>
		<script>
			alert("게시물 업데이트를 실패했습니다.");
			history.back();
		</script>
		<%
	} else if (re == 1) {
		%>
		<script>
			alert("게시물 업데이트에 성공했습니다.");
			location.href="boardView.jsp?b_num=<%= b_num %>";
		</script>
		<%
	}
%>