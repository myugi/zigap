package statistics.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import statistics.action.StatisticsAccountAction;
import statistics.action.StatisticsBudgetAction;
import statistics.action.StatisticsBudgetCateAction;
import statistics.action.StatisticsPremonthAction;
import vo.Action;
import vo.ActionForward;

@WebServlet("*.stat")
public class StatisticsFrontController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		ActionForward forward = null;
		Action action = null;
		
		//카테고리 목록
		if(command.equals("/budget.stat")) {
			action = new StatisticsBudgetAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//카테고리 수정 프로세스
		}else if(command.equals("/premonth.stat")) {
			action = new StatisticsPremonthAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//계좌 수정 프로세스
		}else if(command.equals("/budgetcate.stat")) {
			action = new StatisticsBudgetCateAction();
			try { 
				forward =action.execute(request, response); 
			} catch (Exception e) {
			  e.printStackTrace(); 
			}
			
		}else if(command.equals("/account.stat")) {
			action = new StatisticsAccountAction();
			try { 
				forward =action.execute(request, response); 
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
