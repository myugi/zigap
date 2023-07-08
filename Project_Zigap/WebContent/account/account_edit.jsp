<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "vo.AccountBean" %>
<%
AccountBean ab = (AccountBean)request.getAttribute("accountBean");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/ie-emulation-modes-warning.js.다운로드"></script>
    <script src="js/jquery.js.다운로드"></script>
    
    <script type="text/javascript">
    	function fnSave() {
    		$("#accountform").submit();
    	}
    	
    	function fnDel() {
    		$("#status").val("D");
    		$("#accountform").submit();
    	}
    	
    	function modalClose() {
    		parent.modalClose();
    	}
    </script>
</head>
<body>
	<form class="form-join" id="accountform" name="accountform" action="accountEditSave.acct" method="post">
		<input type="hidden" id="status" name="status" value="<%= request.getAttribute("status") %>" />
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Regist Account</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="bank_name">은행명 </label></td>
				<td><input type="text" name="bank_name" id="bank_name" class="joinform-control" placeholder="Bank Name" required="" autofocus="" value="<%= ab.getBANK_NAME() %>"></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="bacct_no">계좌번호 </label></td>
				<td><input type="text" name="bacct_no" id="bacct_no" class="joinform-control" placeholder="Account Number" required="" value="<%= ab.getBACCT_NO() %>"></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="bacct_comment">계좌설명 </label></td>
				<td><input type="text" name="bacct_comment" id="bacct_comment" class="joinform-control" placeholder="Comment" required="" value="<%= ab.getBACCT_COMMENT() %>"></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="base_amt">초기잔액 </label></td>
				<td><input type="text" name="base_amt" id="base_amt" class="joinform-control" placeholder="Base Balance" required="" value="<%= ab.getBASE_AMT() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right">
					<a class="btn btn-default" href="javascript:fnDel()" role="button">계좌 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="btn btn-default" href="javascript:fnSave()" role="button">확인</a>
					<a class="btn btn-default" href="javascript:modalClose()" role="button">취소</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>