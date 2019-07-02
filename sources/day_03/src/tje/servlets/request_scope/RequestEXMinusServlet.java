package tje.servlets.request_scope;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request_ex_minus")
public class RequestEXMinusServlet extends HttpServlet {
 
	// 포워딩을 통해서 이동할 서블릿 클래스의 URL을 기술
	private static final String nextPage = "/request_ex_mul";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num1 = (Integer)request.getAttribute("num1");
		int num2 = (Integer)request.getAttribute("num2");
		int nResult = num1 - num2;
		String strResult = String.format("<h3>%d - %d = %d</h3>",num1,num2,nResult);
		
		request.setAttribute("output_minus", strResult);
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
