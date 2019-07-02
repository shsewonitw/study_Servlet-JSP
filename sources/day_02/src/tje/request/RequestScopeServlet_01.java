package tje.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/r_01")
public class RequestScopeServlet_01 extends HttpServlet {

    
	// 웹 어플리케이션에서 사용되는 메모리 영역
	// 1. request 영역
	//	 - 하나의 요청이 종료되는 시점까지 값이 유지되는 영역
	// 2. session 영역
	//	 - 특정 클라이언트의 최초 접속 시점부터 
	//	     지정된 시간동안 값이 유지되는 영역 (기본값 30분)
	// 3. application 영역(Context 영역)
	//	 - 서버의 시작부터 종료 시점까지 값이 유지되는 영역
	
	// 각 영역의 특징
	// request 영역
	//  - 포워딩 되는 페이지(서블릿)에서도 값이 유효한 특징을 가짐
	//  - 리다이렉트시 request 영역은 새롭게 만들어 지기 때문에
	//    기존의 request 객체에는 접근할 수 없다. 
	
	// session 영역
	//  - 각각의 클라이언트(접속에 사용된 웹브라우저)마다 생성
	//  - 로그인 정보등과 같은 개인 정보를 저장하는 영역
	
	// application(Servlet Context) 영역
	//  - 모든 클라이언트들이 공유하게 되는 정보를 저장하는 영역
	//  - 사이트의 모든 접속자 수를 저장하는 경우에 사용
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 특정 영역에 값을 저장하는 방법 
		//  - 모든 영역은 setAttribute 메소드를 제공하며
		//    name과 value의 형태로 저장하게 됩니다.
		//  - name은 문자열타입, value는 Object 타입입니다.
		//  - Object 타입을 저장하기 때문에 클래스에 관계없이 
		//    어떠한 타입도 저장할 수 있다.
		
		// String s = new Object(); // (x)
		Object o = new String(); // (o)
		
		// request 객체에 값을 저장
		request.setAttribute("msg", "Hello");
		
		// 1. 특정 영역에 저장된 값을 추출하는 방법
		//  - 모든 영역은 getAttribute 메소드를 제공하며
		//    특정 name 으로 저장된 value 를 반환합니다.
		//  - (주의사항) 모든 값을 Object 타입으로 반환되기 때문에
		//    강제형변환을 통해서 값을 추출해야 합니다.
		//  - (주의사항) getAttribute 메소드의 매개변수로
		//    저장되지 않은 name 값이 사용되는 경우 null 값이 반환됩니다.
		String msg = (String)request.getAttribute("msg");
		Integer age = (Integer)request.getAttribute("age");
		
		System.out.println("msg = "+msg);
		System.out.println("age = "+age);
		
	}


}
