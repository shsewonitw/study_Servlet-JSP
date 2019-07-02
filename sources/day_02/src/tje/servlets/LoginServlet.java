package tje.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	public static final String formPage = "/forms/loginForm.html";
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(formPage); 
		rd.forward(request,response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		if(id.equals(password)) {
			out.println("<h2>로그인 성공</h2>");
		} else {
			out.println("<h2>로그인 실패</h2>");
		}
	}

}
