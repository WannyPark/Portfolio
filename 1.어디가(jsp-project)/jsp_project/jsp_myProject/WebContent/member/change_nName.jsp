<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_nName = request.getParameter("user_nName");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Change Nickname</title>
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
			<form name="chg_nNm" method="post" action="change_nName_process.jsp">
				<table>
					<tr>
						<td>기존 닉네임</td>
						<td>
							<input type="text" name="user_nName" value="<%= user_nName %>" readonly>
						</td>
					</tr>
					<tr>
						<td>새로운 닉네임</td>
						<td>
							<input type="text" name="user_newnName">
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="변경" onclick="change_nName_ok();">
							<input type="reset" value="다시입력">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>