package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.svc.MemberListService;
import vo.Action;
import vo.ActionForward;
import vo.MemberBean;
import vo.PageInfo;

public class MemberListProAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		
		if(session.getAttribute("id").equals("admin")) {
			ArrayList<MemberBean> memberList = new ArrayList<MemberBean> ();
			int page = 1;
			int limit = 5;
			
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			MemberListService memberListService = new MemberListService();
			int listCount = memberListService.getListCount();
			memberList = memberListService.getMemberList(page,limit);

			int maxPage=(int)((double)listCount/limit+0.95);
			
			int startPage = (((int)((double)page/5+0.9))-1)*5+1;

			int endPage = startPage + 5 - 1;
			
			if(endPage > maxPage) endPage = maxPage;
			
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("memberList", memberList);
			forward.setPath("/member_list.jsp");
			
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요!')");
			out.println("location.href='loginForm.jsp'");
			out.println("</script>");
		}
		return forward;
	}	
}
