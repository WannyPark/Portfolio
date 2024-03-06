<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date now = new Date();
	String nowTime = sdf.format(now);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
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
			<form method="post" name="reg_pst" action="register_process.jsp">
				<table width="400">
					<tr>
						<td colspan="2" align="center">
							<h2>회 원 가 입</h2>
						</td>
					</tr>
					<tr>
						<td width="100">아 이 디</td>
						<td colspan="3" width="300"><input type="text" name="user_id"></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td colspan="3"><input type="password" name="user_pw"></td>
					</tr>
					<tr>
						<td>닉 네 임</td>
						<td colspan="3"><input type="text" name="user_nName"></td>
					</tr>
					<tr>
						<td>성 명</td>
						<td colspan="3"><input type="text" name="user_name"></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="text" name="user_num1" size="5">-
							<input type="text" name="user_num2" size="5">-
							<input type="text" name="user_num3" size="5">
						</td>
					</tr>
					<tr>
						<td>생 년 월 일</td>
						<td colspan="3"><input type="date" name="user_birthday" max="<%= nowTime %>" min="1900-01-01" value="<%= nowTime %>"></td>
					</tr>
					<tr>
						<td>지 역</td>
						<td>
							<select name="user_loc1" onChange="cat1_change(this.value,user_loc2);" class="user_loc1">
								<option>-선택-</option>
							  	<option value='1'>서울</option>
								<option value='2'>부산</option>
								<option value='3'>대구</option>
								<option value='4'>인천</option>
								<option value='5'>광주</option>
								<option value='6'>대전</option>
								<option value='7'>울산</option>
								<option value='8'>강원</option>
								<option value='9'>경기</option>
								<option value='10'>경남</option>
								<option value='11'>경북</option>
								<option value='12' >전남</option>
								<option value='13'>전북</option>
								<option value='14'>제주</option>
								<option value='15'>충남</option>
								<option value='16'>충북</option>
							</select>
							<select name="user_loc2" class="user_loc2">
								<option>-선택-</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" value="회원가입" onclick="register_ok();">
							<input type="reset" value="다시쓰기">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>	
		</center>
	</body>
</html>