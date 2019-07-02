package tje.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator_f")
public class CalculatorServletWithForwading extends HttpServlet {
	public static final String formPage = "/forms/calculatorForm.html";

	
	// 포워딩을 이용한 페이지 전환 방법
	// - 리다이렉트와 다르게 클라이언트에게 응답이 전달되지 않은 상태에서
	//   다른페이지 내용을 처리하여 클라이언트로 응답하는 방식
	// - 리다이렉트와 다르게 클라이언트의 URL이 변경되지 않습니다.
	// - (중요) 서블릿 컨테이너의 private 영역인 WEB-INF 내부의 
	//    요소에도 접근할 수 있습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// RequestDispatcher 클래스
		// - 포워딩을 하고자 하는 리소스에 접근할 수 있는 클래스
		// - HttpServletRequest 객체를 통해서 반환받을 수 있음
		// - getRequestDispatcher 메소드의 매갭견수로 지정된 경로의
		//   리소스에 접근할 수 있는 객체가 생성
		// - 포워딩 된 리소스의 경로는 / 로 시작되며
		//   / 는 WebContent를 의미합니다.
		//   (또는 특정 서블릿의 URL 정보가 될수도 있음)
		RequestDispatcher rd = request.getRequestDispatcher(formPage); 
		// 포워딩 실행
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
