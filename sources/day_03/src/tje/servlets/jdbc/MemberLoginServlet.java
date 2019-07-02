package tje.servlets.jdbc;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member_login")
public class MemberLoginServlet extends HttpServlet {
	private static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id = "root";
	private static final String jdbc_password = "SystemManager304";
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = null;
		Cookie[] cookies = request.getCookies();
		for(int i = 0 ; cookies != null && i < cookies.length ; i++) {
			if(cookies[i].getName().equals("id"))
				id = cookies[i].getValue();
		}
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("<form action=\"./member_login\" method=\"post\">");
		buffer.append("<table>");
		buffer.append("<tr>");
		if(id==null)
			buffer.append("<td>아이디</td> <td><input type=\"text\" name=\"id\"></td>");
		else
			buffer.append("<td>아이디</td> <td><input type=\"text\" name=\"id\" value=\""+id+"\"></td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<label><td>패스워드</td> <td><input type=\"password\" name=\"password\"></td></label>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		if(id==null)
			buffer.append("<td><input type=\"submit\" value=\"로그인\"></td><td><label><input type=\"Checkbox\" name=\"chk\">아이디 저장</label></td>");
		else
			buffer.append("<td><input type=\"submit\" value=\"로그인\"></td><td><label><input type=\"Checkbox\" name=\"chk\" checked>아이디 저장</label></td>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</form>");

		out.println(buffer);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		String checked = request.getParameter("chk");
		String name = null;
		
		try {
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		}
		
		try {
			Connection conn = DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
			String sql = "select name from member where id=? and password=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		
		if( name == null ) {
			out.println("<h2>로그인 실패</h2>");
			out.println("<h4>입력한 정보를 확인하세요</h4>");
		} else {
			out.println("<h2>로그인 성공</h2>");
			out.println("<h4>" + name + " 님 환영합니다.</h4>");
			out.println("<form action='./member_main'><input type='submit' value='메인으로~!'></form>");
			
			if(checked==null) {
				Cookie cookie = new Cookie("id", id);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("id", id);
				response.addCookie(cookie);
				
			}
			ServletContext application = request.getServletContext();
			synchronized (application) {
				Integer accessUser = (Integer)application.getAttribute("count");
				if(accessUser == null)
					accessUser = 1;
				accessUser++;
				application.setAttribute("count", accessUser);	
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("login_id", id);
			session.setAttribute("login_name", name);
		}
	}

}
