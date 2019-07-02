package tje.servlets.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

	private static final String formPage = "/WEB-INF/forms/memberForm.html";
	
	private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id = "root";
	private static final String jdbc_pw = "SystemManager304";
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(formPage);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String strId = request.getParameter("id").trim();
		String strPassword = request.getParameter("password").trim();
		String strName = request.getParameter("name").trim();
		String strAge = request.getParameter("age").trim();
		Integer nAge = 0;
		try {
			nAge = Integer.parseInt(strAge);
		} catch(Exception e) {
			nAge = 0;
		}
		String strTel = request.getParameter("tel").trim();
		String strAddress = request.getParameter("address").trim();
		
		try {
			// 웹 어플리케이션에서 JDBC를 활용하는 경우
			// build path에 라이브러리(jar)를 등록하는 것이 아니라
			// WebContent -> WEB-INF -> lib 디렉터리에 jar 파일을 위치시켜야 합니다.
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		}
		
		int result = 0;
		try {
			Connection conn = DriverManager.getConnection(jdbc_url, jdbc_id, jdbc_pw);
			
			String sql = "insert into member values(?,?,?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			pstmt.setString(2, strPassword);
			pstmt.setString(3, strName);
			
			if(nAge != 0)
				pstmt.setInt(4, nAge);
			else
				pstmt.setNull(4,java.sql.Types.NULL);

			if(strTel.length()!=0)
				pstmt.setString(5, strTel);
			else
				pstmt.setNull(5,java.sql.Types.NULL);
			
			if(strAddress.length()!=0)
				pstmt.setString(6, strAddress);
			else
				pstmt.setNull(6,java.sql.Types.NULL);

			result = pstmt.executeUpdate();
			
			pstmt.close();
		
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		if(result==0) {
			// 회원 가입 실패
			out.println("<h2> 회원가입 실패 </h2>");
			out.println("<h4> 입력된 정보를 확인하세요< /h4>");
		}else {
			// 회원 가입 성공			
			out.println("<h2> 회원가입 성공 </h2>");
		}
		
	}

}
