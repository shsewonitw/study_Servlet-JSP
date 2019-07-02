package tje.servlet;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s11")
public class Servlet_11 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String strDan = request.getParameter("dan");
		
		if(strDan == null)
			strDan = "2";
		
		int nDan = Integer.parseInt(strDan);

		String msg = request.getParameter("msg");
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("<table border='1'>");

		buffer.append("<tr>");
		buffer.append("<th colspan='2'>");
		buffer.append(nDan + "단을 출력합니다.");
		buffer.append("</th>");
		buffer.append("</tr>");
		for (int j = 1; j < 10; j++) {
			buffer.append("<tr>");
			buffer.append("<td>");
			buffer.append(String.format("%d * %d", nDan, j));
			buffer.append("</td>");
			buffer.append("<td>");
			buffer.append(nDan * j);
			buffer.append("</td>");
			buffer.append("</tr>");
		}

		buffer.append("</table>");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(buffer.toString());
	}

}
