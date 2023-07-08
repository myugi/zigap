<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--     <link rel="icon" href="http://bootstrapk.com/favicon.ico"> -->
    <title>Signin Template for Bootstrap</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
    
  </head>
  <body>
  
<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>
  
    <div class="container">
	<div class="form-place">
      <form class="form-signin" name="loginForm" action="loginProAction.log" method="post">
        <h2 class="form-signin-heading">Member Login</h2>
        
        <label for="inputEmail" class="sr-only">ID</label>
        <input type="id" name="id" id="id" class="form-control" placeholder="Enter your ID" required="" autofocus="">
        
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="pass" id="pass" class="form-control" placeholder="Password" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
        <h5 class="join-member"><a href="joinForm.jsp">회원가입</a></h5>
      </form>
	</div>
    </div> <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!--     <script src="js/ie10-viewport-bug-workaround.js.다운로드"></script> -->
</body></html>