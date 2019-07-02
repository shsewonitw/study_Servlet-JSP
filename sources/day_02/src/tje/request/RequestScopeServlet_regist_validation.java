package tje.request;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regist_validation_request")
public class RequestScopeServlet_regist_validation extends HttpServlet {

	// ID : 입력된 값 ( 유효성 체크 결과 )
	// ...
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		java.io.PrintWriter out = response.getWriter();
		
		StringBuilder buffer = new StringBuilder();
		buffer.append("<br/>ID: "+(String)request.getParameter("id")+"("+(boolean)request.getAttribute("idCheck")+")");
		buffer.append("<br/>PW: "+(String)request.getParameter("pw")+"("+(boolean)request.getAttribute("pwCheck")+")");
		buffer.append("<br/>NAME: "+(String)request.getParameter("name")+"("+(boolean)request.getAttribute("nameCheck")+")");
		buffer.append("<br/>AGE: "+(String)request.getParameter("age")+"("+(boolean)request.getAttribute("ageCheck")+")");
		buffer.append("<br/>TEL: "+(String)request.getParameter("tel")+"("+(boolean)request.getAttribute("telCheck")+")");
		
		out.println(buffer);
		
		
	}
	
}
