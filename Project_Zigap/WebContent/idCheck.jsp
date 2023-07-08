<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String openInit = "false";
	if(request.getParameter("openInit")!=null)
		openInit="true";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function init(){
		if(${(param.openInit=="true")?true:false}){
			document.getElementById("id").value = opener.document.getElementById("id").value;
			var form = document.f;
			form.submit();
		}
	}
	function ok(v) {
		opener.idcheck=v.trim();
		opener.document.getElementById("id").value=v;
		opener.chkId=true;
		window.close();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ID Check</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<!-- css -->
	<link rel="shortcut icon" href="../favicon.ico">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jquery.fancybox.css" rel="stylesheet">
	<link href="css/jcarousel.css" rel="stylesheet">
	<link href="css/flexslider.css" rel="stylesheet">
	<link href="css/style(1).css" rel="stylesheet">

	<!-- Theme skin -->
	<link href="css/default.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
    <script src="js/ie-emulation-modes-warning.js.다운로드"></script>
</head>
<body onload="init()">
<div class="container">
<div class="idcheck-place">
	<form class="form-signin" action="idCheckPro.mem" method="post" name=f>
	<input class="joinform-control" type="text" name="id" id="id" >
	<button class="btn btn-primary btn-sm" type="submit">중복확인</button>
	<hr>
	</form>
</div>
</div>
<c:if test="${passibleID != null}">
<c:choose>
	<c:when test="${passibleID}">
		<h4 class="idcheck_h4">${id} 는 사용가능한 아이디입니다.
		<a class="btn btn-default-idcheck" href="#" onclick="ok('${id}')">사용하기</a>
		</h4>
	</c:when>
	<c:otherwise><h4 class="idcheck_h4">${id} 는 사용할 수 없는 아이디입니다.</h4></c:otherwise>
</c:choose>
</c:if>
</body>
</html>