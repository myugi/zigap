<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.MemberBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%

	MemberBean memberInfo = (MemberBean)request.getAttribute("memberInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member Information</title>

</head>
<body>

<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<form class="form-join">
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Member Information</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="inputEmail">아이디 </label></td>
				<td class="td-label"><label for="inputEmail"><%=memberInfo.getId() %> </label></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="name">이름 </label></td>
				<td class="td-label"><label for="inputEmail"><%=memberInfo.getName() %> </label></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="email">이메일 주소 </label></td>
				<td class="td-label"><label for="inputEmail"><%=memberInfo.getEmail() %> </label></td>
			</tr>
			<tr>
				<td></td>
				<td><a class="btn btn-default-submit" href="memberModiForm.mem?id=<%=memberInfo.getId() %>" role="button">수정하기</a></td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
</html>