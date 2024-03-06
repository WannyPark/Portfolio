<%@page import="board.BoardDB"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="member.MemberMd"%>
<%@page import="member.MemberDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("user_id");
	request.setCharacterEncoding("utf-8");
	MemberDB manager = MemberDB.getInstance();
	MemberMd member = manager.getMember(id, 1);
%>
<jsp:useBean id="newBoard" class="board.BoardMd"></jsp:useBean>
<jsp:setProperty property="*" name="newBoard"/>
<%
	newBoard.setB_author(member.getUser_nName());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String time = sdf.format(now);
	newBoard.setB_time(time);
	BoardDB manager2 = BoardDB.getInstance();
	int re = manager2.insertBoard(newBoard);
	
	if (re == -1) {
		%>
		<script>
			alert("글 작성에 실패했습니다. 다시 시도해주세요.");
			history.back();
		</script>
		<%
	} else if (re == 1) {
		%>
		<script>
			alert("글 작성에 성공했습니다.");
			location.href="boardMain.jsp";
		</script>
		<%
	}
// 	System.out.println("title ===> " + newBoard.getB_title());
// 	System.out.println("content ===> " + newBoard.getB_content());
// 	System.out.println("latitude ===> " + newBoard.getB_latitude());
// 	System.out.println("longitude ===> " + newBoard.getB_longitude());
// 	System.out.println("loc1 ===> " + newBoard.getB_loc1());
// 	System.out.println("loc2 ===> " + newBoard.getB_loc2());
// 	System.out.println("address ===> " + newBoard.getB_address());
// 	System.out.println("road_address ===> " + newBoard.getB_road_address());
// 	System.out.println("author ===> " + newBoard.getB_author());
// 	System.out.println(time);
%>