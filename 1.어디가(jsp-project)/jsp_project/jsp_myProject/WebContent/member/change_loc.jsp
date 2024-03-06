<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String loc1 = request.getParameter("loc1");
	String loc2 = request.getParameter("loc2");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Change Local</title>
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
			<form name="chg_loc" method="post" action="change_loc_process.jsp">
				<table>
					<tr>
						<td>기존 지역</td>
						<td>
							<input id="user_loc1" type="text" name="user_loc1" readonly>
						</td>
						<td>
							<input id="user_loc2" type="text" name="user_loc2" readonly>
						</td>
					</tr>
					<tr>
						<td>변경할 지역</td>
						<td>
						<select id="user_select_loc1" name="user_select_loc1" onChange="cat1_change(this.value,user_select_loc2);">
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
						<select id="user_select_loc2" name="user_select_loc2">
							<option>-선택-</option>
						</select>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="변경" onclick="change_loc_ok();">
							<input type="button" value="홈으로" onclick="location.href='../main/mainView.jsp'">
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script>
			let user_loc1 = document.getElementById("user_loc1");
			let user_loc2 = document.getElementById("user_loc2");
			user_loc1.value = re_Loc1(<%= loc1 %>);
			user_loc2.value = re_Loc2(<%= loc1 %>,<%= loc2 %>);
		</script>
	</body>
</html>