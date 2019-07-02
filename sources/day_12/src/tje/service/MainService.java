package tje.service;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MainService extends Service {
	// GET 방식의 요청일 경우 반환할 페이지 정보
	private String formPage = "/WEB-INF/views/main.jsp";

	
	
	// GET 요청일 경우의 처리로직을 구현하는 메소드
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String login_id = null;
		Integer login_member_count = null;
		
		HttpSession session = request.getSession(false);
		ServletContext application = request.getServletContext();
		
		if( session != null) {
			login_id = (String)session.getAttribute("login_id");
			login_member_count = (Integer)application.getAttribute("login_member_count");
		}
		
		
		
		return formPage;
	}
	
	// POST 요청일 경우의 처리로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
