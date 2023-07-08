<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.UseIncmExpBean" %>
<%@ page import = "vo.CategoryBean" %>
<%@ page import = "vo.AccountBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	UseIncmExpBean inex = (UseIncmExpBean)request.getAttribute("incmExpBean");
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
	ArrayList<AccountBean> accountList = (ArrayList<AccountBean>)request.getAttribute("accountList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/jquery-ui-1.8.2.custom.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">


	<script src="js/jquery.js.다운로드"></script>
    <script src="js/jquery-3.2.1.js"></script>
	<script src='js/jquery-1.8.3.js'></script>
	<script src="js/bootstrap.min.js"></script>

    
    <script type="text/javascript">
    
    	function fnSave() {
    		$("#inexform").submit();
    	}
    	
    	function fnDel() {
    		$("#status").val("D");
    		$("#inexform").submit();
    	}
    	
    	function modalClose() {
    		parent.modalClose();
    	}
    	

    	function fnCategoryChange() {
    		var incmExp = $("#incm_exp").val();
    		
    		$.ajax ({ 
  				type: "POST", 
  				contentType: "application/json; charset=utf-8", 
  				url: "selectCategoryList.cate?incmExp=" + incmExp, 
  				dataType: 'json', 
  				success: function (data) { 
   					
  					console.log(data);
  					
  					$("#category_no").html("");
  					var html = "";
  					for(var i = 0; i < data.length; i++) {
  						var row = data[i];
  						
  						html += "<option value='" + row.CATEGORY_NO + "'>" + row.CATEGORY_CONTENT + "</option>"
  					}
  					
  					$("#category_no").html(html);
 				}
 			});
    	}
    </script>
</head>
<body>
	<form class="form-join" id="inexform" name="inexform" action="incmExpEditSave.inex" method="post">
		<input type="hidden" id="status" name="status" value="<%= request.getAttribute("status") %>" />
		<input type="hidden" id="sequence_no" name="sequence_no" value="<%= inex.getSEQUENCE_NO() %>" />
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Regist Income/Expense</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label for="incm_exp">수입/지출 구분 </label></td>
				<td>
					<select id="incm_exp" name="incm_exp" class="form-control" onchange="fnCategoryChange()">
					   <option value=""> - 선택 - </option>
	                   <option value="수입" <%= ("수입".equals(inex.getINCM_EXP()) ? "selected" : "" )  %> >수입</option>
	                   <option value="지출" <%= ("지출".equals(inex.getINCM_EXP()) ? "selected" : "" )  %>>지출</option>
               		</select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="category_content">카테고리 </label></td>
				<td>
					<select id="category_no" name="category_no" class="form-control">
					<%
						for(int i=0; i<categoryList.size(); i++){
						%>
						<option value="<%=categoryList.get(i).getCATEGORY_NO() %>" <%=(categoryList.get(i).getCATEGORY_NO()==inex.getCATEGORY_NO() ? "selected" : "" ) %> ><%=categoryList.get(i).getCATEGORY_CONTENT() %></option>
						<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="bacct_no">계좌번호 </label></td>
				<td>
					<select id="bacct_no" name="bacct_no" class="form-control">
					<%
						for(int i=0; i<accountList.size(); i++){
						%>
						<option value="<%=accountList.get(i).getBACCT_NO() %>"  <%=(accountList.get(i).getBACCT_NO().equals(inex.getBACCT_NO()) ? "selected" : "" ) %> ><%=accountList.get(i).getBANK_NAME() %>&nbsp;<%=accountList.get(i).getBACCT_NO() %></option>
						<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="use_date">예정일 </label></td>
				<td><input type="text" name="use_date" id="use_date" class="joinform-control" placeholder="Use Date" required="" value="<%=inex.getUSE_DATE() %>"> ex)10일인 경우 -> 10 </td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="use_amt">예정 금액 </label></td>
				<td><input type="text" name="use_amt" id="use_amt" class="joinform-control" placeholder="Use Amt" required="" value="<%=inex.getUSE_AMT() %>"></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="comment">비고 </label></td>
				<td><input type="text" name="comment" id="comment" class="joinform-control" placeholder="Comment" required="" value="<%=inex.getCOMMENT() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right">
					<c:if test="${status =='U'}">
						<a class="btn btn-default" href="javascript:fnDel()" role="button">삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
						<a class="btn btn-default" href="javascript:fnSave()" role="button">확인</a>
					<a class="btn btn-default" href="javascript:modalClose()" role="button">취소</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>