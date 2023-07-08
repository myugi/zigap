package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberJoinService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;

public class MemberJoinProAction implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		ActionForward forward = null;
		boolean joinchk = false;
		MemberJoinService membersvc = new MemberJoinService();
		joinchk = membersvc.memberJoin(id, pass, name, email);
		
		if (joinchk == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입이 정상적으로 이루어지지 않았습니다')");
			out.println("location.href='joinForm.jsp'");
			out.println("</script>");
		} else {
			//forward의 실행이 더 빠르기때문에 alert창이 뜨기전에 페이지가 넘어가버림
			forward = new ActionForward();
			forward.setPath("main.jsp");
		}
		
		return forward;
	}

}
