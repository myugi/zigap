package category.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import category.action.CategoryEditProAction;
import category.action.CategoryEditSaveProAction;
import category.action.CategoryListAction;
import category.action.CategorySelectIncmExpListAction;
import vo.Action;
import vo.ActionForward;

@WebServlet("*.cate")
public class CategoryFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		//카테고리 목록
		if(command.equals("/categoryList.cate")) {
			action = new CategoryListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//카테고리 수정 프로세스
		}else if(command.equals("/categoryEdit.cate")) {
			action = new CategoryEditProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//계좌 수정 프로세스
		}else if(command.equals("/categoryEditSave.cate")) {
			  
			action = new CategoryEditSaveProAction();
			try { 
				forward =action.execute(request, response); 
			} catch (Exception e) {
			  e.printStackTrace(); 
			}
		
		//수입/지출에따른 카테고리 불러오기
		}else if(command.equals("/selectCategoryList.cate")) {
			action = new CategorySelectIncmExpListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
			 
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
