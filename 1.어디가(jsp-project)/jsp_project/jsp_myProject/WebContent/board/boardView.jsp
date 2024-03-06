<%@page import="member.MemberMd"%>
<%@page import="member.MemberDB"%>
<%@page import="board.BoardMd"%>
<%@page import="board.BoardDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%
	String id = (String)session.getAttribute("user_id");
	MemberMd member = null;
	if (id != null) {
		MemberDB gMem = MemberDB.getInstance();
		member = gMem.getMember(id, 1);
	}
	String b_num = request.getParameter("b_num");
	BoardDB manager = BoardDB.getInstance();
	BoardMd board = manager.getBoard(Integer.parseInt(b_num), true);
// 	System.out.println("title ===> " + board.getB_title());
// 	System.out.println("content ===> " + board.getB_content());
// 	System.out.println("latitude ===> " + board.getB_latitude());
// 	System.out.println("longitude ===> " + board.getB_longitude());
// 	System.out.println("loc1 ===> " + board.getB_loc1());
// 	System.out.println("loc2 ===> " + board.getB_loc2());
// 	System.out.println("address ===> " + board.getB_address());
// 	System.out.println("road_address ===> " + board.getB_road_address());
// 	System.out.println("author ===> " + board.getB_author());
// 	System.out.println("time ===> " + board.getB_time());
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View Board</title>
		<script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh"></script>
	    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=3zjcf830oh&submodules=geocoder"></script>
	</head>
	<body>
		<center>
			<table width="500" border="1" style="border-collapse:collapse">
				<tr>
					<td colspan="2" align="center">
						<h1>게 시 물</h1>
					</td>
				</tr>
				<tr>
					<td width="100" align="center">글 제 목</td>
					<td width="400"><%= board.getB_title() %></td>
				</tr>
				<tr>
					<td align="center">글 쓴 이</td>
					<td><%= board.getB_author() %></td>
				</tr>
				<tr>
					<td colspan="2" align="center">위 치</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="map" style="width:100%;height:300px;"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">내 용</td>
				</tr>
				<tr>
					<td colspan="2">
						<%= board.getB_content() %>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="글목록 가기" onclick="location.href='boardMain.jsp'">
						<%
							if (member != null && member.getUser_nName().equals(board.getB_author())) {
								%>
								<input type="button" value="글 수정" onclick="location.href='boardModify.jsp?b_num=<%= board.getB_num() %>'">
								<input type="button" value="글 삭제" onclick="location.href='boardDelete.jsp?b_num=<%= board.getB_num() %>'">
								<%
							}
						%>
					</td>
				</tr>
			</table>
		</center>
	</body>
	<script>
	let lat = <%= board.getB_latitude() %>;
	let lgi = <%= board.getB_longitude() %>;
	let map = new naver.maps.Map('map', {
		center: new naver.maps.LatLng(lat, lgi),
		zoom: 18,
		zoomControl: true,
	});
	let marker = new naver.maps.Marker({ // 마커 만들기
		position: new naver.maps.LatLng(lat, lgi),
		map: map,
	});
	</script>
</html>