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
	String board_name = (String)request.getAttribute("board_name");
	if(!id.equals(board_name) && !id.equals("admin")){
		out.println("<script>");
		out.println("alert('수정할 권한이 없습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	BoardBean article = (BoardBean)request.getAttribute("article");
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
	<!-- 게시판 등록 -->
		<h2 class="form-signin-heading">Q&A 문의 하기</h2>
		<form class="form-wirte" action="boardModifyPro.bo" name="modifyform" method="post">
		<input type="hidden" name="BOARD_NUM" value="<%=article.getBOARD_NUM() %>" />
		<table class="table table-hover">
			<tr>
				<td class="td-label"><label id="BOARD_NAME" for="BOARD_NAME">글쓴이 </label></td>
				<td class="td-label"><label id="BOARD_NAME" for="BOARD_NAME"><%=article.getBOARD_NAME() %></label></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_PASS">비밀번호</label></td>
				<td><input type="password" name="BOARD_PASS" id="BOARD_PASS" class="joinform-control" required=""></td>
			</tr>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_SUBJECT">제 목</label></td>
				<td><input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" value=<%=article.getBOARD_SUBJECT() %> class="joinform-control" required="required" /></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_CONTENT">내 용</label></td>
				<td><textarea name="BOARD_CONTENT" id="BOARD_CONTENT" value=<%=article.getBOARD_CONTENT() %>cols="40" rows="15" class="form-control" required="required"></textarea></td>
			</tr>
		</table>
				<a class="btn btn-default-reset" href="javascript:history.back()" role="button">뒤로</a>
				<a class="btn btn-default-submit" href="javascript:modifyform.submit()" role="button">수정 하기</a>
		</form>
		</div>
		</div>
</body>
</html>