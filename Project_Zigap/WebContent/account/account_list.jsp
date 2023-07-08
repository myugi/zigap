<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.PageInfo" %>
<%@ page import = "vo.AccountBean" %>
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

   ArrayList<AccountBean> accountList = (ArrayList<AccountBean>)request.getAttribute("accountList");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ZIGAP Board</title>
    
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
          
          
       });
       
       function modalOpen(url) {
          $("#modalIframe").attr("src", url);
          $('#myModal').modal();
       }
       
       function modalClose() {
          $('#myModal').modal('hide');
       }
       
       function tableAppend(bankNm, bacctNo, bacctComment, baseAmt) {
          var html = '<tr id="bacctNo' + bacctNo + '">' +
                     '<td class="td-board"><font column="bankName">' + bankNm + '</font></td>' +
                     '<td class="td-board"><font column="bacctNo">' + bacctNo + '</font></td>' +
                     '<td class="td-board"><font column="bacctComment">' + bacctComment + '</font></td>' +
                     '<td class="td-board"><font column="baseAmt">' + baseAmt + '</font></td>' +
                     '<td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen(\'accountEdit.acct?bacct_no=' + bacctNo +  '\')">수정/삭제</a></td>' +
                  '</tr>';
          
          $("#list").append(html);
       }
       
       function tableUpdate(bankNm, bacctNo, bacctComment, baseAmt) {
          var id = "#bacctNo" + bacctNo;
          
          $(id).find("font[column='bankName']").html(bankNm);
          $(id).find("font[column='bacctNo']").html(bacctNo);
          $(id).find("font[column='bacctComment']").html(bacctComment);
          $(id).find("font[column='baseAmt']").html(baseAmt);
          
          $(id).find(".td-board > font").css({"background-color":"#c0c2ff"});
          setTimeout(function(){
             $(id).find(".td-board > font").css({"background-color":""});
          }, 1000);
          
       }
       
       function tableRemove(bacctNo) {
          $("#bacctNo" + bacctNo).remove();
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
      <h2 class="form-signin-heading">Account List</h2>
   <br>
   
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label"><label for="board_num">은행명</label></td>
            <td class="td-label"><label for="board_subject">계좌 번호</label></td>
            <td class="td-label"><label for="board_name">계좌 설명</label></td>
            <td class="td-label"><label for="board_date">초기 잔액</label></td>
            <td></td>
         </tr>
         <%
         if(accountList != null){
         %>   
            <%
            for(int i=0; i<accountList.size(); i++){
            %>
            <tr id="bacctNo<%=accountList.get(i).getBACCT_NO()%>">
               <td class="td-board"><font column="bankName"><%=accountList.get(i).getBANK_NAME() %></font></td>
               <td class="td-board"><font column="bacctNo"><%=accountList.get(i).getBACCT_NO() %></font></td>
               <td class="td-board"><font column="bacctComment"><%=accountList.get(i).getBACCT_COMMENT() %></font></td>
               <td class="td-board"><font column="baseAmt"><%=StringUtil.numberFormat(accountList.get(i).getBASE_AMT()) %></font></td>
               <td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen('accountEdit.acct?bacct_no=<%= accountList.get(i).getBACCT_NO()%>')">수정/삭제</a></td>
            </tr>
         <%} %>
      </table>

   <%
   }
   else
   {
   %>
      <tr>
         <td colspan=4 class="td-board">등록된 계좌가 없습니다.</td>
      </tr>
   <%
   }
   %>
   <a href="#" onclick="modalOpen('accountEdit.acct')" data-toggle="modal" class="btn btn-default-submit">새 계좌 등록하기</a>
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