package tje.servlets.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbc_01")
public class JDBCServlet_01 extends HttpServlet {

	private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url = "jdbc:mysql://localhost:3306/chat?serverTimezone=UTC";
	private static final String jdbc_id = "root";
	private static final String jdbc_pw = "SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 웹 어플리케이션에서 JDBC를 활용하는 경우
			// build path에 라이브러리(jar)를 등록하는 것이 아니라
			// WebContent -> WEB-INF -> lib 디렉터리에 jar 파일을 위치시켜야 합니다.
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbc_url, jdbc_id, jdbc_pw);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery("select * from member");
			while(rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				String nick = rs.getString(4);
				java.util.Date regist_date = rs.getTimestamp(5);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				System.out.printf("%s, %s, %s, %s, %s\n",id,pw,name,nick,sdf.format(regist_date));
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


}
