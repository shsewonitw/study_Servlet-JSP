package tje.servlet;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Servlet_Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		
		String result="로그인 성공";
		if( !id.equals(password) )
			result = "로그인 실패";
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(String.format("%s%s%s", "<h3>",result,"</h3>"));
		
		
	}

}
