package member.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.MemberDeleteProAction;
import member.action.MemberInfoProAction;
import member.action.MemberJoinProAction;
import member.action.MemberListProAction;
import member.action.MemberModiFormAction;
import member.action.MemberModiProAction;
import member.action.idCheckProAction;
import vo.Action;
import vo.ActionForward;

@WebServlet("*.mem")
public class MemberController extends javax.servlet.http.HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());

		ActionForward forward = null;
		Action action = null;
		
		// 회원가입 폼
		if (command.equals("/joinForm.mem")) {
			forward = new ActionForward();
			forward.setPath("/joinForm.jsp");

		// 회원가입 프로세스
		} else if (command.equals("/joinProcess.mem")) {
			action = new MemberJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		// 회원리스트보기 프로세스
		} else if (command.equals("/memberList.mem")) {
			action = new MemberListProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		// 회원상세정보 프로세스
		} else if (command.equals("/memberInfo.mem")) {
			action = new MemberInfoProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		//회원정보 수정 폼
		} else if (command.equals("/memberModiForm.mem")) {
			action = new MemberModiFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//회원정보수정 프로세스
		} else if (command.equals("/memberModifyPro.mem")) {
			action = new MemberModiProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//회원삭제 프로세스
		} else if (command.equals("/memberDelete.mem")) {
			action = new MemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		//아이디 중복체크 프로세스
		} else if (command.equals("/idCheckPro.mem")) {
			action = new idCheckProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}