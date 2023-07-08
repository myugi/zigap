<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.BudgetBean" %>
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

   ArrayList<BudgetBean> budgetList = (ArrayList<BudgetBean>)request.getAttribute("budgetList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ZIGAP Board</title>

    <script src="js/jquery.mtz.monthpicker.js"></script>
    
    <style type="text/css">
       .td-board > font {
          -webkit-transition:background-color 1s, -webkit-transform 1s;
          transition:background-color 1s, transform 1s;
       }
    </style>
    
    <jsp:include page="../include_head.jsp" flush="ture"></jsp:include>
    
    <script type="text/javascript">
       
       // 페이지 호출이 완료되었을때 실행
       $(document).ready(function() {
    	  $("#yyyymm").monthpicker(); 
       });
       
       function modalOpen(url) {
          $("#modalIframe").attr("src", url);
          $('#myModal').modal();
       }
       
       function modalClose() {
          $('#myModal').modal('hide');
       }
       
       function tableAppend(yyyyMm, incmExp, categoryNo, categoryContent, amT) {
          var html = '<tr id="yyyyMm' + yyyyMm.replace(/\//gi, '') + '_' + categoryNo + '">' +
         			 '<td class="td-board"><font column="yyyyMm">' + yyyyMm + '</font></td>' +
                     '<td class="td-board"><font column="incmExp">' + incmExp + '</font></td>' +
                     '<td class="td-board"><font column="categoryContent">' + categoryContent + '</font></td>' +
                     '<td class="td-board"><font column="amT">' + amT + '</font></td>' +
                     '<td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen(\'budgetEdit.bud?yyyymm='+yyyyMm+'&categoryNo=' + categoryNo + '\')">삭제</a></td>' +
                  '</tr>';
          
          $("#list").append(html);
       }
       
       function tableRemove(yyyyMm, categoryNo) {
          $("#yyyyMm" + yyyyMm + "_" + categoryNo).remove();
       }

       function numberWithCommas(x) {
   	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
   	}
       
    </script>
    
</head>
<body>


<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

   <div class="container">
   <div class="form-place">
   <!--계좌 리스트 -->
   <br>
      <h2 class="form-signin-heading">Budget List</h2>
   <br>
  		<form class="form-search" name="searchForm" action="budgetList.bud">
			<select name="searchCombo">
			    <option value="yyyymm">년/월</option>
			</select>
			<div class="input-group input-group-sm">
			  <input type="text" id="search" name="search" class="form-control" aria-describedby="sizing-addon3">
			  <button class="btn btn-default-submit-search" type="submit">검색</button>
			  </div>
			  
		</form>
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label"><label for="yyyymm">날짜</label></td>
            <td class="td-label"><label for="incm_Exp">수입/지출</label></td>
            <td class="td-label"><label for="category_content">카테고리</label></td>
            <td class="td-label"><label for="amt">예산</label></td>
            <td></td>
         </tr>
         <%
         if(budgetList != null){
         %>   
            <%
            for(int i=0; i<budgetList.size(); i++){
            %>
            <tr id="yyyyMm<%=budgetList.get(i).getYYYYMM().replaceAll("/", "") + "_" + budgetList.get(i).getCATEGORY_NO() %>">
               <td class="td-board"><font column="yyyyMm"><%=budgetList.get(i).getYYYYMM().substring(0, 4)+"/"+budgetList.get(i).getYYYYMM().substring(4) %></font></td>
               <td class="td-board"><font column="incmExp"><%=budgetList.get(i).getINCM_EXP() %></font></td>
               <td class="td-board"><font column="categoryContent"><%=budgetList.get(i).getCATEGORY_CONTENT() %></font></td>
               <td class="td-board"><font column="amt"><%=StringUtil.numberFormat(budgetList.get(i).getAMT()) %></font></td>
               <td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen('budgetEdit.bud?yyyymm=<%= budgetList.get(i).getYYYYMM() %>&categoryNo=<%= budgetList.get(i).getCATEGORY_NO()%>')">삭제</a></td>
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
   <a href="#" onclick="modalOpen('budgetEdit.bud')" data-toggle="modal" class="btn btn-default-submit">예산 등록하기</a>
   </div>
   </div>
   
   <!-- Modal -->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog">
       <div class="modal-content">
         <iframe id="modalIframe" src="" height="500px" width="500px"></iframe>
       </div>
     </div>
   </div>
</body>
</html>