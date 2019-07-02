package tje.request;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regist_request")
public class RequestScopeServlet_regist extends HttpServlet {

	public static final String formPage = "/WEB-INF/forms/registForm.html";
	 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher(formPage);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 회원가입 정보를 request 객체로부터 추철하여
		// 유효성 검증 결과를 RequestScopeServlet_regist_validation
		// 서블릿을 통해 응답하세요
		// ID의 값을 최소 5글자부터 10글자까지 
		// PW의 값을 최소 8글자부터 20글자까지
		// NAME의 값을 최소 3글자부터 5글자까지
		// AGE의 값을 반드시 숫자만 입력되었는지 확인
		// TEL 정보는 - 을 제거한 결과가 11글자 인지 확인 
		String id = request.getParameter("id").trim();
		String pw = request.getParameter("pw").trim();
		String name = request.getParameter("name").trim();
		try {
		Integer age = Integer.parseInt(request.getParameter("age").trim());
		request.setAttribute("ageCheck", true);
		} catch(Exception e) {
			String age = request.getParameter("age").trim();
			request.setAttribute("ageCheck", false);
		}
		String tel = request.getParameter("tel").trim();
		
		StringTokenizer st = new StringTokenizer(tel,"-");
		tel = "";
		while(st.hasMoreTokens()) {
			tel += st.nextToken();
		}
		
		if(id.length() >=5 && id.length() <=10)
			request.setAttribute("idCheck", true);
		else
			request.setAttribute("idCheck", false);
		
		if(pw.length() >=8 && pw.length() <=20)
			request.setAttribute("pwCheck", true);
		else
			request.setAttribute("pwCheck", false);
		
		if(name.length() >=5 && name.length() <=10)
			request.setAttribute("nameCheck", true);
		else
			request.setAttribute("nameCheck", false);
		
		if(tel.length() == 11)
			request.setAttribute("telCheck", true);
		else
			request.setAttribute("telCheck", false);
		
		RequestDispatcher rd = 
				//request.getRequestDispatcher("/regist_validation_request");
				request.getRequestDispatcher("/WEB-INF/result/registResult.jsp");
		rd.forward(request, response);
	}
}
