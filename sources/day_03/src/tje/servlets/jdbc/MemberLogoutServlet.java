package tje.servlets.jdbc;

import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member_logout")
public class MemberLogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.invalidate();
		ServletContext application = request.getServletContext();
		synchronized (application) {
			Integer accessUser = (Integer)application.getAttribute("count");
			try {
			accessUser--;
			} catch(Exception e){
				;
			}
			application.setAttribute("count", accessUser);	
		}
		response.sendRedirect(request.getContextPath()+"/member_main");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
