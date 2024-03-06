<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
	String loc1 = request.getParameter("user_select_loc1");
	String loc2 = request.getParameter("user_select_loc2");
	MemberDB manager = MemberDB.getInstance();
	int re = manager.updateMember_loc(id, loc1, loc2);
	
	if (re == 1) {
		%>
		<script>
			alert("지역 변경에 성공했습니다.");
			location.href = "../main/mainView.jsp";
		</script>
		<%
	} else {
		%>
		<script>
			alert("지역 변경에 실패했습니다.");
			history.back();
		</script>
		<%
	}
%>