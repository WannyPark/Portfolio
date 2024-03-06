<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
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
			<form name="log_frm" method="post" action="login_process.jsp">
				<table>
					<tr>
						<td colspan="2">
							<h1>로 그 인</h1>
						</td>
					</tr>
					<tr>
						<td>
							아 이 디
						</td>
						<td>
							<input type="text" name="user_id">
						</td>
					</tr>
					<tr>
						<td>
							비밀번호
						</td>
						<td>
							<input type="password" name="user_pw">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="로 그 인" onclick="login_ok();">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<span>아이디가 없다면 <a href="register.jsp">회원가입</a>을 해주세요.</span>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>