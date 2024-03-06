<%@page import="board.BoardDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String b_num = request.getParameter("b_num");
	BoardDB manager = BoardDB.getInstance();
	int re = manager.deleteBoard(Integer.parseInt(b_num));
	
	if (re == -1) {
		%>
		<script>
			alert("글 삭제에 실패했습니다.");
			history.back();
		</script>
		<%
	} else if (re == 1) {
		%>
		<script>
			alert("글 삭제에 성공했습니다.");
			location.href="boardMain.jsp";
		</script>
		<%
	}
%>