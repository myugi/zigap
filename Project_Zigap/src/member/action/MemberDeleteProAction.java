package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberDeleteProService;
import vo.Action;
import vo.ActionForward;

public class MemberDeleteProAction implements Action {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		System.out.println(session.getAttribute("id"));
		MemberDeleteProService memberDeleteProService = new MemberDeleteProService();
		
		if(!session.getAttribute("id").equals("admin")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		else {
			boolean isDeleteSuccess = memberDeleteProService.removeMember(id);
			
			if(!isDeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제실패');");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
			else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memberList.mem?id="+session.getAttribute("id"));
			}
		}
		
		return forward;
	}


}
