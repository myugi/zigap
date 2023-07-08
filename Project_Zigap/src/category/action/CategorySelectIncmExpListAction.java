package category.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import category.svc.CategorySelectIncmExpListService;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;

public class CategorySelectIncmExpListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ActionForward forward = null;

		// 데이터조회

		String id = (String) session.getAttribute("id");
		String incmExp = request.getParameter("incmExp");

		CategorySelectIncmExpListService service = new CategorySelectIncmExpListService();
		List<CategoryBean> list = service.selectList(id, incmExp);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		
//		forward = new ActionForward();
		return forward;

	}
}