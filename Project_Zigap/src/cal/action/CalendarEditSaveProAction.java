package cal.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cal.svc.CalendarEditService;
import cal.svc.CalendarWriteProService;
import vo.Action;
import vo.ActionForward;
import vo.CalBean;

public class CalendarEditSaveProAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String status = request.getParameter("status");
		
		// 데이터조회
		String sequenceNo = request.getParameter("sequence_no");
		String id = (String)session.getAttribute("id");
		
		CalendarEditService calEditService = new CalendarEditService();
		
		// CUD처리
		if("C".equals(status)) {
			CalBean incmExpBean = new CalBean();
			CalendarWriteProService calWriteProService = new CalendarWriteProService();
			int max_count = calWriteProService.getMaxCount();
			incmExpBean.setSEQUENCE_NO(max_count);
			incmExpBean.setID((String)session.getAttribute("id"));
			incmExpBean.setINCM_EXP(request.getParameter("incm_exp"));
			incmExpBean.setCATEGORY_NO(Integer.parseInt(request.getParameter("category_no")));
			incmExpBean.setBACCT_NO(request.getParameter("bacct_no"));
			incmExpBean.setINCM_EXP_DATE(request.getParameter("incm_exp_date"));
			incmExpBean.setAMT(Integer.parseInt(request.getParameter("amt")));
			incmExpBean.setCOMMENT(request.getParameter("comment"));
			boolean isWriteSuccess = calWriteProService.registIncmExp(incmExpBean);
			
			if(!isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록완료')");
				out.println("parent.modalClose()");
				out.println("parent.fnSearch()");
				out.println("</script>");
			}
		} else if("U".equals(status)) {
			CalBean incmExpBean = new CalBean();
			incmExpBean.setSEQUENCE_NO(Integer.parseInt(request.getParameter("sequence_no")));
			incmExpBean.setID((String)session.getAttribute("id"));
			incmExpBean.setINCM_EXP(request.getParameter("incm_exp"));
			incmExpBean.setCATEGORY_NO(Integer.parseInt(request.getParameter("category_no")));
			incmExpBean.setBACCT_NO(request.getParameter("bacct_no"));
			incmExpBean.setINCM_EXP_DATE(request.getParameter("incm_exp_date"));
			incmExpBean.setAMT(Integer.parseInt(request.getParameter("amt")));
			incmExpBean.setCOMMENT(request.getParameter("comment"));
			boolean isUpdateSuccess = calEditService.updateIncmExp(incmExpBean);
			String dt = request.getParameter("incm_exp_date");
			String incmExp = request.getParameter("incm_exp");
			
			if(!isUpdateSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정완료')");
				out.println("parent.modalClose()");
				out.println("parent.fnTableListSearch(" + "'" + dt + "'" + ", '" + incmExp + "'" + ")");
				out.println("parent.fnSearch()");
				out.println("</script>");
			}
		} else if("D".equals(status)) {
			// 삭제
			boolean isWriteSuccess = calEditService.deleteCategory(id, sequenceNo);
			String dt = request.getParameter("dt");
			String incmExp = request.getParameter("incmExp");
			
			if(isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제완료')");
				out.println("parent.modalClose()");
				out.println("parent.fnTableListSearch(" + "'" + dt + "'" + ", '" + incmExp + "'" + ")");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제중 에러')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			forward = new ActionForward();
			forward.setPath("/calendar/calendarForm.jsp");
		}
		
		return forward;
		
	}
}
