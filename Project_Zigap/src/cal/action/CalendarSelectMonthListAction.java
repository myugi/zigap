package cal.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import account.svc.AccountListService;
import cal.svc.CalendarSelectMonthListService;
import category.svc.CategoryListService;
import vo.AccountBean;
import vo.Action;
import vo.ActionForward;
import vo.CalBean;
import vo.CategoryBean;

public class CalendarSelectMonthListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ActionForward forward = null;

		// 데이터조회

		String id = (String) session.getAttribute("id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");

		CalendarSelectMonthListService service = new CalendarSelectMonthListService();
		List<CalBean> list = service.selectList(id, start, end);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonStr);
		
//		forward = new ActionForward();
		return forward;

	}
}