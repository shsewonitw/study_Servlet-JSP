package tje.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cal")
public class servlet_cal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		float n1 = Float.parseFloat(request.getParameter("n1").trim());
		float n2 = Float.parseFloat(request.getParameter("n2").trim());
		String oper = request.getParameter("op").trim();


		
		response.setContentType("text/html;charset=utf-8");

		java.io.PrintWriter out = response.getWriter();
		
		float result = 0;
		
		if(oper.equals("+")) {
			result = n1+n2;
		} else if(oper.equals("-")) {
			result = n1-n2;
		} else if(oper.equals("*")) {
			result = n1*n2;
		} else if(oper.equals("/")) {
			result = n1/n2;
		} else if(oper.equals("%")) {
			result = n1%n2;
		}
		
		out.println("결과는 "+result+" 입니다");
	}

}