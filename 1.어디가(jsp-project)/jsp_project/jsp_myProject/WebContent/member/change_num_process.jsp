<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
	String num1 = request.getParameter("user_num1");
	String num2 = request.getParameter("user_num2");
	String num3 = request.getParameter("user_num3");
	String num = num1 + "-" + num2 + "-" + num3;
	MemberDB manager = MemberDB.getInstance();
	int re = manager.updateMember_num(id, num);
	
	if (re == 1) {
		%>
		<script>
			alert("전화번호 변경에 성공했습니다.");
			location.href = "../main/mainView.jsp";
		</script>
		<%
	} else {
		%>
		<script>
			alert("전화번호 변경에 실패했습니다.");
			history.back();
		</script>
		<%
	}
%>