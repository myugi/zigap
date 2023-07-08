<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
%>
<!DOCTYPE html>

<jsp:include page="include_head.jsp" flush="ture"></jsp:include>
<jsp:include page="menu_top.jsp" flush="true"></jsp:include>

<html>
<head>
<meta charset='utf-8' />
<!-- css -->
<link href='resource/css/fullcalendar.css' rel='stylesheet' />
<link href='resource/css/fullcalendar.min.css' rel='stylesheet' />
<link href='resource/css/fullcalendar.print.css' rel='stylesheet' media='print' />

<!-- javascript -->
<script src='resource/lib/moment.min.js'></script>
<script src='resource/js/fullcalendar.js'></script>

</head>

<script>
 	function modalOpen(url) {
	    $("#modalIframe").attr("src", url);
	    $('#myModal').modal();
	 }

 	$(document).ready(function() {
      
      $('#calendar').fullCalendar({
    	 //윗부분 메뉴바
         header: {
            left: 'prev,next today',
            center: 'title',
            right: ''
         },
         
         //날짜를 눌렀을 때 모달팝업창 띄우기
         dayClick: function(date){
        	 modalOpen('calendarEdit.cal?date='+date.format());
         },
         
         //캘린더의 데이터를 클릭했을 때 테이블 생성하기
         eventClick: function(info) {
       	 	
        	 var dt = info.start.format();
        	 var incmExp = info.incmExp;
        	 
        	 fnTableListSearch(dt, incmExp);
         },
         
         //기준이 되는 날
         defaultDate: getToday(),
         editable: true,
         eventLimit: true, // allow "more" link when too many events
         
         //수입/지출 입력하기
         events: function(start, end, timezone, callback) {
          	 //url: 'selectCalendar.cal?yyyymm=201903'
	        var start = start.format().replace(/-/gi, "");
	        var end = end.format().replace(/-/gi, "");
			$.ajax ({ 
 				type: "POST", 
 				contentType: "application/json; charset=utf-8", 
 				url: "selectCalendar.cal?start=" + start + "&end=" + end, 
 				dataType: 'json', 
 				success: function (data) { 
  					var events = []; 
    				$.each(data, function (index, value) { 
					     events.push({ 
					      title: value['title'], 
					      start: value['start'],
					      color: value['color'],
					      incmExp: value['INCM_EXP'],
					      test: 'test'
					      //all data 
					     }); 
    				});
  					callback(events); 
				}
			});
         }
      });
   });
   
   function modalClose() {
	   $('#myModal').modal('hide');
   }
   
   function createTable(data) {
	   $("#incmExpList").html("");
	   var table = "<h4>거래목록</h4>";
	       table += '<table id="list" class="table table-hover" style="width:100%">'
			      + '<tr id = "tr_top">'
			      + '   <td class="td-label"><label >날짜</label></td>'
			      + '   <td class="td-label"><label >수지구분</label></td>'
			      + '   <td class="td-label"><label >카테고리</label></td>'
			      + '   <td class="td-label"><label >금액</label></td>'
			      + '   <td class="td-label"><label >거래은행</label></td>'
			      + '   <td class="td-label"><label >계좌번호</label></td>'
			      + '   <td class="td-label"><label >비 고</label></td>'
			      + '   <td></td>'
			      + '</tr>';
	  
	  for(var i = 0; i < data.length; i++) {
		  var row = data[i];
		  
		  table +=	'<tr>' +
			          '<td class="td-board"><font>' + row.INCM_EXP_DATE + '</font></td>' +
			          '<td class="td-board"><font>' + row.INCM_EXP + '</font></td>' +
			          '<td class="td-board"><font>' + row.CATEGORY_CONTENT + '</font></td>' +
			          '<td class="td-board"><font>' + numberWithCommas(row.AMT) + '</font></td>' +
			          '<td class="td-board"><font>' + row.BANK_NAME + '</font></td>' +
			          '<td class="td-board"><font>' + row.BACCT_NO + '</font></td>' +
			          '<td class="td-board"><font>' + row.COMMENT + '</font></td>' +
			          '<td class="td-board-modi"><a data-toggle="modal" class=" modal2" onclick="modalOpen(\'calendarEdit.cal?sequence_no=' + row.SEQUENCE_NO + '\')">수정/삭제</a></td>' + 
			       '</tr>';
	  }
	  
	  table += "</table>";
	  
	  $("#incmExpList").html(table);
   }
   
   function getToday() {
	   var today = new Date();
	   var dd = today.getDate();
	   var mm = today.getMonth()+1; //January is 0!
	   var yyyy = today.getFullYear();

	   if(dd<10) {
	       dd='0'+dd
	   } 

	   if(mm<10) {
	       mm='0'+mm
	   } 

	   return mm+'-'+dd+'-'+yyyy;
   }
   
   function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
   
   function fnSearch() {
	   $("#calendar").fullCalendar ('refetchEvents');
	}
	
   function fnTableListSearch(dt,incmExp){
	   $.ajax ({ 
				type: "POST", 
				contentType: "application/json; charset=utf-8", 
				url: "selectDateList.cal?dt=" + dt + "&incmExp=" + incmExp, 
				dataType: 'json', 
				success: function (data) { 
					
					createTable(data);
			}
		});
   }
   </script>
<body style="text-align: center;">

<div id='calendar' style="max-width: 1000px; display: inline-block; width:100%;">
</div>
<div id="incmExpList" style="max-width: 1000px; display: inline-block; width:100%;">
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
