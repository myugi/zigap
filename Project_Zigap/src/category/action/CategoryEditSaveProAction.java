package category.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import category.svc.CategoryEditService;
import category.svc.CategoryWriteProService;
import vo.Action;
import vo.ActionForward;
import vo.CategoryBean;

public class CategoryEditSaveProAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		ActionForward forward = null;
		
		String status = request.getParameter("status");
		
		// 데이터조회
		String categoryNo = request.getParameter("category_no");
		String id = (String)session.getAttribute("id");
		
		CategoryEditService categoryEditService = new CategoryEditService();
		
		
		// CUD처리
		if("C".equals(status)) {
			CategoryBean categoryBean = new CategoryBean();
			CategoryWriteProService categoryWriteProService = new CategoryWriteProService();
			int max_count = categoryWriteProService.getMaxCount();
			categoryBean.setCATEGORY_NO(max_count);
			categoryBean.setCATEGORY_ID((String)session.getAttribute("id"));
			categoryBean.setCATEGORY_CONTENT(request.getParameter("category_content"));
			categoryBean.setINCM_EXP(request.getParameter("incm_exp"));
			boolean isWriteSuccess = categoryWriteProService.registCategory(categoryBean);
			
			if(!isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('등록완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableAppend(" + "'" + max_count + "'"
												  + ", '" + request.getParameter("incm_exp") + "'" 
						 						  + ", '" + request.getParameter("category_content") + "'" + ")");
				out.println("</script>");
			}
		} else if("D".equals(status)) {
			// 삭제
			boolean isWriteSuccess = categoryEditService.deleteCategory(id, categoryNo);
			
			if(isWriteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제완료')");
				out.println("parent.modalClose()");
				out.println("parent.tableRemove('" + categoryNo + "')");
				out.println("</script>");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제중 에러')");
				out.println("history.back()");
				out.println("</script>");
			}
		} else {
			forward = new ActionForward();
			forward.setPath("/category/category_edit.jsp");
		}
		
		return forward;
		
	}
}
