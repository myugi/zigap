package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberInfoService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModiFormAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = null;
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		if(session.getAttribute("id")!=null){
			id = request.getParameter("id");
			MemberInfoService memberInfoService = new MemberInfoService();
			MemberBean memberInfo = new MemberBean();
			memberInfo = memberInfoService.getMemberInfo(id);
			request.setAttribute("memberInfo", memberInfo);
			forward.setPath("member_modi.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다!')");
			out.println("location.href='loginForm.jsp'");
			out.println("</script>");
		}
		return forward;
	}
}
