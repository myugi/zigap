package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.svc.MemberInfoService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;

public class idCheckProAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		
		MemberInfoService memberInfoService = new MemberInfoService();
		MemberBean member = memberInfoService.getMemberInfo(id);

		if(member==null) {
			request.setAttribute("passibleID", true);
		}else {
			request.setAttribute("passibleID", false);
		}
		request.setAttribute("id", id);
		
		forward = new ActionForward();
		forward.setPath("idCheck.jsp");
		return forward;
	}

}
