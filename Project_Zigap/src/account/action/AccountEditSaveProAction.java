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

public class AccountEditSaveProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String status = request.getParameter("status");
		
		// 데이터조회
		String bacctNo = request.getParameter("bacct_no");
		String id = (String)session.getAttribute("id");
		
		AccountEditService accountEditService = new AccountEditService();
		
		
		// CUD처리
		if("C".equals(status)) {
			AccountBean accountBean = new AccountBean();
			accountBean.setBACCT_ID((String)session.getAttribute("id"));
			accountBean.setBANK_NAME(request.getParameter("bank_name"));
			accountBean.setBACCT_NO(request.getParameter("bacct_no"));
			accountBean.setBACCT_COMMENT(request.getParameter("bacct_comment"));
			accountBean.setBASE_AMT(Integer.parseInt(request.getParameter("base_amt")));
			AccountWriteProService accountWriteProService = new AccountWriteProService();
			boolean isWriteSuccess = accountWriteProService.registAccount(accountBean);
			
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
				out.println("parent.tableAppend(" + "'" + request.getParameter("bank_name") + "'" 
						 						  + ", '" + request.getParameter("bacct_no") + "'" 
				 								  + ", '" + request.getParameter("bacct_comment") + "'" 
			 									  + ", '" + request.getParameter("base_amt") + "'" + ")");
				out.println("</script>");
			}
		} else if("U".equals(status)) {
			AccountBean accountBean = new AccountBean();
			accountBean.setBACCT_ID((String)session.getAttribute("id"));
			accountBean.setBANK_NAME(request.getParameter("bank_name"));
			accountBean.setBACCT_NO(request.getParameter("bacct_no"));
			accountBean.setBACCT_COMMENT(request.getParameter("bacct_comment"));
			accountBean.setBASE_AMT(Integer.parseInt(request.getParameter("base_amt")));
			boolean isUpdateSuccess = accountEditService.updateAccount(accountBean);
			
			if(!isUpdateSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패')");
				out.println("history.back()");
				out.println("</script>");
			}else{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableUpdate(" + "'" + request.getParameter("bank_name") + "'" 
												  + ", '" + request.getParameter("bacct_no") + "'" 
												  + ", '" + request.getParameter("bacct_comment") + "'" 
												  + ", '" + request.getParameter("base_amt") + "'" + ")");
				out.println("</script>");
			}
		} else if("D".equals(status)) {
			// 삭제
			boolean isWriteSuccess = accountEditService.deleteAccount(id, bacctNo);
			
			if(isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableRemove('" + bacctNo + "')");
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
			forward.setPath("/account/accountEdit.jsp");
		}
		
		return forward;
		
	}
}
