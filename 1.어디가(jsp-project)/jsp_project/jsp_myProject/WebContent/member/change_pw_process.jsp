<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
	String pw = request.getParameter("user_pw");
	String newPw1 = request.getParameter("user_newPw1");
	String newPw2 = request.getParameter("user_newPw2");
	MemberDB manager = MemberDB.getInstance();
	int re = manager.updateMember_pw(id, pw, newPw1, newPw2);
	
	if (re == 1) {
		%>
		<script>
			alert("비밀번호가 성공적으로 변경되었습니다. 다시 로그인 해주세요.");
			<% session.invalidate(); %>
			location.href="../main/mainView.jsp";
		</script>
		<%
	} else if (re == 0) {
		%>
		<script>
			alert("현재 비밀번호가 틀렸습니다. 다시 시도해주세요.");
			history.back();
		</script>
		<%
	} else if (re == -2) {
		%>
		<script>
			alert("새로운 비밀번호를 똑같이 입력해주세요.");
			history.back();
		</script>
		<%
	} else {
		%>
		<script>
			alert("비밀번호 변경에 실패했습니다. 다시 시도해주세요.");
			history.back();
		</script>
		<%
	}
%>