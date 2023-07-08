<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <div id="wrapper">
      <!-- start header -->
      <header>
         <div class="navbar navbar-default navbar-static-top">
            <div class="container">
               <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                  <a class="navbar-brand" href="index.html">
                  <img class="nav-logo" src="images/logoblack.png"></a>
               </div>
               <div class="navbar-collapse collapse ">
                  <ul class="nav navbar-mypage">
                     <li class="mypage"><a href="memberInfo.mem?id=<%=session.getAttribute("id") %>">MyPage</a></li>

                  </ul>
                  <ul class="nav navbar-mypage">
                     <li class="mypage">
                     <%if(session.getAttribute("id")==null){ %>
                     <a href="loginForm.jsp">Login</a>
                     <%}else{ %>
                     <a href="logOut.log">Logout</a>
                     <%} %></li>
                  </ul>
               </div>
            </div>
         <div class="container">
            <div class="navbar-collapse collapse ">
                  <ul class="nav navbar-nav">
                     <li class="active"><a href="main.jsp">Home</a></li>
                     <li class="dropdown">
                        <a href="https://bootstrapmade.com/demo/themes/Moderna/#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">제공 기능<b class=" icon-angle-down"></b></a>
                        <ul class="dropdown-menu">
                           <li><a href="main.jsp#account">계좌 관리</a></li>
                           <li><a href="main.jsp#budget">예산 관리</a></li>
                           <li><a href="main.jsp#incm_exp">수입/지출 관리</a></li>
                           <li><a href="main.jsp#list">다양한 내역</a></li>
                           <li><a href="main.jsp#statistics">통계 보기</a></li>
                        </ul>
                     </li>
                     <li class="dropdown">
                        <a href="https://bootstrapmade.com/demo/themes/Moderna/#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">자산 관리<b class=" icon-angle-down"></b></a>
                        <ul class="dropdown-menu">
                           <li><a href="accountList.acct">계좌 관리</a></li>
                           <li><a href="categoryList.cate">카테고리 관리</a></li>
                           <li><a href="budgetList.bud">예산 관리</a></li>
                           <li><a href="incmexpList.inex">고정 수입/지출 관리</a></li>
                           <li><a href="calendarForm.jsp">캘린더</a></li>
                        </ul>
                     </li>
                     <li class="dropdown">
                        <a href="https://bootstrapmade.com/demo/themes/Moderna/#" class="dropdown-toggle " data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false">통계<b class=" icon-angle-down"></b></a>
                        <ul class="dropdown-menu">
                        	<li role="presentation" class="dropdown-header">예산대비 수지현황</li>
	                           <li><a href="budget.stat?incmExp=수입">수입현황</a></li>
	                           <li><a href="budget.stat?incmExp=지출">지출현황</a></li>
	                        <li role="presentation" class="dropdown-header">전월대비 수지현황</li>
	                           <li><a href="premonth.stat?incmExp=수입">수입현황</a></li>
	                           <li><a href="premonth.stat?incmExp=지출">지출현황</a></li>
	                        <li role="presentation" class="dropdown-header">카테고리별 수지현황</li>
	                           <li><a href="budgetcate.stat?incmExp=수입">수입현황</a></li>
	                           <li><a href="budgetcate.stat?incmExp=지출">지출현황</a></li>
	                        <li role="presentation" class="dropdown-header">계좌별 조회</li>
	                           <li><a href="account.stat">계좌 조회</a></li>
                        </ul>
                     </li>
                     <li><a href="boardList.bo">Q&A</a></li>
                  </ul>
               </div>
            </div>
         </div>
      </header>
   </div>
