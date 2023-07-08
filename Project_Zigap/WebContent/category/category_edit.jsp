<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.CategoryBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
CategoryBean ca = (CategoryBean)request.getAttribute("categoryBean");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/ie-emulation-modes-warning.js.다운로드"></script>
    <script src="js/jquery.js.다운로드"></script>
    
    <script type="text/javascript">
    	function fnSave() {
    		$("#categoryform").submit();
    	}
    	
    	function fnDel() {
    		$("#status").val("D");
    		$("#categoryform").submit();
    	}
    	
    	function modalClose() {
    		parent.modalClose();
    	}
    </script>
</head>
<body>
	<form class="form-join" id="categoryform" name="categoryform" action="categoryEditSave.cate" method="post">
		<input type="hidden" id="status" name="status" value="<%= request.getAttribute("status") %>" />
		<input type="hidden" id="category_no" name="category_no" value="<%= ca.getCATEGORY_NO() %>" />
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Regist Category</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="incm_exp">수입/지출 구분 </label></td>
				<td>
					<select id="incm_exp" name="incm_exp" class="form-control">
                   <option value="수입" <%= ("수입".equals(ca.getINCM_EXP()) ? "selected" : "" )  %> >수입</option>
                   <option value="지출" <%= ("지출".equals(ca.getINCM_EXP()) ? "selected" : "" )  %>>지출</option>
               </select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="category_content">카테고리 </label></td>
				<td><input type="text" name="category_content" id="category_content" class="joinform-control" placeholder="Category Content" required="" value="<%=ca.getCATEGORY_CONTENT() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right">
					<c:if test="${status !='C'}">
						<a class="btn btn-default" href="javascript:fnDel()" role="button">카테고리 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<c:if test="${status != 'U'}">
						<a class="btn btn-default" href="javascript:fnSave()" role="button">확인</a>
					</c:if>
					<a class="btn btn-default" href="javascript:modalClose()" role="button">취소</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>