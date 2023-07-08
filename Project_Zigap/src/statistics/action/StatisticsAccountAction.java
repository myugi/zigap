package statistics.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountListService;
import statistics.svc.StatisticsAccountService;
import statistics.svc.StatisticsBudgetService;
import util.StringUtil;
import vo.AccountBean;
import vo.Action;
import vo.ActionForward;

public class StatisticsAccountAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		String bacctNo = request.getParameter("bacctNo");
		
		ArrayList<AccountBean> accountList = null;
		AccountListService accountListService = new AccountListService();
		accountList = accountListService.getAccountList(id);
		
		if(bacctNo == null || "".equals(bacctNo)) {
			if(accountList != null && accountList.size() > 0) {
				bacctNo = accountList.get(0).getBACCT_NO();
			}
		}
		
		StatisticsAccountService service = new StatisticsAccountService();
		List list = service.getList(id, bacctNo);
		request.setAttribute("list", list);
		request.setAttribute("accountList", accountList);
		request.setAttribute("bacctNo", bacctNo);

		forward.setPath("/statistics/account.jsp");
		
		return forward;
		
	}
}