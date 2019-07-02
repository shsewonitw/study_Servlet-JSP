package tje.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@WebFilter("/fs_03")
public class Filter_03 implements Filter {
	

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter_03의 init 메소드 실행");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// chain.doFilter(request, response)
		// 실행 코드의 앞은 서블릿 또는 JSP로 실제 요청객체가
		// 전달되기 전에 실행되는 영역
		// 인코딩 처리와 같은 작업을 실행할 수 있는 영역
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		// 요청에 대한 사전 작업 영역(request에 대한 작업)
		
		chain.doFilter(request, response);
		
		// 응답에 대한 사후 작업 영역(response에 대한 작업)
		
		// chain.doFilter(request, response)
		// 실행 코드의 뒷 부분은 서블릿 또는 JSP가 모든 실행을 종료한 이후에
		// 실행되는 영역
		// 쿠키를 응답에 추가하는 등과 같은 작업을 수행할 수 있음
		String name = request.getParameter("name");
		if( name != null ) {
			Cookie cookie = new Cookie("name", name);
			HttpServletResponse res = (HttpServletResponse)response;
			res.addCookie(cookie);
		}
	}
	
	
	public void destroy() {		
		System.out.println("Filter_03의 destroy 메소드 실행");		
	}

}
