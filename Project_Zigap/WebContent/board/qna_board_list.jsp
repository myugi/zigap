<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "vo.PageInfo" %>
<%@ page import = "vo.BoardBean" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<%
	ArrayList<BoardBean> articleList = (ArrayList<BoardBean>)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount = pageInfo.getListCount();
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZIGAP Board</title>
<jsp:include page="../include_head.jsp" flush="ture"></jsp:include>
    
</head>
<script>
 function checkCombo(bs, bn){
 	var target = document.getElementById("searchCombo");
 	bs = target.options[target.selectedIndex].value;
	f.submit();
}
</script>
<body>


<jsp:include page="../menu_top.jsp" flush="true"></jsp:include>

	<div class="container">
	<div class="form-place">
	<!--게시판 리스트 -->
	<br>
		<h2 class="form-signin-heading">Q & A</h2>
		<form class="form-search" name="searchForm" action="boardList.bo">
			<select name="searchCombo">
			    <option value="BOARD_SUBJECT">글제목</option>
			    <option value="BOARD_NAME">작성자</option>
			</select>
			<div class="input-group input-group-sm">
			  <input type="text" id="search" name="search" class="form-control" aria-describedby="sizing-addon3">
			  <button class="btn btn-default-submit-search" type="submit">검색</button>
			  </div>
			  
		</form>
		
		<br>
		
		<table class="table table-hover">
				<tr id = "tr_top">
					<td class="td-label"><label for="board_num">번호</label></td>
					<td class="td-label"><label for="board_subject">제목</label></td>
					<td class="td-label"><label for="board_name">작성자</label></td>
					<td class="td-label"><label for="board_date">날짜</label></td>
					<td class="td-label"><label for="board_readcount">조회수</label></td>
				</tr>
			<%
			if(articleList != null && listCount > 0){
			%>	
				<%
				for(int i=0; i<articleList.size(); i++){
				%>
				<tr>
					<td class="td-board">
					<%=articleList.get(i).getBOARD_NUM() %></td>
					
					<td class="td-board">
						<%if(articleList.get(i).getBOARD_RE_LEV()!=0){ %>
						<%for(int a=0; a<articleList.get(i).getBOARD_RE_LEV()*2; a++){ %>
						&nbsp;
						<%} %>&nbsp;&nbsp; ↳
						<%}%>
						
					<a href = "boardDetail.bo?board_num=<%=articleList.get(i).getBOARD_NUM() %>&page=<%=nowPage %>">
					<%=articleList.get(i).getBOARD_SUBJECT() %>
					</a>
				</td>
				<td class="td-board"><%=articleList.get(i).getBOARD_NAME() %></td>
				<td class="td-board"><%=articleList.get(i).getBOARD_DATE() %></td>
				<td class="td-board"><%=articleList.get(i).getBOARD_READCOUNT() %></td>
			</tr>
			<%} %>
		</table>
		<%if(nowPage<=1){ %>
			[이전]&nbsp;
		<%}else{ %>
			<a href = "boardList.bo?page=<%=nowPage-1 %>">[이전]</a>&nbsp;
		<%} %>
		
		<%for(int a=startPage;a<=endPage;a++){
			if(a==nowPage){ %>
				[<%=a %>]
			<%}else{ %>
				<a href = "boardList.bo?page=<%=a %>">[<%=a %>]</a>&nbsp;
			<%} %>
		<%} %>
		<%if(nowPage>=maxPage){ %>
			[다음]
		<%}else{ %>
			<a href="boardList.bo?page=<%=nowPage+1 %>">[다음]</a>
		<%} %>
	<%
	}
	else
	{
	%>
		등록된 글이 없습니다.
	<%
	}
	%>
	<a class="btn btn-default-submit" href="boardWriteForm.bo" role="button">문의하기</a>
	</div>
	</div>
	<jsp:include page="../footer.jsp" flush="false"></jsp:include>
</body>
</html>