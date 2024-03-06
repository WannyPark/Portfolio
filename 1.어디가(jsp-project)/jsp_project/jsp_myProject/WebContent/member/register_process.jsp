<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean class="member.MemberMd" id="mem"></jsp:useBean>
<jsp:setProperty property="*" name="mem" />
<%
	MemberDB manager = MemberDB.getInstance();
	int re = manager.insertMember(mem);
	if (mem.getUser_id() == null) re = -1;
	else if (mem.getUser_pw() == null) re = -1;
	else if (mem.getUser_nName() == null) re = -1;
	
	if (re == 1) {
		%>
		<script>
			alert("회원가입이 정상적으로 되었습니다. 로그인을 해주세요.");
			location.href="../main/mainView.jsp";
		</script>
		<%
	} else if (re == -1) {
		%>
		<script>
			alert("회원가입에 실패했습니다. 다시 시도해주세요.");
			location.href="../main/mainView.jsp";
		</script>
		<%
	} else if (re == -2) {
		%>
		<script>
			alert("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
			history.back();
		</script>
		<%
	} else if (re == -3) {
		%>
		<script>
			alert("이미 존재하는 닉네임입니다. 다른 닉네임를 입력해주세요.");
			history.back();
		</script>
		<%
	}
%>