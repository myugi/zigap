<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "util.StringUtil" %>
<%

   String id = null;
   if(session.getAttribute("id")==null){
      out.println("<script>");
      out.println("alert('로그인이 필요한 서비스입니다.')");
      out.println("location.href='loginForm.jsp'");
      out.println("</script>");
   }else{
      id = (String)session.getAttribute("id");
   }
   
   ArrayList<StatisticsBean> list = (ArrayList<StatisticsBean>)request.getAttribute("list");
   ArrayList<AccountBean> accountList = (ArrayList<AccountBean>)request.getAttribute("accountList");
   
   String bacctNo = request.getParameter("bacctNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ZIGAP Board</title>

	<jsp:include page="../include_head.jsp" flush="ture"></jsp:include>

    <script src="js/jquery.mtz.monthpicker.js"></script>
    
    <style type="text/css">
       .td-board > font {
          -webkit-transition:background-color 1s, -webkit-transform 1s;
          transition:background-color 1s, transform 1s;
       }
    </style>
    
    <script type="text/javascript">
       
       // 페이지 호출이 완료되었을때 실행
       $(document).ready(function() {
    	  $("#date").monthpicker(); 
       });
       
    </script>
    
</head>
<body>


<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

   <div class="container">
   <div class="form-place">
   <!--계좌 리스트 -->
   <br>
      <h2 class="form-signin-heading">계좌별 조회</h2>
   <br>
  		<form name="searchForm" action="account.stat" style="width:100%; margin-bottom: 5px;">
			<div class="input-group input-group-sm" style="width:100%;">
			  <button class="btn btn-default-submit-search" type="submit">검색</button>
			  <select id="bacctNo" name="bacctNo" class="form-control" style="width:200px;float: right;margin-left: 3px;">
				<%
					for(int i=0; i<accountList.size(); i++){
					%>
					<option value="<%=accountList.get(i).getBACCT_NO() %>" <%=(accountList.get(i).getBACCT_NO().equals(bacctNo) ? "selected" : "" ) %>><%=accountList.get(i).getBANK_NAME() %>&nbsp;<%=accountList.get(i).getBACCT_NO() %></option>
					<%
					}
				%>
				</select>
			  
			  </div>
			  
		</form>
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label"><label>일자</label></td>
            <td class="td-label"><label>카테고리명</label></td>
            <td class="td-label"><label>내용</label></td>
            <td class="td-label"><label>입금</label></td>
            <td class="td-label"><label>출금</label></td>
            <td class="td-label"><label>잔액</label></td>
            <td></td>
         </tr>
         <%
         if(list != null){
         %>   
            <%
            for(int i=0; i<list.size(); i++){
            %>
            <tr>
               <td class="td-board"><font><%=StringUtil.getDateFormat(list.get(i).getINCM_EXP_DATE()) %></font></td>
               <td class="td-board"><font><%=list.get(i).getCATEGORY_CONTENT() %></font></td>
               <td class="td-board"><font><%=list.get(i).getCOMMENT() %></font></td>
               <td class="td-board"><font><%=StringUtil.numberFormat(list.get(i).getINCM_AMT()) %></font></td>
               <td class="td-board"><font><%=StringUtil.numberFormat(list.get(i).getEXP_AMT()) %></font></td>
               <td class="td-board"><font><%=StringUtil.numberFormat(list.get(i).getJAN_AMT()) %></font></td>
            </tr>
         <%} %>
      </table>

   <%
   }
   else
   {
   %>
      <tr>
         <td colspan=4 class="td-board">등록된 카테고리가 없습니다.</td>
      </tr>
   <%
   }
   %>
</body>
</html>