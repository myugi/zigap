<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BoardBean" %>

<%
	String id = null;
	if(session.getAttribute("id")==null){
		out.println("<script>");
		out.println("alert('로그인이 필요한 서비스입니다.')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}else{
		id = (String)session.getAttribute("id");
	}

	BoardBean article = (BoardBean)request.getAttribute("article");
	String nowPage = (String)request.getAttribute("page");
	if(nowPage == null) nowPage="1";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>
</head>
<body>

<jsp:include page="../include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<!-- 게시판 상세보기 -->
	<br>
		<h2 class="form-signin-heading">Q&A 문의 하기</h2>
		<table class="table table-hover">
			<tr>
				<td class="td-label"><label id="BOARD_NAME" for="BOARD_NAME">글쓴이 </label></td>
				<td class="td-label"><label id="BOARD_NAME" for="BOARD_NAME"><%=article.getBOARD_NAME() %></label></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_SUBJECT">제 목</label></td>
				<td class="td-label"><label for="BOARD_SUBJECT"><%=article.getBOARD_SUBJECT() %></label></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_CONTENT">내 용</label></td>
				<td><textarea name="BOARD_CONTENT" id="BOARD_CONTENT" cols="40" rows="15" class="form-control" readonly="readonly"><%=article.getBOARD_CONTENT() %></textarea></td>
			</tr>
		</table>
		<div class="btn-group" role="group" aria-label="...">
		  <button onclick="location.href='boardModifyForm.bo?board_num=<%=article.getBOARD_NUM() %>&board_name=<%=article.getBOARD_NAME() %>'" type="button" class="btn btn-default">수정하기</button>
		  <button onclick="location.href='boardDeleteForm.bo?board_num=<%=article.getBOARD_NUM() %>&page=<%=nowPage %>&board_name=<%=article.getBOARD_NAME() %>'" type="button" class="btn btn-default">삭제하기</button>
		  <button onclick="location.href='boardList.bo?page=<%=nowPage %>'" type="button" class="btn btn-default">목록으로</button>
		</div>
		&nbsp;&nbsp;

	</div>
	</div>
</body>
</html>