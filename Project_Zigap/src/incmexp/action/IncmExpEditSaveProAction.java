package incmexp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountListService;
import category.svc.CategoryListService;
import incmexp.svc.IncmExpEditService;
import incmexp.svc.IncmExpWriteProService;
import util.StringUtil;
import vo.Action;
import vo.ActionForward;
import vo.UseIncmExpBean;

public class IncmExpEditSaveProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String status = request.getParameter("status");
		
		// 데이터조회
		String sequenceNo = request.getParameter("sequence_no");
		String id = (String)session.getAttribute("id");
		
		IncmExpEditService incmExpEditService = new IncmExpEditService();
		
		// CUD처리
		if("C".equals(status)) {
			UseIncmExpBean incmExpBean = new UseIncmExpBean();
			IncmExpWriteProService incmExpWriteProService = new IncmExpWriteProService();
			int max_count = incmExpWriteProService.getMaxCount();
			incmExpBean.setSEQUENCE_NO(max_count);
			incmExpBean.setID((String)session.getAttribute("id"));
			incmExpBean.setINCM_EXP(request.getParameter("incm_exp"));
			incmExpBean.setCATEGORY_NO(Integer.parseInt(request.getParameter("category_no")));
			incmExpBean.setBANK_NAME(request.getParameter("bank_name"));
			incmExpBean.setBACCT_NO(request.getParameter("bacct_no"));
			incmExpBean.setUSE_DATE(request.getParameter("use_date"));
			incmExpBean.setUSE_AMT(Integer.parseInt(request.getParameter("use_amt")));
			incmExpBean.setCOMMENT(request.getParameter("comment"));
			boolean isWriteSuccess = incmExpWriteProService.registIncmExp(incmExpBean);
			
			CategoryListService categoryService = new CategoryListService();
			String categoryNm = categoryService.getCategoryNm(Integer.parseInt(request.getParameter("category_no"))); 
			
			AccountListService accountService = new AccountListService();
			String bankNm = accountService.getBankNm(request.getParameter("bacct_no"));
			
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
				out.println("parent.tableAppend(" + "'" + max_count + "'"
												  + ", '" + request.getParameter("incm_exp") + "'" 
												  + ", '" + categoryNm + "'"
												  + ", '" + bankNm + "'"
												  + ", '" + request.getParameter("bacct_no") + "'" 
												  + ", '" + request.getParameter("use_date") + "'" 
						 						  + ", '" + StringUtil.numberFormat(Integer.parseInt(request.getParameter("use_amt"))) + "'"
						 						  + ", '" + request.getParameter("comment") + "'" + ")");
				out.println("</script>");
			}
		} else if("U".equals(status)) {
			UseIncmExpBean incmExpBean = new UseIncmExpBean();
			IncmExpWriteProService incmExpWriteProService = new IncmExpWriteProService();
			incmExpBean.setSEQUENCE_NO(Integer.parseInt(request.getParameter("sequence_no")));
			incmExpBean.setID((String)session.getAttribute("id"));
			incmExpBean.setINCM_EXP(request.getParameter("incm_exp"));
			incmExpBean.setCATEGORY_NO(Integer.parseInt(request.getParameter("category_no")));
			incmExpBean.setBACCT_NO(request.getParameter("bacct_no"));
			incmExpBean.setUSE_DATE(request.getParameter("use_date"));
			incmExpBean.setUSE_AMT(Integer.parseInt(request.getParameter("use_amt")));
			incmExpBean.setCOMMENT(request.getParameter("comment"));
			boolean isUpdateSuccess = incmExpEditService.updateIncmExp(incmExpBean);
			
			CategoryListService categoryService = new CategoryListService();
			String categoryNm = categoryService.getCategoryNm(Integer.parseInt(request.getParameter("category_no"))); 
			
			AccountListService accountService = new AccountListService();
			String bankNm = accountService.getBankNm(request.getParameter("bacct_no"));
			
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
				out.println("parent.tableUpdate(" + "'" + request.getParameter("sequence_no") + "'"
												  + ", '" + request.getParameter("incm_exp") + "'" 
												  + ", '" + categoryNm + "'"
												  + ", '" + bankNm + "'"
												  + ", '" + request.getParameter("bacct_no") + "'" 
												  + ", '" + request.getParameter("use_date") + "'" 
						 						  + ", '" + request.getParameter("use_amt") + "'"
						 						  + ", '" + request.getParameter("comment") + "'" + ")");
				out.println("</script>");
			}
		} else if("D".equals(status)) {
			// 삭제
			boolean isWriteSuccess = incmExpEditService.deleteCategory(id, sequenceNo);
			
			if(isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableRemove('" + sequenceNo + "')");
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
			forward.setPath("/incmExp/incmExp_edit.jsp");
		}
		
		return forward;
		
	}
}
