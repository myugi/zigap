package budget.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import budget.svc.BudgetEditService;
import budget.svc.BudgetWriteProService;
import category.svc.CategoryListService;
import vo.Action;
import vo.ActionForward;
import vo.BudgetBean;

public class BudgetEditSaveProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		// 데이터조회
		String	yyyymm = request.getParameter("yyyymm");
		String id = (String)session.getAttribute("id");
		String status = request.getParameter("status");
		int	categoryNo = Integer.parseInt(request.getParameter("category_no"));
		
		BudgetEditService budgetEditService = new BudgetEditService();

		// CUD처리
		if("C".equals(status)) {
			BudgetBean budgetBean = new BudgetBean();
			BudgetWriteProService budgetWriteProService = new BudgetWriteProService();
			budgetBean.setYYYYMM(request.getParameter("yyyymm"));
			budgetBean.setBUDGET_ID((String)session.getAttribute("id"));
			budgetBean.setCATEGORY_NO(Integer.parseInt(request.getParameter("category_no")));
			budgetBean.setINCM_EXP(request.getParameter("incm_exp"));
			budgetBean.setAMT(Integer.parseInt(request.getParameter("amt")));
			boolean isWriteSuccess = budgetWriteProService.registBudget(budgetBean);
			CategoryListService categoryService = new CategoryListService();
			String categoryNm = categoryService.getCategoryNm(Integer.parseInt(request.getParameter("category_no")));
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
				out.println("parent.tableAppend(" + "'" + request.getParameter("yyyymm") + "'"
												  + ", '" + request.getParameter("incm_exp") + "'" 
												  + ", '" + request.getParameter("category_no") + "'"
												  + ", '" + categoryNm + "'" 
						 						  + ", '" + request.getParameter("amt") + "'" + ")");
				out.println("</script>");
			}
		} else if("D".equals(status)) {
			// 삭제
			boolean isWriteSuccess = budgetEditService.deleteBudget(id, yyyymm, categoryNo);

			if(isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableRemove('" + yyyymm.replace("/", "") + "', '" + categoryNo + "')");
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
			forward.setPath("/budget/budget_edit.jsp");
		}
		
		return forward;
		
	}
}
