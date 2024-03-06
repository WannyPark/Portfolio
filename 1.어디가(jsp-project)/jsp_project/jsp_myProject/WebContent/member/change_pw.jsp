<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Change PassWord</title>
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
			<form name="change_pw" method="post" action="change_pw_process.jsp">
				<table>
					<tr>
						<td>현재 비밀번호</td>
						<td>
							<input type="password" name="user_pw">
						</td>
					</tr>
					<tr>
						<td>새로운 비밀번호</td>
						<td>
							<input type="password" name="user_newPw1">
						</td>
					</tr>
					<tr>
						<td>새로운 비밀번호 확인</td>
						<td>
							<input type="password" name="user_newPw2">
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="변경" onclick="change_pw_ok();">
							<input type="reset" value="다시입력">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>