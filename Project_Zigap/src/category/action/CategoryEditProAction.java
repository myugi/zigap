package category.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.svc.CategoryEditService;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;

public class CategoryEditProAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		// 데이터조회
		String categoryNo = request.getParameter("category_no");
		String id = (String)session.getAttribute("id");
		String status = "";
		
		CategoryEditService categoryEditService = new CategoryEditService();
		CategoryBean categoryBean = categoryEditService.selectAccount(id, categoryNo);
		if(categoryBean == null) {
			categoryBean = new CategoryBean();
			categoryBean.setCATEGORY_NO(0);
			categoryBean.setCATEGORY_ID("");
			categoryBean.setCATEGORY_CONTENT("");
			categoryBean.setINCM_EXP("");
			status = "C";		// 데이터가 없으면 C
		} else {
			status = "U";		// 데이터가 있으면 U
		}
		
		request.setAttribute("categoryBean", categoryBean);
		request.setAttribute("status", status);
		
		forward = new ActionForward();
		forward.setPath("/category/category_edit.jsp");
		
		return forward;
		
	}
}