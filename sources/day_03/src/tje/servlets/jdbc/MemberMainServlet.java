package tje.servlets.jdbc;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member_main")
public class MemberMainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		Integer accessUser;
		
		synchronized(application) {
			accessUser = (Integer)application.getAttribute("count");
		}
			if(accessUser == null)
				accessUser=0;
		application.setAttribute("count", accessUser);
		
		String id = (String) session.getAttribute("login_id");
		String name = (String) session.getAttribute("login_name");
		
		
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		
		// 아래의 기능이 동작할 수 있도록 서블릿과 HTML 파일을 완성하세요
		// (회원목록보기,로그아웃은 로그인한 클라이언트만 가능)
		// (회원가입,로그인은 로그인이 되지 않은 클라이언트만 가능) 
		// (로그인된 클라이언트는 메인화면에서 현재 접속된 클라이언트의 인원수를 확인)
		out.println("<h2>메인 화면</h2>");
		out.println("<h2>현재 접속된 클라이언트 수: "+accessUser+"</h2>");
		out.println("<ul>");
		
		if(id != null) {
			out.println("<li><a href=\"./member_select\">회원목록 보기</a></li>");
			out.println("<li><a href=\"./member_logout\">로그아웃</a></li>");
		} else {
			out.println("<li><a href=\"./member_login\">로그인</a></li>");
			out.println("<li><a href=\"./member_regist\">회원가입</a></li>");
		}
		
		out.println("</ul>");
		
		out.flush();

	}

}