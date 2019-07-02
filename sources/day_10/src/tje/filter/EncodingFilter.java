package tje.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class EncodingFilter implements Filter {
	private String strEncoding;

	public void init(FilterConfig fConfig) throws ServletException {
		strEncoding = fConfig.getInitParameter("encoding");
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding(strEncoding);
		chain.doFilter(request, response);
		
		
	}

}
