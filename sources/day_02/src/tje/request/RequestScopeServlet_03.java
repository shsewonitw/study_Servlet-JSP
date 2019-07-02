package tje.request;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/r_03")
public class RequestScopeServlet_03 extends HttpServlet {

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// request 객체에 값을 저장
		request.setAttribute("msg", "Hello 03~!");
		
		// r_04 를 URL로 사용하는 서블릿으로 이동하기 위한
		// RequestDispatcher 를 생성한 후 포워딩
		// (현재 request 객체가 소멸되지 않고 유지됨)
		RequestDispatcher rd = request.getRequestDispatcher("/r_04");
		rd.forward(request, response);
		
	}


}
