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

import tje.dao.*;
import tje.jdbc.JDBCCloser;
import tje.jdbc.JDBCConnection;
import tje.model.*;

public class MemeberRegistService extends Service {
	
	private static final String formPage = "/WEB-INF/forms/memberForm.html";	
	private static final String submitPage = "/WEB-INF/submits/memberSubmit.jsp";
	
	private MemberDAO memberDAO = new MemberDAO();
	
	@Override
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// 클라이언트가 전달한 폼 데이터를 추출 
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		String name = request.getParameter("name").trim();
		String strAge = request.getParameter("age").trim();
		int age = 0;
		try {
			age = Integer.parseInt(strAge);
		} catch(Exception e) {
			// 나이 입력안함...
			age = 0;
		}
		String tel = request.getParameter("tel").trim();
		String address = request.getParameter("address").trim();
		
		// 폼 데이터를 사용하여 자바 빈 객체를 생성
		Member member = new Member(id, password, name, age, tel, address);
		
		
		
		
		// JDBC를 사용한 데이터베이스 처리
		Connection conn = JDBCConnection.getConnection();
		
		
		boolean result = memberDAO.insert(conn, member);
		JDBCCloser.close(conn);
		
		
		// 1. request 영역에 사용자가 입력한 폼데이터를 저장하고 있는
		// 자바 빈 객체 저장
		request.setAttribute("member", member);
		// 2. request 영역에 회원 가입의 결과를 저장
		request.setAttribute("result", result);
		
		// 3. JSP 페이지로 포워딩
		return submitPage;
	}
}