<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.CategoryBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>
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

   ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("categoryList");

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
       
       function tableAppend(categoryNo, incmExp, categoryContent) {
          var html = '<tr id="categoryNo' + categoryNo + '">' +
                     '<td class="td-board"><font column="incmExp">' + incmExp + '</font></td>' +
                     '<td class="td-board"><font column="categoryContent">' + categoryContent + '</font></td>' +
                     '<td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen(\'categoryEdit.cate?category_no='+categoryNo+'\')">삭제</a></td>' +
                  '</tr>';
          
          $("#list").append(html);
       }
       
       function tableRemove(categoryNo) {
          $("#categoryNo" + categoryNo).remove();
       }

       
    </script>
    
</head>
<body>


<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

   <div class="container">
   <div class="form-place">
   <!--계좌 리스트 -->
   <br>
      <h2 class="form-signin-heading">Category List</h2>
   <br>
   
      <table id="list" class="table table-hover">
         <tr id = "tr_top">
            <td class="td-label" width=20%><label for="incm_exp">수입/지출 구분</label></td>
            <td class="td-label"><label for="category_content">카테고리</label></td>
            <td></td>
         </tr>
         <%
         if(categoryList != null){
         %>   
            <%
            for(int i=0; i<categoryList.size(); i++){
            %>
            <tr id="categoryNo<%=categoryList.get(i).getCATEGORY_NO()%>">
               <td class="td-board"><font column="incmExp"><%=categoryList.get(i).getINCM_EXP() %></font></td>
               <td class="td-board"><font column="categoryContent"><%=categoryList.get(i).getCATEGORY_CONTENT() %></font></td>
               <td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen('categoryEdit.cate?category_no=<%= categoryList.get(i).getCATEGORY_NO()%>')">삭제</a></td>
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
   <a href="#" onclick="modalOpen('categoryEdit.cate')" data-toggle="modal" class="btn btn-default-submit">새 카테고리 등록하기</a>
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