package tje.servlet.cookie;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class CalculatorWithCookie extends HttpServlet {
	
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Cookie [] cookies = request.getCookies();
		String n1 = null;
		String n2 = null;
		String op = null;
		
		for(int i = 0 ; cookies !=null && i < cookies.length ; i++) {
			if(cookies[i].getName().equals("n1"))
				n1 = cookies[i].getValue();
			else if(cookies[i].getName().equals("n2"))
				n2 = cookies[i].getValue();
			else if(cookies[i].getName().equals("op"))
				op = cookies[i].getValue();
		}
		
		
		
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("<form action=\"./calculator\" method=\"post\">");
		
		if(n1 == null)
			buffer.append("<label>n1: <input type=\"text\" name=\"n1\" required></label><br/>");
		else
			buffer.append("<label>n1: <input type=\"text\" name=\"n1\" value=\""+n1+"\" required></label><br/>");
		
		buffer.append("<label>op: ");
		buffer.append("<select name=\"op\">");
		if(op.equals("+"))
			buffer.append("<option value=\"+\" selected>+</option>");
		else
			buffer.append("<option value=\"+\">+</option>");
		if(op.equals("-"))
			buffer.append("<option value=\"-\" selected>-</option>");
		else
			buffer.append("<option value=\"-\">-</option>");
		if(op.equals("*"))
			buffer.append("<option value=\"*\" selected>*</option>");
		else
			buffer.append("<option value=\"*\">*</option>");
		if(op.equals("/"))
			buffer.append("<option value=\"/\" selected>/</option>");
		else
			buffer.append("<option value=\"/\">/</option>");
		if(op.equals("%"))
			buffer.append("<option value=\"%\" selected>%</option>");
		else
			buffer.append("<option value=\"%\">%</option>");
		buffer.append("</select><br/>");
		
		if(n2 == null)
			buffer.append("<label>n2: <input type=\"text\" name=\"n2\" required></label><br/>");
		else
			buffer.append("<label>n2: <input type=\"text\" name=\"n2\" value=\""+n2+"\" required></label><br/>");
		buffer.append("</label><br/>");
		buffer.append("<label><input type=\"submit\" value=\"연산\"></label>");
		buffer.append("</form>");
		
		out.println(buffer);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int n1;
		int n2;
		int result=0;
		Cookie cookie_n1 = null;
		Cookie cookie_n2 = null;
		try {
			n1 = Integer.parseInt(request.getParameter("n1").trim());
			n2 = Integer.parseInt(request.getParameter("n2").trim());
			cookie_n1 = new Cookie("n1",String.valueOf(n1)); 
			cookie_n2 = new Cookie("n2",String.valueOf(n2));
			
		}catch(Exception e) {
			out.println("<h1>입력값을 확인하세요</h1>");
			return;
		}
		String op = request.getParameter("op");
		Cookie cookie_op = new Cookie("op",op);
		
		response.addCookie(cookie_n1);
		response.addCookie(cookie_n2);
		response.addCookie(cookie_op);
		
		if(op.equals("+"))
			result = n1+n2;
		else if(op.equals("-"))
			result = n1-n2;
		else if(op.equals("%"))
			try {
				result = n1%n2;
			}catch(Exception e) {
				out.println("<h1>0으로 못나눠요</h1>");
				return;
			}
		else if(op.equals("*"))
			result = n1*n2;
		else if(op.equals("/")) {
			try {
				result = n1/n2;
			}catch(Exception e) {
				out.println("<h1>0으로 못나눠요</h1>");
				return;
			}
		}
		out.println("<h1>연산결과: "+result+"</h1>");
	}

}
