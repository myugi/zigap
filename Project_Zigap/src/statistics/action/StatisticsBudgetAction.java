package statistics.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import statistics.svc.StatisticsBudgetService;
import util.StringUtil;
import vo.Action;
import vo.ActionForward;

public class StatisticsBudgetAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		String date = request.getParameter("date");
		String incmExp = request.getParameter("incmExp");
		
		if(date == null || "".equals(date)) {
			date = StringUtil.getToday("yyyyMM");
		}
		
		date = date.replaceAll("/", "");
		
		StatisticsBudgetService service = new StatisticsBudgetService();
		List list = service.getList(id, date, incmExp);
		request.setAttribute("list", list);
		
		
		request.setAttribute("date", date.substring(0, 4) + "/" + date.substring(4));
		
		forward.setPath("/statistics/budget.jsp");
		
		return forward;
		
	}
}