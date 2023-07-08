<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	int board_num = (Integer)request.getAttribute("board_num");
	String board_name = (String)request.getAttribute("board_name");
	String nowPage = (String)request.getAttribute("page");
	
	System.out.println(board_name);
	if(!id.equals(board_name) && !id.equals("admin")){
		out.println("<script>");
		out.println("alert('삭제할 권한이 없습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>
<style>
	#passForm{
		width:400px;
		margin:auto;
		border: 1px solid orange;
	}
</style>
</head>
<body>
	<section id = "passForm">
		<form name = "deleteForm" action = "boardDeletePro.bo?board_num=<%=board_num %>" method="post">
		<input type="hidden" name="page" value="<%=nowPage %>" />
		<table>
			<tr>
				<td>
					<label>글 비밀번호 : </label>
				</td>
				<td>
					<input name="BOARD_PASS" type = "password">
				</td>
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "삭제" />
					&nbsp; &nbsp;
					<input type = "button" value = "돌아가기" onclick="javascript:history.go(-1)"/>
				</td>
			</tr>
		</table>
		</form>
</body>
</html>