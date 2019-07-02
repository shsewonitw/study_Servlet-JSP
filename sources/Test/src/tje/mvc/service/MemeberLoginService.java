package tje.mvc.service;

import java.io.*;
import java.sql.*;
import tje.jdbc.*;
import tje.dao.*;
import tje.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemeberLoginService extends Service {
	
	private static final String formPage = "/WEB-INF/forms/loginForm.html";
	private static final String errorPage = "/WEB-INF/errors/loginError.html";

	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		boolean isLogin = false;
		if( session != null && session.getAttribute("login_id") != null )
			isLogin = true;
		
		String forwardPage = formPage;
		
		if( isLogin ) {
			forwardPage = errorPage;
		}
		
		return forwardPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String SuccessPage = "/WEB-INF/forms/loginSuccess.html";
		String FailPage = "/WEB-INF/forms/loginFail.html";
		
		
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		
		Member member = new Member(id,password,null,0,null,null);
		
		
		// 로그인 성공 여부의 값을 저장하는 변수
		
		
		// JDBC를 사용한 데이터베이스 처리
		Connection conn = JDBCConnection.getConnection();
		Member searchMember = memberDAO.selectOne(conn, member);
		JDBCCloser.close(conn);
		
		// 로그인 여부 처리한 후, request 객체에 저장
		// (JDBC 처리로 반환된 모델 객체는 반드시 null 값 여부 체크)
		boolean isLogin = false;
		if( searchMember != null &&
				member.getPassword().equals(searchMember.getPassword()) )
			isLogin = true;
		
		request.setAttribute("isLogin", isLogin);
		
		
		// 로그인 여부에 따라 작업
		if( isLogin ) {
			// 현재 로그인된 사용자의 아이디를 세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("login_id", id);
			ServletContext application = request.getServletContext();
			// 현재 웹 서버(서블릿 컨테이너)에 로그인된 사용자의 수를 증가
			synchronized (application) {
				if( application.getAttribute("login_member_count") == null )
					application.setAttribute("login_member_count", 1);
				else {
					Integer count = (Integer)application.getAttribute("login_member_count");
					application.setAttribute("login_member_count", count+1);
				}
			}
			
			return SuccessPage;	
		} else {
			return FailPage;
		}
		
		
	}

	
	
	
}

