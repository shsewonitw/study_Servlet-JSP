package tje.servlets.jdbc;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.model.Member;

@WebServlet("/member_detail")
public class MemeberDetailServlet extends HttpServlet {
	
	private static final String formPage = "/WEB-INF/forms/memberDetail.jsp";	
	
	private static String jdbc_url;
	private static String jdbc_id;
	private static String jdbc_password;

	public void init(ServletConfig config) throws ServletException {
		ServletContext application = config.getServletContext();
		jdbc_url = application.getInitParameter("JDBC_URL");
		jdbc_id = application.getInitParameter("JDBC_ID");
		jdbc_password = application.getInitParameter("JDBC_PASSWORD");
				
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 조회를 하고자 하는 멤버의 아이디값을 쿼리 스트링에 추출
		String id = request.getParameter("id");
		
		// 조회할 회원 정보를 저장할 객체 생성
		// (포워딩되는 JSP 파일에서 참조할 객체)
		Member member = null;
		
		// JDBC를 사용한 데이터베이스 처리
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(jdbc_url, jdbc_id, jdbc_password);
			String query = "select * from member where id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				// Member 테이블의 각 레코드를
				// 자바빈 객체로 변환
				member = new Member(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getInt(4), 
						rs.getString(5), 
						rs.getString(6));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if( conn != null ) conn.close();
			if( pstmt != null ) pstmt.close();
			if( rs != null ) rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher(formPage);
		rd.forward(request, response);
	}
	
}

