package tje.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class gugudan extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		java.io.PrintWriter out = response.getWriter();

		StringBuilder buffer = new StringBuilder();

//		buffer.append("<table border='1'>");
//		for(int i = 2 ; i < 10 ; i++) {
//			buffer.append("<tr>");	
//			buffer.append("<th colspan='2'>");
//			buffer.append(i+"단을 출력합니다.");
//			buffer.append("</th>");
//			buffer.append("</tr>");
//			for(int j = 1 ; j < 10 ; j++) {
//				buffer.append("<tr>");	
//				buffer.append("<td>");
//				buffer.append(String.format("%d * %d",i,j));
//				buffer.append("</td>");
//				buffer.append("<td>");
//				buffer.append(i*j);
//				buffer.append("</td>");
//				buffer.append("</tr>");
//			}
//		}
//		buffer.append("</table>");
//		out.println(buffer.toString());

//		buffer.append("<table border='1'>");
//		for (int i = 0; i < 10; i++) {
//			buffer.append("<tr>");
//			for (int j = 2; j < 10; j++) {
//				if(i == 0) {
//					buffer.append("<th colspan='2'>");
//					buffer.append(j+"단을 출력합니다.");
//					buffer.append("</th>");	
//				} else {
//					buffer.append("<td>");
//					buffer.append(String.format("%d * %d",j,i));
//					buffer.append("</td>");
//					buffer.append("<td>");
//					buffer.append(j*i);
//					buffer.append("</td>");
//				}
//				
//			}
//			buffer.append("</tr>");
//		}
//		buffer.append("</table>");

		String s = request.getParameter("dan");

		try {
		int dan = Integer.parseInt(s);
		buffer.append("<table border='1'>");	
		for(int i = 1 ; i < 10 ; i++) {
			buffer.append("<tr>");	
			buffer.append("<td>");
			buffer.append(String.format("%d * %d",dan,i));
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(dan*i);
			buffer.append("</td>");
			buffer.append("</tr>");
		}
		buffer.append("</table>");	
		out.println(buffer);
		}catch(NumberFormatException e) {
			
		}

	}

}
