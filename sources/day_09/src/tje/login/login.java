package tje.login;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	
	private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id = "root";
	private static final String jdbc_password = "SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id").trim();
		String password = (String) request.getParameter("password").trim();
		
		String name = null;
		HttpSession session = request.getSession();
		try {
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {System.out.println("클래스로드예외발생");}
		
		
		
		try {
			Connection conn = DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
			String sql = "select name from member where id=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				name = rs.getString("name");
			} else {
				name = null;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {System.out.println("DB처리 예외발생");}
		
		if(name != null) {
			session.setAttribute("id", id);
			session.setAttribute("password", password);
		}
		request.setAttribute("name", name);

		
		RequestDispatcher rd = request.getRequestDispatcher("/login/loginCheck.jsp");
		rd.forward(request, response);
				
	}

}
