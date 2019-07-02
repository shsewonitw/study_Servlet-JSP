package tje.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 서블릿 클래스의 객체를 웹 서버의 구동 시
// 자동으로 생성하는 방법
// web.xml 파일의 servlet 태그 내부에 load-on-startup 태그를 정의
// - load-on-startup 태그는 정수의 값을 입력해야하며, 0 보다 큰 값을 입력
// - load-on-startup 태그에 정의된 정수의 값이 작을수록 먼저 생성됩니다.
public class Servlet_06 extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet_06.init 메소드 실행");
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_06.doGet 메소드 실행");		
	}
}
