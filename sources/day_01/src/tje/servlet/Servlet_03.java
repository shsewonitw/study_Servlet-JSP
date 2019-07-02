package tje.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 클래스의 생명주기
// 1. 객체 생성 - 생성자 호출에 의해서 객체 생성
//	- 서블릿 컨테이너에 의해서 객체가 생성
//	- 특정 서블릿 클래스가 등록된 URL 요청이 최초로 전달될 때 단 한번만 생성 

// 2. 객체의 생성 후, 서블릿 클래스의 초기화를 위해서
//	  init 메소드가 자동 호출됨
//	  - 서블릿 클래스의 초기화를 위한 작업을 수행
//	  - 데이터베이스의 초기화, 데이터베이스로부터 로그기록을 검색하는 코드...

// 3. 클라이언트의 요청을 처리하기 위한 service 메소드 실행
//	  - 클라이언트의 요청 메소드에 따라서 doGet, doPost ... 호출하는 메소드
//	  - 클라이언트의 요청 메소드에 관계없이 처리하고자 하는 로직을 작성하는 경우 오버라이딩 할 수 있습니다.
//	  - super.service 를 호출하지 않는 경우 doGet, doPost와 같은 메소드가 실행되지 않습니다.

// 4. service 메소드에 의해서 호출되는
//	  doGet, doPost 메소드가 실행

// 5. 클라이언트의 새로운 요청이 전달되는 겨웅
//	    기존에 생성된 객체를 사용하여
//	    위의 3~4까지의 과정이 반복됨
@WebServlet("/s03")
public class Servlet_03 extends HttpServlet {       
    public Servlet_03() {
    	System.out.println("Servlet_03 객체 생성!");
    }
    
    

    public void init(ServletConfig config) throws ServletException {
    	System.out.println("Servlet_03.init(ServletConfig config)!");
    	super.init(config);
    }
    
    public void init() throws ServletException {
    	System.out.println("Servlet_03.init()");
    	super.init();
    }
    
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Servlet_03.service");
    	super.service(req, resp);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("doGet");

	}

}
