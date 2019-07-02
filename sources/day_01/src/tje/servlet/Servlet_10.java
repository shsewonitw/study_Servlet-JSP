package tje.servlet;

import java.io.*;
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
@WebServlet("/s10")
public class Servlet_10 extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HttpServletRequest 클래스는 클라이언트의 요청 정보를 저장하고 있는 객체입니다.
		// - 클라이언트의 요청이 서버로 전달되면 서블릿 컨테이ㅓㄴ에 의해서
		// 	 HttpServletRequest 객체가 생성되며, 요청 메소드에 따라
		// 	 doGet, doPost 메소드로 전달됨
		
		// 요청객체에 데이터를 전달하는 방법
		// 1. GET 방식의 메소드 호출
		// - 요청 URL에 쿼리스트링을 추가하여 데이터를  전달
		// - URL?키1=값1
		// - URL?키1=값1&키2=값2&키N=값N
		// - URL을 직접 입력하는 방식, a 태그를 사용하여 전달할 수 있다
		
		// 2. POST 방식의 메소드 호출
		// - 데이터가 외부로 노출되지 않는 방식
		// - http 프로토콜의 헤더부분을 사용하여 데이터를 전달하는 방식
		// - HTML 의 form 태그를 사용하여 전달할 수 있음
		//	 (method='post'로 설정한 경우)
		
		
		// HttpServletRequest 클래스의 getParameter 메소드는
		// 클라이언트의 요청 정보를 추출할 수 있는 메소드로써, 
		// request.getparameter("요청매개변수의 키값") 의 형태로 사용
		
		String msg = request.getParameter("msg");
		
		
	}

}
