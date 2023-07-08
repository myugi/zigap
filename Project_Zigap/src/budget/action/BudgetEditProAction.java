package budget.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import budget.svc.BudgetEditService;
import category.svc.CategoryListService;
import vo.Action;
import vo.ActionForward;
import vo.BudgetBean;
import vo.CategoryBean;

public class BudgetEditProAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		String yyyymm = null;
		
		// 데이터조회
		if(request.getParameter("yyyymm") != null) {
		yyyymm = request.getParameter("yyyymm");
		}
		String id = (String)session.getAttribute("id");
		String status = "";
		String categoryNoStr = request.getParameter("categoryNo");
		int categoryNo = 0;
		if(categoryNoStr != null) {
			categoryNo = Integer.parseInt(categoryNoStr);
		}
		
		BudgetEditService budgetEditService = new BudgetEditService();
		BudgetBean budgetBean = budgetEditService.selectBudget(id, yyyymm, categoryNo);
		if(budgetBean == null) {
			budgetBean = new BudgetBean();
			budgetBean.setYYYYMM("");
			budgetBean.setBUDGET_ID("");
			budgetBean.setCATEGORY_CONTENT("");
			budgetBean.setINCM_EXP("");
			budgetBean.setAMT(0);
			status = "C";		// 데이터가 없으면 C
		} else {
			status = "U";		// 데이터가 있으면 U
		}
		
		//카테고리 조회
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryListService categoryListService = new CategoryListService();
		categoryList = categoryListService.getCategoryList(id);

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("budgetBean", budgetBean);
		request.setAttribute("status", status);
		
		forward = new ActionForward();
		forward.setPath("/budget/budget_edit.jsp");
		
		return forward;
		
	}
}