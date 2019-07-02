package tje.servlet.cookie;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login_cookie")
public class loginServletWithCookie extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 쿠키 여부 확인
		Cookie [] cookies = request.getCookies();
		String save_id = null;
		
		for( int i = 0 ; cookies !=null && i < cookies.length ; i++ ) {
			if(cookies[i].getName().equals("save_id")) {
				save_id = cookies[i].getValue();
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("<form action=\"./login_cookie\" method=\"post\">");	
		buffer.append("<table>");
		buffer.append("<caption>로그인</caption>");
		buffer.append("<tr>");
		buffer.append("<th>ID</th>");
		if( save_id == null )
			buffer.append("<th><input type=\"text\" name=\"id\" required></th>");
		else
			buffer.append("<th><input type=\"text\" name=\"id\" value=\""+save_id+"\" required></th>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<td>PW</td>");
		buffer.append("<td><input type=\"password\" name=\"password\" required></td>");
		buffer.append("</tr>");
		buffer.append("<tr>");
		buffer.append("<th colspan=\"2\"><input type=\"submit\" value=\"로그인\">");
		if( save_id == null )
			buffer.append("<label>아이디 저장<input type=\"checkbox\" name=\"save_id\"></label></th>");
		else
			buffer.append("<label>아이디 저장<input type=\"checkbox\" name=\"save_id\" checked></label></th>");
		buffer.append("</tr>");
		buffer.append("</table>");
		buffer.append("</form>");
		
		out.println(buffer);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		// 체크박스인 경우 체크를 하면 on 문자열, 체크를 하지 않은 경우 null값이 반환
		String save_id = request.getParameter("save_id");
		
		// System.out.printf("%s %s %s",id,password,save_id);
		
		boolean isLogin = false;
		if(id.equals(password))
			isLogin = true;
		
		response.setContentType("text/html;charset=utf-8");
		
		Cookie cookie = null;
		
		if( isLogin && save_id != null) {
			cookie = new Cookie("save_id", id);
			// 클라이언트에게 전송하는 응답 흐름에 쿠키 객체를 추가
			// (클라이언트의 웹 브라우저에 쿠키가 저장)
			response.addCookie(cookie);
		} else if(isLogin && save_id == null) {
			cookie = new Cookie("save_id", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		PrintWriter out = response.getWriter();
		if(isLogin)
			out.println("<h3>로그인 성공</h3>");
		else
			out.println("<h3>로그인 실패</h3>");
	}

}
