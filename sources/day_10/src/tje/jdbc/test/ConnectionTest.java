package tje.jdbc.test;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tje.jdbc.JDBCInfo;

@WebServlet("/conn_test")
public class ConnectionTest extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		JDBCInfo info = (JDBCInfo)context.getAttribute("jdbc_info");
		
		try {
			Connection conn = DriverManager.getConnection(info.getUrl(),info.getUser(),info.getPassword());
			System.out.println("JDBC 연결 성공");
			conn.close();
		} catch (SQLException e) {
			System.out.println("JDBC 연결 실패");
			e.printStackTrace();
		}
	}
}
