package tje.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 웹 어플리케이션의 동작 방식
// 1. 클라이언트의 URL 요청
// (form 태그의 정보가 전달될 수 있음)
// 2. 서블릿 컨테이너의 서블릿 객체 호출
// (해당 url을 처리할 서블릿 객체 실행)
// 3. 서블릿 객체가 반환하는 값을 클라이언트에게 응답

// 클라이언트의 요청 정보는 HttpServletRequest 객체로 전달
// 클라이언트에게 응답하기 위한 HttpServletResponse 객체가 전달
@WebServlet("/s09")
public class Servlet_09 extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답 스트림에 출력할 문자열 데이터가 한글이 포함되는 경우
		// 반드시 실행해야 하는 코드
		// 주의사항!
		// getWriter 메소드를 사용하기 전에 실행해야함.
		response.setContentType("text/html;charset=utf-8");
		
		java.io.PrintWriter out = response.getWriter();
		
//		java.util.Date d = new java.util.Date();
//		String now = d.toString();
		
		java.util.Date now = java.util.Calendar.getInstance().getTime();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("현재 시간은 HH시 mm분 ss초 입니다."); 
		out.println("<h1>");
		out.println(sdf.format(now));
		out.println("</h1>");
	}

}
