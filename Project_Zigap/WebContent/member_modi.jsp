<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.MemberBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%

	String id = null;
	if(session.getAttribute("id")==null){
		out.println("<script>");
		out.println("alert('로그인 해주세요!')");
		out.println("location.href='loginForm.jsp'");
		out.println("</script>");
	}else if(session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
	}
	
	MemberBean member = (MemberBean)request.getAttribute("memberInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Member Modify</title>

	<script type="text/javascript">
		function passCheck(form) {
			if (form.pass.value.trim() == "") {
				alert("비밀번호를 입력하세요!");
				form.pass.focus();
				return false;
			}
			if (form.pass.value.trim() != form.passco.value.trim()) {
				alert("비밀번호가 일치하지 않습니다!");
				form.pass.value = "";
				form.passco.value = "";
				form.pass.focus();
				return false;
			}
			form.submit();
		}	
</script>

</head>

<body>

<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<form name="modiform" action ="memberModifyPro.mem" method="post">
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Member Information</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="inputEmail">아이디 </label></td>
				<td class="td-label"><label for="inputEmail"><%=member.getId() %> </label></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="pass">비밀번호 </label></td>
				<td><input type="password" name="pass" id="pass" class="joinform-control" placeholder="Password" required=""></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="passco">비밀번호 확인 </label></td>
				<td><input type="password" name="passco" id="passco" class="joinform-control" placeholder="Password" required=""></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="name">이름 </label></td>
				<td><input type="text" name="name" id="name" class="joinform-control" value=<%=member.getName() %> required=""></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="email">이메일 주소 </label></td>
				<td><input type="text" name="email" id="email" class="joinform-control" value=<%=member.getEmail() %> /></td>
			</tr>
			<tr>
				<td><a class="btn btn-default-reset" href="javascript:modiform.reset()" role="button">다시 작성</a></td>
				<td><a class="btn btn-default-submit" href="javascript:passCheck(modiform)" role="button">수정 완료</a></td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
</html>