package tje.el;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import tje.model.Member;

@WebServlet("/el_05")
public class ELServlet_05 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member model = new Member();
		model.id = "111";
		model.password = "222";
		model.name = "333";
		// request 영역에 저장
		request.setAttribute("member", model);
		
		String forwardPage =  "/el_05.jsp";
		// application 영역에 저장
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

}
