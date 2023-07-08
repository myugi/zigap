package cal.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.svc.AccountListService;
import cal.svc.CalendarEditService;
import category.svc.CategoryListService;
import vo.AccountBean;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;
import vo.CalBean;


public class CalendarEditProAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	HttpSession session = request.getSession();
	ActionForward forward = null;
	
	// 데이터조회
	String sequenceNo = request.getParameter("sequence_no");
	String id = (String)session.getAttribute("id");
	String date = request.getParameter("date");
	System.out.println(date);
	String status = "";
	
	CalendarEditService calEditService = new CalendarEditService();
	CalBean calBean = calEditService.selectIncmExp(id, sequenceNo);
	if(calBean == null) {
		calBean = new CalBean();
		calBean.setSEQUENCE_NO(0);
		calBean.setID("");
		calBean.setINCM_EXP("");
		calBean.setCATEGORY_NO(0);
		calBean.setBACCT_NO("");
		calBean.setINCM_EXP_DATE(date);
		calBean.setAMT(0);
		calBean.setCOMMENT("");
		status = "C";		// 데이터가 없으면 C
	} else {
		status = "U";		// 데이터가 있으면 U
	}
	
	// 카테고리 조회
	ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
	CategoryListService categoryListService = new CategoryListService();
	categoryList = categoryListService.getCategoryList(id);
	
	// 계좌 조회
	ArrayList<AccountBean> accountList = null;
	AccountListService accountListService = new AccountListService();
	accountList = accountListService.getAccountList(id);
	
	request.setAttribute("accountList", accountList);
	request.setAttribute("categoryList", categoryList);
	request.setAttribute("calBean", calBean);
	request.setAttribute("status", status);
	
	forward = new ActionForward();
	forward.setPath("/calendar/calendar_edit.jsp");
	
	return forward;
	
}
}