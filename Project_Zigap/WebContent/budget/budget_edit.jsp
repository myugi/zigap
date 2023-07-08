<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BudgetBean" %>
<%@ page import = "vo.CategoryBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	BudgetBean bud = (BudgetBean)request.getAttribute("budgetBean");
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/jquery-ui-1.8.2.custom.css" rel="stylesheet">
    
    <script src="js/ie-emulation-modes-warning.js.다운로드"></script>
    <script src="js/jquery.js.다운로드"></script>
    <script src="js/jquery.mtz.monthpicker.js"></script>
    
    <script type="text/javascript">
    
	    $(document).ready(function() {
	  	  $("#yyyymm").monthpicker(); 
	     });
    
    	function fnSave() {
    		$("#budgetform").submit();
    	}
    	
    	function fnDel() {
    		$("#status").val("D");
    		$("#budgetform").submit();
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
	<form class="form-join" id="budgetform" name="budgetform" action="budgetEditSave.bud" method="post">
		<input type="hidden" id="status" name="status" value="<%= request.getAttribute("status") %>" />
		<table class="table table-hover">
			<tr>
				<td colspan="2"><h2 class="form-signin-heading">Regist Budget</h2></td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="yyyymm">년/월 </label></td>
				<td><input type="text" name="yyyymm" id="yyyymm" class="joinform-control" placeholder="Year/Month" required="" value="<%=bud.getYYYYMM() %>"></td>
			</tr>
			<tr>
				<td class="td-label"><label for="incm_exp">수입/지출 구분 </label></td>
				<td>
					<select id="incm_exp" name="incm_exp" class="form-control" onchange="fnCategoryChange()">
					   <option value=""> - 선택 - </option>
	                   <option value="수입" <%= ("수입".equals(bud.getINCM_EXP()) ? "selected" : "" )  %> >수입</option>
	                   <option value="지출" <%= ("지출".equals(bud.getINCM_EXP()) ? "selected" : "" )  %>>지출</option>
               		</select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="category_content">카테고리 </label></td>
				<td>
					<select id="category_no" name="category_no" class="form-control">
					<%
						int category_no = bud.getCATEGORY_NO();
						for(int i=0; i<categoryList.size(); i++){
						%>
						<option value="<%=categoryList.get(i).getCATEGORY_NO() %>" <%=(categoryList.get(i).getCATEGORY_NO()==category_no ? "selected" : "" ) %> ><%=categoryList.get(i).getCATEGORY_CONTENT() %></option>
						<%
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td-label"><label id="lb" for="amt">예산 설정 금액 </label></td>
				<td><input type="text" name="amt" id="amt" class="joinform-control" placeholder="Budget Amt" required="" value="<%=bud.getAMT() %>"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: right">
					<c:if test="${status !='C'}">
						<a class="btn btn-default" href="javascript:fnDel()" role="button">예산 삭제</a>&nbsp;&nbsp;&nbsp;&nbsp;
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