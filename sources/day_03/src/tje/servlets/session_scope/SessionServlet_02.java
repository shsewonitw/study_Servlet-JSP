package tje.servlets.session_scope;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session_02")
public class SessionServlet_02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 (Session)
		// 웹 어플리케이션에서 각각의 클라이언트마다 할당되는 메모리 영역
		// 특정 클라이언트의 웹 서버 접속 시점부터 일정 시간동안 값이 유지되는 영역
		// - 로그인 정보등과 같은 클라이언트의 고유정보를 저장하는 영역
		
		// 세션 객체의 참조 방법
		// request 객체의 getSession 메소드를 통해서 참조값을 반환
		HttpSession session = request.getSession();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		// 세션의 생성 시간
		java.util.Date ct = new Date(session.getCreationTime());
		// 세션의 마지막 접속 시간
		java.util.Date lat = new Date(session.getLastAccessedTime());
		// 세션의 최대 유효시간
		int maxInterval = session.getMaxInactiveInterval();
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		out.println("<h3>세션 생성 시간: ");
		out.println(sdf.format(ct)+"</h3>");
		
		out.println("<h3>세션 마지막 접속 시간: ");
		out.println(sdf.format(lat)+"</h3>");
		
		out.println("<h3>세션 최대 유효 시간: ");
		out.println(maxInterval+"초 </h3>");
	}

}
