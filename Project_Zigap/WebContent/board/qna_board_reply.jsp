<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BoardBean" %>
<%
	String id = null;
	if(!session.getAttribute("id").equals("admin")){
		out.println("<script>");
		out.println("alert('관리자로 로그인하십시오.')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}else{
		id = (String)session.getAttribute("id");
	}
	BoardBean article = (BoardBean) request.getAttribute("article");
	String nowPage = (String) request.getAttribute("page");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MVC 게시판</title>
<script language = "javascript">
</script>
</head>
<body>

<jsp:include page="../include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<!-- 게시판 답변 -->
		<h2 class="form-signin-heading">답변하기</h2>
		<form class="form-wirte" action="boardReplyPro.bo" name="boardform" method="post">
		<input type="hidden" name="page" value="<%=nowPage %>" />
		<input type="hidden" name="BOARD_NUM" value="<%=article.getBOARD_NUM() %>">
		<input type="hidden" name="BOARD_RE_REF" value="<%=article.getBOARD_RE_REF() %>">
		<input type="hidden" name="BOARD_RE_LEV" value="<%=article.getBOARD_RE_LEV() %>">
		<input type="hidden" name="BOARD_RE_SEQ" value="<%=article.getBOARD_RE_SEQ() %>">
		<table class="table table-hover">
			<tr>
				<td class = "td-label"><label for = "BOARD_NAME">글쓴이</label></td>
				<td><input type="text" name="BOARD_NAME" id="BOARD_NAME"/></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_PASS">비밀번호</label></td>
				<td><input type="password" name="BOARD_PASS" id="BOARD_PASS" class="joinform-control" required=""></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_SUBJECT">제 목</label></td>
				<td><input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" class="joinform-control" required="required" /></td>
			</tr>
			<tr>
				<td class="td-label"><label for="BOARD_CONTENT">내 용</label></td>
				<td><textarea name="BOARD_CONTENT" id="BOARD_CONTENT" cols="40" rows="15" class="form-control" required="required"></textarea></td>
			</tr>
		</table>
				<a class="btn btn-default-reset" href="javascript:boardform.reset()" role="button">다시 작성</a>
				<a class="btn btn-default-submit" href="javascript:boardform.submit()" role="button">작성 하기</a>
		</form>
	</div>
	</div>
</body>
</html>