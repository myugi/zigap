package account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountListService;

import java.util.ArrayList;

import vo.AccountBean;
import vo.Action;
import vo.ActionForward;
import vo.PageInfo;

public class AccountListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
	
		AccountListService accountListService = new AccountListService();
		accountList = accountListService.getAccountList(id);

		request.setAttribute("accountList", accountList);
		forward.setPath("/account/account_list.jsp");
		
		
		return forward;
	}

}
