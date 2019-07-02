package tje.mvc.service;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemeberLogoutService extends Service {
	
	private static final String formPage = "/WEB-INF/forms/logoutForm.jsp";
	String logoutPage = "/WEB-INF/forms/logoutSuccess.html";


	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 세션을 제거하여 로그인 정보를 무효화
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 사용자가 로그아웃 되었기 때문에
		// 현재 접속된 사용자의 수를 1 감소
		ServletContext application = request.getServletContext();
		
		synchronized (application) {
			if( application.getAttribute("login_member_count") == null )
				application.setAttribute("login_member_count", 0);
			else {
				Integer count = (Integer)application.getAttribute("login_member_count");
				application.setAttribute("login_member_count", count-1);
			}
		}	
		
		return logoutPage;
	}

	
	
}







