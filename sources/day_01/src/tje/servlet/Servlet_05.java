package tje.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 서블릿 클래스의 초기화
// - @WebServlet 어노테이션을 활용하는 방법
// 1. @WebServlet 정의부에 initparams 속성으로 초기화 값을 지정
// 2. 서블릿 클래스의 init 메소드 내부에서 
//	  web.xml 파일에 저장된 초기화 정보를 로딩
@WebServlet(name ="Servlet_05",urlPatterns = {"/s05"},
	initParams = {
			@WebInitParam(name="USER_ID",value="root"),
			@WebInitParam(name="USER_PW",value="SystemManager304")})
public class Servlet_05 extends HttpServlet {
	private String user_id;
	private String user_pw;
	
	
	


	public void init(ServletConfig config) throws ServletException {
		user_id = config.getInitParameter("USER_ID");
		user_pw = config.getInitParameter("USER_PW");
		System.out.printf("(init) ID : %s, PW : %s \n",user_id,user_pw);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.printf("(doGet) ID : %s, PW : %s \n",user_id,user_pw);
	}

}
