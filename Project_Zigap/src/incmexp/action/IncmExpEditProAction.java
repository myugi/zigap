package incmexp.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountListService;
import category.svc.CategoryListService;
import incmexp.svc.IncmExpEditService;
import vo.AccountBean;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;
import vo.UseIncmExpBean;

public class IncmExpEditProAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		// 데이터조회
		String sequenceNo = request.getParameter("sequence_no");
		System.out.println(sequenceNo);
		String id = (String)session.getAttribute("id");
		String status = "";
		
		IncmExpEditService incmExpEditService = new IncmExpEditService();
		UseIncmExpBean incmExpBean = incmExpEditService.selectIncmExp(id, sequenceNo);
		if(incmExpBean == null) {
			incmExpBean = new UseIncmExpBean();
			incmExpBean.setSEQUENCE_NO(0);
			incmExpBean.setID("");
			incmExpBean.setBANK_NAME("");
			incmExpBean.setBACCT_NO("");
			incmExpBean.setINCM_EXP("");
			incmExpBean.setCATEGORY_NO(0);
			incmExpBean.setUSE_DATE("");
			incmExpBean.setUSE_AMT(0);
			incmExpBean.setCOMMENT("");
			status = "C";		// 데이터가 없으면 C
		} else {
			status = "U";		// 데이터가 있으면 U
		}
		
		// 카테고리 조회
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		CategoryListService categoryListService = new CategoryListService();
		categoryList = categoryListService.getCategoryList(id);
		
		// 계좌 조회
		ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
		AccountListService accountListService = new AccountListService();
		accountList = accountListService.getAccountList(id);
		
		request.setAttribute("accountList", accountList);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("incmExpBean", incmExpBean);
		request.setAttribute("status", status);
		
		forward = new ActionForward();
		forward.setPath("/incmExp/incmExp_edit.jsp");
		
		return forward;
		
	}
}