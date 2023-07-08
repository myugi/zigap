package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberModifyProService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;

public class MemberModiProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		boolean isModifySuccess = false;
		HttpSession session = request.getSession();
		MemberBean member = new MemberBean();
		MemberModifyProService memberModifyProService = new MemberModifyProService();
		
			member.setId((String)session.getAttribute("id"));
			member.setPassword(request.getParameter("pass"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			isModifySuccess = memberModifyProService.modifyMember(member);
			System.out.println(isModifySuccess);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back()");
				out.println("</script>");
			}
			else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberInfo.mem?id="+member.getId());
			}
		
		return forward;
		
	}

}
