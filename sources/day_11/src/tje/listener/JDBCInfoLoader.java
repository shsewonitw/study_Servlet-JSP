package tje.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import tje.jdbc.*;

@WebListener
public class JDBCInfoLoader implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		// 웹 서버의 구동 시 , 데이터베이스 접속에 필요한 정보를 
		// 자바빈 객ㅣㄹ리갱ㄴ
		
		JDBCInfo info = new JDBCInfo(
				context.getInitParameter("JDBC_URL"), 
				context.getInitParameter("JDBC_USER"), 
				context.getInitParameter("JDBC_PASSWORD"));
		JDBCConnection.setInfo(info);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
