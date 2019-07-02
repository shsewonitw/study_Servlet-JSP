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

@WebServlet("/session_04")
public class SessionServlet_04 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// 세션의 유효 시간을 제어하는 방법
		// 2. web.xml 파일을 통해 세션의 유효 시간을 지정하는 방법
		//   - session-config 태그를 정의한 후,
		//     내부에 session-timeout 태그를 작성하여 시간을 지정
		//   - session-timeout 태그의 값을 정수를 입력해야 하며,
		//     단위는 '분'
		//   - 모든 세션에 대한 지속 시간을 제어하는 경우에 사용
		
		// 세션의 최대 유효 시간(초)
		int maxInterval = session.getMaxInactiveInterval();
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();

		
		out.println("<h3>세션 최대 유효 시간: ");
		out.println(maxInterval+"초 </h3>");
	}

}
