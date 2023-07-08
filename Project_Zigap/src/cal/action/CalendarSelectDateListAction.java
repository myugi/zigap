package cal.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cal.svc.CalendarSelectDateListService;
import vo.Action;
import vo.ActionForward;
import vo.CalBean;

public class CalendarSelectDateListAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		ActionForward forward = null;

		// 데이터조회

		String id = (String) session.getAttribute("id");
		String dt = request.getParameter("dt");
		String incmExp = request.getParameter("incmExp");

		CalendarSelectDateListService service = new CalendarSelectDateListService();
		List<CalBean> list = service.selectList(id, dt, incmExp);
		
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