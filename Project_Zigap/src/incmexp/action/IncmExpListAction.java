package incmexp.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import incmexp.svc.IncmExpListService;
import vo.Action;
import vo.ActionForward;
import vo.UseIncmExpBean;

public class IncmExpListAction implements Action {
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		String id = (String)session.getAttribute("id");
		ArrayList<UseIncmExpBean> incmExpList = new ArrayList<UseIncmExpBean>();
	
		IncmExpListService incmExpListService = new IncmExpListService();
		incmExpList = incmExpListService.getIncmExpList(id);

		request.setAttribute("incmExpList", incmExpList);
		forward.setPath("/incmExp/incmExp_list.jsp");
		
		return forward;
	}

}