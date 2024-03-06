<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)session.getAttribute("user_id");
	String user_newnName = request.getParameter("user_newnName");
	MemberDB manager = MemberDB.getInstance();
	int re = manager.updateMember_nName(id, user_newnName);
	
	if (re == 1) {
		session.setAttribute("user_nName", user_newnName);
		%>
		<script>
			alert("닉네임 변경에 성공했습니다.");
			location.href = "../main/mainView.jsp";
		</script>
		<%
	} else {
		%>
		<script>
			alert("닉네임 변경에 실패했습니다. 다시 시도해주세요.");
			history.back();
		</script>
		<%
	}
%>