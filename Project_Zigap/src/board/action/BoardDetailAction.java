package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.svc.BoardDetailService;
import vo.Action;
import vo.ActionForward;
import vo.BoardBean;

public class BoardDetailAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		ActionForward forward = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("id")!=null){
			int board_num=Integer.parseInt(request.getParameter("board_num"));
			String page = request.getParameter("page");
			BoardDetailService boardDetailService = new BoardDetailService();
			BoardBean article = boardDetailService.getArticle(board_num);
			forward = new ActionForward();
			request.setAttribute("page", page);
			request.setAttribute("article", article);
			forward.setPath("/board/qna_board_view.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.')");
			out.println("location.href='loginForm.jsp'");
			out.println("</script>");
		}
		return forward;
	}
}