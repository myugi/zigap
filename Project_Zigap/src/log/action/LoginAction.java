package log.action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import log.svc.LoginService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;

public class LoginAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		String id = null;
		String pass = null;
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
			pass = request.getParameter("pass");
		}

		ActionForward forward = null;
		MemberBean member = null;
		LoginService loginService = new LoginService();
		member = loginService.loginCheck(id);

			if (member.getId() != null && !pass.equals(member.getPassword())) {
				out.println("<script>");
				out.println("alert('아이디와 비밀번호가 일치하지 않습니다')");
				out.println("location.href='loginForm.jsp'");
				out.println("</script>");
			} else if (member.getId()==null) {
				out.println("<script>");
				out.println("alert('아이디를 확인해주세요')");
				out.println("location.href='loginForm.jsp'");
				out.println("</script>");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				out.println("<script>");
				out.println("alert('로그인 성공!')");
				out.println("</script>");
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("loginForm.log");
			}
		return forward;
	}
}
