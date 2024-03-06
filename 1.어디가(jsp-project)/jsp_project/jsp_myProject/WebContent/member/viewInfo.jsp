<%@page import="member.MemberMd"%>
<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
	MemberDB manager = MemberDB.getInstance();
	MemberMd member = manager.getMember(id, 1);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View My Information</title>
		<script type="text/javascript" src="js/check2.js"></script>
		<style>
			table {
				border: 2px solid #263343;
				padding: 10px;
				background-color: #FAEED1;
			}
		</style>
	</head>
	<body>
		<center>
			<form method="post" action="change_info.jsp">
				<table>
					<tr>
						<td colspan="2" align="center">
							<h2>회 원 정 보</h2>
						</td>
					</tr>
					<tr>
						<td width="100">아 이 디</td>
						<td colspan="3" width="300">
							<input type="text" name="user_id" value="<%= member.getUser_id() %>" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="change_pw.jsp">비밀번호 변경</a>
						</td>
					</tr>
					<tr>
						<td>닉 네 임</td>
						<td colspan="3">
							<input type="text" name="user_nName" value="<%= member.getUser_nName() %>" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="change_nName.jsp?user_nName=<%= member.getUser_nName() %>">닉네임 변경</a>
						</td>
					</tr>
					<tr>
						<td>성 명</td>
						<td colspan="3">
							<input type="text" name="user_name" value="<%= member.getUser_name() %>" readonly>
						</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="text" name="user_num" value="<%= member.getUser_num1() + '-' + member.getUser_num2()  + '-' + member.getUser_num3() %>" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="change_num.jsp?user_num=<%= member.getUser_num1() + '-' + member.getUser_num2()  + '-' + member.getUser_num3() %>">전화번호 변경</a>
						</td>
					</tr>
					<tr>
						<td>생 년 월 일</td>
						<td colspan="3">
							<input type="text" name="user_birthday" max="" value="<%= member.getUser_birthday() %>" readonly>
						</td>
					</tr>
					<tr>
						<td>지 역</td>
						<td>
							<input id="user_loc1" type="text" name="user_loc1" readonly>
						</td>
						<td>
							<input id="user_loc2" type="text" name="user_loc2" readonly>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="change_loc.jsp?loc1=<%= member.getUser_loc1() %>, loc2=<%= member.getUser_loc2() %>">지역 변경</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script>
			let user_loc1 = document.getElementById("user_loc1");
			let user_loc2 = document.getElementById("user_loc2");
			user_loc1.value = re_Loc1(<%= member.getUser_loc1() %>);
			user_loc2.value = re_Loc2(<%= member.getUser_loc1() %>,<%= member.getUser_loc2() %>);
		</script>
	</body>
</html>