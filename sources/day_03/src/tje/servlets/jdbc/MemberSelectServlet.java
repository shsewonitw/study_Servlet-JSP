package tje.servlets.jdbc;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member_select")
public class MemberSelectServlet extends HttpServlet {
	
	private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id = "root";
	private static final String jdbc_pw = "SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			Connection conn = DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_pw);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("select * from member");
			
			while(rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
				String tel = rs.getString(5);
				String address = rs.getString(6);
				
				
				out.printf("<h6>ID-> %s, PW-> %s, NAME-> %s, AGE-> %d, TEL-> %s, ADDRESS-> %S</h6>", id,password,name,age,tel,address);
				
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
