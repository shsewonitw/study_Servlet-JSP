package tje.servlets.session_scope;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_06")
public class SessionServlet_06 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 (Session)
		// 웹 어플리케이션에서 각각의 클라이언트마다 할당되는 메모리 영역
		// 특정 클라이언트의 웹 서버 접속 시점부터 일정 시간동안 값이 유지되는 영역
		// - 로그인 정보등과 같은 클라이언트의 고유정보를 저장하는 영역
		
		// 세션 객체의 참조 방법
		// request 객체의 getSession 메소드를 통해서 참조값을 반환
		HttpSession session = request.getSession();
		
		Integer count1 = (Integer)session.getAttribute("count1");
		
		if( count1 == null) {
			count1 = 1;
		} else {
			count1++;
		}
		session.setAttribute("count1",count1);
		
		
		Integer count2 = (Integer)session.getAttribute("count2");
		
		if( count2 == null) {
			count2 = 1;
		} else {
			count2++;
		}
		session.setAttribute("count2",count2);
		
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		out.println("<h3>COUNT1: "+count1+"</h3>");
		out.println("<h3>COUNT2: "+count2+"</h3>");
		
		// 세선에 저장된 특정 속성을 제거하는 메소드
		session.removeAttribute("count2");
	}

}
