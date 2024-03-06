<%@page import="board.BoardMd"%>
<%@page import="board.BoardDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String b_num = request.getParameter("b_num");
	BoardDB manager = BoardDB.getInstance();
	BoardMd board = manager.getBoard(Integer.parseInt(b_num), false);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Board Modify</title>
		<script type="text/javascript" src="../member/js/check2.js"></script>
		<style type="text/css">
	    	textarea {
	    		width: 100%;
			    height: 10em;
			    resize: none;
	    	}
	    </style>
	</head>
	<body>
		<center>
			<form name="frm_board" method="post" action="boardModify_process.jsp?b_num=<%= b_num %>">
				<table width="500">
					<tr>
						<td>
							<h1>글 수 정</h1>
						</td>
					</tr>
					<tr>
						<td>
							<h4>제 목 : </h4>
						</td>
						<td>
							<input name="b_title" type="text" placeholder="글 제목" value="<%= board.getB_title() %>">
						</td>
					</tr>
					<tr>
						<td>
							<h4>내 용</h4>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="b_content" placeholder="내용을 입력해주세요."><%= board.getB_content() %></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="작성" onclick="check_board_write();">
							<input type="button" value="돌아가기" onclick="history.back();">
						</td>
					</tr>
				</table>
			</form>
		</center>
		<script>
			function check_board_write() {
		    	if (document.frm_board.b_title.value.length == 0) {
		    		alert("글 제목을 적어주세요.");
		    		return ;
		    	}
		    	if (document.frm_board.b_content.value.length == 0) {
		    		alert("글 내용을 적어주세요.");
		    		return ;
		    	}
		    	document.frm_board.submit();
		    }
		</script>
	</body>
	
</html>