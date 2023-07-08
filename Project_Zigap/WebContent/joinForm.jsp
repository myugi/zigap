<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--     <link rel="icon" href="http://bootstrapk.com/favicon.ico"> -->
    <title>Member Join</title>
    
<script type="text/javascript">
	var idchk;

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
	
	function idCheck(){
		window.open('idCheck.jsp?openInit=true','','width=500,height=200');
	}
	
</script>

</head>
<body>

<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<form class="form-join" name="joinform" action="joinProcess.mem" method="post">
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Member Join</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="inputEmail">아이디 </label></td>
				<td><input type="id" name="id" id="id" class="joinform-control" placeholder="Enter your ID" required="" autofocus="">
					<input type="button" class="btn btn-primary btn-sm" name="idchk" id="idchk" value="아이디 중복확인"  onclick="idCheck()" /></td>
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
				<td><input type="text" name="name" id="name" class="joinform-control" placeholder="Name" required=""></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="email">이메일 주소 </label></td>
				<td><input type="text" name="email" id="email" class="joinform-control" placeholder="Email Address" /></td>
			</tr>
			<tr>
				<td><a class="btn btn-default-reset" href="javascript:joinform.reset()" role="button">다시 작성</a></td>
				<td><a class="btn btn-default-submit" href="javascript:passCheck(joinform)" role="button">회원 가입</a></td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
</html>