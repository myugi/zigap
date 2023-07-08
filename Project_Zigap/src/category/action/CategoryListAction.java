package category.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.svc.CategoryListService;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;

public class CategoryListAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
	
		CategoryListService categoryListService = new CategoryListService();
		categoryList = categoryListService.getCategoryList(id);

		request.setAttribute("categoryList", categoryList);
		forward.setPath("/category/category_list.jsp");
		
		return forward;
	}

}