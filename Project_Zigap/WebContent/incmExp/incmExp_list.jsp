<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.UseIncmExpBean" %>
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

   ArrayList<UseIncmExpBean> incmExpList = (ArrayList<UseIncmExpBean>)request.getAttribute("incmExpList");
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
 
       function modalOpen(url) {
          $("#modalIframe").attr("src", url);
          $('#myModal').modal();
       }
       
       function modalClose() {
          $('#myModal').modal('hide');
       }
       
       function tableAppend(sequenceNo, incmExp, categoryContent, bankName, bacctNo, useDate, useAmt, comment) {
          var html = '<tr id="sequenceNo' + sequenceNo + '">' +
                     '<td class="td-board"><font column="incmExp">' + incmExp + '</font></td>' +
                     '<td class="td-board"><font column="categoryContent">' + categoryContent + '</font></td>' +
                     '<td class="td-board"><font column="bankName">' + bankName + '</font></td>' +
                     '<td class="td-board"><font column="bacctNo">' + bacctNo + '</font></td>' +
                     '<td class="td-board"><font column="useDate">' + useDate + '</font></td>' +
                     '<td class="td-board"><font column="useAmt">' + useAmt + '</font></td>' +
                     '<td class="td-board"><font column="comment">' + comment + '</font></td>' +
                     '<td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen(\'incmExpEdit.inex?sequence_no='+sequenceNo+'\')">수정/삭제</a></td>' +
                  '</tr>';
          
          $("#list").append(html);
       }
       
       function tableUpdate(sequenceNo, incmExp, categoryContent, bankName, bacctNo, useDate, useAmt, comment) {
           var id = "#sequenceNo" + sequenceNo;
           
           $(id).find("font[column='incmExp']").html(incmExp);
           $(id).find("font[column='categoryContent']").html(categoryContent);
           $(id).find("font[column='bankName']").html(bankName);
           $(id).find("font[column='bacctNo']").html(bacctNo);
           $(id).find("font[column='useDate']").html(useDate);
           $(id).find("font[column='useAmt']").html(useAmt);
           $(id).find("font[column='comment']").html(comment);
           
           $(id).find(".td-board > font").css({"background-color":"#c0c2ff"});
           setTimeout(function(){
              $(id).find(".td-board > font").css({"background-color":""});
           }, 1000);
           
           console.log();
           
        }
       
       function tableRemove(sequenceNo) {
          $("#sequenceNo" + sequenceNo).remove();
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
      <h2 class="form-signin-heading">Income/Expense List</h2>
   <br>
   
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label"><label for="incmExp">수입/지출</label></td>
            <td class="td-label"><label for="categoryContent">카테고리</label></td>
            <td class="td-label"><label for="bankName">은행명</label></td>
            <td class="td-label"><label for="bacctno">계좌 번호</label></td>
            <td class="td-label"><label for="useDate">예정일</label></td>
            <td class="td-label"><label for="useAmt">예정금액</label></td>
            <td class="td-label"><label for="comment">비고</label></td>
            <td></td>
         </tr>
         <%
         if(incmExpList != null){
         %>   
            <%
            for(int i=0; i<incmExpList.size(); i++){
            %>
            <tr id="sequenceNo<%=incmExpList.get(i).getSEQUENCE_NO()%>">
               <td class="td-board"><font column="incmExp"><%=incmExpList.get(i).getINCM_EXP() %></font></td>
               <td class="td-board"><font column="categoryContent"><%=incmExpList.get(i).getCATEGORY_CONTENT() %></font></td>
               <td class="td-board"><font column="bankName"><%=incmExpList.get(i).getBANK_NAME() %></font></td>
               <td class="td-board"><font column="bacctNo"><%=incmExpList.get(i).getBACCT_NO() %></font></td>
               <td class="td-board"><font column="useDate"><%=incmExpList.get(i).getUSE_DATE() %></font></td>
               <td class="td-board"><font column="useAmt"><%=StringUtil.numberFormat(incmExpList.get(i).getUSE_AMT()) %></font></td>
               <td class="td-board"><font column="comment"><%=incmExpList.get(i).getCOMMENT() %></font></td>
               <td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen('incmExpEdit.inex?sequence_no=<%= incmExpList.get(i).getSEQUENCE_NO() %>')">수정/삭제</a></td>
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
   <a href="#" onclick="modalOpen('incmExpEdit.inex')" data-toggle="modal" class="btn btn-default-submit">고정수입/지출 등록하기</a>
   </div>
   </div>
   
   <!-- Modal -->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
     <div class="modal-dialog">
       <div class="modal-content">
         <iframe id="modalIframe" src="" height="600px" width="500px"></iframe>
       </div>
     </div>
   </div>
</body>
</html>