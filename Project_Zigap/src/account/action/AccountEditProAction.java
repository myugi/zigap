package account.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountEditService;
import account.svc.AccountWriteProService;
import vo.AccountBean;
import vo.Action;
import vo.ActionForward;

public class AccountEditProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		// 데이터조회
		String bacctNo = request.getParameter("bacct_no");
		String id = (String)session.getAttribute("id");
		String status = "";
		
		AccountEditService accountEditService = new AccountEditService();
		AccountBean accountBean = accountEditService.selectAccount(id, bacctNo);
		if(accountBean == null) {
			accountBean = new AccountBean();
			accountBean.setBACCT_COMMENT("");
			accountBean.setBACCT_ID("");
			accountBean.setBACCT_NO("");
			accountBean.setBANK_NAME("");
			accountBean.setBASE_AMT(0);
			status = "C";		// 데이터가 없으면 C
		} else {
			status = "U";		// 데이터가 있으면 U
		}
		
		request.setAttribute("accountBean", accountBean);
		request.setAttribute("status", status);
		
		forward = new ActionForward();
		forward.setPath("/account/account_edit.jsp");
		
		return forward;
		
	}
}
