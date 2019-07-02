package tje.servlets.request_scope;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/request_ex_div")
public class RequestEXDivServlet extends HttpServlet {
 
	// 포워딩을 통해서 이동할 서블릿 클래스의 URL을 기술
	private static final String nextPage = "/request_ex_output";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num1 = (Integer)request.getAttribute("num1");
		int num2 = (Integer)request.getAttribute("num2");
		double nResult = (double)num1 / num2;
		String strResult = String.format("<h3>%d / %d = %.2f</h3>",num1,num2,nResult);
		
		request.setAttribute("output_div", strResult);
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
