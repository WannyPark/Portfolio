<%@page import="member.MemberMd"%>
<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("user_id");
	String pw = request.getParameter("user_pw");
	MemberDB manager = MemberDB.getInstance();
	int re = manager.loginMember(id, pw);
	
	if (re == 1) {
		MemberMd member = manager.getMember(id, 1);
		session.setAttribute("user_id", member.getUser_id());
		session.setAttribute("user_nName", member.getUser_nName());
		%>
		<script>
			alert("<%= member.getUser_nName() %>님 반갑습니다.");
			location.href="../main/mainView.jsp";
		</script>
		<%
	} else if (re == 0) {
		%>
		<script>
			alert("비밀번호가 틀렸습니다.");
			history.back();
		</script>
		<%
	} else {
		%>
		<script>
			alert("로그인에 실패했습니다.");
			history.back();
		</script>
		<%
	}
%>