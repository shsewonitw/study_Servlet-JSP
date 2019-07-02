package tje.jdbc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JDBCLoader extends HttpServlet {
	
	
       
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String JDBC_DRIVER_CLASS = config.getServletContext().getInitParameter("JDBC_DRIVER_CLASS_NAME");
		
		try {
			Class.forName(JDBC_DRIVER_CLASS);
			System.out.println("JDBC Driver 클래스 로딩 완료");
			JDBCInfo info = new JDBCInfo(
					config.getServletContext().getInitParameter("JDBC_URL"), 
					config.getServletContext().getInitParameter("JDBC_ID"), 
					config.getServletContext().getInitParameter("JDBC_PASSWORD"));
			
			config.getServletContext().setAttribute("jdbc_info", info);
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC CLASS 로딩 실패");
		}
	}
}
