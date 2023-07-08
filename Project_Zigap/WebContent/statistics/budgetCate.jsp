<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.StatisticsBean" %>
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

   String amtNm = "";
   String incmExp = request.getParameter("incmExp");
   if("수입".equals(incmExp)) {
	   amtNm = "수입금액";
   }else {
	   amtNm = "지출금액";
   }
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

       google.charts.load("current", {packages:["corechart"]});
       google.charts.setOnLoadCallback(drawChart);
       function drawChart() {
         var data = google.visualization.arrayToDataTable([
           <%
             if(list != null){ %>
             ['카테고리', '금액'],
               <%for(int i=0; i<list.size(); i++){ %>
                 ['<%=list.get(i).getCATEGORY_CONTENT()%>', <%= list.get(i).getAMT()%> ]
                 <% if(i + 1 != list.size() ) {%>
                 ,
                 <% }%>
             <%}
             }
           %>
         ]);

         var options = {

         };

         var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
         chart.draw(data, options);
       }
       
    </script>
    
</head>
<body>


<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

   <div class="container">
   <div class="form-place">
   <!--계좌 리스트 -->
   <br>
      <h2 class="form-signin-heading">카테고리별 <%=incmExp%> 현황</h2>
   <br>
  		<form class="form-search" name="searchForm" action="budgetcate.stat">
  			<input type="hidden" id="incmExp" name="incmExp" value="<%= incmExp %>" />
			<select name="searchCombo">
			    <option value="yyyymm">년/월</option>
			</select>
			<div class="input-group input-group-sm">
			  <input type="text" id="date" name="date" class="form-control" aria-describedby="sizing-addon3" value="<%= request.getAttribute("date") %>">
			  <button class="btn btn-default-submit-search" type="submit">검색</button>
			  </div>
			  
		</form>
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label"><label>카테고리명</label></td>
            <td class="td-label"><label>수입/지출</label></td>
            <td class="td-label"><label><%=amtNm%></label></td>
            <td></td>
         </tr>
         <%
         if(list != null){
         %>   
            <%
            for(int i=0; i<list.size(); i++){
            %>
            <tr>
               <td class="td-board"><font><%=list.get(i).getCATEGORY_CONTENT() %></font></td>
               <td class="td-board"><font><%=list.get(i).getINCM_EXP() %></font></td>
               <td class="td-board"><font><%=StringUtil.numberFormat(list.get(i).getAMT()) %></font></td>
            </tr>
         <%} %>
      </table>
	
		<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
	
   <%
   }
   else
   {
   %>
      <tr>
         <td colspan="3" class="td-board">등록된 카테고리가 없습니다.</td>
      </tr>
   <%
   }
   %>
</body>
</html>