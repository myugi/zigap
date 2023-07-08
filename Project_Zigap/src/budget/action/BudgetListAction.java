package budget.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import budget.svc.BudgetListService;
import vo.Action;
import vo.ActionForward;
import vo.BudgetBean;

public class BudgetListAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		String search = request.getParameter("search");
		if(search == null) search = "201903";
		
		ArrayList<BudgetBean> budgetList = new ArrayList<BudgetBean>();
	
		BudgetListService budgetListService = new BudgetListService();
		budgetList = budgetListService.getBudgetList(id,search);

		request.setAttribute("budgetList", budgetList);
		forward.setPath("/budget/budget_list.jsp");
		
		return forward;
	}

}
