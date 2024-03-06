<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_num = request.getParameter("user_num");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Change Number</title>
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
			<form name="chg_num" method="post" action="change_num_process.jsp">
				<table>
					<tr>
						<td>기존 번호</td>
						<td>
							<input type="text" name="user_num" value="<%= user_num %>" readonly>
						</td>
					</tr>
					<tr>
						<td>새로운 번호</td>
						<td>
							<input type="text" name="user_num1" size="5">-
							<input type="text" name="user_num2" size="5">-
							<input type="text" name="user_num3" size="5">
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="변경" onclick="change_num_ok();">
							<input type="reset" value="다시입력">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>